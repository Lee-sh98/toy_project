package hicc.toy_project.controller.dto.response.rental;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LockerListResponse {

    private int lockerCount;
    private List<LockerResponse> lockers;

    private LockerListResponse(List<LockerResponse> lockers){
        this.lockerCount = lockers.size();
        this.lockers = lockers;
    }

    public static LockerListResponse create(List<LockerResponse> lockers) {
        return new LockerListResponse(lockers);
    }
}
