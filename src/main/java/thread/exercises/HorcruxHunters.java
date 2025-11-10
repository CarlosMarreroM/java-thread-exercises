package thread.exercises;

import java.util.Random;

/**
 * Clase que simula a los buscadores de Horrocruxes en un entorno multihilo.
 * Tres personajes (Harry, Hermione y Ron) buscan un Horrocrux en diferentes ubicaciones.
 * El primer personaje que encuentra el Horrocrux detiene la búsqueda de los demás.
 */
public class HorcruxHunters {
    private static volatile boolean foundHorcrux = false;
    private String winner;

    public String getWinner() {
        return winner;
    }

    public static boolean isFoundHorcrux() {
        return foundHorcrux;
    }

    private class Searcher implements Runnable {
        private String name;
        private String location;
        private Random random = new Random();

        public Searcher(String name, String location) {
            this.name = name;
            this.location = location;
        }

        @Override
        public void run() {
            int time = random.nextInt(1500) + 500;

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if(!foundHorcrux) {
                winner = name;
                foundHorcrux = true;
                System.out.println(winner + " encontro un Horrocrux en " + location + ". ¡Busqueda terminada!");
            }
        }
    }

    public static void main(String[] args) {
        HorcruxHunters hunt = new HorcruxHunters();

        Thread harry = new Thread(hunt.new Searcher("Harry", "Bosque Prohibido"));
        Thread hermione = new Thread(hunt.new Searcher("Hermione", "Biblioteca Antigua"));
        Thread ron = new Thread(hunt.new Searcher("Ron", "Mazmorras del castillo"));
    
        harry.start();
        hermione.start();
        ron.start();

        try {
            harry.join();
            hermione.join();
            ron.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
