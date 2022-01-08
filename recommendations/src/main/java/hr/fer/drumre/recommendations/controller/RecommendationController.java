package hr.fer.drumre.recommendations.controller;

import hr.fer.drumre.recommendations.model.Genre;
import hr.fer.drumre.recommendations.model.GenreReturnData;
import hr.fer.drumre.recommendations.service.RecommendationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationController {

    @Autowired
    RecommendationService service;

    @GetMapping(value="/personalize")
    public List<GenreReturnData> personalize(int id){
        return service.personalize(id);
    }

    @GetMapping(value ="/addClick")
    public ResponseEntity<String> addClick(@RequestParam int userId, @RequestParam int movieId, @RequestParam String movieName, @RequestParam String genreName){
        service.addClick(userId,movieId,movieName,genreName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
