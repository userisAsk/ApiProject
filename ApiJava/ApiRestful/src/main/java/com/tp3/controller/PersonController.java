package com.tp3.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tp3.model.Person;
import com.tp3.repository.PersonRepository;
@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    PersonRepository personRepository;
    @GetMapping("/persons")
    public List<Person> getPerson(){
        return personRepository.findAll();
    }
    @GetMapping("/persons/{id}")
    public Optional<Person> getPerson(@PathVariable Integer id) {
        return personRepository.findById(id);
    }

    @PostMapping("/persons")
    public Person createNewPerson(@RequestBody Person newPerson){
        return personRepository.save(newPerson);
    }

    @PutMapping("/persons/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Integer id)
    {
        return personRepository.findById(id)
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    return personRepository.save(person);
                })
                .orElseGet(() -> {
                    return personRepository.save(newPerson);
                });
    }
    @DeleteMapping("/persons")
    public String deletePerson(){
        personRepository.deleteAll();
        return "All persons deleted";
    }

    @DeleteMapping("/persons/{id}")
    public boolean deletePerson(@PathVariable Integer id){
        personRepository.deleteById(id);
        return true;
    }
}