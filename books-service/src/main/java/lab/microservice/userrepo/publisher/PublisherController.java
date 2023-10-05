package lab.microservice.userrepo.publisher;

import java.util.List;
import java.util.Map;
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
public class PublisherController {
    @Autowired
    PublisherRepository publisherRepository;

    // select all Publisher
    @GetMapping("/publishers")
    public ResponseEntity<List<Publisher>> getAllPublisher() {
        List<Publisher> publishers = publisherRepository.findByOrderByNameDesc();
        return ResponseEntity.ok(publishers);
    }

    // select Publisher by id
    @GetMapping("/publishers/{id}")
    public ResponseEntity getPublisherById(@PathVariable long id) {
        Optional<Publisher> optPublisher = publisherRepository.findById(id);

        // check if id is exists
        if (!optPublisher.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found");
        }

        // show Publisher by id
        Publisher Publisher = optPublisher.get();

        // return success status
        return ResponseEntity.ok(Publisher);
    }

    // select Publisher by name
    @GetMapping("/publishers/name/{name}")
    public ResponseEntity getPublisherByName(@PathVariable String name) {
        // get Publisher
        List<Publisher> publishers = publisherRepository.findByNameStartingWith(name);

        // chevk if Publisher is empty
        if (publishers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found");
        }

        // return success status
        return ResponseEntity.ok(publishers);
    }

    // create Publisher
    @PostMapping("/publishers")
    public ResponseEntity createPublisher(@RequestBody Publisher publisher) {
        // add Publisher
        publisherRepository.save(publisher);

        // return success message
        return ResponseEntity.ok("Publisher is created");
    }

    // update Publisher
    @PutMapping("/publishers")
    public ResponseEntity<String> updatePublisher(@RequestBody Publisher publisher) {
        // check if id not exists
        if (!publisherRepository.existsById(publisher.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found");
        }
        // update Publisher
        publisherRepository.save(publisher);

        // return success message
        return ResponseEntity.ok("Publisher is updated");
    }

    @PatchMapping("/publishers/{id}")
    public ResponseEntity<String> patchPublisher(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // Fetch the existing publisher by ID
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);

        if (!publisherOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found");
        }

        Publisher publisher = publisherOptional.get();

        // Apply partial updates from the request body
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    publisher.setName((String) value);
                    break;
            }
        });

        // Save the updated Publisher
        publisherRepository.save(publisher);

        return ResponseEntity.ok("Publisher is updated");
    }

    // delete Publisher by id
    @DeleteMapping("/publishers/{id}")
    public ResponseEntity deletePublisherById(@PathVariable long id) {
        // check if id exists in db
        if (!publisherRepository.existsById(id)) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found");
        }
        // delete Publisher by id
        publisherRepository.deleteById(id);

        // return success message
        return ResponseEntity.ok("Publisher is deleted");
    }

    // delete all Publisher
    @DeleteMapping("/publishers")
    public ResponseEntity deleteAllPublisher() {
        // delete all Publisher
        publisherRepository.deleteAll();

        // return success message
        return ResponseEntity.ok("All of Publisher are deleted");
    }
}
