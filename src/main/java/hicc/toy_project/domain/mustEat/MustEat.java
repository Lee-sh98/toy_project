package hicc.toy_project.domain.mustEat;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class MustEat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private final String name;

    @Column(nullable = false)
    private final String lat;

    @Column(nullable = false)
    private final String lng;

    @OneToMany(mappedBy = "mustEat")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    private MustEat(String name, String lat, String lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public Long getScore() {

        if (reviews.isEmpty()) {
            System.out.println();
            return 0L;
        }

        long sum = reviews.stream()
                .map(Review::getScore)
                .mapToLong(Long::longValue)
                .sum();
        return sum / reviews.size();
    }
}
