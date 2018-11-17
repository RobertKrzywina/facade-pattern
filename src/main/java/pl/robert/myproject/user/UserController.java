package pl.robert.myproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.robert.myproject.user.domain.UserFacade;

import javax.validation.Valid;

@Controller
class UserController {

    private UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new UserFacade());
        return "register-user";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid UserFacade facade,
                      Errors errors,
                      Model model) {
        if (errors.hasErrors()) {
            return "register-user";
        } else {
            userFacade.add(facade.getUser());
            model.addAttribute("user", facade.getUser());
            return "register-user-success";
        }
    }
}
