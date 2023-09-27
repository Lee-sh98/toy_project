package hicc.toy_project.domain.mustEat;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "must_eat_id")
    private MustEat mustEat;

    private Long score;
}
