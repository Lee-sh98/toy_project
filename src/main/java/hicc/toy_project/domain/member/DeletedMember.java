package hicc.toy_project.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DeletedMember extends MemberAbstract{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID deletedMemberId;
    private LocalDateTime expelledDate;

    // 생성자
    public DeletedMember(Member member){
        copyProperties(member,this,"member_id");
        this.expelledDate = LocalDateTime.now();
    }
}
