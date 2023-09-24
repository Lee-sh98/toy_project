package hicc.toy_project.domain.rental;

import hicc.toy_project.domain.member.Member;
import jakarta.persistence.*;
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
    private UUID lockerId;

    @Column(unique = true)
    private int lockerNumber;

    private RentalStatus rentalStatus;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member lessor;

    private LocalDateTime rentalDate;


    public void rentLocker(Member member) {
        this.lessor = member;
        this.rentalStatus = RentalStatus.WAITING;
    }

    public void approveRental() {
        this.rentalStatus = RentalStatus.OCCUPIED;
        this.rentalDate = LocalDateTime.now();
    }

    public void rejectRental() {
        this.lessor = null;
        this.rentalStatus = RentalStatus.USABLE;
        this.rentalDate = null;
    }

    public void updateStatus(RentalStatus status) {
        this.rentalStatus = status;
    }
}
