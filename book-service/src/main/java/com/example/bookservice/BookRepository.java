package th.cmu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    public List<Book> findByOrderByTitleDesc();

    public List<Book> findByTitleStartingWith(String prefix);
}
