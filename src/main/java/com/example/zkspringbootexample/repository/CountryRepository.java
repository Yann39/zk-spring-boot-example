package com.example.zkspringbootexample.repository;

import com.example.zkspringbootexample.entities.Country;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Yann39
 * @since nov 2018
 */
public interface CountryRepository extends CrudRepository<Country, Long> {

}