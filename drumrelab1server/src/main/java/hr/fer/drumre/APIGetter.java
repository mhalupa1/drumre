package hr.fer.drumre;

import com.uwetrottmann.trakt5.TraktV2;
import com.uwetrottmann.trakt5.entities.Movie;
import com.uwetrottmann.trakt5.enums.Extended;
import com.uwetrottmann.trakt5.services.Movies;
import hr.fer.drumre.dao.*;
import hr.fer.drumre.model.*;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class APIGetter {

    @Autowired
    MovieRepository repository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    WriterRepository writerRepository;
    List<DrMovie> newMovies;

    /*@EventListener(ApplicationReadyEvent.class)
    public void getData2() throws IOException, ParseException, NoSuchFieldException {
        newMovies = new LinkedList<>();
        TraktV2 trakt = new TraktV2("348c786d476c1d93105567ad6087d3f0c129b70ebb5fc5caa0be49817e5099e8");
        Movies movies = trakt.movies();
        Response<List<Movie>> response;
        int idCounter = 0;
        TmdbMovies tmdbMovies = new TmdbApi("ceb862e981581c4562d9983396e38ef6").getMovies();
        String inline = "";
        LinkedHashMap<String, Object> data;
        JSONParser parse;
        Scanner scanner;



        //https://api.themoviedb.org/3/search/movie?pi_key=<<api_key>>&query=haha&include_adult=false
        for(int i = 0; i<5; ++i){
            response =movies.popular(i+1,100, Extended.FULL).execute();

            for(Movie movie : response.body()){
                DrMovie newMovie = new DrMovie();
                newMovie.setId(++idCounter);
                newMovie.setTraktId(movie.ids.trakt.toString());
                newMovie.setTitle(movie.title);
                newMovie.setReleaseDate(movie.released.toString());
                newMovie.setTrailer(movie.trailer);
                newMovie.setHomepage(movie.homepage);
                newMovie.setGenres(movie.genres);
                newMovie.setSynopsis(movie.overview);

                URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=ceb862e981581c4562d9983396e38ef6&query=" + movie.title.replace(' ', '+') +
                        "&include_adult=false");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                inline = "";
                scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();

                parse = new JSONParser(inline);
                data = parse.parseObject();
                ArrayList<LinkedHashMap<String,Object>> movieDbList = (ArrayList<LinkedHashMap<String, Object>>) data.get("results");
                Optional<LinkedHashMap<String, Object>> movieDbMov = movieDbList.stream().filter(map -> map.get("overview").toString().equals(newMovie.getSynopsis())).findFirst();

                if(movieDbMov.isPresent()){
                    newMovie.setPoster(movieDbMov.get().get("poster_path").toString());
                    newMovie.setMovieDbId(movieDbMov.get().get("id").toString());

                }


               url = new URL("https://www.omdbapi.com/?t=" + movie.title.replace(' ', '+') + "&apikey=55101b5");

                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                inline = "";
                scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();

                parse = new JSONParser(inline);
                data = parse.parseObject();


                newMovie.setDirectors(data.get("Director") != null ? parseList(data.get("Director").toString()) : null);
                newMovie.setWriters(data.get("Writer") != null ? parseList(data.get("Writer").toString()) : null);
                newMovie.setActors(data.get("Actors") != null ? parseList(data.get("Actors").toString()) : null);
                try {
                    newMovie.setMetascore(data.get("Metascore") != null ? data.get("Metascore").toString() : null);
                } catch (Exception e){

                }
                try {
                    newMovie.setImdb(data.get("imdbRating") != null ? data.get("imdbRating").toString() : null);
                } catch (Exception e){

                }
                newMovie.setImdbId(data.get("imdbID") != null ? data.get("imdbID").toString() : null);

                System.out.println(idCounter + " " + newMovie.getTitle());
                newMovies.add(newMovie);
                repository.save(newMovie);

            }


        }
    }*/

    public List<String> parseList(String list){
        return Arrays.stream(list.split(",")).collect(Collectors.toList());
    }

    /*private void getGenres(){
        List<DrMovie> movies = repository.findAll();

        Set<String> genreSet = new HashSet<>();
        for(DrMovie movie : movies){
            List<String> genres = movie.getGenres();
            genreSet.addAll(genres);
        }

        int idCounter = 0;
        List<DrGenre> genres = new LinkedList<>();
        for(String genreName: genreSet){
            genres.add(new DrGenre(++idCounter,genreName));
        }

        genreRepository.saveAll(genres);


    }*/

    /*@EventListener(ApplicationReadyEvent.class)
    public void getActors(){
        List<DrMovie> movies = repository.findAll();

        Set<String> actorSet = new HashSet<>();
        for(DrMovie movie : movies){
            List<String> actors = movie.getActors();
            if(actors != null) {
                actorSet.addAll(actors);
            }
        }

        int idCounter = 0;
        List<DrActor> actors = new LinkedList<>();
        for(String actorName: actorSet){
            actors.add(new DrActor(++idCounter,actorName.trim()));
        }

        actorRepository.saveAll(actors);
    }*/

    /*@EventListener(ApplicationReadyEvent.class)
    public void getDirectors(){
        List<DrMovie> movies = repository.findAll();

        Set<String> directorSet = new HashSet<>();
        for(DrMovie movie : movies){
            List<String> directors = movie.getDirectors();
            if(directors != null) {
                directorSet.addAll(directors);
            }
        }

        int idCounter = 0;
        List<DrDirector> directors = new LinkedList<>();
        for(String directorName: directorSet){
            directors.add(new DrDirector(++idCounter,directorName.trim()));
        }

        directorRepository.saveAll(directors);
    }*/

    /*@EventListener(ApplicationReadyEvent.class)
    public void getWriters(){
        List<DrMovie> movies = repository.findAll();

        Set<String> writerSet = new HashSet<>();
        for(DrMovie movie : movies){
            List<String> writers = movie.getWriters();
            if(writers != null) {
                writerSet.addAll(writers);
            }
        }

        int idCounter = 0;
        List<DrWriter> writers = new LinkedList<>();
        for(String writerName: writerSet){
            writers.add(new DrWriter(++idCounter,writerName.trim()));
        }

        writerRepository.saveAll(writers);
    }*/



}
