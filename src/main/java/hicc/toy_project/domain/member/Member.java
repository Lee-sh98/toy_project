package hicc.toy_project.domain.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Entity
@Getter
@NoArgsConstructor
public class Member extends MemberAbstract{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID memberId;



    @Builder
    private Member(String id, String nickName, String phoneNumber, Role role){
        super.idNumber = id;
        super.nickName = nickName;
        super.phoneNumber = phoneNumber;
        super.role = role;
    }

    public boolean update(String nickName, String phoneNumber){
        super.nickName = nickName;
        super.phoneNumber = phoneNumber;
        return true;
    }

    public boolean updateRole(Role role){
        super.role = role;
        return true;
    }
}
