package hicc.toy_project.repository;

import hicc.toy_project.domain.mustEat.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByMustEatId(Long id);
}
