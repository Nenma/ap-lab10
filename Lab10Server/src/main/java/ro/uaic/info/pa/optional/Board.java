package ro.uaic.info.pa.optional;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Class simulating a 15x15 Go board
 */
public class Board {

    private Map<Pair<Character, Character>, Integer> board;
    private String whoseTurn = "White";

    /**
     * Initialize the map with 0 on all positions, meaning all combinations
     * (a,a), (a,b), ..., (a,m), ..., (m ,a), (m, b), ..., (m, m)
     */
    public Board() {
        board = new HashMap<>();
        for (int i = 97; i <= 109; ++i) {
            for (int j = 97; j <= 109; ++j) {
                board.put(new Pair<>((char)i, (char)j), 0);
            }
        }
    }

    /**
     * @return The current contents of the board map as a String in a format
     * as a 15x15 matrix with letter a-m across the left and top sides
     */
    public String getStatus() {
        StringBuilder status = new StringBuilder();
        for (int i = 97; i <= 109; ++i) {
            status.append("   ").append((char) i);
        }
        status.append('\n');
        for (int i = 97; i <= 109; ++i) {
            status.append((char)i).append("  ");
            for (int j = 97; j <= 109; ++j) {
                status.append(board.get(new Pair<>((char)i, (char)j))).append("   ");
            }
            status.append('\n');
        }
        return status.toString();
    }

    public boolean isFull() {
        return !board.containsValue(0);
    }

    /**
     * Synchronized method that handles the player turns and piece board placement,
     * as well as player status messages sent across their respective sockets
     * @param player The Player that is currently taking their turn
     * @param row The row on which to place the piece
     * @param column The column on which to place the piece
     */
    public synchronized void placePiece(Player player, char row, char column) {
        if (player.getName().equals(whoseTurn)) {
            Pair<Character, Character> pos = new Pair<>(row, column);
            board.put(pos, player.getColour());
            player.getOpponent().getOut().println("[server] " + player.getName() + " placed a piece on " + row + column);
            player.getOpponent().getOut().flush();
            System.out.println("[server] " + player.getName() + " placed a piece on " + row + column);
            if (player.getName().equals("White")) {
                whoseTurn = "Black";
            } else {
                whoseTurn = "White";
            }
            notifyAll();
        } else {
            try {
                player.getOut().println("[server] Waiting for " + player.getOpponent().getName() + "'s move...");
                player.getOut().flush();
                System.out.println("[server] Waiting for " + player.getOpponent().getName() + "'s move...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    public Map<Pair<Character, Character>, Integer> getBoard() {
        return board;
    }
}
