package hicc.toy_project.service;

import hicc.toy_project.controller.dto.request.rental.UmbrellaRentalRequest;
import hicc.toy_project.controller.dto.response.rental.UmbrellaListResponse;
import hicc.toy_project.controller.dto.response.rental.UmbrellaResponse;
import hicc.toy_project.controller.dto.response.rental.UmbrellaSimpleResponse;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
import hicc.toy_project.domain.rental.RentalStatus;
import hicc.toy_project.domain.rental.Umbrella;
import hicc.toy_project.exception.CustomException;
import hicc.toy_project.exception.ErrorCode;
import hicc.toy_project.repository.MemberRepository;
import hicc.toy_project.repository.UmbrellaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UmbrellaRentalService {
    private final MemberRepository memberRepository;
    private final UmbrellaRepository umbrellaRepository;


    @Transactional(readOnly = true)
    public UmbrellaListResponse umbrellaList(String id) {

        validatePresident(id);

        List<UmbrellaResponse> umbrellas = umbrellaRepository.findAll()
                .stream()
                .map(UmbrellaResponse::new)
                .toList();

        return UmbrellaListResponse.create(umbrellas);
    }


    public UmbrellaSimpleResponse rental(UmbrellaRentalRequest request) {
        Member member = getEligibleMember(request.getLessorId());

        Umbrella umbrella = getUmbrella(request.getUmbrellaNumber());

        // 우산이 대여 가능한 상태인지 확인 후 대여처리
        validateUmbrellaStatus(umbrella, member);
        umbrella.rental(member);

        return UmbrellaSimpleResponse.create(umbrella.getUmbrellaNumber());
    }

    public UmbrellaSimpleResponse returnUmbrella(UmbrellaRentalRequest request) {
        Member member = getEligibleMember(request.getLessorId());

        Umbrella umbrella = getUmbrella(request.getUmbrellaNumber());
        if (!umbrella.getLessor().equals(member)) {
            throw new CustomException(ErrorCode.REQUEST_NOT_PERMITTED);
        }
        umbrella.returnUmbrella();

        return UmbrellaSimpleResponse.create(umbrella.getUmbrellaNumber());
    }

    public UmbrellaSimpleResponse updateStatus(String id, UmbrellaRentalRequest request) {

        validatePresident(id);

        Umbrella umbrella = getUmbrella(request.getUmbrellaNumber());

        umbrella.updateStatus(request.getStatus());

        return UmbrellaSimpleResponse.create(umbrella.getUmbrellaNumber());
    }


    private Umbrella getUmbrella(int umbrellaNumber) {
        return umbrellaRepository.findByUmbrellaNumber(umbrellaNumber)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_REQUEST));
    }

    private boolean isEligible(Role role) {
        return List.of(Role.PRESIDENT, Role.EXECUTIVE, Role.GENERAL)
                .contains(role);
    }

    private void validateUmbrellaStatus(Umbrella umbrella, Member member) {
        if (!umbrella.getRentalStatus().equals(RentalStatus.USABLE)) {
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }

        if (!umbrellaRepository.findAllByLessorIdNumber(member.getIdNumber())
                .isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }

    }

    private Member getMember(String memberId) {
        return memberRepository.findByIdNumber(memberId)
                .orElseThrow(() ->
                        new CustomException(ErrorCode.MEMBER_NOT_FOUND)
                );
    }

    private Member getEligibleMember(String memberId) {
        Member member = getMember(memberId);

        if (!isEligible(member.getRole())) {
            throw new CustomException(ErrorCode.REQUEST_NOT_PERMITTED);
        }

        return member;
    }

    private void validatePresident(String presidentId) {
        Member president = getMember(presidentId);

        if (!president.getRole().equals(Role.PRESIDENT)) {
            throw new CustomException(ErrorCode.REQUEST_NOT_PERMITTED);
        }
    }
}
