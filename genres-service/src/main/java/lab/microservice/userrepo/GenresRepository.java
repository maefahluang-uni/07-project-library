package lab.microservice.userrepo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GenresRepository extends CrudRepository<Genres, Long> {
    public List<Genres> findByOrderByNameDesc();
}
