package hicc.toy_project.controller.dto.response.mustEat;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hicc.toy_project.domain.mustEat.MustEat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MustEatResponse {

    private Long id;
    private String name;
    private Long score;

    @Builder
    private MustEatResponse(Long id, String name, Long score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }



    public static MustEatResponse create(MustEat mustEat) {
        return MustEatResponse.builder()
                .id(mustEat.getId())
                .name(mustEat.getName())
                .score(mustEat.getScore())
                .build();
    }
}
