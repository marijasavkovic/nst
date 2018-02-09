package com.master.nst.validator.employee;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.repository.EmployeeRepository;
import com.master.nst.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeValidator {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeValidator(final EmployeeService employeeService, final EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @Component
    public class Add {

        public void validate(EmployeeCmd employeeCmd) {
            List<Error> errors = new ArrayList<>();

            String personalIndentificationNumber = employeeCmd.getPersonalIdentificationNumber();
            final Optional<EmployeeEntity> employeeEntity = employeeRepository.findByPersonalIdentificationNumber(
                personalIndentificationNumber);

            if(employeeEntity.isPresent()){
                errors.add(new Error("Employee with this personal indentification number already exists"));
            }


        }
    }

    @Component
    public class Edit {

        public void validate(Long employeId, EmployeeCmd employeeCmd) {

        }
    }
}
