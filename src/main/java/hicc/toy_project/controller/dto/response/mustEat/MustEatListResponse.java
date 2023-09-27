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
public class MustEatListResponse {

    private int count;
    private List<MustEatResponse> mustEatList;

    @Builder(access = AccessLevel.PRIVATE)
    private MustEatListResponse(List<MustEatResponse> responses) {
        this.count = responses.size();
        this.mustEatList = responses;
    }

    public static MustEatListResponse create(List<MustEatResponse> responses){
        return MustEatListResponse.builder()
                .responses(responses)
                .build();
    }
}