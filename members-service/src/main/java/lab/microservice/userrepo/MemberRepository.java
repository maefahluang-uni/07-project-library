package lab.microservice.userrepo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    public List<Member> findByOrderByFnameDesc();

    public List<Member> findByFnameStartingWith(String prefix);
}
