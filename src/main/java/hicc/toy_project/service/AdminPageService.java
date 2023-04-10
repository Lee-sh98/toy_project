package hicc.toy_project.service;

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

    @Transactional
    public List<MemberResponse> members(String id){
        if (!memberRepository.existsById(id)) {
            return new ArrayList<>();
        }
        if (memberRepository.findById(id).get().getRole().equals(Role.PRESIDENT)) {
            return memberRepository.findAll()
                    .stream()
                    .map(MemberResponse::new)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
