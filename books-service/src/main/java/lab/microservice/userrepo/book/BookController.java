package lab.microservice.userrepo.book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Id;

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

import lab.microservice.userrepo.author.Author;
import lab.microservice.userrepo.publisher.Publisher;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // select all book
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBook() {
        List<Book> books = bookRepository.findByOrderByTitleDesc();

        return ResponseEntity.ok(books);
    }

    // select book by id
    @GetMapping("/books/{id}")
    public ResponseEntity getBookById(@PathVariable long id) {
        Optional<Book> optBook = bookRepository.findById(id);

        // check if id is exists
        if (!optBook.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // show author by id
        Book book = optBook.get();

        // return success status
        return ResponseEntity.ok(book);
    }

    // select book by title
    @GetMapping("/books/title/{title}")
    public ResponseEntity getBookByTitle(@PathVariable String title) {
        // get book from db
        List<Book> books = bookRepository.findByTitleStartingWith(title);
        // check if book is empty
        if (books.isEmpty()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("book not found");
        }
        return ResponseEntity.ok(books);
    }

    @PostMapping("/books")
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        // add book
        bookRepository.save(book);

        // return success message
        return ResponseEntity.ok("Book is created");
    }

    // update book
    @PutMapping("/books")
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        // check if id not exists
        if (!bookRepository.existsById(book.getId())) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }

        // update book
        bookRepository.save(book);

        // return success message
        return ResponseEntity.ok("Book is updated");
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<String> patchBook(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // Fetch the existing book by ID
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (!bookOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }

        Book book = bookOptional.get();

        // Apply partial updates from the request body
        updates.forEach((key, value) -> {
            switch (key) {
                case "title":
                    book.setTitle((String) value);
                    break;
                case "genresID":
                    book.setGenresID((String) value);
                    break;
                case "publication":
                    book.setPublication((int) value);
                    break;
                case "author":
                    Author author = book.getAuthor();
                    if (value instanceof Map) {
                        Map<String, Object> authorUpdates = (Map<String, Object>) value;
                        authorUpdates.forEach((authorKey, authorValue) -> {
                            switch (authorKey) {
                                case "name":
                                    author.setName((String) authorValue);
                                    break;
                            }
                        });
                    }
                    break;
                case "publisher":
                    Publisher publisher = book.getPublisher();
                    if (value instanceof Map) {
                        Map<String, Object> publisherUpdates = (Map<String, Object>) value;
                        publisherUpdates.forEach((publisherKey, publisherValue) -> {
                            switch (publisherKey) {
                                case "name":
                                    publisher.setName((String) publisherValue);
                                    break;
                                case "country":
                                    publisher.setCountry((String) value);
                                    break;
                            }
                        });
                    }
                    break;
            }
        });

        // Save the updated book
        bookRepository.save(book);

        return ResponseEntity.ok("Book is updated");
    }

    // delete book by id
    @DeleteMapping("/books/{id}")
    public ResponseEntity deleteBookById(@PathVariable long id) {
        // check if id exists in db
        if (!bookRepository.existsById(id)) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        // delete book by id
        bookRepository.deleteById(id);

        // return success message
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // delete all book
    @DeleteMapping("/books")
    public ResponseEntity deleteAllBook() {
        // delete all book
        bookRepository.deleteAll();

        // return success message
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
