module com.itis.multiplayer_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.itis.multiplayer_game to javafx.fxml;
    exports com.itis.multiplayer_game;
}