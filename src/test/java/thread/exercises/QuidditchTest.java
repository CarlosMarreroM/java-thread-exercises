package thread.exercises;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class QuidditchTest {

    @Test
    public void testQuidditch() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        QuidditchMatch match = new QuidditchMatch();
        match.main(null);

        System.setOut(originalOut);
        String salida = outputStream.toString();

        assertTrue(salida.contains("¡Snitch dorada atrapada!"),
                "Debería aparecer el mensaje de que la snitch fue atrapada.");

        // Verificar que se imprimió el marcador final
        assertTrue(salida.contains("Resultado final:"),
                "Debería aparecer el marcador final al terminar el juego.");
    }
}
