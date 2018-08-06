package com.master.nst.model.user;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.domain.UserRole;
import com.master.nst.sheard.errors.ErrorMessages;
import com.master.nst.sheard.validation.ValidationGroups;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserCmd {

    @NotNull(message = ErrorMessages.ID_NULL, groups = ValidationGroups.Edit.class)
    private Long id;
    @NotEmpty(message = ErrorMessages.USERNAME_EMPTY, groups = ValidationGroups.Add.class)
    private String username;
    @NotEmpty(message = ErrorMessages.PASSWORD_EMPTY,  groups = ValidationGroups.Add.class)
    private String password;
    @NotEmpty(message = ErrorMessages.EMAIL_EMPTY,  groups = ValidationGroups.Add.class)
    private String email;
    @NotNull(message = ErrorMessages.USER_ROLE_NULL)
    private UserRole role;
    @NotNull(message = ErrorMessages.EMPLOYEE_NULL)
    private Long employeeId;

    public UserCmd() {
    }

    public UserCmd(
        final String username,
        final String password,
        final String email,
        final UserRole role,
        final Long employeeId)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.employeeId = employeeId;
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

    public Long getEmployee() {
        return employeeId;
    }

    public void setEmployee(final Long employeeId) {
        this.employeeId = employeeId;
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
