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
        return memberRepository.findById(id).isPresent()
                && memberRepository.findById(id).get().getRole().equals(Role.PRESIDENT);
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
    public boolean memberModify(AdminPageRequest request) {
        if (isPresident(request.getId()) && !request.getId().equals(request.getTargetId())) {
            return memberRepository.findById(request.getTargetId())
                    .map(member ->
                            member.update(request.getNickName(), request.getPhoneNumber()))
                    .orElse(false);
        }
        return false;
    }
}
