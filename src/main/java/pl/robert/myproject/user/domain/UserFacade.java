package pl.robert.myproject.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void add(User user) {
        if(validate(user)) {
            userRepository.save(user);
        } else {
            // Place for PersonExceptions class!
            System.out.println("some error");
        }
    }

    private boolean validate(User user) {
        if (userValidator.supports(User.class)) {
            // Place for validate method!
            return true;
        }
        return false;
    }
}
