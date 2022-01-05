package hr.fer.drumre.service;

import hr.fer.drumre.dao.ActorRepository;
import hr.fer.drumre.dao.WriterRepository;
import hr.fer.drumre.model.DrActor;
import hr.fer.drumre.model.DrWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterService {

    @Autowired
    WriterRepository repo;

    public List<DrWriter> getAll(){
        return repo.findAll();
    }

    public List<DrWriter> getByName(String name){
        return repo.getByName(name);
    }
}
