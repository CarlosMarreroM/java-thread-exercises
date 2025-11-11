package thread.exercises;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CityInDangerTest {
    @Test
    public void testSoloUnGanador() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        CityInDanger.main(null);
        String salida = output.toString();

        CityInDanger ciudad = new CityInDanger();

        assertTrue(ciudad.isAmenazaNeutralizada(),
                "La amenaza debería haberse neutralizado.");

        long ocurrencias = salida.lines()
                .filter(line -> line.contains("neutralizada por"))
                .count();

        assertEquals(1, ocurrencias,
                "Solo un héroe debe neutralizar la amenaza.");
    }
}
