package adrianobruzzese.be-m5-w2-d5.controllers;

import adrianobruzzese.be-m5-w2-d5.entities.Employee;
import adrianobruzzese.be-m5-w2-d5.exceptions.BadRequestException;
import adrianobruzzese.be-m5-w2-d5.payloads.EmployeeDTO;
import adrianobruzzese.be-m5-w2-d5.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/employees")
public class EmployeeManagementController {

    // Iniezione del servizio EmployeeService tramite l'annotazione @Autowired;
    @Autowired
    private EmployeeService employeeService;

    // Metodo per ottenere tutti gli impiegat, con paginazione e ordinamento.
    @GetMapping
    public Page<Employee> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        return this.employeeService.findAll(page, size, sort);
    }

    // Metodo per ottenere un impiegato specifico tramite il suo ID.
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return this.employeeService.findById(id);
    }

    // Metodo per creare un nuovo impiegato; valida il corpo della richiesta e gestisce eventuali errori di validazione
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveNewEmployee(@RequestBody @Validated EmployeeDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.employeeService.save(body);
    }

    // Metodo per aggiornare un impiegato esistente tramite ID. Gestisce anche la validazione del corpo della richiesta.
    @PutMapping("/{id}")
    public Employee findByIdAndUpdate(@PathVariable long id, @RequestBody @Validated EmployeeDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.employeeService.findByIdAndUpdate(id, body);
    }
    // Metodo per caricare l'immagine del profilo di un impiegato
    @PatchMapping("/{id}/upload")
    public Employee uploadAvatar(@PathVariable long id, @RequestParam("avatar") MultipartFile image) throws IOException {
        return this.employeeService.changeAvatarImage(id, image);
    }

    // Metodo per eliminare un impiegato tramite ID, non restituisce contenuto
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable long id) {
        this.employeeService.findByIdAndDelete(id);
    } }


