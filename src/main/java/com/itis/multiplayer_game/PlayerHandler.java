package com.itis.multiplayer_game;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PlayerHandler implements Runnable{

    public static ArrayList<PlayerHandler> playerHandlers = new ArrayList<>();
    private Map<String, Double[]> playerCoordinates = new HashMap<>();
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private Player player;
    private String playerUsername;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public PlayerHandler (DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public PlayerHandler (DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }


    public PlayerHandler(Socket socket) {
        try {
            this.socket = socket;
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(socket.getOutputStream());
            this.playerUsername = input.readLine();
            this.player = new Player();
            playerHandlers.add(this);
            broadcastMessage("GAME: " + playerUsername + " connected!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        String messageFromPlayer;

        while (socket.isConnected()) {
            try {
                messageFromPlayer = input.readLine();
                broadcastMessage(messageFromPlayer);
                readFromPlayer();
                writeFromPlayer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void broadcastMessage (String messageToSend) {
        for (PlayerHandler playerHandlers : playerHandlers) {
            try {
                if (!playerHandlers.playerUsername.equals(playerUsername)) {
                    playerHandlers.output.write(messageToSend);
                    playerHandlers.output.println();
                    playerHandlers.output.flush();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readFromPlayer () throws IOException{

        System.out.println("RFC " + playerUsername + " Runnable Create");
        double x = dataInputStream.readDouble();
        double y = dataInputStream.readDouble();

        playerCoordinates.put(playerUsername, new Double[]{x, y});
    }

    public void writeFromPlayer() throws IOException {
        Double[] coordinates = getPlayerCoordinates(playerUsername);

        if (coordinates != null) {
            dataOutputStream.writeDouble(coordinates[0]);
            dataOutputStream.writeDouble(coordinates[1]);
            dataOutputStream.flush();
        }

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Double[] getPlayerCoordinates(String username) {
        return playerCoordinates.get(username);
    }

    public void sendStartMsg() {
        try {
            dataOutputStream.writeUTF("We have at least 3 players, GO!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
