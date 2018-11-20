package pl.robert.myproject.user.domain;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
class UserValidator implements Validator {

    private UserRepository userRepo;

    public UserValidator(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    private static final String REQUIRED_FIELD = "required.field";
    private static final String EMAIL_FILED = "email.field";
    private static final String LENGTH_FIELD = "length.field";
    private static final String ALREADY_EXISTS = "exists.field";

    private static final String FIRST_NAME_REQUIRED = "Enter your name";
    private static final String EMAIL_REQUIRED = "Enter your e-mail";
    private static final String USERNAME_REQUIRED = "Enter your username";
    private static final String PASSWORD_REQUIRED = "Enter your password";

    private static final String WRONG_LENGTH = "Current field should contain from 3 to 13 characters";
    private static final String WRONG_PASSWORD_LENGTH = "Current field should contain from 6 to 30 characters";
    private static final String WRONG_EMAIL_FORMAT = "Enter a valid email address";
    private static final String USER_ALREADY_EXISTS = "This username is already used by other user";
    private static final String EMAIL_ALREADY_EXISTS = "This email address is already used by other user";

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.name", REQUIRED_FIELD, FIRST_NAME_REQUIRED);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.email", REQUIRED_FIELD, EMAIL_REQUIRED);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.username", REQUIRED_FIELD, USERNAME_REQUIRED);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.password", REQUIRED_FIELD, PASSWORD_REQUIRED);

        User user = (User) target;

        if (user.getName().length() < 3 || user.getName().length() > 13) {
            errors.rejectValue("user.name", LENGTH_FIELD, WRONG_LENGTH);
        }

        if (!verifyEmail(user.getEmail())) {
            errors.rejectValue("user.email", EMAIL_FILED, WRONG_EMAIL_FORMAT);
        }

        if (isEmailExists(user.getEmail())) {
            errors.rejectValue("user.email", EMAIL_FILED, EMAIL_ALREADY_EXISTS);
        }

        if (user.getUsername().length() < 3 || user.getUsername().length() > 13) {
            errors.rejectValue("user.username", LENGTH_FIELD, WRONG_LENGTH);
        }

        if (isUsernameExists(user.getUsername())) {
            errors.rejectValue("user.username", ALREADY_EXISTS, USER_ALREADY_EXISTS);
        }

        if (user.getPassword().length() < 6 || user.getPassword().length() > 30) {
            errors.rejectValue("user.password", LENGTH_FIELD, WRONG_PASSWORD_LENGTH);
        }
    }

    private boolean verifyEmail(String email) {
        return email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
    }

    private boolean isEmailExists(String email) {
        return userRepo.findByEmail(email) != null;
    }

    private boolean isUsernameExists(String username) {
        return userRepo.findByUsername(username) != null;
    }
}