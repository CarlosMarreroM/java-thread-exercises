package thread.exercises;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TardisRaceTest {

    @Test
    public void testTardis() {
        // Capturar la salida por consola
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TardisRace tardis = new TardisRace();
        tardis.main(null);

        String salida = outContent.toString();

        // Verificar que se haya alcanzado el destino
        assertTrue(tardis.isDestinationReached(), "El destino debería haber sido alcanzado.");

        // Verificar que hay una era ganadora
        assertNotNull(tardis.getWinningEra(), "Debe existir una era ganadora.");

        // Verificar que solo una era haya sido la primera en llegar
        long count = salida.lines().filter(line -> line.contains("llegó primero")).count();
        assertEquals(1, count, "Solo una era debería haber llegado primero.");
    }
}
