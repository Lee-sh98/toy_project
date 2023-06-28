package hicc.toy_project.service;


import hicc.toy_project.controller.dto.CommentResponse;
import hicc.toy_project.controller.dto.MyPageRequest;
import hicc.toy_project.controller.dto.PostResponse;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.exception.CustomException;
import hicc.toy_project.exception.ErrorCode;
import hicc.toy_project.repository.CommentRepository;
import hicc.toy_project.repository.MemberRepository;
import hicc.toy_project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    @Transactional
    public Member memberInfo(String id) {
        return memberRepository.findByIdNumber(id).orElseThrow(() ->
                new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );
    }

    @Transactional
    public boolean memberModify(MyPageRequest request) throws CustomException {
        return memberRepository.findByIdNumber(request.getId()).map(member ->
                member.update(request.getNickName(), request.getPhoneNumber())
        ).orElseThrow(() ->
                new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );
    }

    @Transactional
    public List<PostResponse> myPost(String id) {
        if (memberRepository.findByIdNumber(id).isEmpty()) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }

        return postRepository.findAllByMemberIdNumber(id)
                .stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CommentResponse> myComment(String id) {
        if (memberRepository.findByIdNumber(id).isEmpty()) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }
        return commentRepository.findAllByMemberIdNumber(id)
                .stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
}
