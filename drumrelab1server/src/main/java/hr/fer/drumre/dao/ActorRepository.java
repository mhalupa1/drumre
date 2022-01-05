package hr.fer.drumre.dao;


import hr.fer.drumre.model.DrActor;
import hr.fer.drumre.model.DrDirector;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ActorRepository extends MongoRepository<DrActor,Integer> {

    @Query(value = "{'name': {'$regex' : '?0', '$options': 'i'}}")
    public List<DrActor> getByName(String name);
}
