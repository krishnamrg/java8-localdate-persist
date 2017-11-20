package com.krishnamg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import listeners.EntityEventListener;
import lombok.NonNull;

/**
 * Created by krishnamg on 13/11/17.
 */
@Entity(name = "person")
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(EntityEventListener.class)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private @NonNull String firstName;

    @Column(name = "last_name")
    private String lastName;

    // As of Hibernate 5.2 LocalDate is automatically mapped to sql type Date
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dob")
    private @NonNull LocalDate dateOfBirth;

    // As of Hibernate 5.2 LocalDateTime is autdomatically mapped to sql type Timestamp
    @Column(name = "create_datetime")
    private LocalDateTime createdDate;

    @Column(name = "modified_datetime")
    private LocalDateTime modifiedDate;

    @Version
    @Column(name = "version")
    private int version;
    // Getters and Setters omitted for brevity


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}

