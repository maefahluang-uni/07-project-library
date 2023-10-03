package lab.microservice.userrepo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenresController {
    @Autowired
    GenresRepository genresRepository;

    @GetMapping("/genres")
    public ResponseEntity<List<Genres>> getAllGenres() {
        List<Genres> genres = genresRepository.findByOrderByNameDesc();
        return ResponseEntity.ok(genres);
    }

    // select Genres by id
    @GetMapping("/genres/{id}")
    public ResponseEntity getGenresById(@PathVariable long id) {
        Optional<Genres> optGenres = genresRepository.findById(id);

        // check if id is exists
        if (!optGenres.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genres not found");
        }

        // show Genres by id
        Genres Genres = optGenres.get();

        // return success status
        return ResponseEntity.ok(Genres);
    }

    // create Genres
    @PostMapping("/genres")
    public ResponseEntity createGenres(@RequestBody Genres Genres) {
        // add Genres
        genresRepository.save(Genres);

        // return success message
        return ResponseEntity.ok("Genres is created");
    }

    // update Genres
    @PutMapping("/genres")
    public ResponseEntity<String> updateGenres(@RequestBody Genres genres) {
        // check if id not exists
        if (!genresRepository.existsById(genres.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genres not found");
        }
        // update Genres
        genresRepository.save(genres);

        // return success message
        return ResponseEntity.ok("Genres is updated");
    }

    // delete Genres by id
    @DeleteMapping("/genres/{id}")
    public ResponseEntity deleteGenresById(@PathVariable long id) {
        // check if id exists in db
        if (!genresRepository.existsById(id)) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genres not found");
        }
        // delete Genres by id
        genresRepository.deleteById(id);

        // return success message
        return ResponseEntity.ok("Genres is deleted");
    }

    // delete all Genres
    @DeleteMapping("/genres")
    public ResponseEntity deleteAllGenres() {
        // delete all Genres
        genresRepository.deleteAll();

        // return success message
        return ResponseEntity.ok("All of Genres are deleted");
    }

}
