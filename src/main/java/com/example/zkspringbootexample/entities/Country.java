package com.example.zkspringbootexample.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Yann39
 * @since nov 2018
 */
@Entity
@Table(name = "country")
public class Country {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String codeIso;

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

    public String getCodeIso() {
        return codeIso;
    }

    public void setCodeIso(String codeIso) {
        this.codeIso = codeIso;
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
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) &&
                Objects.equals(codeIso, country.codeIso) &&
                Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codeIso, name);
    }
    //endregion

}