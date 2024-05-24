package adrianobruzzese.be-m5-w2-d5.exceptions;

import adrianobruzzese.be-m5-w2-d5.payloads.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorResponseDTO handleBadRequest(InvalidRequestException ex) {
        if (ex.getErrorList() != null) {
            // Componi un messaggio di errore concatenando tutti i messaggi di errore presenti nella lista.
            String message = ex.getErrorList().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            return new ErrorResponseDTO(message, LocalDateTime.now());
        } else {
            // Restituisci un ErrorResponseDTO con il messaggio di errore generico.
            return new ErrorResponseDTO(ex.getMessage(), LocalDateTime.now());
        }
    }

    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorResponseDTO handleNotFound(ElementNotFoundException ex) {
        // Restituisci un ErrorResponseDTO con il messaggio dell'eccezione.
        return new ErrorResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorResponseDTO handleGenericErrors(Exception ex) {
        // Stampa lo stack trace per aiutare con il debug e restituisci un messaggio generico.
        ex.printStackTrace();
        return new ErrorResponseDTO("Problem server side", LocalDateTime.now());
    }
}
