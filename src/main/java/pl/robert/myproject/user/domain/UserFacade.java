package pl.robert.myproject.user.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.robert.myproject.user.domain.dto.UserDTO;
import pl.robert.myproject.user.domain.exceptions.UserException;

import java.util.List;

@Component
@Getter
public class UserFacade {

    private User user;
    private UserRepository userRepository;
    private UserValidator userValidator;

    public UserFacade() {
        user = new User();
    }

    @Autowired
    public UserFacade(User user,
                      UserRepository userRepository,
                      UserValidator userValidator) {
        this.user = user;
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    private User createUserFromDTO(UserDTO dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        return user;
    }

    public void registerUser(User user, BindingResult result) throws UserException {
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

    public void registerUserREST(UserDTO userDto, BindingResult result) throws UserException {

        User user = createUserFromDTO(userDto);

        if (userValidator.supports(User.class)) {
            userValidator.validateREST(user, result);
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.getById(id);
    }

    public void login(User user, BindingResult result) throws UserException {
        loginMethod(user, result);
    }

    public void loginREST(UserDTO userDto, BindingResult result) throws UserException {

        User user = createUserFromDTO(userDto);

        loginMethod(user, result);
    }

    private void loginMethod(User user, BindingResult result) throws UserException {
        if (userValidator.supports(User.class)) {
            userValidator.validateLogin(user, result);
            if (result.hasErrors()) {
                showErrors(result);
            }
        } else {
            throw new UserException();
        }
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();;
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
