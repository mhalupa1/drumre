package hr.fer.drumre.controller;

import hr.fer.drumre.model.DrMovie;
import hr.fer.drumre.model.util.GenreRecommendations;
import hr.fer.drumre.service.RecommendationService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class RecommendationController {

    @Autowired
    RecommendationService service;

    @GetMapping(value ="/personalize")
    public List<DrMovie> personalize(@RequestParam Long id) throws IOException, ParseException, NoSuchFieldException, IllegalAccessException {
        return service.personalize(id);
    }

    @PostMapping(value = "/addClick")
    public ResponseEntity<String> addClick(@RequestParam Long userId, @RequestParam int movieId,
                                           @RequestParam String movieName, @RequestParam String genreName) throws IOException, ParseException {
        service.addClick(userId,movieId,movieName,genreName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
