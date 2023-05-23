import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

public class PrenotazioniRistoranteTest {

    @Test
    void testAggiungiCliente() {
        PrenotazioniRistorante prenotazioniRistorante = new PrenotazioniRistorante();
        prenotazioniRistorante.aggiungiCliente("Diego", "Gavoci", "diego@gmail.com", "1234567890");
        prenotazioniRistorante.aggiungiCliente("Mattia", "Masper", "mattia@gmail.com", "9876543210");
        List<Cliente> clienti = prenotazioniRistorante.elencoClientiOrdinatiPerPrenotazioni();
        Assertions.assertEquals(2, clienti.size());

        Cliente primoCliente = clienti.get(0);
        Assertions.assertEquals("Diego", primoCliente.getNome());
        Assertions.assertEquals("Gavoci", primoCliente.getCognome());
        Assertions.assertEquals("diego@gmail.com", primoCliente.getEmail());
        Assertions.assertEquals("1234567890", primoCliente.getTelefono());

        Cliente secondoCliente = clienti.get(1);
        Assertions.assertEquals("Mattia", secondoCliente.getNome());
        Assertions.assertEquals("Masper", secondoCliente.getCognome());
        Assertions.assertEquals("mattia@gmail.com", secondoCliente.getEmail());
        Assertions.assertEquals("9876543210", secondoCliente.getTelefono());
    }

    @Test
    void testModificaCliente() {
        PrenotazioniRistorante prenotazioniRistorante = new PrenotazioniRistorante();
        prenotazioniRistorante.aggiungiCliente("Diego", "Gavoci", "diego@gmail.com", "1234567890");
        prenotazioniRistorante.modificaCliente("diego@gmail.com", "Luigi", "Verdi", "0987654321");
        List<Cliente> clienti = prenotazioniRistorante.elencoClientiOrdinatiPerPrenotazioni();
        Assertions.assertEquals(1, clienti.size());
        Cliente cliente = clienti.get(0);
        Assertions.assertEquals("Luigi", cliente.getNome());
        Assertions.assertEquals("Verdi", cliente.getCognome());
        Assertions.assertEquals("diego@gmail.com", cliente.getEmail());
        Assertions.assertEquals("0987654321", cliente.getTelefono());
    }
}
