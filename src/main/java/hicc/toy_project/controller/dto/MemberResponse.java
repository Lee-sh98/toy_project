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
    private String nickname;
    private String major;
    private String name;
    private String phoneNumber;

    public MemberResponse(Member member){
        this.id = member.getIdNumber();
        this.role = member.getRole();
        this.nickname = member.getNickname();
        this.major = member.getMajor();
        this.name = member.getName();
        this.phoneNumber = member.getPhoneNumber();
    }
}
