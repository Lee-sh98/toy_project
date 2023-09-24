package hicc.toy_project.controller.dto.response.rental;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UmbrellaSimpleResponse {
    private int umbrellaNumber;

    protected UmbrellaSimpleResponse(int umbrellaNumber) {
        this.umbrellaNumber = umbrellaNumber;
    }

    public static UmbrellaSimpleResponse create(int umbrellaNumber) {
        return new UmbrellaSimpleResponse(umbrellaNumber);
    }
}
