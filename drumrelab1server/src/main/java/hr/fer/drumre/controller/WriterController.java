package hr.fer.drumre.controller;

import hr.fer.drumre.model.DrActor;
import hr.fer.drumre.model.DrWriter;
import hr.fer.drumre.service.ActorService;
import hr.fer.drumre.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WriterController {

    @Autowired
    WriterService service;

    @GetMapping(value = "/getWriters")
    List<DrWriter> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/getWriterByName")
    public List<DrWriter> getByName(@RequestParam String name){
        return service.getByName(name);
    }
}
