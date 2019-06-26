package de.fred4jupiter.swagger.service;

import de.fred4jupiter.swagger.api.person.Person;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

@Service
public class PersonService {

    private final List<Person> persons = new ArrayList<>();

    @PostConstruct
    void init() {
        persons.add(new Person(1, "John", "Smith", 42));
        persons.add(new Person(2, "Jane", "Jonhnson", 19));
        persons.add(new Person(3, "Kate", "Jones", 33));
    }

    public List<Person> getAllPersons() {
        return this.persons;
    }

    public Person getPersonById(Integer id) {
        return this.persons
                .stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Person createPerson(Person person) {
        OptionalInt maxId = this.persons.stream().mapToInt(Person::getId).max();
        if (maxId.isPresent()) {
            person.setId(maxId.getAsInt() + 1);
        }

        this.persons.add(person);
        return person;
    }

    public void deletePerson(int id) {
        this.persons.removeIf(person -> person.getId().equals(id));
    }
}