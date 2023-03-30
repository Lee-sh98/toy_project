package hicc.toy_project.controller.dto;

import hicc.toy_project.domain.member.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyPageRequestDto {
    private final String id;
    private Role role;
    private String nickName;
    private String phoneNumber;

}
