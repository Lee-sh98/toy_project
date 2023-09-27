package hicc.toy_project.controller;

import hicc.toy_project.controller.dto.request.mustEat.MustEatCreateRequest;
import hicc.toy_project.controller.dto.response.mustEat.MustEatSimpleResponse;
import hicc.toy_project.service.MustEatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/must-eat")
public class MustEatController {

    private final MustEatService mustEatService;

    @PostMapping
    public MustEatSimpleResponse create(@RequestBody MustEatCreateRequest request) {

        return mustEatService.create(request);
    }
}
