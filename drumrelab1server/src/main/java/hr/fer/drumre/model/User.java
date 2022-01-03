package hr.fer.drumre.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("user")
public  class User {
    public User(String id, String screenName){
        this.id = id;
        this.screenName = screenName;
    }

    @Id
    private String id;
    private String screenName;
}
