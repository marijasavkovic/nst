package com.master.nst.model.user;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.domain.UserRole;

import java.io.Serializable;

public class User implements Serializable{

    private Long id;
    private String username;
    private String password;
    private String email;
    private UserRole role;
    private EmployeeEntity employee;

    public User() {
    }

    public User(
        final String username,
        final String password,
        final String email,
        final UserRole role,
        final EmployeeEntity employee)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.employee = employee;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
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

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(final EmployeeEntity employee) {
        this.employee = employee;
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
