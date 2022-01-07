package hr.fer.drumre.controller;

import hr.fer.drumre.model.DrGenre;
import hr.fer.drumre.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    GenreService service;

    @GetMapping(value = "/getGenres")
    List<DrGenre> getAll(){
        return service.getAll();
    }
}
