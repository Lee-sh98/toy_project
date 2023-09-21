package hicc.toy_project.domain.rental;

import hicc.toy_project.domain.member.Member;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class Locker {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID umbrellaId;

    private int lockerNumber;

    private RentalStatus rentalStatus;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member lessor;

    private LocalDateTime rentalDate;

    public void rental(Member member){
        this.lessor = member;
        this.rentalStatus = RentalStatus.OCCUPIED;
        this.rentalDate = LocalDateTime.now();

    }

    public void updateStatus(RentalStatus status){
        this.rentalStatus = status;
    }
}
