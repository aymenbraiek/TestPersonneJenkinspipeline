package com.example.esprit.demoPipeline.Controller;

import com.example.esprit.demoPipeline.Entity.Personne;
import com.example.esprit.demoPipeline.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/biat")
public class PersonneController  {


    @Autowired
    private PersonneRepository personneRepository;

    @RequestMapping("/create")
    public Personne createPersonne(@RequestBody Personne personne) {
        personne.setLastName("aymen");
        personne.setAdresse("rue hfjhzhfzf");
        personne.setName("ehththrhr");

        return  personneRepository.save(personne);
    }
}
