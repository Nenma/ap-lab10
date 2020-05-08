package sample;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Class simulating a 15x15 Go board
 */
public class Board {

    private Map<Pair<Character, Character>, Integer> board;

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

    public boolean placePiece(int colour, char row, char column) {
        Pair<Character, Character> pos = new Pair<>(row, column);
        if (board.get(pos) == 0) {
            board.put(pos, colour);
            return true;
        } else {
            return false;
        }
    }

    public Map<Pair<Character, Character>, Integer> getBoard() {
        return board;
    }
}
