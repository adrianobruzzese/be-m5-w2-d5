package adrianobruzzese.be-m5-w2-d5.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DeviceDTO(
        @NotEmpty(message = "The type field is required.") // Assicura che il campo 'type' non sia vuoto
        @Size(min = 3, max = 30, message = "Type field length must be between 3 and 30 characters.") // Controlla la lunghezza del campo 'type'
        String type,

        @NotEmpty(message = "The status field is required.") // Assicura che il campo 'status' non sia vuoto.
        @Size(min = 3, max = 30, message = "Status field length must be between 3 and 30 characters.") // Controlla la lunghezza del campo 'status'
        String status,

        @Min(value = 1, message = "Invalid ID value.") // Garantisce che l'ID dell'impiegato sia valido e maggiore di 0
        @NotNull(message = "The employee ID field must not be null.") // Assicura che l'ID dell'impiegato non sia nullo
        long employeeId
)