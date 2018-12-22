package com.example.zkspringbootexample.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Yann39
 * @since nov 2018
 */
@Entity
@Table(name = "course")
public class Course {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String location;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Language language;

    @ManyToOne(fetch = FetchType.EAGER)
    private Instructor instructor;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseCategory> coursesCategories;
    //endregion

    //region Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<CourseCategory> getCoursesCategories() {
        return coursesCategories;
    }

    public void setCoursesCategories(Set<CourseCategory> coursesCategories) {
        this.coursesCategories = coursesCategories;
    }
    //endregion

}