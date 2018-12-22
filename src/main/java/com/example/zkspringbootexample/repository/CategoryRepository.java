package com.example.zkspringbootexample.repository;

import com.example.zkspringbootexample.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Yann39
 * @since nov 2018
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT 1 FROM CourseCategory cc where cc.category.id = ?1")
    boolean isUsed(long categoryId);

    List<Category> getCategoryByTitleLike(String titleFilter);

}