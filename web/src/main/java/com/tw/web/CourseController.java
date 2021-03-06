package com.tw.web;

import com.tw.core.Course;
import com.tw.core.CourseDate;
import com.tw.core.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenbojian on 15-6-26.
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/")
    public ModelAndView listCourse() {
        ModelAndView modelAndView = new ModelAndView("courseList");
        List<Course> courses = courseService.listCourse();
        modelAndView.addObject("courses", courses);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addCoursePage(CourseBean courseBean) {
        return new ModelAndView("addCourse");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addCourse(@ModelAttribute CourseBean courseBean) {
        courseService.addCourse(courseBean.toCourse());
        return new ModelAndView("redirect:/course/");
    }

    @RequestMapping(value = "/{id}/delete")
    public ModelAndView deleteCourse(@PathVariable("id") long id) {
        courseService.deleteCourse(id);
        return new ModelAndView("redirect:/course/");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editCoursePage(@PathVariable("id") long id) {
        Course course = courseService.findCourseById(id);
        ModelAndView modelAndView = new ModelAndView("courseEdit");
        modelAndView.addObject("courseBean", new CourseBean(course));
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editCourse(@PathVariable("id") long id, @ModelAttribute CourseBean courseBean) {
        courseService.updateCourse(courseBean.toCourse());
        return new ModelAndView("redirect:/course/");
    }

    @RequestMapping(value = "calendar")
    public ModelAndView showCalendar() {
        return new ModelAndView("calendar");
    }

    @RequestMapping(value = "calendar/feed")
    @ResponseBody
    public List<Event> getCourse(@RequestParam Date start,
                            @RequestParam Date end) {

        List<Event> events = new ArrayList<Event>();
        List<CourseDate> courseDates = courseService.findCourseBetween(start,end);
        for (CourseDate courseDate : courseDates){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String event_start = sdf.format(courseDate.getDate());
            String event_title = courseDate.getCourse().getCourseName();
            events.add(new Event(event_title,event_start));
        }
        return events;
    }

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}

class Event{
    private String title;
    private String start;

    public Event(String title, String start) {
        this.title = title;
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}