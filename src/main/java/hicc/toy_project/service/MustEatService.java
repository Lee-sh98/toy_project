package hicc.toy_project.service;


import hicc.toy_project.controller.dto.request.mustEat.MustEatCreateRequest;
import hicc.toy_project.controller.dto.request.mustEat.ReviewCreateRequest;
import hicc.toy_project.controller.dto.response.mustEat.MustEatListResponse;
import hicc.toy_project.controller.dto.response.mustEat.MustEatResponse;
import hicc.toy_project.controller.dto.response.mustEat.ReviewResponse;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.mustEat.MustEat;
import hicc.toy_project.domain.mustEat.Review;
import hicc.toy_project.exception.CustomException;
import hicc.toy_project.exception.ErrorCode;
import hicc.toy_project.repository.MemberRepository;
import hicc.toy_project.repository.MustEatRepository;
import hicc.toy_project.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MustEatService {

    private final MustEatRepository mustEatRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

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

    public ReviewResponse writeReview(Long mustEatId,
                                      String memberId,
                                      ReviewCreateRequest request) {
        MustEat mustEat = mustEatRepository.findById(mustEatId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        Member member = memberRepository.findByIdNumber(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        Review review = Review.builder()
                .mustEat(mustEat)
                .member(member)
                .content(request.getContent())
                .score(request.getScore())
                .build();

        reviewRepository.save(review);

        return ReviewResponse.create(review);
    }


}
