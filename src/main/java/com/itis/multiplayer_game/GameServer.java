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
//                Socket socket = serverSocket.accept();
//                System.out.println("New Player");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void acceptPlayers() {
//        try {
//            System.out.println("Waiting for more players...");
//
//            while (numPlayers < maxPlayers) {
//                Socket socket = serverSocket.accept();
//                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//
//                numPlayers ++;
//                dataOutputStream.writeInt(numPlayers);
//                System.out.println("Player #" + numPlayers + " has connected");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(1234);
        GameServer gameServer = new GameServer(serverSocket);
        gameServer.startServer();
    }
}
