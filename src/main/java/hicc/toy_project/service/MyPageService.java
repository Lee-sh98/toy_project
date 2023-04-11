package hicc.toy_project.service;


import hicc.toy_project.controller.dto.CommentResponse;
import hicc.toy_project.controller.dto.MyPageRequest;
import hicc.toy_project.controller.dto.PostResponse;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.repository.CommentRepository;
import hicc.toy_project.repository.MemberRepository;
import hicc.toy_project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Member memberInfo(String id) {
        Optional<Member> result = memberRepository.findById(id);
        return result.orElseGet(() ->
                new Member("NoMember", "NoMember", "NoMember")
        );
    }

    @Transactional
    public boolean memberModify(MyPageRequest request) {
        Optional<Member> result = memberRepository.findById(request.getId());
        return result.map(member ->
                member.update(request.getNickName(), request.getPhoneNumber()))
                .orElse(false);
    }

    @Transactional
    public List<PostResponse> myPost(String id) {
        return postRepository.findAllByMemberId(id)
                .stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CommentResponse> myComment(String id) {
        return commentRepository.findAllByMemberId(id)
                .stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
}
