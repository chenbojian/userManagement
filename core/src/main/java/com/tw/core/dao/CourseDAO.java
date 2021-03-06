package com.tw.core.dao;

import com.tw.core.Coach;
import com.tw.core.Course;
import com.tw.core.CourseDate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by chenbojian on 15-6-26.
 */
@Repository
@Transactional
public class CourseDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public CourseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Course> listCourse() {
        return sessionFactory.getCurrentSession().createQuery("from Course")
                .list();
    }

    public boolean addCourse(Course course) {
        Coach coach = (Coach) sessionFactory.getCurrentSession().get(Coach.class, course.getCoach().getId());
        if (coach != null) {
            course.setCoach(coach);
            sessionFactory.getCurrentSession().save(course);
            return true;
        }
        return false;
    }

    public void deleteCourse(long id) {
        Course course = findCourseById(id);
        if (course != null) {
            sessionFactory.getCurrentSession().delete(course);
        }
    }

    public Course findCourseById(long id) {
        return (Course) sessionFactory.getCurrentSession().get(Course.class, id);
    }

    public void updateCourse(Course course) {
        sessionFactory.getCurrentSession().update(course);
    }

    public List<CourseDate> findDateBetween(Date start, Date end) {
        return sessionFactory.getCurrentSession()
                .createQuery("From CourseDate as c where c.date between :startDate and :endDate")
                .setTimestamp("startDate", start)
                .setTimestamp("endDate", end)
                .list();
    }
}