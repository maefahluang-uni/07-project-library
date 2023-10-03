package lab.microservice.userrepo.author;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    // select all author
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthor() {
        List<Author> authors = authorRepository.findByOrderByNameDesc();
        return ResponseEntity.ok(authors);
    }

    // select author by id
    @GetMapping("/authors/{id}")
    public ResponseEntity getAuthorById(@PathVariable long id) {
        Optional<Author> optAuthor = authorRepository.findById(id);

        // check if id is exists
        if (!optAuthor.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }

        // show author by id
        Author author = optAuthor.get();

        // return success status
        return ResponseEntity.ok(author);
    }

    // select author by name
    @GetMapping("/authors/name/{name}")
    public ResponseEntity getAuthorByName(@PathVariable String name) {
        // get author
        List<Author> authors = authorRepository.findByNameStartingWith(name);

        // chevk if author is empty
        if (authors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("author not found");
        }

        // return success status
        return ResponseEntity.ok(authors);
    }

    // create author
    @PostMapping("/authors")
    public ResponseEntity createAuthor(@RequestBody Author author) {
        // add author
        authorRepository.save(author);

        // return success message
        return ResponseEntity.ok("Author is created");
    }

    // update author
    @PutMapping("/authors")
    public ResponseEntity<String> updateAuthor(@RequestBody Author author) {
        // check if id not exists
        if (!authorRepository.existsById(author.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        // update author
        authorRepository.save(author);

        // return success message
        return ResponseEntity.ok("Author is updated");
    }

    // delete author by id
    @DeleteMapping("/authors/{id}")
    public ResponseEntity deleteAuthorById(@PathVariable long id) {
        // check if id exists in db
        if (!authorRepository.existsById(id)) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        // delete Author by id
        authorRepository.deleteById(id);

        // return success message
        return ResponseEntity.ok("Author is deleted");
    }

    // delete all Author
    @DeleteMapping("/authors")
    public ResponseEntity deleteAllAuthor() {
        // delete all author
        authorRepository.deleteAll();

        // return success message
        return ResponseEntity.ok("All of Author are deleted");
    }

}
