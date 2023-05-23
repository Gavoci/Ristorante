import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cliente {
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private List<Prenotazione> prenotazioni = new LinkedList<>();

    public Cliente(String nome, String cognome, String email, String telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void aggiungiPrenotazione(Prenotazione prenotazione) {
        prenotazioni.add(prenotazione);
    }

    public void modificaPrenotazione(Predicate<Prenotazione> filtro, int coperti) {
        prenotazioni.stream().filter(filtro).findFirst().ifPresent(prenotazione -> prenotazione.setCoperti(coperti));
    }

    public void eliminaPrenotazione(Predicate<Prenotazione> filtro) {
        prenotazioni.removeIf(filtro);
    }

    @Override
    public String toString() {
        return nome + " " + cognome + " (" + email + ")";
    }
}