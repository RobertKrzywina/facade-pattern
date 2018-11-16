package pl.robert.myproject.person.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PersonConfig {

    @Bean
    Person person() {
        return new Person();
    }

    @Bean
    PersonFacade facade(Person person,
                        PersonValidation validation,
                        PersonRepository repository) {
        return new PersonFacade(person, repository, new PersonValidation());
    }
}
