package hr.fer.drumre.controller;

import hr.fer.drumre.model.DrActor;
import hr.fer.drumre.model.DrDirector;
import hr.fer.drumre.service.ActorService;
import hr.fer.drumre.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DirectorController {

    @Autowired
    DirectorService service;

    @GetMapping(value = "/getDirectors")
    List<DrDirector> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/getDirectorByName")
    public List<DrDirector> getByName(@RequestParam String name){
        return service.getByName(name);
    }

}
