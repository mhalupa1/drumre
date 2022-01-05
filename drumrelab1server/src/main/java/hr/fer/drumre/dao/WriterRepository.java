package hr.fer.drumre.dao;

import hr.fer.drumre.model.DrDirector;
import hr.fer.drumre.model.DrWriter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface WriterRepository extends MongoRepository<DrWriter,Integer> {

    @Query(value = "{'name': {'$regex' : '?0', '$options': 'i'}}")
    public List<DrWriter> getByName(String name);
}
