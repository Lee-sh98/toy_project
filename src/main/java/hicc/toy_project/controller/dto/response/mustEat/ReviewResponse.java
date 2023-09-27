package hicc.toy_project.controller.dto.response.mustEat;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hicc.toy_project.domain.mustEat.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReviewResponse {

    String name;
    String content;
    Long score;

    @Builder
    private ReviewResponse(String name, String content, Long score) {
        this.name = name;
        this.content = content;
        this.score = score;
    }

    public static ReviewResponse create(Review review) {
        return ReviewResponse.builder()
                .name(review.getMember().getName())
                .content(review.getContent())
                .score(review.getScore())
                .build();
    }
}
