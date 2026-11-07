package com.example.bancodigital;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


import java.io.IOException;



public class HelloController {
    @FXML
    private void  trocar(ActionEvent event) throws IOException {
        FXMLLoader carregar = new FXMLLoader(
                getClass().getResource("dashboard.fxml")
        );

        Parent novaTela  = carregar.load();
        Node scene1 = (Node) event.getSource();
        Scene scene2 = scene1.getScene();
        scene2.setRoot(novaTela);


    }
}
