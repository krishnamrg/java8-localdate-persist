package com.krishnamg.service;

import com.krishnamg.model.Person;

/**
 * Created by krishnamg on 13/11/17.
 */
 public interface PersonService {

     Person create(Person person);

     Person read(Long id);

     Person update(Person person);

     void delete(Long id);

     void close();

}
