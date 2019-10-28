package com.yufly.controller;

import com.yufly.bean.Person;
import com.yufly.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * day24资料
 */
@RestController
@RequestMapping(value="/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping(path="addPerson")
    public void addPerson(@RequestBody Person person) {
        personRepository.save(person);
    }

    @GetMapping(path="getAllPerson")
    public List<Person> getPerson(){
        return personRepository.findAll();
    }

    @DeleteMapping(path="deletePerson")
    public void deletePerson(@RequestParam Long id) {
        personRepository.deleteById(id);
    }

    @PutMapping(path="updatePerson")
    public void updatePerson(@RequestBody Person person) {
        personRepository.saveAndFlush(person);
    }
}
