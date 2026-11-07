package com.example.bancodigital;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override

    public void start(Stage stage) throws IOException {

        Font font = Font.loadFont(
                getClass().getResourceAsStream("/fonts/poppins.black.ttf" ) ,
                20);
        System.out.print(font);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("index.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("BancoDigital");

        stage.setScene(scene);
        stage.show();
    }
}
