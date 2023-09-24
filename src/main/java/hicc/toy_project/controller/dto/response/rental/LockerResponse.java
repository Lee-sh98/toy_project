package hicc.toy_project.controller.dto.response.rental;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hicc.toy_project.domain.rental.Locker;
import hicc.toy_project.domain.rental.RentalStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LockerResponse extends LockerSimpleResponse{

    private RentalStatus status;
    private String lessor;
    private LocalDateTime rentalDate;

    public LockerResponse(Locker locker) {
        super(locker.getLockerNumber());
        this.status = locker.getRentalStatus();
        this.lessor = locker.getLessor().getName();
        this.rentalDate = locker.getRentalDate();
    }
}
