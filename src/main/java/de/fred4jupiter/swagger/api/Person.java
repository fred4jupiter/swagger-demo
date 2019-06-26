package de.fred4jupiter.swagger.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

@ApiModel(description = "Class representing a person tracked by the application.")
public class Person {

    @NotNull
    @ApiModelProperty(notes = "${person.id}", example = "1", required = true, position = 0)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 20)
    @ApiModelProperty(notes = "${person.firstname}", example = "John", required = true, position = 1)
    private String firstName;

    @NotBlank
    @ApiModelProperty(notes = "${person.lastlame}", example = "Doe", required = true, position = 2)
    private String lastName;

    @Min(0)
    @Max(100)
    @ApiModelProperty(notes = "${person.age}", example = "42", position = 3)
    private Integer age;

    public Person() {
    }

    public Person(Integer id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}