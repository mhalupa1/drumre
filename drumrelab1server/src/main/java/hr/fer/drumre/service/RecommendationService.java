package hr.fer.drumre.service;

import hr.fer.drumre.model.DrGenre;
import hr.fer.drumre.model.util.GenreRecommendations;
import info.movito.themoviedbapi.model.Genre;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class RecommendationService {

    public List<Object> personalize(int id) throws IOException, ParseException{
        URL url = new URL("http://localhost:8081/personalize?id=" + id);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        scanner.close();
        JSONParser parse = new JSONParser(inline);
        ArrayList<Object> data = parse.parseArray();

        return data;
    }

    public void addClick(int userId, int movieId, String movieName, String genreName) throws IOException, ParseException {
        URL url = new URL("http://localhost:8081/addClick?userId=" + userId + "&movieId=" + movieId + "&movieName=" + movieName +"&genreName=" + genreName);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        url.openStream();

    }
}
