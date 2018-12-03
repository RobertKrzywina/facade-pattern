package pl.robert.myproject.admin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO implements Serializable {
    private static final long serialVersionUID = 100L;

    protected long id;
    protected String name;
    protected int age;
    protected String email;
    protected String username;
    protected String password;
}
