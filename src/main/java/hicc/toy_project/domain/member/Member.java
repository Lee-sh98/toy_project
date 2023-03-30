package hicc.toy_project.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    private String id;
    private Role role;
    private String nickName;
    private String phoneNumber;
    private String major;

    @Builder
    public Member(String id, String nickName, String phoneNumber){
        this.id = id;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }
}
