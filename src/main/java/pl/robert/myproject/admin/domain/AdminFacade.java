package pl.robert.myproject.admin.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.robert.myproject.admin.domain.dto.AdminDTO;
import pl.robert.myproject.admin.domain.exceptions.AdminException;
import pl.robert.myproject.user.domain.UserFacade;

import java.util.List;

@Component
@Getter
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

    private Admin createAdminFromDTO(AdminDTO dto) {
        Admin admin = new Admin();

        admin.setId(dto.getId());
        admin.setName(dto.getName());
        admin.setAge(dto.getAge());
        admin.setEmail(dto.getEmail());
        admin.setUsername(dto.getUsername());
        admin.setPassword(dto.getPassword());

        return admin;
    }

    public void login(Admin admin, BindingResult result) throws AdminException {
        loginMethod(admin, result);
    }

    public void loginREST(AdminDTO adminDto, BindingResult result) throws AdminException  {

        Admin admin = createAdminFromDTO(adminDto);

        loginMethod(admin, result);
    }

    private void loginMethod(Admin admin, BindingResult result) throws AdminException {
        if (adminValidator.supports(Admin.class)) {
            adminValidator.validate(admin, result);
            if (result.hasErrors()) {
                showErrors(result);
            }
        } else {
            throw new AdminException();
        }
    }

    private void showErrors(BindingResult result) {
        List<ObjectError> errors = result.getAllErrors();
        System.out.println("Total errors: " + errors.size());
        errors.forEach(err -> System.out.println(err.getDefaultMessage()));
    }
}
