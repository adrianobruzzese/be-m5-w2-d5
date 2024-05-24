package adrianobruzzese.be-m5-w2-d5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employee {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;  // Nome utente univoco per l'impiegato
    private String firstName;  // Nome dell'impiegato
    private String lastName;  // Cognome dell'impiegato
    private String email;  // Email dell'impiegato
    private String avatarImage;  // URL dell'immagine del profilo.

    @OneToMany(mappedBy = "employee")  // Mappatura inversa: un impiegao può avere più dispositivi assegnati
    @JsonIgnore  // Evita che la lista di dispositivi assegnati venga inclusa nella serializzazione json.
    private List<Device> assignedDevicesList;

    // Costruttore con argomenti per inizializzare un nuovo impiegato
    public Employee(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
