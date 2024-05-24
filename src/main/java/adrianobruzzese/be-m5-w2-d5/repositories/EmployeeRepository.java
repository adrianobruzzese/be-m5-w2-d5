package adrianobruzzese.be-m5-w2-d5.repositories;

import adrianobruzzese.be-m5-w2-d5.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Metodo per verificare l'esistenza di un impiegato tramite email
    boolean existsByEmail(String email);

    // Metodo per trovare un impiegato per email. Restituisce un Optional che può contenere
    // un Employee se trovato, o essere vuoto se nessun Employee corrisponde all'email fornita
    Optional<Employee> findByEmail(String email);
}
