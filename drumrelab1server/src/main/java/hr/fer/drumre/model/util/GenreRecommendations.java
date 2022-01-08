package hr.fer.drumre.model.util;

public class GenreRecommendations {

    private Integer num;
    private String genre;

    public GenreRecommendations(){

    }

    public GenreRecommendations(Integer num, String genre) {
        this.num = num;
        this.genre = genre;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
