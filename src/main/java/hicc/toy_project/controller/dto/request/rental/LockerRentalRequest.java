package hicc.toy_project.controller.dto.request.rental;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hicc.toy_project.domain.rental.RentalStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LockerRentalRequest {

    private String lessorId;
    private int lockerNumber;
    private RentalStatus status;
}
