package adrianobruzzese.be-m5-w2-d5.services;

import adrianobruzzese.be-m5-w2-d5.entities.Employee;
import adrianobruzzese.be-m5-w2-d5.exceptions.InvalidRequestException;
import adrianobruzzese.be-m5-w2-d5.exceptions.ElementNotFoundException;
import adrianobruzzese.be-m5-w2-d5.payloads.EmployeeDTO;
import adrianobruzzese.be-m5-w2-d5.repositories.EmployeeRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository; // Autowired permette l'iniezione automatica del repository.
    @Autowired
    private Cloudinary cloudinary; // Servizio Cloudinary per la gestione delle immagini.

    // Metodo per recuperare tutti gli impiegati paginati e ordinati.
    public Page<Employee> findAll(int page, int size, String sort) {
        size = Math.min(size, 30); // Limito la dimensione massima delle pagine a 30 per evitare prestazioni degradate.
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort)); // Creo un oggetto pageable per gestire la paginazione.
        return employeeRepository.findAll(pageable);
    }

    // Metodo per trovare un impiegato per ID.
    public Employee findById(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(id)); // Lancia un'eccezione se non trovato.
    }

    // Metodo per salvare un nuovo impiegato, verificando prima l'unicità dell'email.
    public Employee save(EmployeeDTO body) {
        employeeRepository.findByEmail(body.email()).ifPresent(employee -> {
            throw new InvalidRequestException("Email " + employee.getEmail() + " is already used!"); // Verifica se l'email è già utilizzata.
        });
        Employee newEmployee = new Employee(body.username(), body.firstName(), body.lastName(), body.email());
        newEmployee.setAvatarImage("https://ui-avatars.com/api/?name=" + body.firstName() + "+" + body.lastName()); // Imposta un'immagine predefinita.
        return employeeRepository.save(newEmployee); // Salva l'impiegato nel database.
    }

    // Metodo per aggiornare un impiegato esistente.
    public Employee findByIdAndUpdate(long id, EmployeeDTO modifiedEmployee) {
        Employee found = findById(id); // Trova l'impiegato esistente.
        found.setUsername(modifiedEmployee.username()); // Aggiorna username.
        found.setFirstName(modifiedEmployee.firstName()); // Aggiorna nome.
        found.setLastName(modifiedEmployee.lastName()); // Aggiorna cognome.
        found.setAvatarImage("https://ui-avatars.com/api/?name=" + modifiedEmployee.firstName() + "+" + modifiedEmployee.lastName()); // Aggiorna immagine.
        return employeeRepository.save(found); // Salva le modifiche.
    }

    // Metodo per eliminare un impiegato tramite ID.
    public void findByIdAndDelete(long id) {
        Employee found = findById(id);
        employeeRepository.delete(found); // Elimina l'impiegato dal database.
    }

    // Metodo per cambiare l'immagine dell'avatar di un impiegato.
    public Employee changeAvatarImage(long id, MultipartFile image) throws IOException {
        Employee found = findById(id);
        String url = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url").toString();
        found.setAvatarImage(url); // Aggiorna l'URL dell'immagine dell'avatar.
        return employeeRepository.save(found); // Salva le modifiche.
    }
}
