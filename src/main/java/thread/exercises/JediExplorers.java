package thread.exercises;

import java.util.Random;

/**
 * Simula una exploración de planetas por parte de dos Jedis en busca de una
 * pista. El primer Jedi que encuentra la pista detiene la búsqueda del otro.
 */
public class JediExplorers {
    private static volatile boolean clueFound = false;
    private static volatile String winner = null;
    private final Random random = new Random();

    public static boolean isClueFound() {
        return clueFound;
    }

    private class Jedi implements Runnable {
        private String name;
        private String planet;

        public Jedi(String name, String planet) {
            this.name = name;
            this.planet = planet;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(1100) + 400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (!clueFound) {
                clueFound = true;
                winner = name;
                System.out.println(name + " halló una pista en " + planet + ". Fin de búsqueda.");
            }
        }
    }

    public static void main(String[] args) {
        JediExplorers exploration = new JediExplorers();

        Thread kenobi = new Thread(exploration.new Jedi("Kenobi", "Tatooine"));
        Thread skywalker = new Thread(exploration.new Jedi("Skywalker", "Dagobah"));

        kenobi.start();
        skywalker.start();

        try {
            kenobi.join();
            skywalker.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Exploración completada. Ganador: " + winner);
    }
}
