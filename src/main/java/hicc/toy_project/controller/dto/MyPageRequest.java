package hicc.toy_project.controller.dto;

import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPageRequest {
    private String id;
    private Role role;
    private String nickName;
    private String phoneNumber;

    @Builder
    public Member toEntity(String id){
        return Member.builder()
                .id(id)
                .build();
    }
}
