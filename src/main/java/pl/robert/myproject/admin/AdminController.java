package pl.robert.myproject.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.robert.myproject.admin.domain.AdminFacade;
import pl.robert.myproject.admin.domain.exceptions.AdminException;

import javax.validation.Valid;

@Controller
class AdminController {

    private AdminFacade adminFacade;

    @Autowired
    public AdminController(AdminFacade adminFacade) {
        this.adminFacade = adminFacade;
    }

    @RequestMapping(value = "/admin-login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("admin") AdminFacade facade,
                        BindingResult result,
                        Model model) throws AdminException {
        adminFacade.login(facade.getAdmin(), result);
        if (result.hasErrors()) {
            model.addAttribute("admin", facade);
            return "login-admin";
        } else {
            model.addAttribute("admin", facade.getAdmin());
            return "admin-panel";
        }
    }
}
