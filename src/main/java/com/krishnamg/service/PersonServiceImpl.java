package com.krishnamg.service;

import com.krishnamg.dao.PersonDAO;
import com.krishnamg.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by krishnamg on 13/11/17.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDAO personDAO;

    @Override
    public Person create(Person person) {
        return personDAO.create(person);
    }

    @Override
    public Person read(Long id) {
        return personDAO.read(id);
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
