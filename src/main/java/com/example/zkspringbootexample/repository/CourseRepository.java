package com.example.zkspringbootexample.repository;

import com.example.zkspringbootexample.entities.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Yann39
 * @since nov 2018
 */
public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> getCourseByTitleContainingOrDescriptionContaining(String titleFilter, String descriptionFilter);

}