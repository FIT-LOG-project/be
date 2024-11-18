package lesw.fit_log.api.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final int code;
    private final HttpStatus status;
    private final String errorMassage;

    private ErrorResponse(HttpStatus status, String errorMassage) {
        this.code = status.value();
        this.status = status;
        this.errorMassage = errorMassage;
    }

    public static ErrorResponse of(HttpStatus status, String errorMassage) {
        return new ErrorResponse(status, errorMassage);
    }
}
