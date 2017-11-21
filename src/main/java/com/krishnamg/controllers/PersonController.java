package com.krishnamg.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krishnamg.model.Person;
import com.krishnamg.service.PersonService;
import dto.PersonDTO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.Gender;

/**
 * Created by krishnamg on 20/11/17.
 */
@CrossOrigin(
        origins = {"http://localhost:4200", "http://localhost:8080"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
@RestController
@RequestMapping(value = "/v1/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    private Environment env;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PersonDTO>> getAll() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Person> create(@RequestBody String json) throws IOException {
        Person person = getPersonObject(getJsonNode(json));
        if (Objects.nonNull(person)) {
            return new ResponseEntity<>(personService.create(person), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Person> read(@PathVariable Long id) {
        if (Objects.nonNull(id)) {
            return new ResponseEntity<>(personService.get(Long.valueOf(id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Person> update(@RequestBody String json) throws IOException {
        Person person = getPersonObject(getJsonNode(json));
        if (Objects.nonNull(person)) {
            return new ResponseEntity<>(personService.update(person), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Person> delete(@PathVariable String id) {
        if (Objects.nonNull(id)) {
            personService.delete(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private Person getPersonObject(JsonNode jsonNode) {
        Person person = new Person();
        if (jsonNode.has("id")) {
            person.setId(jsonNode.get("id").asLong());
        }
        person.setFirstName(jsonNode.get("firstName").textValue());
        person.setLastName(jsonNode.get("lastName").textValue());
        LocalDate dob = LocalDate
                .parse(jsonNode.get("dob").textValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        person.setDateOfBirth(dob);
        person.setEmail(jsonNode.get("email").textValue());
        person.setGender(Gender.valueOf(jsonNode.get("gender").textValue()));
        return person;
    }

    private JsonNode getJsonNode(@RequestBody String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(json);
    }

}
