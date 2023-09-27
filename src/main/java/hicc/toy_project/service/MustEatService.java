package hicc.toy_project.service;


import hicc.toy_project.controller.dto.request.mustEat.MustEatCreateRequest;
import hicc.toy_project.controller.dto.response.mustEat.MustEatResponse;
import hicc.toy_project.domain.mustEat.MustEat;
import hicc.toy_project.repository.MustEatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MustEatService {

    private final MustEatRepository mustEatRepository;

    public MustEatResponse create(MustEatCreateRequest request) {

        MustEat mustEat = MustEat.builder()
                .name(request.getName())
                .lat(request.getLat())
                .lng(request.getLng())
                .build();

        mustEatRepository.save(mustEat);
        return MustEatResponse.create(mustEat);
    }




}
