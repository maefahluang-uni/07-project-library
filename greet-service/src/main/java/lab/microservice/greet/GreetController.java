package lab.microservice.greet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @Autowired
    private BookServiceProxy bookServiceProxy;

    // add service to greet book by id
    @RequestMapping("/greet/{id}")
    public ResponseEntity<String> greetBookById(@PathVariable Long id) {
        // call book-service to get book by id
        BookDTO bookDTO = bookServiceProxy.getBook(id);

        // return hello with bookname
        return new ResponseEntity<String>("Hello " + bookDTO.getTitle() + " at port:" + bookDTO.getPort(),
                HttpStatus.OK);

    }
}
