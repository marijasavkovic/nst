package com.master.nst.model.employee;

import com.master.nst.domain.EmployeeTitle;
import com.master.nst.domain.EmployeeVocation;
import com.master.nst.sheard.errors.ErrorMessages;
import com.master.nst.sheard.validation.ValidationGroups;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeeCmd implements Serializable{

    @NotNull(message = ErrorMessages.ID_NULL, groups = ValidationGroups.Edit.class)
    private Long id;

    @NotEmpty(message = ErrorMessages.NAME_EMPTY)
    private String name;

    @NotEmpty(message = ErrorMessages.SURNAME_EMPTY)
    private String surname;

    @Pattern(regexp = "^(\\d{13})?$", message = ErrorMessages.PIN_13_NUM, groups = ValidationGroups.Add.class)
    private String personalIdentificationNumber;

    @NotNull(message = ErrorMessages.DATE_OF_BIRTH_NULL)
    private Date dateOfBirth;

    @NotNull(message = ErrorMessages.DATE_OF_EMPLOYMENT_NULL)
    private Date dateOfEmployment;

    @NotEmpty(message = ErrorMessages.ADDRESS_EMPTY)
    private String address;

    @NotNull(message = ErrorMessages.TITLE_NULL)
    private EmployeeTitle title;

    @NotNull(message = ErrorMessages.VOCATION_NULL)
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

    public String getPersonalIdentificationNumber() {
        return personalIdentificationNumber;
    }

    public void setPersonalIdentificationNumber(final String personalIdentificationNumber) {
        this.personalIdentificationNumber = personalIdentificationNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(final Date dateOfEmployment) {
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
