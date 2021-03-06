package pl.robert.myproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.robert.myproject.user.domain.UserFacade;
import pl.robert.myproject.user.domain.exceptions.UserException;

import javax.validation.Valid;

@Controller
class UserController {

    private UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("user") UserFacade facade,
                      BindingResult result,
                      Model model) throws UserException {
        userFacade.registerUser(facade.getUser(), result);
        if (result.hasErrors()) {
            model.addAttribute("user", facade);
            return "register-user";
        } else {
            model.addAttribute("user", facade.getUser());
            return "register-user-success";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("user") UserFacade facade,
                        BindingResult result,
                        Model model) throws UserException {
        userFacade.login(facade.getUser(), result);
        if (result.hasErrors()) {
            model.addAttribute("user", facade);
            return "login-user";
        } else {
            model.addAttribute("user", facade.getUser());
            return "login-user-success";
        }
    }
}
