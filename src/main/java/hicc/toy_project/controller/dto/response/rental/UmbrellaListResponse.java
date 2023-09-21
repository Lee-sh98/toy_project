package hicc.toy_project.controller.dto.response.rental;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UmbrellaListResponse {
    private int umbrellaCount;
    private List<UmbrellaResponse> umbrellas;

    @Builder
    private UmbrellaListResponse(List<UmbrellaResponse> umbrellas) {
        this.umbrellaCount = umbrellas.size();
        this.umbrellas = umbrellas;
    }
}
