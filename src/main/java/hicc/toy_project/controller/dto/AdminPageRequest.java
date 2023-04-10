package hicc.toy_project.controller.dto;

import hicc.toy_project.domain.member.Member;
import hicc.toy_project.domain.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminPageRequest {
    private String id;
    private String targetId;
    private Role role;
    private String nickname;
    private String phoneNumber;

    @Builder
    public Member toEntity(){
        return Member.builder()
                .id(id)
                .build();
    }
}
