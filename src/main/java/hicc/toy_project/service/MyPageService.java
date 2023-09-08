package hicc.toy_project.service;


import hicc.toy_project.controller.dto.CommentResponse;
import hicc.toy_project.controller.dto.MemberResponse;
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


    private Member getMember(String id) {
        return memberRepository.findByIdNumber(id)
                .orElseThrow(() ->
                        new CustomException(ErrorCode.MEMBER_NOT_FOUND)
                );
    }

    @Transactional(readOnly = true)
    public MemberResponse memberInfo(String id) {
        return new MemberResponse(getMember(id));
    }

    @Transactional
    public boolean memberModify(MyPageRequest request) throws CustomException {
        return getMember(request.getId())
                .update(request.getNickName(), request.getPhoneNumber());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> myPost(String id) {
        getMember(id);

        return postRepository.findAllByMemberIdNumber(id)
                .stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> myComment(String id) {
        getMember(id);

        return commentRepository.findAllByMemberIdNumber(id)
                .stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
}
