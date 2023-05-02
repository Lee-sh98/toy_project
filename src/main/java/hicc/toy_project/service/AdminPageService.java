package hicc.toy_project.service;

import hicc.toy_project.controller.dto.AdminPageRequest;
import hicc.toy_project.controller.dto.MemberResponse;
import hicc.toy_project.domain.member.Role;
import hicc.toy_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminPageService {
    private final MemberRepository memberRepository;

    private boolean isPresident(String id) {
        /*return memberRepository.findById(id).isPresent()
                && memberRepository.findById(id).get().getRole().equals(Role.PRESIDENT);*/

        return memberRepository.findById(id)
                .map(member -> member.getRole().equals(Role.PRESIDENT))
                .orElse(false);
    }

    private boolean isExecutive(String id) {
        /*return memberRepository.findById(id).isPresent()
                && memberRepository.findById(id).get().getRole().equals(Role.EXECUTIVE);*/

        return memberRepository.findById(id)
                .map(member -> member.getRole().equals(Role.EXECUTIVE))
                .orElse(false);
    }

    private boolean isGuest(String id){
        return memberRepository.findById(id)
                .map(member -> member.getRole().equals(Role.GUEST))
                .orElse(false);
    }

    @Transactional
    public List<MemberResponse> members(String id) {
        if (isPresident(id)) {
            return memberRepository.findAll()
                    .stream()
                    .map(MemberResponse::new)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Transactional
    public boolean changeRole(AdminPageRequest request) {
        /*if (isPresident(request.getId())
                && !request.getId().equals(request.getTargetId())
                && ) {
            return memberRepository.findById(request.getTargetId())
                    .map(member ->
                        member.updateRole(request.getRole()))
                    .orElse(false);
        }
        return false;*/
        if (!isPresident(request.getId())) {
            return false;
        }
        if (request.getId().equals(request.getTargetId())) {
            return false;
        }
        if (request.getRole().equals(Role.PRESIDENT)) {
            return false;
        }
        return memberRepository.findById(request.getTargetId())
                .map(member -> member.updateRole(request.getRole()))
                .orElse(false);
    }

    @Transactional
    public List<MemberResponse> applicants(String id) {
        if (isPresident(id) || isExecutive(id)) {
            return memberRepository.findAll()
                    .stream()
                    .filter(member ->
                            member.getRole().equals(Role.GUEST))
                    .map(MemberResponse::new)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Transactional
    public boolean approve(AdminPageRequest request) {
        if (!isPresident(request.getId()) && !isExecutive(request.getId())) {
            return false;
        }

        if (!isGuest(request.getTargetId())) {
            return false;
        }
        System.out.println("approve a person");
        return memberRepository.findById(request.getTargetId())
                .map(member -> member.updateRole(Role.GENERAL))
                .orElse(false);
    }
}
