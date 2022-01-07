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

    @GetMapping(value = "/getMoviesByActor")
    public List<DrMovie> getMoviesByActor(@RequestParam String name){
        return service.getMoviesByActor(name);
    }

    @GetMapping(value = "/getMoviesByDirector")
    public List<DrMovie> getMoviesByDirector(@RequestParam String name){
        return service.getMoviesByDirector(name);
    }

    @GetMapping(value = "/getMoviesByGenre")
    public List<DrMovie> getMoviesByGenre(@RequestParam String name){
        return service.getMoviesByGenre(name);
    }

    @GetMapping(value = "/getMoviesByWriter")
    public List<DrMovie> getMoviesByWriter(@RequestParam String name){
        return service.getMoviesByWriter(name);
    }

    @GetMapping(value = "/getMoviesByGenreImdbRanked")
    public List<DrMovie> getMoviesByGenreImdbRanked(@RequestParam String genre){
    	return service.getMoviesByGenreImdbRanked(genre);
    }

    @GetMapping(value = "/getMoviesByGenreMetascoreRanked")
    public List<DrMovie> getMoviesByGenreMetascoreRanked(@RequestParam String genre){
    	return service.getMoviesByGenreMetascoreRanked(genre);
    }
}
