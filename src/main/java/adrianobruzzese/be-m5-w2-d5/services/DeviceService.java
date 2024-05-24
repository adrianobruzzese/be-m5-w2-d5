package adrianobruzzese.be-m5-w2-d5.services;

import adrianobruzzese.be-m5-w2-d5.entities.Device;
import adrianobruzzese.be-m5-w2-d5.exceptions.ElementNotFoundException;
import adrianobruzzese.be-m5-w2-d5.payloads.DeviceDTO;
import adrianobruzzese.be-m5-w2-d5.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private EmployeeService employeeService;

    // Metodo per recuperare tutte le entità Device in pagine.
    public Page<Device> findAll(int page, int size, String sort) {
        size = Math.min(size, 30);  // Limito la dimensione della pagina a 30 per evitare sovraccarico del server.
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort)); // Creo un oggetto pageable per organizzare la paginazione e l'ordinamento dei risultati.
        return deviceRepository.findAll(pageable); // Utilizzo il repository per trovare tutte le entità Device paginate.
    }

    // Metodo per trovare un singolo Device tramite il suo ID.
    public Device findById(long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(id)); // Se non trovo il Device, lancio un'eccezione personalizzata.
    }

    // Metodo per salvare un nuovo Device nel database.
    public Device save(DeviceDTO body) {
        // Creo un nuovo Device utilizzando i dati dal DTO e recupero l'Employee associato tramite il suo ID.
        Device newDevice = new Device(body.type(), body.status(), employeeService.findById(body.employeeId()));
        return deviceRepository.save(newDevice); // Salvo il Device nel database.
    }

    // Metodo per aggiornare un Device esistente.
    public Device findByIdAndUpdate(long id, DeviceDTO body) {
        Device found = findById(id); // Trovo il Device esistente
        // Aggiorno i campi del Device con i nuovi valori dal DTO.
        found.setType(body.type());
        found.setStatus(body.status());
        found.setEmployee(employeeService.findById(body.employeeId())); // Aggiorno l'employee associato
        return deviceRepository.save(found); // Salvo le modifiche nel db
    }

    // Metodo per eliminare un Device tramite il suo ID.
    public void findByIdAndDelete(long id) {
        Device found = findById(id); // Trovo il Device.
        deviceRepository.delete(found); // Elimino il Device dal db.
    }
}
