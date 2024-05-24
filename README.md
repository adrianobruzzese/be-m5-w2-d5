# Nome del Progetto

## Descrizione
Questo progetto è una piattaforma per gestire l'assegnazione di dispositivi aziendali (es. smartphone, tablet, laptop) ai dipendenti. Il sistema è costruito utilizzando Spring Boot e un database relazionale per memorizzare le informazioni sui dipendenti e sui dispositivi.

## Struttura del Progetto
Il progetto è suddiviso in diverse componenti principali, organizzate in pacchetti all'interno dell'applicazione:

### Entità
- `Employee`: Rappresenta i dettagli di un dipendente, inclusi username, nome, cognome, email e immagine del profilo.
- `Device`: Rappresenta un dispositivo che può essere assegnato ai dipendenti. Include campi per tipo, stato e l'assegnazione al dipendente.

### Repository
- `EmployeeRepository`: Interfaccia per l'accesso ai dati dei dipendenti.
- `DeviceRepository`: Interfaccia per l'accesso ai dati dei dispositivi.

### Servizi
- `EmployeeService`: Servizi per gestire le operazioni sui dati dei dipendenti.
- `DeviceService`: Servizi per gestire le operazioni sui dati dei dispositivi.

### Controller
- `EmployeeController`: Endpoint API per operazioni CRUD sui dipendenti.
- `DeviceController`: Endpoint API per operazioni CRUD sui dispositivi.

### Configurazione
- `ServerConfig`: Configurazione del server e altri beans necessari per l'applicazione.

## Funzionalità
- Gestione dei dipendenti e dei dispositivi tramite API RESTful.
- Caricamento e gestione delle immagini dei profili dei dipendenti.
- Assegnazione e tracciamento dello stato dei dispositivi.

## Tecnologie Utilizzate
- Spring Boot
- Java
- PostgreSQL
- Maven
- Cloudinary (per la gestione delle immagini)

## Come eseguire il progetto
Per eseguire questo progetto, seguire questi passi:
1. Clonare il repository: `git clone URL_DEL_REPOSITORY`
2. Entrare nella directory del progetto: `cd NOME_DIRECTORY`
3. Avviare l'applicazione: `./mvnw spring-boot:run`

## Licenza
Questo progetto è rilasciato sotto la licenza [INSERIRE_TIPO_DI_LICENZA].

## Autore
[Il tuo nome] - [Il tuo contatto email]

