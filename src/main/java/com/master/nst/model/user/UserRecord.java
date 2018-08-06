package com.master.nst.model.user;

import com.master.nst.domain.UserRole;

import java.io.Serializable;

public class UserRecord implements Serializable {

    private Long id;
    private String username;
    private String email;
    private UserRole role;
    private String employeeName;
    private String employeeSurname;

    public UserRecord() {
    }

    public UserRecord(
        final Long id,
        final String username,
        final String email,
        final UserRole role,
        final String employeeName,
        final String employeeSurname)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(final UserRole role) {
        this.role = role;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(final String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getSimpleName())
            .append("id")
            .append(id)
            .append("username")
            .append(username)
            .append("email")
            .append(email)
            .toString();
    }
}
