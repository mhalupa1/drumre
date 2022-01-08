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
    private String zanr;

    public Genre(Integer id_zanra, String naziv, String zanr) {
        this.id_zanra = id_zanra;
        this.naziv = naziv;
        this.zanr = zanr;
    }

    public Genre() {

    }


    public String getZanr() {
        return zanr;
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

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }
}
