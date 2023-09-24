package hicc.toy_project.service;

import hicc.toy_project.controller.dto.request.rental.RentalRequest;
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
    public UmbrellaListResponse umbrellaList() {
        List<UmbrellaResponse> umbrellas = umbrellaRepository.findAll()
                .stream()
                .map(UmbrellaResponse::new)
                .toList();

        return UmbrellaListResponse.builder()
                .umbrellas(umbrellas)
                .build();
    }


    public UmbrellaSimpleResponse rental(RentalRequest request) {
        Member member = getEligibleMember(request.getLessorId());

        Umbrella umbrella = getUmbrella(request.getTargetId());

        validateUmbrellaStatus(umbrella, member);
        umbrella.rental(member);

        return UmbrellaSimpleResponse.builder()
                .umbrellaId(umbrella.getUmbrellaNumber())
                .build();
    }

    public UmbrellaSimpleResponse manageUmbrella(RentalRequest request) {

        Umbrella umbrella = getUmbrella(request.getTargetId());

        umbrella.updateStatus(request.getStatus());

        return UmbrellaSimpleResponse.builder()
                .umbrellaId(umbrella.getUmbrellaNumber())
                .build();
    }


    private Umbrella getUmbrella(String umbrellaId) {
        return umbrellaRepository.findById(umbrellaId)
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

    public void validatePresident(String presidentId) {
        Member president = getMember(presidentId);

        if (!president.getRole().equals(Role.PRESIDENT)) {
            throw new CustomException(ErrorCode.REQUEST_NOT_PERMITTED);
        }
    }
}