package ro.uaic.info.pa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            String request = "";
            while (!request.equals("exit")) {
                if (request.equals("stop")) {
                    out.println("[server] Server stopped...");
                    out.flush();
                    System.out.println("[server] Shutting down client socket...");
                    socket.close();
                    break;
                } else {
                    request = in.readLine();
                    String response = "[server] Client has used the " + request + " command!";
                    out.println(response);
                    out.flush();
                }
            }
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
