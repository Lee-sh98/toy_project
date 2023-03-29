package hicc.toy_project.controller;

import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class myPageApiController {
    @GetMapping("/mypage/info")
    @ResponseBody
    public Member myPageInfo(@RequestParam("id") long id){
        Member member = new Member(id, Role.PRESIDENT);
        return member;
    }


}
