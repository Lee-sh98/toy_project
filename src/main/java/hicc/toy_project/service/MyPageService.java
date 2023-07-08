package hicc.toy_project.service;


import hicc.toy_project.controller.dto.CommentResponse;
import hicc.toy_project.controller.dto.MyPageRequest;
import hicc.toy_project.controller.dto.PostResponse;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
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

    private boolean isPresident(Member member) {
        return member.getRole().equals(Role.PRESIDENT);
    }

    private Member getMember(String id) {
        return memberRepository.findByIdNumber(id).orElseThrow(() ->
                new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );
    }

    private void validatePresidentNeverWithdraw(Member withdrawalMember){
        // 회장은 회원탈퇴를 할 수 없음

        if (isPresident(withdrawalMember)){
            throw new CustomException(ErrorCode.INVALID_REQUEST);
        }
    }

    @Transactional
    public Member memberInfo(String id) {
        return getMember(id);
    }

    @Transactional
    public boolean memberModify(MyPageRequest request) throws CustomException {
        Member modifyingMember = getMember(request.getId());

        return modifyingMember.update(request.getNickName(), request.getPhoneNumber());
    }

    @Transactional
    public List<PostResponse> myPost(String id) {
        Member member = getMember(id);

        return postRepository.findAllByMember(member)
                .stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CommentResponse> myComment(String id) {
        Member member = getMember(id);

        return commentRepository.findAllByMember(member)
                .stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean withdraw(String id) {
        Member withdrawalMember = getMember(id);

        validatePresidentNeverWithdraw(withdrawalMember);

        memberRepository.delete(withdrawalMember);
        return true;
    }
}
