package com.itis.multiplayer_game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Player {

    private double x, y, width, height;
    private Color color;
    private Rectangle rectangle;
    private String username;
    private BufferedReader input;
    private PrintWriter output;
    private Socket socket;

    public Player(){}

    public Player(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    public Player(double x, double y, double width, double height, Color color, Rectangle rectangle, String username) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.username = username;
        this.rectangle = rectangle;

        rectangle.setLayoutX(x);
        rectangle.setLayoutY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setFill(color);
    }

    public Rectangle createPlayer() {
        return rectangle;
    }

//    public void sendMessage() {
//        try {
//            output.write(username);
//            output.println();
//            output.flush();
//
//            Scanner scanner = new Scanner(System.in);
//            while (socket)
//        }
//    }




    public void moveX(double n) {
        x += n;
        rectangle.setLayoutX(x);
    }

    public void moveY(double n) {
        y += n;
        rectangle.setLayoutY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Insert username: ");
//        String username = scanner.nextLine();
//
//        Socket socket = new Socket("localhost", 8888);
//        Player player = new Player(socket,username);
//    }
}
