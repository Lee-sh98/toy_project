package hicc.toy_project.controller.dto.request.rental;


import hicc.toy_project.domain.rental.RentalStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UmbrellaRentalRequest {

    private String lessorId;
    private int umbrellaNumber;
    private RentalStatus status;

}
