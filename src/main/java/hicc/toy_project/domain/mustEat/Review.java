package hicc.toy_project.domain.mustEat;

import hicc.toy_project.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "must_eat_id")
    private MustEat mustEat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long score;

    @Column(nullable = false)
    private String content;

    @Builder
    private Review(MustEat mustEat,
                   Member member,
                   Long score,
                   String content) {
        this.mustEat = mustEat;
        this.member = member;
        this.score = score;
        this.content = content;
    }
}
