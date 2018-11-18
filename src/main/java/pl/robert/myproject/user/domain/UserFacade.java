package pl.robert.myproject.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Component
public class UserFacade {

    private User user;
    private UserRepository userRepository;
    private UserValidator userValidator;

    public UserFacade() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    @Autowired
    public UserFacade(User person,
                      UserRepository userRepository,
                      UserValidator userValidator) {
        this.user = person;
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public void addUser(User user, BindingResult result) {
        if (userValidator.supports(User.class)) {
            userValidator.validate(user, result);
            if (result.hasErrors()) {
                List<ObjectError> errors = result.getAllErrors();
                System.out.println("Total errors: " + errors.size());
                errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            } else {
                userRepository.save(user);
            }
        }
    }
}
