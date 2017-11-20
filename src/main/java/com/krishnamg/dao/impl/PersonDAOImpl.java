package com.krishnamg.dao.impl;

import com.krishnamg.dao.PersonDAO;
import com.krishnamg.model.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

;

/**
 * Created by krishnamg on 13/11/17.
 */
@Repository
public class PersonDAOImpl implements PersonDAO {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.krishnamg");
    private EntityManager em;

    public PersonDAOImpl() {
        em = emf.createEntityManager();
    }

    @Override
    public Person create(Person person) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(person);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            LOGGER.debug("person created {}", person);
        }
        return person;
    }

    @Override
    public Person read(Long id) {
        Person person = null;
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            person = em.find(Person.class, id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOGGER.debug("person found {}",person);
        }
        return person;
    }

    @Override
    public Person update(Person person) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Person persisted = em.find(Person.class, person.getId());
            persisted = populate(persisted, person);
            person = em.merge(persisted);
            transaction.commit();
        } catch (Exception e) {
                e.printStackTrace();
            transaction.rollback();
        } finally {
        }
        return person;
    }

    private Person populate(Person persisted, Person person) {
        persisted.setFirstName(person.getFirstName());
        persisted.setLastName(person.getLastName());
        persisted.setDateOfBirth(person.getDateOfBirth());
        return persisted;
    }

    @Override
    public void delete(Long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(read(id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
        }
    }

    @Override
    public void close() {
        emf.close();
    }
}
