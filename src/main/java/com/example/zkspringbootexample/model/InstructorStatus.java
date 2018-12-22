package com.example.zkspringbootexample.model;

import com.example.zkspringbootexample.entities.Instructor;

/**
 * @author Yann39
 * @since nov 2018
 */
public class InstructorStatus {

    public InstructorStatus(Instructor instructor, boolean displayEdit) {
        this.instructor = instructor;
        this.displayEdit = displayEdit;
    }

    //region Properties
    private Instructor instructor;

    private boolean displayEdit;
    //endregion

    //region Getters and setters
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public boolean isDisplayEdit() {
        return displayEdit;
    }

    public void setDisplayEdit(boolean displayEdit) {
        this.displayEdit = displayEdit;
    }
    //endregion

}