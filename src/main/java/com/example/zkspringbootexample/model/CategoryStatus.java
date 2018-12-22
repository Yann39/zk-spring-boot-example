package com.example.zkspringbootexample.model;

import com.example.zkspringbootexample.entities.Category;

/**
 * @author Yann39
 * @since nov 2018
 */
public class CategoryStatus {

    public CategoryStatus(Category category, boolean displayEdit) {
        this.category = category;
        this.displayEdit = displayEdit;
    }

    //region Properties
    private Category category;

    private boolean displayEdit;
    //endregion

    //region Getters and setters
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isDisplayEdit() {
        return displayEdit;
    }

    public void setDisplayEdit(boolean displayEdit) {
        this.displayEdit = displayEdit;
    }
    //endregion

}