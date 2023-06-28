package hicc.toy_project.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Custom Exception
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> customException(final CustomException e){
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }

    /**
     * HTTP 500 Exception
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> internalException(final Exception e){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
