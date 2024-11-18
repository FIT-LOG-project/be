package lesw.fit_log.api.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestResponse<T> {
    private final int code;
    private final HttpStatus status;
    private final String message;
    private final T data;

    private RestResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> RestResponse<T> of(HttpStatus status, String message, T data) {
        return new RestResponse<>(status, message, data);
    }
}
