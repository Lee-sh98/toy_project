package hicc.toy_project.service;


import hicc.toy_project.controller.dto.request.mustEat.MustEatCreateRequest;
import hicc.toy_project.controller.dto.response.mustEat.MustEatListResponse;
import hicc.toy_project.controller.dto.response.mustEat.MustEatResponse;
import hicc.toy_project.domain.mustEat.MustEat;
import hicc.toy_project.repository.MustEatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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

    @Transactional(readOnly = true)
    public MustEatListResponse listMustEat() {
        List<MustEatResponse> responses = mustEatRepository
                .findAll()
                .stream()
                .map(MustEatResponse::create)
                .toList();

        return MustEatListResponse.create(responses);
    }


}
