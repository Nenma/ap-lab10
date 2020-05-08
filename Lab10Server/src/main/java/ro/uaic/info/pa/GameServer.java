package ro.uaic.info.pa;

import ro.uaic.info.pa.optional.Board;
import ro.uaic.info.pa.optional.Game;
import ro.uaic.info.pa.optional.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Singleton handling client connections by starting a Go game
 * only after a pair of client has connected and treating their
 * game actions in a separate thread, thus handling multiple games
 * at the same time
 */
public enum GameServer {
    INSTANCE;

    private static final int PORT = 2048;
    private static final int WHITE = 1;
    private static final int BLACK = 2;
    private ServerSocket serverSocket = null;

    public ServerSocket getSocket() {
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("[server] Waiting for clients...");

                Socket socket1 = serverSocket.accept();
                System.out.println("[server] Accepted client " + socket1.getPort() + " (1/2)");
                Socket socket2 = serverSocket.accept();
                System.out.println("[server] Accepted client " + socket2.getPort() + " (2/2)");

                System.out.println("[server] Starting new game...");
                Board board = new Board();
                Player white = new Player(socket1, WHITE, null, board);
                Player black = new Player(socket2, BLACK, null, board);
                white.setOpponent(black);
                black.setOpponent(white);

                Game game = new Game(board, white, black);
                new ClientThread(game).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return serverSocket;
    }
}
