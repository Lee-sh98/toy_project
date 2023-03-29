package toy.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import toy.project.domain.member.Member;
import toy.project.domain.member.Role;

@Controller
public class myPageApiController {
    @GetMapping("/mypage/info")
    @ResponseBody
    public Member info(@RequestParam("name") Long name){
        System.out.println(name);
        Member member = new Member(name, Role.PRESIDENT);
        return member;
    }
}
