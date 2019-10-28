package com.yufly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yufly.bean.Person;
import com.yufly.dao.PersonRepository;

@RestController
@RequestMapping("/person5")
public class Person5Controller {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("findFirstByNameOrderByIdDesc/{name}")
    public Person findFirstByNameOrderByIdDesc(@PathVariable String name) {

        return personRepository.findFirstByNameOrderByIdDesc(name);
    }


    @GetMapping("findFirst10ByNameLikeOrderByIdDesc/{name}")
    public List<Person> findFirst10ByNameLikeOrderByIdDesc(@PathVariable String name) {
        return personRepository.findFirst10ByNameLikeOrderByIdDesc("%"+name+"%");
    }



    @GetMapping("findTopByNameOrderByIdAsc/{name}")
    public Person findTopByNameOrderByIdAsc(@PathVariable String name) {
        return personRepository.findTopByNameOrderByIdAsc(name);
    }

    @GetMapping("findTop10ByNameLikeOrderByIdAsc/{name}")
    public List<Person> findTop10ByNameLikeOrderByIdAsc(@PathVariable String name) {
        return personRepository.findTop10ByNameLikeOrderByIdAsc("%"+name+"%");
    }
}
