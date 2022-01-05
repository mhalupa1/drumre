package hr.fer.drumre.dao;

import hr.fer.drumre.model.DrMovie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<DrMovie,Integer> {

    @Query(value = "{'title': {'$regex' : '?0', '$options':'i'}}")
    public List<DrMovie> getMoviesByTitle(String title);

	@Query(value = "{'genres': {'$regex' : '?0', '$options': 'i'}}")
	public List<DrMovie> getMoviesByGenre(String genre);

	@Query(value = "{'actors': {'$regex' : '?0', '$options': 'i'}}")
	public List<DrMovie> getMoviesByActor(String actor);
    @Query(value = "{'directors': {'$regex' : '?0', '$options':'i'}}")
    public List<DrMovie> getMoviesByDirector(String name);

    @Query(value = "{'writers': {'$regex' : '?0', '$options':'i'}}")
    public List<DrMovie> getMoviesByWriter(String name);
}
