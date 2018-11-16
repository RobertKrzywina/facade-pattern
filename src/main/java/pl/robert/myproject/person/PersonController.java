package pl.robert.myproject.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.robert.myproject.person.domain.PersonFacade;

@Controller
class PersonController {

    private PersonFacade personFacade;

    @Autowired
    public PersonController(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("works", "works :))");
        return "index";
    }

    @RequestMapping("/secret")
    public String secret(Model model) {
        model.addAttribute("newPerson", new PersonFacade());
        return "secret";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute PersonFacade facade, Model model) {
        personFacade.add(facade.getPerson());
        model.addAttribute("person", facade.getPerson());
        return "index";
    }
}
