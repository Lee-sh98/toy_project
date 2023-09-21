package hicc.toy_project.controller;

import hicc.toy_project.controller.dto.request.rental.RentalRequest;
import hicc.toy_project.controller.dto.response.rental.UmbrellaListResponse;
import hicc.toy_project.controller.dto.response.rental.UmbrellaSimpleResponse;
import hicc.toy_project.service.UmbrellaRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/umbrella")
@RequiredArgsConstructor
public class UmbrellaRentalApiController {
    private final UmbrellaRentalService umbrellaRentalService;

    @PostMapping
    public UmbrellaSimpleResponse rental(@RequestBody RentalRequest request){
        String memberId = "C011225";

        return umbrellaRentalService.rental(memberId, request);
    }

    @GetMapping
    public UmbrellaListResponse rentalList(){
        String memberId = "C011225";

        return umbrellaRentalService.umbrellaList(memberId);
    }

    @PatchMapping
    public UmbrellaSimpleResponse manageUmbrella(@RequestBody RentalRequest request){
        String memberId = "C011001";

        return umbrellaRentalService.manageUmbrella(memberId, request);
    }
}
