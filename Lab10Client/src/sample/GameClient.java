package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 2048;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Board board = new Board();

    public GameClient() {
        initializeConnection();

        Scanner input = new Scanner(System.in);
        String command = "";
        while (!board.isFull() && !command.equals("exit")) {
            System.out.print("[Gomoku] Your turn: ");
            command = input.nextLine();
            out.println(command);
            out.flush();
            try {
                System.out.println(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
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

    private void initializeConnection() {
        try {
            socket = new Socket(ADDRESS, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Successfully connected!");
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
