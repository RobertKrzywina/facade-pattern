package pl.robert.myproject.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.robert.myproject.admin.domain.AdminFacade;
import pl.robert.myproject.admin.domain.exceptions.AdminException;
import pl.robert.myproject.user.domain.UserFacade;

import javax.validation.Valid;

@Controller
class AdminController {

    private AdminFacade adminFacade;
    private UserFacade userFacade;

    @Autowired
    public AdminController(AdminFacade adminFacade,
                           UserFacade userFacade) {
        this.adminFacade = adminFacade;
        this.userFacade = userFacade;
    }

    @RequestMapping(value = "/admin-panel", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("admin") AdminFacade facade,
                        BindingResult result,
                        Model model) throws AdminException {
        adminFacade.login(facade.getAdmin(), result);
        if (result.hasErrors()) {
            model.addAttribute("admin", facade);
            return "login-admin";
        } else {
            model.addAttribute("allUsers", userFacade.findAll());
            return "admin-panel";
        }
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.GET)
    public String deleteUser(@RequestParam(name = "userId") String id,
                             Model model) {
        userFacade.deleteUser(id);
        model.addAttribute("allUsers", userFacade.findAll());
        return "admin-panel";
    }
}
