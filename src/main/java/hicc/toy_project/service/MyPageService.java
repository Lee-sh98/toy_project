package hicc.toy_project.service;


import hicc.toy_project.controller.dto.MyPageRequest;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member memberInfo(String id) {
        Optional<Member> result = memberRepository.findById(id);
        return result.orElseGet(() ->
                new Member("NoMember", "NoMember", "NoMember")
        );
    }

    @Transactional
    public Boolean memberModify(MyPageRequest request) {
        Optional<Member> result = memberRepository.findById(request.getId());
        return result.map(member -> member.update(request.getNickName(), request.getPhoneNumber())).orElse(false);
    }
}
