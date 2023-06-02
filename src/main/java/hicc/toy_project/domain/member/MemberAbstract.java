package hicc.toy_project.domain.member;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class MemberAbstract {


    private String idNumber;
    private Role role;
    private String nickName;
    private String phoneNumber;
    private String major;


}
