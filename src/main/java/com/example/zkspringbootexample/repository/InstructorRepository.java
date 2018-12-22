package com.example.zkspringbootexample.repository;

import com.example.zkspringbootexample.entities.Instructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Yann39
 * @since nov 2018
 */
public interface InstructorRepository extends CrudRepository<Instructor, Long> {

    @Query("SELECT 1 FROM Course c where c.instructor.id = ?1")
    boolean isUsed(long instructorId);

    List<Instructor> getInstructorByFirstNameContainingOrLastNameContainingOrEmailContaining(String firstNameFilter, String lastNameFilter, String emailFilter);

}