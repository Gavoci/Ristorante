import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Prenotazione {
    private Cliente cliente;
    private LocalDate dataInserimento;
    private LocalDate dataPrenotazione;
    private int coperti;

    public Prenotazione(Cliente cliente, LocalDate dataInserimento, LocalDate dataPrenotazione, int coperti) {
        this.cliente = cliente;
        this.dataInserimento = dataInserimento;
        this.dataPrenotazione = dataPrenotazione;
        this.coperti = coperti;
        cliente.aggiungiPrenotazione(this);
    }
    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataInserimento() {
        return dataInserimento;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public int getCoperti() {
        return coperti;
    }

    public void setCoperti(int coperti) {
        this.coperti = coperti;
    }

    @Override
    public String toString() {
        return cliente + " - " + dataPrenotazione + " - " + coperti + " coperti";
    }
}