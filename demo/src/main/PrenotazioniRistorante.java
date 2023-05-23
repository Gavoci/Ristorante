import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PrenotazioniRistorante {
    private LinkedList<Cliente> clienti = new LinkedList<>();

    public void aggiungiCliente(String nome, String cognome, String email, String telefono) {
        clienti.add(new Cliente(nome, cognome, email, telefono));
    }

    public void modificaCliente(String email, String nuovoNome, String nuovoCognome, String nuovoTelefono) {
        Predicate<Cliente> filtro = cliente -> cliente.getEmail().equals(email);
        clienti.stream().filter(filtro).findFirst().ifPresent(cliente -> {
            cliente.setNome(nuovoNome);
            cliente.setCognome(nuovoCognome);
            cliente.setEmail(email);
            cliente.setTelefono(nuovoTelefono);
        });
    }

    public void eliminaCliente(String email) {
        Predicate<Cliente> filtro = cliente -> cliente.getEmail().equals(email);
        clienti.removeIf(filtro);
    }

    public void aggiungiPrenotazione(String emailCliente, LocalDate dataInserimento, LocalDate dataPrenotazione, int coperti) {
        Predicate<Cliente> filtro = cliente -> cliente.getEmail().equals(emailCliente);
        clienti.stream().filter(filtro).findFirst().ifPresent(cliente -> {
            cliente.aggiungiPrenotazione(new Prenotazione(cliente, dataInserimento, dataPrenotazione, coperti));
        });
    }

    public void modificaPrenotazione(String emailCliente, LocalDate dataPrenotazione, int coperti) {
        Predicate<Cliente> filtro = cliente -> cliente.getEmail().equals(emailCliente);
        clienti.stream().filter(filtro).findFirst().ifPresent(cliente -> {
            Predicate<Prenotazione> filtroPrenotazione = prenotazione -> prenotazione.getDataPrenotazione().equals(dataPrenotazione);
            cliente.modificaPrenotazione(filtroPrenotazione, coperti);
        });
    }

    public void eliminaPrenotazione(String emailCliente, LocalDate dataPrenotazione) {
        Predicate<Cliente> filtro = cliente -> cliente.getEmail().equals(emailCliente);
        clienti.stream().filter(filtro).findFirst().ifPresent(cliente -> {
            Predicate<Prenotazione> filtroPrenotazione = prenotazione -> prenotazione.getDataPrenotazione().equals(dataPrenotazione);
            cliente.eliminaPrenotazione(filtroPrenotazione);
        });
    }

    public List<Prenotazione> ricercaPrenotazioniPerCliente(String emailCliente) {
        Predicate<Cliente> filtro = cliente -> cliente.getEmail().equals(emailCliente);
        return clienti.stream().filter(filtro).flatMap(cliente -> cliente.getPrenotazioni().stream()).collect(Collectors.toList());
    }

    public List<Prenotazione> ricercaPrenotazioniPerData(LocalDate dataPrenotazione) {
        Predicate<Prenotazione> filtro = prenotazione -> prenotazione.getDataPrenotazione().equals(dataPrenotazione);
        return clienti.stream().flatMap(cliente -> cliente.getPrenotazioni().stream()).filter(filtro).collect(Collectors.toList());
    }

    public int numeroCopertiPerData(LocalDate dataPrenotazione) {
        Predicate<Prenotazione> filtro = prenotazione -> prenotazione.getDataPrenotazione().equals(dataPrenotazione);
        return clienti.stream().flatMap(cliente -> cliente.getPrenotazioni().stream()).filter(filtro).mapToInt(Prenotazione::getCoperti).sum();
    }
    
    public int numeroCopertiInRange(LocalDate dataInizio, LocalDate dataFine) {
        Predicate<Prenotazione> filtro = prenotazione -> prenotazione.getDataPrenotazione().isAfter(dataInizio) && prenotazione.getDataPrenotazione().isBefore(dataFine);
        return clienti.stream().flatMap(cliente -> cliente.getPrenotazioni().stream()).filter(filtro).mapToInt(Prenotazione::getCoperti).sum();
    }

    public List<Cliente> elencoClientiOrdinatiPerPrenotazioni() {
        return clienti.stream().sorted(Comparator.comparingInt(cliente -> cliente.getPrenotazioni().size())).collect(Collectors.toList());
    }

}