package com.example.zkspringbootexample.repository;

import com.example.zkspringbootexample.entities.Language;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Yann39
 * @since nov 2018
 */
public interface LanguageRepository extends CrudRepository<Language, Long> {

}