package pl.robert.myproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.myproject.user.domain.UserFacade;
import pl.robert.myproject.user.domain.dto.UserDTO;
import pl.robert.myproject.user.domain.exceptions.UserException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:4200")
class UserRestController {

    private UserFacade userFacade;

    @Autowired
    public UserRestController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO user,
                                          BindingResult result) throws UserException {
        userFacade.registerUserREST(user, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDTO user,
                                       BindingResult result) throws UserException {
        userFacade.loginREST(user, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }
}
