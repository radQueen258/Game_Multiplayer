package com.itis.multiplayer_game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {

    private double x, y, width, height;
    private Color color;
    private Rectangle rectangle;
    private String username;

    public Player(){}

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
}
