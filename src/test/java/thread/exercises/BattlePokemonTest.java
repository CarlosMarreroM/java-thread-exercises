package thread.exercises;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BattlePokemonTest {

    @Test
    void BatallaPokemon_debeHaberGanador() {
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salida));

        BattlePokemon battle = new BattlePokemon();
        BattlePokemon.main(null);

        String textOut = salida.toString();
        assertTrue(textOut.contains("La batalla ha terminado."));
        assertTrue(BattlePokemon.isGameOver());
        assertTrue(battle.getHpCharmander() <= 0 || battle.getHpPikachu() <= 0);
    }
}
