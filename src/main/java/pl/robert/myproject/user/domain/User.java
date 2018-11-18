package pl.robert.myproject.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @NotNull
    @Size(min = 3, max = 13)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 13)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min = 3, max = 13)
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min = 6, max = 30)
    private String password;
}
