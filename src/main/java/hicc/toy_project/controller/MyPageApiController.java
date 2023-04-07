package hicc.toy_project.controller;

import hicc.toy_project.controller.dto.CommentResponse;
import hicc.toy_project.controller.dto.MyPageRequest;
import hicc.toy_project.controller.dto.PostResponse;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageApiController {
    private final MyPageService myPageService;

    @GetMapping("/mypage/info")
    @ResponseBody
    public Member myPageInfo(@RequestBody MyPageRequest request) {
        return myPageService.memberInfo(request.getId());
    }

    @PostMapping("/mypage/modify")
    @ResponseBody
    public Boolean myPageModify(@RequestBody MyPageRequest request) {
        return myPageService.memberModify(request);
    }

    @GetMapping("/mypage/post")
    @ResponseBody
    public List<PostResponse> myPagePost(@RequestBody MyPageRequest request) {
        return myPageService.myPost(request.getId());
    }

    @GetMapping("/mypage/comment")
    @ResponseBody
    public List<CommentResponse> myPageComment(@RequestBody MyPageRequest request) {
        return myPageService.myComment(request.getId());
    }


    @GetMapping("/mypage/{id}/rental")
    @ResponseBody
    public String myPageRental() {

        return "rentals";
    }


}
