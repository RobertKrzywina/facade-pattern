package pl.robert.myproject.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.robert.myproject.admin.domain.AdminFacade;
import pl.robert.myproject.user.domain.UserFacade;

@Controller
class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "error";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new UserFacade());
        return "register-user";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new UserFacade());
        return "login-user";
    }

    @RequestMapping(value = "/login-admin", method = RequestMethod.GET)
    public String loginAdmin(Model model) {
        model.addAttribute("admin", new AdminFacade());
        return "login-admin";
    }
}
