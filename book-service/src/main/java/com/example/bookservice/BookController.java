package com.example.bookservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

// import javax.persistence.Id;

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
