package th.cmu;

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

public class PublisherController {
    @Autowired
    PublisherRepository publisherRepository;

    // select all publisher
    @GetMapping("/publishers")
    public ResponseEntity<List<Publisher>> getAllpublisher() {
        List<Publisher> publishers = publisherRepository.findByOrderByNameDesc();
        return ResponseEntity.ok(publishers);
    }

    // select publisher by id
    @GetMapping("/publishers/{id}")
    public ResponseEntity getpublisherById(@PathVariable long id) {
        Optional<Publisher> optpublisher = publisherRepository.findById(id);

        // check if id is exists
        if (!optpublisher.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("publisher not found");
        }

        // show publisher by id
        Publisher publisher = optpublisher.get();

        // return success status
        return ResponseEntity.ok(publisher);
    }

    // select publisher by name
    @GetMapping("/publishers/name/{name}")
    public ResponseEntity getpublisherByName(@PathVariable String name) {
        // get publisher
        List<Publisher> publishers = publisherRepository.findByNameStartingWith(name);

        // chevk if publisher is empty
        if (publishers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("publisher not found");
        }

        // return success status
        return ResponseEntity.ok(publishers);
    }

    // create publisher
    @PostMapping("/publishers")
    public ResponseEntity createpublisher(@RequestBody Publisher publisher) {
        // add publisher
        publisherRepository.save(publisher);

        // return success message
        return ResponseEntity.ok("publisher is created");
    }

    // update publisher
    @PutMapping("/publishers")
    public ResponseEntity<String> updatepublisher(@RequestBody Publisher publisher) {
        // check if id not exists
        if (!publisherRepository.existsById(publisher.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("publisher not found");
        }
        // update publisher
        publisherRepository.save(publisher);

        // return success message
        return ResponseEntity.ok("publisher is updated");
    }

    // delete publisher by id
    @DeleteMapping("/publishers/{id}")
    public ResponseEntity deletepublisherById(@PathVariable long id) {
        // check if id exists in db
        if (!publisherRepository.existsById(id)) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("publisher not found");
        }
        // delete publisher by id
        publisherRepository.deleteById(id);

        // return success message
        return ResponseEntity.ok("publisher is deleted");
    }

    // delete all publisher
    @DeleteMapping("/publishers")
    public ResponseEntity deleteAllpublisher() {
        // delete all publisher
        publisherRepository.deleteAll();

        // return success message
        return ResponseEntity.ok("All of publisher are deleted");
    }
}
