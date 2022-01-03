package hr.fer.drumre.controller;

import hr.fer.drumre.model.DrMovie;
import hr.fer.drumre.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService service;

    @GetMapping(value = "/getMovies")
    public List<DrMovie> getMovies(){
        return service.getMovies();
    }

    @GetMapping(value = "/getMovieById")
    public DrMovie getMovieById(@RequestParam int id) {
        return service.getMovieById(id);
    }

    @GetMapping(value = "/getMoviesByTitle")
    public List<DrMovie> getMoviesByTitle(@RequestParam String title){
        return service.getMoviesByTitle(title);
    }
}
