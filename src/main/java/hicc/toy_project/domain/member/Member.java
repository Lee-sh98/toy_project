package hicc.toy_project.domain.member;

import lombok.Getter;

@Getter
public class Member {

    private long id;
    private Role role;

    public Member(long id, Role role){
        this.id = id;
        this.role = role;
    }
}
