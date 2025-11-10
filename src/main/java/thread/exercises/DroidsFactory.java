package thread.exercises;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Simula una fábrica de droides donde un hilo los ensambla
 * y otro hilo los activa. Usa una BlockingQueue para coordinar
 * la producción y el consumo sin necesidad de sincronización manual.
 */
public class DroidsFactory {
    private BlockingQueue<String> assembly = new LinkedBlockingQueue<>();
    int droidCount = 10;
    private volatile int activatedDroids = 0;
    private final Random random = new Random();

    DroidsFactory() {}

    private class Assembler implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= droidCount; i++) {
                try {
                    Thread.sleep(random.nextInt(200) + 100);
                    String doridString = "Droid-" + i;
                    System.out.println("Ensamblado " + doridString);
                    assembly.put(doridString);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private class Activator implements Runnable {

        @Override
        public void run() {
            int count = 0;

            while (count < droidCount) {
                try {
                    String droid = assembly.take();
                    System.out.println("Activado " + droid);
                    count++;
                    activatedDroids++;
                    Thread.sleep(random.nextInt(300) + 50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        DroidsFactory factory = new DroidsFactory();

        Thread ensamblador = new Thread(factory.new Assembler());
        Thread activador = new Thread(factory.new Activator());

        ensamblador.start();
        activador.start();

        try {
            ensamblador.join();
            activador.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Todos los droides han sido ensamblados y activados. Total activados: " + factory.activatedDroids);
    }

}
