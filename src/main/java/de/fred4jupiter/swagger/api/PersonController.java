package de.fred4jupiter.swagger.api;

import de.fred4jupiter.swagger.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/persons")
@Api(value = "persons", produces = "application/json")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    @ApiOperation(value = "${personcontroller.getallpersons}")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    @ApiOperation("${personcontroller.getpersonbyid}")
    public Person getPersonById(@ApiParam(name = "id", value = "Id of the person to be obtained. Cannot be empty.", required = true) @PathVariable Integer id) {
        return personService.getPersonById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("${personcontroller.deleteperson}")
    public void deletePerson(@ApiParam(name = "id", value = "Id of the person to be deleted. Cannot be empty.", required = true) @PathVariable Integer id) {
        personService.deletePerson(id);
    }

    @PostMapping
    @ApiOperation("${personcontroller.createperson}")
    public Person createPerson(@ApiParam("Person information for a new person to be created.") @RequestBody Person person) {
        return personService.createPerson(person);
    }

}
