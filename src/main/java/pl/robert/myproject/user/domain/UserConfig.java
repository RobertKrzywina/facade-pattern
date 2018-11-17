package pl.robert.myproject.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfig {

    @Bean
    User user() {
        return new User();
    }

    @Bean
    UserFacade facade(User user,
                        UserValidator validation,
                        UserRepository repository) {
        return new UserFacade(user, repository, new UserValidator());
    }
}
