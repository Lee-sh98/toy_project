package hicc.toy_project.service;

import hicc.toy_project.controller.dto.*;
import hicc.toy_project.domain.member.DeletedMember;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
import hicc.toy_project.exception.CustomException;
import hicc.toy_project.exception.ErrorCode;
import hicc.toy_project.repository.DeletedMemberRepository;
import hicc.toy_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminPageService {
    private final MemberRepository memberRepository;
    private final DeletedMemberRepository deletedMemberRepository;

    private Member getMember(String id) {
        return memberRepository.findByIdNumber(id)
                .orElseThrow(() ->
                        new CustomException(ErrorCode.MEMBER_NOT_FOUND)
                );
    }

    private void validateMandated(String id) {
        Member member = getMember(id);
        if (!member.checkRole(Role.PRESIDENT) && !member.checkRole(Role.EXECUTIVE)) {
            throw new CustomException(ErrorCode.REQUEST_NOT_PERMITTED);
        }
    }

    private void validatePresident(String id) {
        if (!getMember(id).checkRole(Role.PRESIDENT)) {
            throw new CustomException(ErrorCode.REQUEST_NOT_PERMITTED);
        }
    }


    // 모든 회원 조회
    @Transactional(readOnly = true)
    public List<MemberResponse> members(String id) {
        validatePresident(id);

        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::new)
                .collect(Collectors.toList());
    }

    // 회원 정보(등급) 수정
    @Transactional
    public boolean changeRole(AdminPageRequest request) {

        validatePresident(request.getId());

        // 타겟 멤버 조회
        Member targetMember = getMember(request.getTargetId());

        // 회장 자기자신은 U 불가능
        // id == targetId 인 경우
        if (request.getId().equals(request.getTargetId())) {
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }

        // 회장으로 직책을 변경할 수 없음
        if (request.getRole().equals(Role.PRESIDENT)) {
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }

        // 게스트로 변경할 수 없음
        if (request.getRole().equals(Role.GUEST)) {
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }


        // 같은 등급으로 변경할 수 없음
        if (targetMember.getRole().equals(request.getRole())) {
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }


        return targetMember.updateRole(request.getRole());
    }

    //회원 제명
    @Transactional
    public boolean expel(AdminPageRequest request) {
        validatePresident(request.getId());

        Member expelledMember = getMember(request.getTargetId());
        // 회장 자기 자신을 제명할 수 없음
        if (expelledMember.checkRole(Role.PRESIDENT)) {
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }

        DeletedMember deletedMember = new DeletedMember(expelledMember);
        deletedMemberRepository.save(deletedMember);
        memberRepository.deleteByIdNumber(request.getTargetId());

        return true;
    }

    // 가입 승인 대기 목록 조회
    @Transactional(readOnly = true)
    public List<MemberResponse> applicants(String id) {
        // 가입 승인 대기 목록 조회는 회장 또는 임원진만 가능
        validateMandated(id);

        return memberRepository.findAll()
                .stream()
                .filter(member -> member.getRole().equals(Role.GUEST))
                .map(MemberResponse::new)
                .collect(Collectors.toList());
    }

    // 가입 승인 처리
    @Transactional
    public ApproveResponse approve(AdminPageRequest request) {
        // 가입 승인 처리는 회장 또는 임원진만 가능
        validateMandated(request.getId());

        // 타겟 id가 게스트인지 확인
        Member targetMember = getMember(request.getTargetId());
        if (!targetMember.checkRole(Role.GUEST)) {
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }

        // 가입 승인
        if (request.getApproveStatus().equals(ApproveStatus.APPROVED)) {
            targetMember.updateRole(Role.GENERAL);
        }
        // 가입 거부
        else {
            memberRepository.delete(targetMember);
        }

        return new ApproveResponse(request.getApproveStatus());
    }
}
