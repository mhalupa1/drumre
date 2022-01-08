package hr.fer.drumre.recommendations.dao;

import hr.fer.drumre.recommendations.model.Genre;
import hr.fer.drumre.recommendations.model.GenreReturnData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

public interface RecommendationRepo extends JpaRepository<Genre, Integer> {


    @Query(value = "select br, zanr from public.personaliziraj(:id)", nativeQuery = true)
    public List<GenreReturnData> personalize(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "call public.dodaj_klik(:userId,:movieId,:movieName,:genreName)", nativeQuery = true)
    public void addClick(@Param("userId") Long userId, @Param("movieId") int movieId, @Param("movieName") String movieName, @Param("genreName") String genreName);
}
