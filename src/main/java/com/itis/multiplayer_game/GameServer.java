package com.itis.multiplayer_game;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket serverSocket;
    private int numPlayers;
    private int maxPlayers;

    public GameServer(ServerSocket serverSocket) {
        System.out.println("===== GAME SERVER =====");
        numPlayers = 0;
        maxPlayers = 3;
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                while (numPlayers < maxPlayers) {
                    Socket socket = serverSocket.accept();
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    numPlayers ++;
                    dataOutputStream.writeInt(numPlayers);
                    System.out.println("Player #" + numPlayers + " has connected");
                    PlayerHandler playerHandler = new PlayerHandler(socket);

                    Thread thread = new Thread(playerHandler);
                    thread.start();
                }
            }
            System.out.println("No longer accepting connections");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(8888);
        GameServer gameServer = new GameServer(serverSocket);
        gameServer.startServer();
    }
}
