package ro.uaic.info.pa;

import java.net.ServerSocket;

public class Lab10 {
    public static void main(String[] args) {
        ServerSocket server = GameServer.INSTANCE.getSocket();
    }
}
