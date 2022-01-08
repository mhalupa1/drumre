package hr.fer.drumre.recommendations.service;

import hr.fer.drumre.recommendations.dao.RecommendationRepo;
import hr.fer.drumre.recommendations.model.Genre;
import hr.fer.drumre.recommendations.model.GenreReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Stream;

@Service
public class RecommendationService {

    @Autowired
    RecommendationRepo repo;

    public List<GenreReturnData> personalize(int id){
        return repo.personalize(id);
    }

    public void addClick(int userId, int movieId,  String movieName,  String genreName){
        repo.addClick(userId,movieId,movieName,genreName);
    }
}
