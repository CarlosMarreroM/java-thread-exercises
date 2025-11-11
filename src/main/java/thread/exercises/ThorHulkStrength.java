package thread.exercises;

import java.util.Random;

/*
 * Simula una competencia de fuerza entre Thor y Hulk. Cada héroe
 * levanta pesos aleatorios en intervalos aleatorios durante un tiempo
 * determinado. Al final del tiempo, se determina quién levantó más peso.
 */
public class ThorHulkStrength {
    private final int durationMS = 5000;
    private volatile boolean timeUp = false;
    private volatile int totalThor = 0;
    private volatile int totalHulk = 0;
    private final Random random = new Random();

    private class Timer implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(durationMS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            timeUp = true;
            System.out.println("¡Tiempo!");
        }
    }

    private class Thor implements Runnable {
        @Override
        public void run() {
            while (!timeUp) {
                int weight = random.nextInt(16) + 5;
                totalThor += weight;
                try {
                    Thread.sleep(random.nextInt(71) + 50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private class Hulk implements Runnable {
        @Override
        public void run() {
            while (!timeUp) {
                int weight = random.nextInt(16) + 5;
                totalHulk += weight;
                try {
                    Thread.sleep(random.nextInt(71) + 50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThorHulkStrength competition = new ThorHulkStrength();

        Thread timer = new Thread(competition.new Timer());
        Thread thor = new Thread(competition.new Thor());
        Thread hulk = new Thread(competition.new Hulk());

        timer.start();
        thor.start();
        hulk.start();

        try {
            timer.join();
            thor.join();
            hulk.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (competition.totalThor > competition.totalHulk) {
            System.out.println("Thor gana con " + competition.totalThor + " vs " + competition.totalHulk);
        } else if (competition.totalHulk > competition.totalThor) {
            System.out.println("Hulk gana con " + competition.totalHulk + " vs " + competition.totalThor);
        } else {
            System.out.println("Empate: " + competition.totalThor);
        }
    }
}
