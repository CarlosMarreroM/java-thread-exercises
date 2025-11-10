package thread.exercises;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class JediExplorersTest {

    @Test
    public void testSoloUnGanador() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        JediExplorers.main(new String[] {});
        String salida = output.toString();

        assertTrue(JediExplorers.isClueFound(),
                "La pista debería haberse encontrado.");

        long ocurrencias = salida.lines()
                .filter(line -> line.contains("halló una pista"))
                .count();

        assertEquals(1, ocurrencias,
                "Solo un Jedi debe hallar la pista.");
    }
}
