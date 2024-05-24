package adrianobruzzese.be-m5-w2-d5.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;
import java.util.List;

@Getter
public class InvalidRequestException extends RuntimeException {
    private List<ObjectError> errorList;

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(List<ObjectError> errorList) {
        super("Errors in payload validation!");
        this.errorList = errorList;
    }
}
