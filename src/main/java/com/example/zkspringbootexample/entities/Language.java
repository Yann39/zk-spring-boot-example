package com.example.zkspringbootexample.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Yann39
 * @since nov 2018
 */
@Entity
@Table(name = "language")
public class Language {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private String name;
    //endregion

    //region Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    //region Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Language)) return false;
        Language language = (Language) o;
        return Objects.equals(id, language.id) &&
                Objects.equals(code, language.code) &&
                Objects.equals(name, language.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name);
    }
    //endregion
}