package hicc.toy_project.repository;

import hicc.toy_project.domain.mustEat.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
