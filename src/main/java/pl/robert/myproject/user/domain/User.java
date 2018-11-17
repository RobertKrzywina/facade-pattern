package pl.robert.myproject.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class User implements Serializable {
    private static final long serialVersionUID = 100L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field should be not empty!")
    @Size(min = 3, max = 13, message = "First name should contain from 3 to 13 characters!")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "This field should be not empty!")
    @Size(min = 3, max = 13, message = "Last name should contain from 3 to 13 characters!")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "This field should be not empty!")
    @Email(message = "Wrong email format!")
    @Column(unique = true)
    private String email;

    @NotNull(message = "This field should be not empty!")
    @Size(min = 3, max = 13, message = "Username should contain from 3 to 13 characters!")
    @Column(unique = true)
    private String username;

    @NotNull(message = "This field should be not empty!")
    @Size(min = 6, max = 30, message = "Password should contain from 6 to 25 characters!")
    private String password;
}
