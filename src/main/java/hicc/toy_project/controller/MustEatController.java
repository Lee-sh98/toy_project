package hicc.toy_project.controller;

import hicc.toy_project.controller.dto.request.mustEat.MustEatCreateRequest;
import hicc.toy_project.controller.dto.request.mustEat.ReviewCreateRequest;
import hicc.toy_project.controller.dto.response.mustEat.MustEatListResponse;
import hicc.toy_project.controller.dto.response.mustEat.MustEatResponse;
import hicc.toy_project.controller.dto.response.mustEat.ReviewResponse;
import hicc.toy_project.service.MustEatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/must-eat")
public class MustEatController {

    private final MustEatService mustEatService;

    @PostMapping
    public MustEatResponse create(@RequestBody MustEatCreateRequest request) {

        return mustEatService.create(request);
    }

    @GetMapping
    public MustEatListResponse listMustEat() {

        return mustEatService.listMustEat();
    }

    @PostMapping("/{must-eat-id}")
    public ReviewResponse createReview(@PathVariable("must-eat-id") Long mustEatId,
                                       @RequestBody ReviewCreateRequest request) {
        String memberId = "C011001";
        return mustEatService.writeReview(mustEatId, memberId, request);
    }
}
