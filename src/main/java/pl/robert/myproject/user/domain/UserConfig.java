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
                      UserRepository repository,
                      UserValidator validation) {
        return new UserFacade(user, repository, validation);
    }
}
