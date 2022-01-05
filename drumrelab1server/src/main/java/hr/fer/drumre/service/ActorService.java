package hr.fer.drumre.service;

import hr.fer.drumre.dao.ActorRepository;
import hr.fer.drumre.model.DrActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    ActorRepository repo;

    public List<DrActor> getAll(){
        return repo.findAll();
    }

    public List<DrActor> getByName(String name){
        return repo.getByName(name);
    }
}
