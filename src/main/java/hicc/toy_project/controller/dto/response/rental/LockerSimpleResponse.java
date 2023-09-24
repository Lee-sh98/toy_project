package hicc.toy_project.controller.dto.response.rental;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LockerSimpleResponse {
    private int LockerNumber;

    protected LockerSimpleResponse(int lockerNumber) {
        this.LockerNumber = lockerNumber;
    }

    public static LockerSimpleResponse create(int lockerNumber) {
        return new LockerSimpleResponse(lockerNumber);
    }
}
