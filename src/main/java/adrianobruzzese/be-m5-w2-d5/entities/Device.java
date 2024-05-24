package adrianobruzzese.be-m5-w2-d5.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import adrianobruzzese.be-m5-w2-d5.entities.Employee;

// Utilizzo delle annotazioni Lombok per ridurre il boilerplate:
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Device {


    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Configura la generazione automatica del valore
    private long id;

    private String type;  // Tipo di dispositivo
    private String status;  // Stato del dispositivo, es. "Available", "Assigned"

    @ManyToOne  // Specifica che più dispositivi possono essere associati a un singolo impiegato
    private Employee employee;

    // Costruttore per un dispositivo con specificato tipo, stato e impiegato:
    public Device(String type, String status, Employee employee) {
        this.type = type;
        this.status = status;
        this.employee = employee;
    }

    // Costruttore per un dispositivo disponibile (non assegnato):
    public Device(String type) {
        this.type = type;
        this.status = "Available";  // Imposta lo stato predefinito su "Available"
        this.employee = null;  // Nessun impiegato associato
    }
}
