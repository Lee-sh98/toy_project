package hicc.toy_project.domain.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.Modifying;

import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends MemberAbstract{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID memberId;



    @Builder
    public Member(String id, String nickName, String phoneNumber){
        this.setIdNumber(id);
        this.setNickName(nickName);
        this.setPhoneNumber(phoneNumber);
    }

    @Modifying(clearAutomatically = true)
    public boolean update(String nickName, String phoneNumber){
        this.setNickName(nickName);
        this.setPhoneNumber(phoneNumber);
        return true;
    }

    @Modifying(clearAutomatically = true)
    public boolean updateRole(Role role){
        this.setRole(role);
        return true;
    }

    public boolean checkRole(Role role){
        return this.getRole().equals(role);
    }
}
