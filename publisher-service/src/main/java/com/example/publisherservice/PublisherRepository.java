package th.cmu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    public List<Publisher> findByOrderByNameDesc();

    public List<Publisher> findByNameStartingWith(String prefix);
}
