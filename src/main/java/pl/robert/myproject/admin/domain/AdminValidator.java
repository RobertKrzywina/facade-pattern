package pl.robert.myproject.admin.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
class AdminValidator implements Validator {

    private AdminRepository adminRepo;

    @Autowired
    public AdminValidator(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    private static final String LOGIN_FIELD = "login.field";
    private static final String WRONG_LOGIN = "Wrong username or password";

    @Override
    public boolean supports(Class<?> clazz) {
        return Admin.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Admin admin = (Admin) target;

        if (!isUsernameExists(admin.getUsername())) {
            errors.rejectValue("admin.password", LOGIN_FIELD, WRONG_LOGIN);
        } else {
            if (!isPasswordExists(admin.getPassword())) {
                errors.rejectValue("admin.password", LOGIN_FIELD, WRONG_LOGIN);
            }
        }
    }

    private boolean isUsernameExists(String username) {
        return adminRepo.findByUsername(username) != null;
    }

    private boolean isPasswordExists(String password) {
        return adminRepo.findByPassword(password) != null;
    }
}
