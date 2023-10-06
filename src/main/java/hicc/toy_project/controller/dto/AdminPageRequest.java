package hicc.toy_project.controller.dto;

import hicc.toy_project.domain.member.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminPageRequest {
    private String id;
    private String targetId;
    private Role role;
    private ApproveRequest approveRequest;


    @Builder
    private AdminPageRequest(String id, String targetId, Role role){
        this.id = id;
        this.targetId = targetId;
        this.role = role;
    }
}
