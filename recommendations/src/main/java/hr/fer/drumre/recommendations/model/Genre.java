package hr.fer.drumre.recommendations.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="zanr")
public class Genre {

    @Id
    private Integer id_zanra;
    private String naziv;

    public Genre(Integer id_zanra, String naziv) {
        this.id_zanra = id_zanra;
        this.naziv = naziv;
    }

    public Genre() {

    }



    public Integer getId_zanra() {
        return id_zanra;
    }

    public void setId_zanra(Integer id_zanra) {
        this.id_zanra = id_zanra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
}
