package thread.exercises;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class DroidsFactoryTest {
    @Test
    void testDroidsFactory() {
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salida));

        DroidsFactory factory = new DroidsFactory();
        factory.main(null);

        for (int k = 1; k <= factory.getDroidCount(); k++) {
            String ensamblado = "Ensamblado Droid-" + k;
            String activado = "Activao Droid-" + k;

            int idxE = salida.toString().indexOf(ensamblado);
            int idxA = salida.toString().indexOf(activado);

            assertTrue(idxE != -1, "No se encontró mensaje de ensamblado para " + ensamblado);
            assertTrue(idxA != -1, "No se encontró mensaje de activación para " + activado);
            assertTrue(idxE < idxA, "El droid " + k + " se activó antes de ser ensamblado.");
        }

        assertTrue(factory.getActivatedDroids() == 10,
            "El número de droides activados no coincide con el ensamblado. " + factory.getActivatedDroids());
    }
}
