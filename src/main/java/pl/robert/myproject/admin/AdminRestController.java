package pl.robert.myproject.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robert.myproject.admin.domain.AdminFacade;
import pl.robert.myproject.admin.domain.dto.AdminDTO;
import pl.robert.myproject.admin.domain.exceptions.AdminException;
import pl.robert.myproject.user.domain.UserFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin-panel")
@CrossOrigin("http://localhost:4200")
class AdminRestController {

    private AdminFacade adminFacade;
    private UserFacade userFacade;

    @Autowired
    public AdminRestController(AdminFacade adminFacade,
                               UserFacade userFacade) {
        this.adminFacade = adminFacade;
        this.userFacade = userFacade;
    }

    @GetMapping("/users")
    public ResponseEntity<List<?>> allUsers() {
        List<?> users = userFacade.findAll();

        if (users.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        Object user = userFacade.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody AdminDTO dto,
                                   BindingResult result) throws AdminException {
        adminFacade.loginREST(dto, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(dto.getUsername(), HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> deleteAllUsers() {

        if (userFacade.findAll() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userFacade.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {

        if (userFacade.getUserById(Long.parseLong(id)) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userFacade.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
