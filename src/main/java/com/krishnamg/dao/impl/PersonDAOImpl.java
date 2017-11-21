package com.krishnamg.dao.impl;

import com.krishnamg.dao.PersonDAO;
import com.krishnamg.model.Person;
import dto.PersonDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

;

/**
 * Created by krishnamg on 13/11/17.
 */
@Repository
public class PersonDAOImpl implements PersonDAO {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.krishnamg");
    private EntityManager em;

    public PersonDAOImpl() {
        em = emf.createEntityManager();
    }

    @Override
    public List<PersonDTO> getAll(){
        final List<PersonDTO> persons = new ArrayList<>();
        try {
            jdbcTemplate.query("select * from person", new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet rs) throws SQLException {
                    PersonDTO person = new PersonDTO();
                    person.setId(rs.getLong("id"));
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                    person.setDob(rs.getDate("dob").toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    person.setEmail(rs.getString("email"));
                    person.setGender(rs.getString("gender"));
                    persons.add(person);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOGGER.debug("{} persons found", persons.size());
        }
        return persons;
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
    public Person get(Long id) {
        Person person = null;
        try {
            person = em.find(Person.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOGGER.debug("person found {}", person);
        }
        return person;
    }

    @Override
    public Person update(Person person) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Person persisted = get(person.getId());
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
        persisted.setGender(person.getGender());
        persisted.setEmail(person.getEmail());
        return persisted;
    }

    @Override
    public void delete(Long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(get(id));
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
