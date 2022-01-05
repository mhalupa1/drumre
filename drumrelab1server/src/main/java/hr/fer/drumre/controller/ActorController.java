package hr.fer.drumre.controller;

import hr.fer.drumre.model.DrActor;
import hr.fer.drumre.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActorController {

    @Autowired
    ActorService service;

    @GetMapping(value = "/getActors")
    List<DrActor> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/getActorByName")
    public List<DrActor> getByName(@RequestParam String name){
        return service.getByName(name);
    }
}
