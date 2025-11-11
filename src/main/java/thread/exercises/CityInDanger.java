package thread.exercises;

import java.util.Random;

public class CityInDanger {
    private static volatile boolean amenazaNeutralizada = false;
    private String ganador = null;

    private final String[] zonasA = { "Norte", "Centro", "Este" };
    private final String[] zonasB = { "Oeste", "Sur" };

    private final Random random = new Random();

    public boolean isAmenazaNeutralizada() {
        return amenazaNeutralizada;
    }

    public String getGanador() {
        return ganador;
    }

    private class Superman implements Runnable {
        @Override
        public void run() {
            for (String zona : zonasA) {
                try {
                    Thread.sleep(random.nextInt(301) + 200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (amenazaNeutralizada)
                    break;

                System.out.println("Superman salvó " + zona);
            }

            if (!amenazaNeutralizada) {
                amenazaNeutralizada = true;
                ganador = "Superman";
                System.out.println("Amenaza neutralizada por Superman");
            }
        }
    }

    private class Batman implements Runnable {
        @Override
        public void run() {
            for (String zona : zonasB) {
                try {
                    Thread.sleep(random.nextInt(301) + 300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (amenazaNeutralizada)
                    break;

                System.out.println("Batman salvó " + zona);
            }

            if (!amenazaNeutralizada) {
                amenazaNeutralizada = true;
                ganador = "Batman";
                System.out.println("Amenaza neutralizada por Batman");
            }
        }
    }

    public static void main(String[] args) {
        CityInDanger ciudad = new CityInDanger();

        Thread superman = new Thread(ciudad.new Superman());
        Thread batman = new Thread(ciudad.new Batman());

        superman.start();
        batman.start();

        try {
            superman.join();
            batman.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Ganador: " + ciudad.ganador);
    }
}
