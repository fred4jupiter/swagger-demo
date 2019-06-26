package de.fred4jupiter.swagger.service;

import de.fred4jupiter.swagger.api.Person;
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
        Person john = new Person(1, "John", "Smith", 42);
        persons.add(john);

        Person jane = new Person(2, "Jane", "Jonhnson", 19);
        persons.add(jane);

        Person kate = new Person(3, "Kate", "Jones", 33);
        persons.add(kate);
    }

    public List<Person> getAllPersons() {
        return this.persons;
    }

    public Person getPersonById(Integer id) {
        return this.persons
                .stream()
                .filter(person -> person.getId() == id)
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
        for (Person person : this.persons) {
            if (person.getId() == id) {
                this.persons.remove(person);
                return;
            }
        }
    }
}