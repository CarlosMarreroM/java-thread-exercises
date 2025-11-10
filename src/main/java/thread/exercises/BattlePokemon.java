package thread.exercises;

import java.util.Random;

/**
 * Simula una batalla por turnos entre Pikachu y Charmander utilizando hilos.
 * Cada Pokemon ataca en su turno, infligiendo daño aleatorio al oponente.
 * 
 */
public class BattlePokemon {
    private static volatile boolean gameOver = false;
    private static int hpPikachu = 100;
    private static int hpCharmander = 100;
    private String turn = "Pikachu";
    private final Object lock = new Object();
    private final Random random = new Random();

    public int getHpPikachu() {
        return hpPikachu;
    }

    public int getHpCharmander() {
        return hpCharmander;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    private void attack(String attacker, String target) {
        int damage = random.nextInt(16) + 5;
        int hpTarget = 0;

        if (target.equals("Charmander")) {
            hpTarget = hpCharmander -= damage;
            if (hpCharmander < 0)
                hpCharmander = 0;
        } else {
            hpTarget = hpPikachu -= damage;
            if (hpPikachu < 0)
                hpPikachu = 0;
        }

        System.out.println(attacker + " ataca con " + damage + " de daño. HP rival: " + hpTarget);

        if (hpTarget <= 0 && !gameOver) {
            gameOver = true;
            System.out.println(attacker + " Ha ganado la batalla!");
        }

        try {
            Thread.sleep(random.nextInt(400) + 200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected class ThreadPikachu implements Runnable {

        @Override
        public void run() {
            while (!gameOver) {
                synchronized (lock) {
                    while (!turn.equals("Pikachu") && !gameOver) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    if (gameOver)
                        break;

                    attack("Pikachu", "Charmander");
                    turn = "Charmander";
                    lock.notifyAll();
                }
            }
        }
    }

    protected class ThreadCharmander implements Runnable {

        @Override
        public void run() {
            while (!gameOver) {
                synchronized (lock) {
                    while (!turn.equals("Charmander") && !gameOver) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    if (gameOver)
                        break;

                    attack("Charmander", "Pikachu");
                    turn = "Pikachu";
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        BattlePokemon battle = new BattlePokemon();

        Thread pikachu = new Thread(battle.new ThreadPikachu());
        Thread charmander = new Thread(battle.new ThreadCharmander());

        pikachu.start();
        charmander.start();

        try {
            pikachu.join();
            charmander.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("La batalla ha terminado.");
        System.out.println(battle.getHpPikachu());
        System.out.println(battle.getHpCharmander());
    }
}
