package pl.robert.myproject.person.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "people")
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
class Person implements Serializable {
    private static final long serialVersionUID = 100L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "age")
    private int age;
}
