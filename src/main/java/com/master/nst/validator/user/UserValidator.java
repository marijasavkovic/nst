package com.master.nst.validator.user;

import com.master.nst.domain.UserEntity;
import com.master.nst.model.user.UserCmd;
import com.master.nst.repository.UserRepository;
import com.master.nst.sheard.errors.Error;
import com.master.nst.sheard.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    @Component
    public class Add {

        public void validate(UserCmd userCmd) {

            String username = userCmd.getUsername();
            final Optional<UserEntity> userEntity = userRepository.findByUsername(username);

            List<Error> errors = new ArrayList<>();

            if (userEntity.isPresent()) {
                errors.add(new Error("User with this username already exists"));
            }

            if (!errors.isEmpty()) {
                throw new ValidationException(errors);
            }

        }
    }

    @Component
    public class Edit {

        public void validate(Long userId, UserCmd userCmd) {

            String username = userCmd.getUsername();
            final Optional<UserEntity> userEntity = userRepository.findByUsername(username);

            List<Error> errors = new ArrayList<>();

            if (userEntity.isPresent()) {
                if (!userEntity.get().getId().equals(userId)) {
                    errors.add(new Error("User with username already exists"));
                }
            }

            UserEntity user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ValidationException(new Error("User with that id does not exists")));
            ;

            if (!errors.isEmpty()) {
                throw new ValidationException(errors);
            }

        }
    }

    @Component
    public class Delete {

        public void validate(Long userId) {

            UserEntity user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ValidationException(new Error("User with that id does not exists")));
            ;
        }

    }
}
