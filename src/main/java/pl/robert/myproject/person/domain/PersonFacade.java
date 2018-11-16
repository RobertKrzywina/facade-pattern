package pl.robert.myproject.person.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonFacade {

    private Person person;
    private PersonRepository personRepository;
    private PersonValidation personValidation;

    public PersonFacade() {
        person = new Person();
    }

    public Person getPerson() {
        return person;
    }

    @Autowired
    public PersonFacade(Person person,
                        PersonRepository personRepository,
                        PersonValidation personValidation) {
        this.person = person;
        this.personRepository = personRepository;
        this.personValidation = personValidation;
    }

    public void add(Person person) {
        personRepository.save(person);
    }
}
