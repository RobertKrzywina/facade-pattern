package pl.robert.myproject.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.robert.myproject.user.domain.exceptions.UserException;

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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Autowired
    public UserFacade(User user,
                      UserRepository userRepository,
                      UserValidator userValidator) {
        this.user = user;
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public void addUser(User user, BindingResult result) throws UserException {
        if (userValidator.supports(User.class)) {
            userValidator.validate(user, result);
            if (result.hasErrors()) {
                showErrors(result);
            } else {
                userRepository.save(user);
                updateId();
            }
        } else {
            throw new UserException();
        }
    }

    public void login(User user, BindingResult result) throws UserException {
        if (userValidator.supports(User.class)) {
            userValidator.validateLogin(user, result);
            if (result.hasErrors()) {
                showErrors(result);
            }
        } else {
            throw new UserException();
        }
    }

    public void deleteUser(String id) {
        User user = userRepository.getById(Long.parseLong(id));
        userRepository.delete(user);
        updateId();
    }

    private void updateId() {
        List<User> users = findAll();
        Long checker = 0L;
        for (User user : users) {
            if (!user.getId().equals(++checker)) {
                userRepository.updateUserId(checker, user.getId());
            }
        }
    }

    private void showErrors(BindingResult result) {
        List<ObjectError> errors = result.getAllErrors();
        System.out.println("Total errors: " + errors.size());
        errors.forEach(err -> System.out.println(err.getDefaultMessage()));
    }
}
