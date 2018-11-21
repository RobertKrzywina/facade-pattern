package pl.robert.myproject.admin.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.robert.myproject.admin.domain.exceptions.AdminException;
import pl.robert.myproject.user.domain.UserFacade;

import java.util.List;

@Component
public class AdminFacade {

    private Admin admin;
    private AdminValidator adminValidator;
    private UserFacade userFacade;

    public AdminFacade() {
        admin = new Admin();
    }

    @Autowired
    public AdminFacade(Admin admin,
                       AdminValidator adminValidator,
                       UserFacade userFacade) {
        this.admin = admin;
        this.adminValidator = adminValidator;
        this.userFacade = userFacade;
    }

    public Admin getAdmin() {
        return admin;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void login(Admin admin, BindingResult result) throws AdminException {
        if (adminValidator.supports(Admin.class)) {
            adminValidator.validate(admin, result);
            if (result.hasErrors()) {
                showErrors(result);
            }
        } else {
            throw new AdminException();
        }
    }

    public void adminPanel() {

    }

    private void showErrors(BindingResult result) {
        List<ObjectError> errors = result.getAllErrors();
        System.out.println("Total errors: " + errors.size());
        errors.forEach(err -> System.out.println(err.getDefaultMessage()));
    }
}
