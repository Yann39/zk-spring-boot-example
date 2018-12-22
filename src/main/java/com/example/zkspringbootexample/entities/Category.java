package com.example.zkspringbootexample.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Yann39
 * @since nov 2018
 */
@Entity
@Table(name = "category")
public class Category {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
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

    public Set<CourseCategory> getCoursesCategories() {
        return coursesCategories;
    }

    public void setCoursesCategories(Set<CourseCategory> coursesCategories) {
        this.coursesCategories = coursesCategories;
    }
    //endregion

}