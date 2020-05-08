package ro.uaic.info.pa;

import ro.uaic.info.pa.optional.Game;

/**
 * Class that kickstarts a thread for each Client application's actions
 */
public class ClientThread extends Thread {

    private Game game;

    public ClientThread(Game game) {
        this.game = game;
    }

    public void run() {
        Thread white = new Thread(game.getPlayer1());
        white.setName(game.getPlayer1().getName());
        Thread black = new Thread(game.getPlayer2());
        black.setName(game.getPlayer2().getName());

        white.start();
        black.start();
    }
}
