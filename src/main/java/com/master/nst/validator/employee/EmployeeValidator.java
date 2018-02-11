package com.master.nst.validator.employee;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.repository.EmployeeRepository;
import com.master.nst.service.EmployeeService;
import com.master.nst.sheard.exception.ValidationException;
import com.master.nst.sheard.validation.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeValidator {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeValidator(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Component
    public class Add {

        public void validate(EmployeeCmd employeeCmd) {

            String personalIndentificationNumber = employeeCmd.getPersonalIdentificationNumber();
            final Optional<EmployeeEntity> employeeEntity = employeeRepository
                    .findByPersonalIdentificationNumber(personalIndentificationNumber);

            if (employeeEntity.isPresent()) {
                throw new ValidationException("Employee with this personal indentification number already exists");
            }

            DateValidator.dateInPast(employeeCmd.getDateOfBirth(), "Date of birth");

        }
    }

    @Component
    public class Edit {

        public void validate(Long employeeId, EmployeeCmd employeeCmd) {

            EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                                                              .orElseThrow(RuntimeException::new);

            DateValidator.dateInPast(employeeCmd.getDateOfBirth(), "Date of birth");
        }
    }
}
