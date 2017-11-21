package com.krishnamg.service.impl;

import com.krishnamg.dao.PersonDAO;
import com.krishnamg.model.Person;
import com.krishnamg.service.PersonService;
import dto.PersonDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by krishnamg on 13/11/17.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDAO personDAO;

    @Override
    public Person create(Person person) {
        return personDAO.create(person);
    }

    @Override
    public List<PersonDTO> getAll() {
        return personDAO.getAll();
    }

    @Override
    public Person get(Long id) {
        return personDAO.get(id);
    }

    @Override
    public Person update(Person person) {
        return personDAO.update(person);
    }

    @Override
    public void delete(Long id) {
        personDAO.delete(id);
    }

    @Override
    public void close() {
        personDAO.close();
    }
}
