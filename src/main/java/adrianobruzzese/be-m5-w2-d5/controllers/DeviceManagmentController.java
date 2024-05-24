package adrianobruzzese.be-m5-w2-d5.controllers;

import adrianobruzzese.be-m5-w2-d5.entities.Device;
import adrianobruzzese.be-m5-w2-d5.exceptions.BadRequestException;
import adrianobruzzese.be-m5-w2-d5.payloads.DeviceDTO;
import adrianobruzzese.be-m5-w2-d5.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// Definisco il controller con l'annotazione @RestController e specifico il percorso base per tutte le richieste gestite da questo controller
@RestController
@RequestMapping("/devices")
public class DeviceManagementController {

    // Iniezione del servizio DeviceService tramite l'annotazione @Autowired
    @Autowired
    private DeviceService deviceService;

    // Metodo per ottenere tutti i dispositivi, con paginazione e ordinamento.
    @GetMapping
    public Page<Device> getAllDevices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        return this.deviceService.findAll(page, size, sort);
    }

    // Metodo per ottenere un dispositivo specifico tramite il suo ID
    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable long id) {
        return this.deviceService.findById(id);
    }

    // Metodo per creare un nuovo dispositivo. valida il corpo della richiesta e gestisce eventuali errori di validazione
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveNewDevice(@RequestBody @Validated DeviceDTO body, BindingResult validation){
        if(validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.deviceService.save(body);
    }

    // Metodo per aggiornare un dispositivo esistente tramite ID, gestisce anche la validazione del corpo della richiesta
    @PutMapping("/{id}")
    public Device findDeviceByIdAndUpdate(@PathVariable long id, @RequestBody @Validated DeviceDTO body, BindingResult validation){
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.deviceService.findByIdAndUpdate(id, body);
    }

    // Metodo per eliminare un dispositivo tramite ID, non restituisce contenuto
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.deviceService.findByIdAndDelete(id);
    }
}
