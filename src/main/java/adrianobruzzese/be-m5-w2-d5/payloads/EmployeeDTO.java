package adrianobruzzese.be-m5-w2-d5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

// Definizione di un record Java per trasportare i dati degli impiegati in modo immutabile e validato
public record EmployeeDTO(
        @NotEmpty(message = "The username field is required.")
        @Size(min = 3, max = 20, message = "Username field length must be between 3 and 20 characters.")
        String username,

        @NotEmpty(message = "The first name field is required.")
        @Size(min = 1, max = 30, message = "Name field length must be between 1 and 30 characters.")
        String firstName,

        @NotEmpty(message = "The last name field is required.")
        @Size(min = 1, max = 30, message = "Surname field length must be between 1 and 30 characters.")
        String lastName,

        @NotEmpty(message = "The email field is required.")
        @Email(message = "Invalid email format.")
        String email
)
