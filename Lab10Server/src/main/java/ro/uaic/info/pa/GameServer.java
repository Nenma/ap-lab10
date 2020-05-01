package ro.uaic.info.pa;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public enum GameServer {
    INSTANCE;

    private static final int PORT = 2048;
    private ServerSocket serverSocket = null;

    public ServerSocket getSocket() {
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("[server] Waiting for client...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
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
