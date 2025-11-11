package thread.exercises;

import java.util.Random;

public class MillenniumFalcon {
    private volatile boolean finished = false;
    private volatile boolean destroyed = false;
    private final int missionTimeMS = 4000;
    private long startTime;

    private volatile int speed = 0;
    private volatile int shields = 100;
    private final Random random = new Random();

    private class HanSolo implements Runnable {
        @Override
        public void run() {
            while (!finished) {
                speed += random.nextInt(11) + 5;

                if (random.nextInt(100) < 5) { // 5% probabilidad
                    destroyed = true;
                    finished = true;
                    System.out.println("Fallo de hiperimpulsor. ¡La nave se destruye!");
                }

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (System.currentTimeMillis() - startTime >= missionTimeMS) {
                    finished = true;
                }
            }
        }
    }

    private class Chewbacca implements Runnable {
        @Override
        public void run() {
            while (!finished) {
                shields += random.nextInt(16) - 10;

                if (shields <= 0) {
                    destroyed = true;
                    finished = true;
                    System.out.println("¡Escudos agotados! ¡La nave se destruye!");
                }

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (System.currentTimeMillis() - startTime >= missionTimeMS) {
                    finished = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        MillenniumFalcon mission = new MillenniumFalcon();
        mission.startTime = System.currentTimeMillis();

        Thread han = new Thread(mission.new HanSolo());
        Thread chewie = new Thread(mission.new Chewbacca());

        han.start();
        chewie.start();

        try {
            han.join();
            chewie.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (!mission.destroyed) {
            System.out.println("¡Han y Chewie escapan! Vel=" + mission.speed + ", Escudos=" + mission.shields);
        }
    }
}
