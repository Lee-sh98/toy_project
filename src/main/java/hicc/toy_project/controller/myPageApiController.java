package hicc.toy_project.controller;

import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class myPageApiController {
    @GetMapping("/mypage/{id}")
    @ResponseBody
    public Member myPageInfo(@PathVariable long id){
        Member member = new Member(id, Role.PRESIDENT,"aaa");
        return member;
    }

    @PatchMapping("/mypage/{id}/modify")
    @ResponseBody
    public void myPageModify(@PathVariable long id){

    }

    @GetMapping("/mypage/{id}/post")
    @ResponseBody
    public void myPagePost(){

    }

    @GetMapping("/mypage/{id}/comment")
    @ResponseBody
    public void myPageComment(){

    }


    @GetMapping("/mypage/{id}/rental")
    @ResponseBody
    public String myPageRental(){

        return "rentals";
    }


}
