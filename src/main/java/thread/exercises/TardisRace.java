package thread.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TardisRace {
    private static volatile boolean destinationReached = false;
    private static String winningEra = null;
    private final Random random = new Random();

    public boolean isDestinationReached() {
        return destinationReached;
    }

    public static String getWinningEra() {
        return winningEra;
    }

    private class TimeTravel implements Runnable {
        private String era;

        public TimeTravel(String era) {
            this.era = era;
        }

        @Override
        public void run() {
            int travelTime = random.nextInt(1501) + 500;

            try {
                Thread.sleep(travelTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (!destinationReached) {
                destinationReached = true;
                winningEra = era;
                System.out.println("La TARDIS llegó primero a " + era);
            }
        }
    }

    public static void main(String[] args) {
        String[] eras = { "Roma Antigua", "Futuro Lejano", "Era Victoriana", "Año 3000" };
        List<Thread> threads = new ArrayList<>();

        TardisRace race = new TardisRace();

        for (String era : eras) {
            Thread t = new Thread(race.new TimeTravel(era));
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Era ganadora: " + winningEra);
    }
}
