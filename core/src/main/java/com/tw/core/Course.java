package com.tw.core;

import com.tw.core.dao.CourseDAO;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by chenbojian on 15-6-26.
 */

@Entity
@Table(name = "COURSE")
public class Course {
    private long id;
    private long coachId;
    private Customer customer;
    private String courseName;
    private Set<CourseDate> courseDates;

    @Id
    @Column(name = "ID")
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "COACH_ID")
    public long getCoachId() {
        return coachId;
    }

    public void setCoachId(long coachId) {
        this.coachId = coachId;
    }

    @Column(name = "COURSE_NAME")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    @OneToMany//unidirectional
    @JoinColumn(name = "COURSE_ID",referencedColumnName = "ID")
    public Set<CourseDate> getCourseDates() {
        return courseDates;
    }

    public void setCourseDates(Set<CourseDate> courseDates) {
        this.courseDates = courseDates;
    }

    @ManyToMany
    @JoinTable(
            name = "CUSTOMER_SELECT_COURSE",
            joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CUSTOMER_ID")
    )
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
