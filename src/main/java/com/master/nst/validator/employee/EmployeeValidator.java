package com.master.nst.validator.employee;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.repository.EmployeeRepository;
import com.master.nst.sheard.errors.Error;
import com.master.nst.sheard.exception.ValidationException;
import com.master.nst.sheard.validation.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
            final Optional<EmployeeEntity> employeeEntity = employeeRepository.findByPersonalIdentificationNumber(
                personalIndentificationNumber);

            List<Error> errors = new ArrayList<>();

            if (employeeEntity.isPresent()) {
                errors.add(new Error("Employee with this personal indentification number already exists"));
            }

            Error error = DateValidator.dateInPast(employeeCmd.getDateOfBirth(), "Date of birth");
            if(error!=null) errors.add(error);

            if(!errors.isEmpty()){
                throw new ValidationException(errors);
            }

        }
    }

    @Component
    public class Edit {

        public void validate(Long employeeId, EmployeeCmd employeeCmd) {

            EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow(RuntimeException::new);

            Error error = DateValidator.dateInPast(employeeCmd.getDateOfBirth(), "Date of birth");

            if(error!=null){
                throw new ValidationException(error);
            }
        }
    }
}
