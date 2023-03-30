package hicc.toy_project.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Member {

    @Id
    private final String id;
    private Role role;
    private String nickName;
    private String phoneNumber;
    private String major;


    public Member(String id, Role role, String nickName) {
        this.id = id;
        this.role = role;
        this.nickName = nickName;
    }
}
