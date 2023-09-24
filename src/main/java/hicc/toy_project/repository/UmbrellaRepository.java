package hicc.toy_project.repository;

import hicc.toy_project.domain.rental.Umbrella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UmbrellaRepository extends JpaRepository<Umbrella, UUID> {
    List<Umbrella> findAllByLessorIdNumber(String id);

    Optional<Umbrella> findByUmbrellaNumber(int umbrellaNumber);
}
