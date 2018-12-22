package com.example.zkspringbootexample.service;

import com.example.zkspringbootexample.entities.Country;
import com.example.zkspringbootexample.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yann39
 * @since nov 2018
 */
@Transactional
@Component("countryMgr")
public class CountryManager {

    @Autowired
    private CountryRepository countryRepository;

    /**
     * Get all countries from the database
     *
     * @return A list of Country elements representing the countries
     */
    public List<Country> getAll() {
        return (List<Country>) countryRepository.findAll();
    }

}