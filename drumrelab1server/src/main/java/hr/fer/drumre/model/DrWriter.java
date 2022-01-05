package hr.fer.drumre.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("writer")
public class DrWriter {

    private int id;
    private String name;

    public DrWriter(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
