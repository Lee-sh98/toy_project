package hicc.toy_project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Member not exists"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Request is invalid"),
    REQUEST_NOT_PERMITTED(HttpStatus.FORBIDDEN,"Request is not permitted"),
    UNAUTHORIZED_REQUEST(HttpStatus.UNAUTHORIZED, "Request is not authenticated"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error caused")

    ;

    private final HttpStatus httpStatus;
    private final String message;

}
