package hicc.toy_project.controller;

import hicc.toy_project.controller.dto.CommentResponse;
import hicc.toy_project.controller.dto.MyPageRequest;
import hicc.toy_project.controller.dto.PostResponse;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mypage")
@RequiredArgsConstructor
public class MyPageApiController {
    private final MyPageService myPageService;

    /**
     * 나의 정보를 확인한다.
     * @param request Body 부분에 Member Id를 넣어야 한다.
     *                Ex) {"id":"X011001"}
     * @return Member
     */
    @GetMapping("/info")
    public Member myPageInfo(@RequestBody MyPageRequest request) {
        return myPageService.memberInfo(request.getId());
    }

    /**
     * 나의 정보를 수정한다.
     * @param request Body 부분에 Member Id를 비롯한 수정할 사항을 입력한다.
     *                Ex) {
     *                "id": "X011001",
     *                "nickName": "modifiedNickName",
     *                "phoneNumber": "01012341234"
     *                }
     * @return 정보 수정이 완료되면 True, 아닐 경우 False
     */
    @PostMapping("/modify")
    public Boolean myPageModify(@RequestBody MyPageRequest request) {
        return myPageService.memberModify(request);
    }

    /**
     * 내가 작성한 글을 확인한다.
     * @param request Body 부분에 Member Id를 넣어야 한다.
     *                Ex) {"id":"X011001"}
     * @return List of PostResponse DTO
     */
    @GetMapping("/post")
    public List<PostResponse> myPagePost(@RequestBody MyPageRequest request) {
        return myPageService.myPost(request.getId());
    }

    /**
     * 내가 작성한 댓글을 확인한다.
     * @param request Body 부분에 Member Id를 넣어야한다.
     *                Ex) {"id":"X011001"}
     * @return List of CommentResponse DTO
     */
    @GetMapping("/comment")
    public List<CommentResponse> myPageComment(@RequestBody MyPageRequest request) {
        return myPageService.myComment(request.getId());
    }


    /**
     * 회원 탈퇴를 할 수 있다.
     * @param request Body 부분에 Member Id를 넣어야한다.
     *                EX) {"id": "X011001"}
     */
    @PostMapping("/withdraw")
    public boolean withdraw(@RequestBody MyPageRequest request){
        return myPageService.withdraw(request.getId());
    }


}
