package com.example.zkspringbootexample.service;

import com.example.zkspringbootexample.entities.Category;
import com.example.zkspringbootexample.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yann39
 * @since nov 2018
 */
@Transactional
@Component("categoryMgr")
public class CategoryManager {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Get all categories from the database
     *
     * @return A list of Category elements representing the categories
     */
    public List<Category> getAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    /**
     * Get all categories from the database containing the specified string
     * Search is done on title
     *
     * @param filter The filter to apply
     * @return A list of Category elements representing the filtered categories
     */
    public List<Category> getFiltered(String filter) {
        return categoryRepository.getCategoryByTitleLike("%" + filter + "%");
    }

    /**
     * Save or update the specified category data
     *
     * @param category The Category object to save or update
     */
    public void saveOrUpdate(Category category) {
        categoryRepository.save(category);
    }

    /**
     * Remove the specified category from the database
     *
     * @param category The category to delete
     */
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    /**
     * Check if the specified category is used in a course
     *
     * @param categoryId The category id
     * @return True is the specified category is used, false if not
     */
    public boolean isUsed(long categoryId) {
        return categoryRepository.isUsed(categoryId);
    }

}