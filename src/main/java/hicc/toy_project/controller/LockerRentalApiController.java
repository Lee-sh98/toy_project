package hicc.toy_project.controller;


import hicc.toy_project.controller.dto.request.rental.LockerRentalRequest;
import hicc.toy_project.controller.dto.response.rental.LockerListResponse;
import hicc.toy_project.controller.dto.response.rental.LockerSimpleResponse;
import hicc.toy_project.exception.CustomException;
import hicc.toy_project.exception.ErrorCode;
import hicc.toy_project.service.LockerRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/locker")
@RequiredArgsConstructor
public class LockerRentalApiController {

    private final LockerRentalService lockerRentalService;

    /*
    사물함 대여하기 기능
    개발을 위해 구현하였고 추후 제거 필요
     */
    public LockerSimpleResponse rentLocker(@RequestBody LockerRentalRequest request) {
        lockerRentalService.validateEligibleMember(request.getLessorId());

        return lockerRentalService.rentLocker(request);
    }

    // 사물함 대여 현황 조회
    @GetMapping
    public LockerListResponse listLocker(@RequestParam("id") String id) {

        return lockerRentalService.listLocker(id);
    }

    // 사물함 대여 승인/거부
    @PatchMapping
    public LockerSimpleResponse processRental(@RequestParam("id") String id,
                                              @RequestParam("status") String status,
                                              @RequestBody LockerRentalRequest request) {

        if (status.equals("approve")) {
            return lockerRentalService.approveRental(id, request);
        }
        if (status.equals("reject")) {
            return lockerRentalService.rejectRental(id, request);
        }

        throw new CustomException(ErrorCode.INVALID_REQUEST);
    }


    // 사물함 관리
    @PatchMapping("/manage")
    public LockerSimpleResponse manageLocker(@RequestBody LockerRentalRequest request) {
        String presidentId = "C011001";
        lockerRentalService.validatePresident(presidentId);

        return lockerRentalService.manageLocker(request);
    }

}
