package com.tp3.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tp3.model.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findById(Integer id);
    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String lastName);
}