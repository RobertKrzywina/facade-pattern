package pl.robert.myproject.admin.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.robert.myproject.user.domain.UserFacade;

@Configuration
class AdminConfig {

    @Bean
    Admin admin() {
        return new Admin();
    }

    @Bean
    AdminFacade facade(Admin admin,
                       AdminValidator validator,
                       UserFacade userFacade) {
        return new AdminFacade(admin, validator, userFacade);
    }
}
