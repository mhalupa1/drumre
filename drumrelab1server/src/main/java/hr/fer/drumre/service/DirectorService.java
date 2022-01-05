package hr.fer.drumre.service;

import hr.fer.drumre.dao.ActorRepository;
import hr.fer.drumre.dao.DirectorRepository;
import hr.fer.drumre.model.DrActor;
import hr.fer.drumre.model.DrDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {

    @Autowired
    DirectorRepository repo;

    public List<DrDirector> getAll(){
        return repo.findAll();
    }


    public List<DrDirector> getByName(String name){
        return repo.getByName(name);
    }



}
