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
    
    @Query(value = "{'genres': {'$regex': '?0', '$options': 'i'}, 'imdb': {'$ne': 'N/A'}}",
    		sort = "{'imdb': -1, 'metascore': -1}")
    public List<DrMovie> getMoviesByGenreImdbRanked(String genre);

    @Query(value = "{'genres': {'$regex': '?0', '$options': 'i'}, 'metascore': {'$ne': 'N/A'}}",
    		sort = "{'metascore': -1, 'imdb': -1}")
    public List<DrMovie> getMoviesByGenreMetascoreRanked(String genre);
}
