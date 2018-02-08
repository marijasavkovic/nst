package com.master.nst.model.employee;

import com.master.nst.domain.EmployeeTitle;
import com.master.nst.domain.EmployeeVocation;

import java.io.Serializable;

public class Employee implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String personalIdentityNumber;
    private String dateOfBirth;
    private String dateOfEmployment;
    private String address;
    private EmployeeTitle title;
    private EmployeeVocation vocation;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getPersonalIdentityNumber() {
        return personalIdentityNumber;
    }

    public void setPersonalIdentityNumber(final String personalIdentityNumber) {
        this.personalIdentityNumber = personalIdentityNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(final String dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public EmployeeTitle getTitle() {
        return title;
    }

    public void setTitle(final EmployeeTitle title) {
        this.title = title;
    }

    public EmployeeVocation getVocation() {
        return vocation;
    }

    public void setVocation(final EmployeeVocation vocation) {
        this.vocation = vocation;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getSimpleName())
            .append("id")
            .append(id)
            .append("name")
            .append(name)
            .append("surname")
            .append(surname).toString();
    }
}
