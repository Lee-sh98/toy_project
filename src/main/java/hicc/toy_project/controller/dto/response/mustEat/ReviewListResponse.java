package hicc.toy_project.controller.dto.response.mustEat;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReviewListResponse {

    private int count;
    private String name;
    private List<ReviewResponse> reviewList;

    @Builder(access = AccessLevel.PRIVATE)
    private ReviewListResponse(List<ReviewResponse> responses, String name) {
        this.name = name;
        this.count = responses.size();
        this.reviewList = responses;
    }

    public static ReviewListResponse create(List<ReviewResponse> responses, String name) {
        return ReviewListResponse.builder()
                .responses(responses)
                .name(name)
                .build();
    }
}
