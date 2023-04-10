package hicc.toy_project.controller.dto;

import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {
    private String id;
    private Role role;
    private String nickName;
    private String major;

    public MemberResponse(Member member){
        this.id = member.getId();
        this.role = member.getRole();
        this.nickName = member.getNickName();
        this.major = member.getMajor();
    }
}
