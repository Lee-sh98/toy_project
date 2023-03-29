package toy.project.domain.member;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Member {
    private Long id;
    private Role role;

    public Member(Long id, Role role) {
        this.id = id;
        this.role = role;
    }
}
