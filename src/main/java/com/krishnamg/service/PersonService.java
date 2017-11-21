package com.krishnamg.service;

import com.krishnamg.model.Person;
import dto.PersonDTO;
import java.util.List;

/**
 * Created by krishnamg on 13/11/17.
 */
public interface PersonService {

    List<PersonDTO> getAll();

    Person create(Person person);

    Person get(Long id);

    Person update(Person person);

    void delete(Long id);

    void close();

}
