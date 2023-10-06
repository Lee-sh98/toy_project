package hicc.toy_project.domain.member;

import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class MemberAbstract {

    protected String idNumber;
    protected Role role;
    protected String nickName;
    protected String phoneNumber;
    protected String major;
  
}
