package com.example.zkspringbootexample.service;

import com.example.zkspringbootexample.entities.Instructor;
import com.example.zkspringbootexample.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yann39
 * @since nov 2018
 */
@Transactional
@Component("instructorMgr")
public class InstructorManager {

    @Autowired
    private InstructorRepository instructorRepository;

    /**
     * Get all instructors from the database
     *
     * @return A list of Instructor elements representing the instructors
     */
    public List<Instructor> getAll() {
        return (List<Instructor>) instructorRepository.findAll();
    }

    /**
     * Get all instructors from the database containing the specified string
     * Search is done on first name, last name and e-mail
     *
     * @param filter The filter to apply
     * @return A list of Instructor elements representing the filtered instructors
     */
    public List<Instructor> getFiltered(String filter) {
        return instructorRepository.getInstructorByFirstNameContainingOrLastNameContainingOrEmailContaining(filter, filter, filter);
    }

    /**
     * Save or update the specified instructor data
     *
     * @param instructor The Instructor object to save or update
     */
    public void saveOrUpdate(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    /**
     * Remove the specified instructor from the database
     *
     * @param instructor The instructor to delete
     */
    public void delete(Instructor instructor) {
        instructorRepository.delete(instructor);
    }

    /**
     * Check if the specified instructor is used in a course
     *
     * @param instructorId The instructor id
     * @return True is the specified instructor is used, false if not
     */
    public boolean isUsed(long instructorId) {
        return instructorRepository.isUsed(instructorId);
    }

}