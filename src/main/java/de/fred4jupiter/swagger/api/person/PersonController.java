package de.fred4jupiter.swagger.api.person;

import de.fred4jupiter.swagger.api.common.ResourceNotFoundException;
import de.fred4jupiter.swagger.service.PersonService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v2/persons")
@Api(tags = "persons")
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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Person"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public Person getPersonById(@ApiParam(name = "id", value = "Id of the person to be obtained. Cannot be empty.", required = true) @PathVariable Integer id) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            throw new ResourceNotFoundException("person with id=" + id + " could not be found!");
        }

        return person;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("${personcontroller.deleteperson}")
    public void deletePerson(@ApiParam(name = "id", value = "Id of the person to be deleted. Cannot be empty.", required = true) @PathVariable Integer id) {
        personService.deletePerson(id);
    }

    @PostMapping
    @ApiOperation("${personcontroller.createperson}")
    public Person createPerson(@ApiParam("Person information for a new person to be created.") @Valid @RequestBody Person person) {
        return personService.createPerson(person);
    }

}
