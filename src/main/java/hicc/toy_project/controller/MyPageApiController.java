package hicc.toy_project.controller;

import hicc.toy_project.controller.dto.MyPageRequestDto;
import hicc.toy_project.domain.member.Member;
import hicc.toy_project.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class MyPageApiController {
    private final MyPageService myPageService;

    @GetMapping("/mypage/info")
    @ResponseBody
    public Member myPageInfo(@RequestBody MyPageRequestDto request) {
        return myPageService.memberInfo(request.getId());
    }

    @PostMapping("/mypage/modify")
    @ResponseBody
    public Boolean myPageModify(@RequestBody MyPageRequestDto request) {
        return myPageService.memberModify(request);
    }

    @GetMapping("/mypage/post")
    @ResponseBody
    public void myPagePost() {

    }

    @GetMapping("/mypage/{id}/comment")
    @ResponseBody
    public void myPageComment() {

    }


    @GetMapping("/mypage/{id}/rental")
    @ResponseBody
    public String myPageRental() {

        return "rentals";
    }


}
