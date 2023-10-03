package lab.microservice.userrepo.publisher;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    public List<Publisher> findByOrderByNameDesc();

    public List<Publisher> findByNameStartingWith(String prefix);

}
