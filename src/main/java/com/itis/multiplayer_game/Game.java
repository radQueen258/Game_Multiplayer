package com.itis.multiplayer_game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH / ROWS;


    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;


    private GraphicsContext gc;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player[] players = new Player[] {player1, player2, player3};
    private Image fruitImage;
    private Image defaultImage;
    private int fruitX;
    private int fruitY;
    private boolean gameOver;
    private int currentDirection;
    private int score = 0;
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Snake");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        gc = canvas.getGraphicsContext2D();


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (currentDirection != LEFT) {
                        currentDirection = RIGHT;
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (currentDirection != RIGHT) {
                        currentDirection = LEFT;
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (currentDirection != DOWN) {
                        currentDirection = UP;
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (currentDirection != UP) {
                        currentDirection = DOWN;
                    }
                }
            }
        });

        fruitImage = new Image(getClass().getResourceAsStream("peach.png"));
        generateFruit();
    }


    private void run(GraphicsContext gc) {
        drawBackground(gc);
        drawFruit(gc);

        switch (currentDirection) {
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
        }

    }

    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i ++) {
            for (int j = 0; j < COLUMNS; j ++) {
                if ((i + j) % 2 ==  0) {
                    gc.setFill(Color.GRAY);
                } else {
                    gc.setFill(Color.GOLD);
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void generateFruit() {
        //ONLY ONE TYPE OF FRUIT IS GENERATED, MAKE IT RANDOM FRUITS AT A TIME
        fruitX = (int) (Math.random() * ROWS);
        fruitY = (int) (Math.random() * COLUMNS);

    }

    private void drawFruit(GraphicsContext gc) {
        gc.drawImage(fruitImage, fruitX * SQUARE_SIZE,fruitY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }



//    private void moveRight1() {
//        player1.moveX(1);
//    }
//    private void moveLeft1() {
//        player1.moveX(-1);
//    }
//    private void moveUp1() {
//        player1.moveY(1);
//    }
//    private void moveDown1() {
//        player1.moveY(-1);
//    }

    private void moveRight() {
        for (int i = 0; i < players.length; i ++) {
            players[i].moveX(1);
        }
    }
    private void moveLeft() {
        for (int i = 0; i < players.length; i ++) {
            players[i].moveX(-1);
        }
    }
    private void moveUp() {
        for (int i = 0; i < players.length; i ++) {
            players[i].moveY(1);
        }
    }
    private void moveDown() {
        for (int i = 0; i < players.length; i ++) {
            players[i].moveY(-1);
        }
    }




//    private void moveRight2() {
//        player2.moveX(1);
//    }
//    private void moveLeft2() {
//        player2.moveX(-1);
//    }
//    private void moveUp2() {
//        player2.moveY(1);
//    }
//    private void moveDown2() {
//        player2.moveY(-1);
//    }


//    private void moveRight3() {
//        player3.moveX(1);
//    }
//    private void moveLeft3() {
//        player3.moveX(-1);
//    }
//    private void moveUp3() {
//        player3.moveY(1);
//    }
//    private void moveDown3() {
//        player3.moveY(-1);
//    }




    public static void main(String[] args) {
        launch();
    }
}
