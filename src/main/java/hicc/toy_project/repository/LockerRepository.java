package hicc.toy_project.repository;

import hicc.toy_project.domain.rental.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LockerRepository extends JpaRepository<Locker, UUID> {
    Optional<Locker> findByLockerNumber(int lockerNumber);
}
