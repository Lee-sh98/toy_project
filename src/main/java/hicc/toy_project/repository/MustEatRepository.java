package hicc.toy_project.repository;


import hicc.toy_project.domain.mustEat.MustEat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MustEatRepository extends JpaRepository<MustEat, Long> {
}
