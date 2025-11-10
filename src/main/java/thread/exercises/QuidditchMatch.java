package thread.exercises;

import java.util.Random;

/**
 * Simula un partido de Quidditch entre dos equipos con cazadores
 * y buscadores. Los cazadores anotan puntos y los buscadores
 * intentan atrapar la snitch dorada para finalizar el partido.
 */
public class QuidditchMatch {
    private static volatile boolean snitchCaught = false;
    private int teamAPoints = 0;
    private int teamBPoints = 0;

    private final Object lock = new Object();
    private final Random random = new Random();

    private class chaserA implements Runnable {
        @Override
        public void run() {
            while (!snitchCaught) {
                try {
                    Thread.sleep(random.nextInt(300) + 200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                int gol = random.nextInt(2) * 10;

                if (gol > 0) {
                    synchronized (lock) {
                        if (snitchCaught)
                            break;
                        teamAPoints += gol;
                        System.out.println("Equipo A anota 10. Total A=" + teamAPoints);
                    }
                }
            }
        }
    }

    private class chaserB implements Runnable {
        @Override
        public void run() {
            while (!snitchCaught) {
                try {
                    Thread.sleep(random.nextInt(300) + 200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                int gol = random.nextInt(2) * 10;

                if (gol > 0) {
                    synchronized (lock) {
                        if (snitchCaught)
                            break;
                        teamBPoints += gol;
                        System.out.println("Equipo B anota 10. Total B=" + teamBPoints);
                    }
                }
            }
        }
    }

    private class seeker implements Runnable {
        @Override
        public void run() {
            while (!snitchCaught) {
                try {
                    Thread.sleep(random.nextInt(400) + 300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (random.nextInt(100) < 5) {
                    snitchCaught = true;
                    System.out.println("¡Snitch dorada atrapada!");
                }
            }
        }
    }

    public static void main(String[] args) {
        QuidditchMatch match = new QuidditchMatch();

        Thread chaserAThread = new Thread(match.new chaserA());
        Thread chaserBThread = new Thread(match.new chaserB());
        Thread seekerThread = new Thread(match.new seeker());

        chaserAThread.start();
        chaserBThread.start();
        seekerThread.start();

        try {
            chaserAThread.join();
            chaserBThread.join();
            seekerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Partido terminado. Resultado final: Equipo A " + match.teamAPoints + " - Equipo B "
                + match.teamBPoints);
    }
}
