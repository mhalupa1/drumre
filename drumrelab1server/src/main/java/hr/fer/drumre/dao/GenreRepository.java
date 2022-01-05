package hr.fer.drumre.dao;

import hr.fer.drumre.model.DrGenre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<DrGenre, Integer> {
}
