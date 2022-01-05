package hr.fer.drumre.service;

import hr.fer.drumre.dao.ActorRepository;
import hr.fer.drumre.dao.GenreRepository;
import hr.fer.drumre.model.DrActor;
import hr.fer.drumre.model.DrGenre;
import info.movito.themoviedbapi.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository repo;

    public List<DrGenre> getAll(){
        return repo.findAll();
    }
}
