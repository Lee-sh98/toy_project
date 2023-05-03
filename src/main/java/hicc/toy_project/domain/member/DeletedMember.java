package hicc.toy_project.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class DeletedMember {

    @Id
    private String id;
    private Role role;
    private String nickName;
    private String phoneNumber;
    private String major;


    @Builder
    public DeletedMember(Member member){
        this.id = member.getId();
        this.role = member.getRole();
        this.nickName = member.getNickName();
        this.phoneNumber = member.getPhoneNumber();
        this.major = member.getMajor();
    }
}