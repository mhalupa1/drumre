package hr.fer.drumre.service;

import hr.fer.drumre.dao.MovieRepository;
import hr.fer.drumre.model.DrMovie;
import hr.fer.drumre.model.GenreReturnData;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class RecommendationService {

    @Autowired
    MovieRepository repo;
	
    public List<DrMovie> personalize(Long id) throws IOException, ParseException{
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

        List<GenreReturnData> genres = new ArrayList<>();
        int sum = 0;

        for(int i = 0; i < data.size(); i++) {
        	GenreReturnData grd = new GenreReturnData();
        	String[] cSplit = data.get(i).toString().split(",");
        	cSplit[0] = cSplit[0].replace("{", "");
        	cSplit[1] = cSplit[1].replace("}", "");
        	
        	String[] eSplit = cSplit[0].split("=");
        	
        	if(cSplit[0].contains("zanr")) {
        		grd.setName(eSplit[1]);
        		
        		eSplit = cSplit[1].split("=");
        		grd.setNumber(Double.parseDouble(eSplit[1]));
            	sum += Integer.parseInt(eSplit[1]);
        	} else {
        		grd.setNumber(Double.parseDouble(eSplit[1]));
            	sum += Integer.parseInt(eSplit[1]);
            	
            	eSplit = cSplit[1].split("=");
            	grd.setName(eSplit[1]);
        	}

        	genres.add(grd);
        }
        List<DrMovie> result = new LinkedList<DrMovie>();
        
        for(GenreReturnData grd : genres) {
        	double num = Math.ceil((grd.getNumber() / sum) * 10);
        	List<DrMovie> list = repo.getMoviesByGenre(grd.getName());
        	for(int i = 0; i < (int)num; i++) {
        		int random = (int)(Math.random()*(list.size()-1));
        		
        		if(list.size() == 0) {
        			continue;
        		}
        		
        		if(result.contains(list.get(random))) {
        			i--;
        		} else {
        			result.add(list.get(random));
        		}
        		if(result.size() >= 10) {
        			break;
        		}
        	}
        	if(result.size() >= 10) {
    			break;
    		}
        }
        return result;
    }

    public void addClick(Long userId, int movieId, String movieName, String genreName) throws IOException, ParseException {
        URL url = new URL("http://localhost:8081/addClick?userId=" + userId + "&movieId=" + movieId + "&movieName=" + movieName +"&genreName=" + genreName);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        url.openStream();

    }
}
