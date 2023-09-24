package hicc.toy_project.service;


import hicc.toy_project.controller.dto.request.rental.LockerRentalRequest;
import hicc.toy_project.controller.dto.response.rental.LockerListResponse;
import hicc.toy_project.controller.dto.response.rental.LockerResponse;
import hicc.toy_project.controller.dto.response.rental.LockerSimpleResponse;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
import hicc.toy_project.domain.rental.Locker;
import hicc.toy_project.exception.CustomException;
import hicc.toy_project.exception.ErrorCode;
import hicc.toy_project.repository.LockerRepository;
import hicc.toy_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LockerRentalService {

    private final MemberRepository memberRepository;
    private final LockerRepository lockerRepository;

    public LockerSimpleResponse rentLocker(LockerRentalRequest request) {
        Locker locker = getLocker(request.getLockerNumber());
        Member member = getMember(request.getLessorId());

        locker.rentLocker(member);

        return LockerSimpleResponse.create(locker.getLockerNumber());
    }

    @Transactional(readOnly = true)
    public LockerListResponse listLocker() {
        List<LockerResponse> lockers = lockerRepository.findAll()
                .stream()
                .map(LockerResponse::new)
                .toList();

        return LockerListResponse.create(lockers);
    }

    public LockerSimpleResponse manageLocker(LockerRentalRequest request) {
        Locker locker = getLocker(request.getLockerNumber());

        locker.updateStatus(request.getStatus());

        return LockerSimpleResponse.create(locker.getLockerNumber());
    }

    public LockerSimpleResponse approveRental(LockerRentalRequest request) {
        Locker locker = getLocker(request.getLockerNumber());

        locker.approveRental();

        return LockerSimpleResponse.create(locker.getLockerNumber());
    }

    public LockerSimpleResponse rejectRental(LockerRentalRequest request) {
        Locker locker = getLocker(request.getLockerNumber());

        locker.rejectRental();

        return LockerSimpleResponse.create(locker.getLockerNumber());
    }


    private Member getMember(String memberId) {
        return memberRepository.findByIdNumber(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private Locker getLocker(int lockerNumber) {
        return lockerRepository.findByLockerNumber(lockerNumber)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }


    public void validatePresident(String presidentId) {
        Member president = getMember(presidentId);

        if (!president.getRole().equals(Role.PRESIDENT)) {
            throw new CustomException(ErrorCode.REQUEST_NOT_PERMITTED);
        }
    }

    public void validateEligibleMember(String memberId) {
        Member member = getMember(memberId);

        boolean isMember = List.of(Role.PRESIDENT, Role.EXECUTIVE, Role.GENERAL)
                .contains(member.getRole());

        if (!isMember) {
            throw new CustomException(ErrorCode.REQUEST_NOT_PERMITTED);
        }
    }
}
