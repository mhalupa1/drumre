package hr.fer.drumre.service;

import hr.fer.drumre.dao.MovieRepository;
import hr.fer.drumre.model.DrMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository repo;


    public List<DrMovie> getMovies(){
        return repo.findAll();
    }

    public DrMovie getMovieById(int id){
        Optional<DrMovie> movie = repo.findById(id);
        return movie.orElse(null);
    }

    public List<DrMovie> getMoviesByTitle(String title){
        return repo.getMoviesByTitle(title);
    }

    public List<DrMovie> getMoviesByActor(String name){
        return repo.getMoviesByActor(name);
    }

    public List<DrMovie> getMoviesByDirector(String name){
        return repo.getMoviesByDirector(name);
    }

    public List<DrMovie> getMoviesByWriter(String name){
        return repo.getMoviesByWriter(name);
    }

    public List<DrMovie> getMoviesByGenre(String name){
        return repo.getMoviesByGenre(name);
    }

}
