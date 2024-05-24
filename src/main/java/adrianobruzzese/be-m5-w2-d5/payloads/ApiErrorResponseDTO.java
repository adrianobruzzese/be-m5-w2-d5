package adrianobruzzese.be-m5-w2-d5.payloads;

import java.time.LocalDateTime;

// Definizione di un record Java per rappresentare le risposte di errore delle API in modo immutabile.
public record ApiErrorResponseDTO(
        String message,      // Il messaggio di errore da visualizzare.
        LocalDateTime timestamp  // Timestamp dell'errore.
)
