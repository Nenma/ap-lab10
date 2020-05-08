package ro.uaic.info.pa.optional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class simulating a Go player
 */
public class Player implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private int colour;
    private String name;
    private Board board;
    private Player opponent;

    public Player(Socket socket, int colour, Player opponent, Board board) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.colour = colour;
        this.opponent = opponent;
        this.board = board;
        if (colour == 1) {
            name = "White";
        } else {
            name = "Black";
        }
    }

    /**
     * Player thread action that mirrors the Client's application while loop and sends
     * the position commands to the Board; a command should be a combination of 2 letters
     * ranging from 'a' to 'm' (no validation right now)
     */
    @Override
    public void run() {
        out.println("[Gomoku] Successfully connected as " + name);
        out.flush();

        String command;
        while (!board.isFull()) {
            try {
                command = in.readLine();
                if (!command.equals("exit")) {
                    board.placePiece(this, command.charAt(0), command.charAt(1));
                } else {
                    System.out.println("[server] Client " + socket.getPort() + " left the game...");
                    out.println("[Gomoku] Left the game...");
                    out.flush();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public int getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return opponent;
    }

    public Board getBoard() {
        return board;
    }
}
