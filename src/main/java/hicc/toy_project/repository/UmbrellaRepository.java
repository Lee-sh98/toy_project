package hicc.toy_project.repository;

import hicc.toy_project.domain.rental.Umbrella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmbrellaRepository extends JpaRepository<Umbrella, String> {
    List<Umbrella> findAllByLessorIdNumber(String id);
}
