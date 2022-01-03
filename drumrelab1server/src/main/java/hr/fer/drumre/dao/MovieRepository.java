package hr.fer.drumre.dao;

import hr.fer.drumre.model.DrMovie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<DrMovie,String> {

    @Query(value = "{'title': {'$regex' : '?0', '$options':'i'}}")
    public List<DrMovie> getMoviesByTitle(String title);
}
