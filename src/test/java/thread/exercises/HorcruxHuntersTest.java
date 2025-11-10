package thread.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

public class HorcruxHuntersTest {
    @Test
    void HorcruxHunters_Searcher() {
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salida));

        HorcruxHunters hunt = new HorcruxHunters();
        hunt.main(null);

        String textOut = salida.toString();

        assertTrue(HorcruxHunters.isFoundHorcrux());

        List<String> listWinner = List.of("Harry", "Hermione", "Ron");

        assertTrue(listWinner.contains(hunt.getWinner()), "Valor: " + hunt.getWinner());

        int ocurrencias = textOut.split("encontro un Horrocrux", -1).length - 1;

        assertEquals(1,ocurrencias);

    }
}
