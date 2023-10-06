package hicc.toy_project.controller;

import hicc.toy_project.controller.dto.request.rental.UmbrellaRentalRequest;
import hicc.toy_project.controller.dto.response.rental.UmbrellaListResponse;
import hicc.toy_project.controller.dto.response.rental.UmbrellaSimpleResponse;
import hicc.toy_project.service.UmbrellaRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/umbrella")
@RequiredArgsConstructor
public class UmbrellaRentalApiController {
    private final UmbrellaRentalService umbrellaRentalService;

    /*
    우산 대여하기 기능
    개발을 위해 구현하였고 추후 제거 필요
     */
    @PatchMapping("/rent")
    public UmbrellaSimpleResponse rental(@RequestBody UmbrellaRentalRequest request) {

        return umbrellaRentalService.rentUmbrella(request);
    }

    /*
    우산 반납하기 기능
    개발을 위해 구현하였고 추후 제거 필요
     */
    @PatchMapping("/return")
    public UmbrellaSimpleResponse returnUmbrella(@RequestBody UmbrellaRentalRequest request) {
        return umbrellaRentalService.returnUmbrella(request);
    }

    @GetMapping
    public UmbrellaListResponse rentalList(@RequestParam("id") String id) {

        return umbrellaRentalService.listUmbrella(id);
    }

    @PatchMapping("/update-status")
    public UmbrellaSimpleResponse updateStatus(@RequestParam("id") String id,
                                                 @RequestBody UmbrellaRentalRequest request) {

        return umbrellaRentalService.updateUmbrellaStatus(id, request);
    }
}
