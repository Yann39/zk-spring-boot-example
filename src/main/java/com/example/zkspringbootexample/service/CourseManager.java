package com.example.zkspringbootexample.service;

import com.example.zkspringbootexample.entities.Course;
import com.example.zkspringbootexample.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yann39
 * @since nov 2018
 */
@Transactional
@Component("courseMgr")
public class CourseManager {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Get all courses from the database
     *
     * @return A list of Course elements representing the courses
     */
    public List<Course> getAll() {
        return (List<Course>) courseRepository.findAll();
    }

    /**
     * Get all courses from the database containing the specified string
     * Search is done on title and description
     *
     * @param filter The filter to apply
     * @return A list of Course elements representing the filtered courses
     */
    public List<Course> getFiltered(String filter) {
        return courseRepository.getCourseByTitleContainingOrDescriptionContaining(filter, filter);
    }

    /**
     * Save or update the specified course data
     *
     * @param course The Course object to save or update
     */
    public void saveOrUpdate(Course course) {
        courseRepository.save(course);
    }

    /**
     * Remove the specified course from the database
     *
     * @param course The course to delete
     */
    public void delete(Course course) {
        courseRepository.delete(course);
    }

}