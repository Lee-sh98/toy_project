package hicc.toy_project.controller.dto.response.rental;

import hicc.toy_project.domain.rental.RentalStatus;
import hicc.toy_project.domain.rental.Umbrella;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UmbrellaResponse extends UmbrellaSimpleResponse{
    private int umbrellaNumber;
    private RentalStatus rentalStatus;
    private String nickname;
    private LocalDateTime rentalDate;

    public UmbrellaResponse(Umbrella umbrella){
        super(umbrella.getUmbrellaNumber());
        this.umbrellaNumber = umbrella.getUmbrellaNumber();
        this.rentalStatus = umbrella.getRentalStatus();
        this.nickname = umbrella.getLessor().getNickname();
        this.rentalDate = umbrella.getRentalDate();
    }
}
