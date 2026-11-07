package com.example.bancodigital;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class DashController {
    @FXML
    protected ImageView logo;
    @FXML
    protected TextField inputName;
    @FXML
    protected TextField inputPassword;
    @FXML
    protected Button dash_button;
    @FXML
    protected Button reButton;
    @FXML
    protected Label dashText;

    JSONObject jsonUsers = new JSONObject();








    @FXML
     public  void initialize() {
          Image image = new Image(getClass().getResourceAsStream("/imagens/capibaraBank.png"));
          logo.setFitHeight(100.0);
          logo.setFitHeight(100);
          logo.setPreserveRatio(true);
            logo.setImage(image);


     }




     public void loginDash(ActionEvent event)  throws  IOException{
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("C:\\Users\\igore\\IdeaProjects\\Banco DIgital\\src\\main\\java\\com\\example\\bancodigital\\capiBas\\database.json");
            try {
                JSONObject jsonUsersRead = (JSONObject) parser.parse(reader);
                if (jsonUsersRead.containsKey(inputName.getText())) {

                    JSONObject tempUser =  (JSONObject) jsonUsersRead.get(inputName.getText());

                    if (tempUser.get("password").equals(inputPassword.getText())) {
                        // CRIA UMA SESSÃO PRO USUÁRIO NO LOGIN
                        try(FileWriter writer = new FileWriter("C:\\Users\\igore\\IdeaProjects\\Banco DIgital\\src\\main\\java\\com\\example\\bancodigital\\capiBas\\database.json")) {
                            writer.write(tempUser.toJSONString());
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        // TROCAR PARA A PROXIMA TELA
                        FXMLLoader carregar = new FXMLLoader(getClass().getResource("hub.fxml"));
                        Parent newScreen = carregar.load();
                        Node scene1 = (Node) event.getSource();
                        Scene scene2 = scene1.getScene();
                        scene2.setRoot(newScreen);



                    }

                    else {
                        dashText.setText("Senha ou Usuários incorretos.");
                    }





                }

                else {
                    System.out.println("Usuário não existe.");
                }
            }

            catch (ParseException e) {
                e.printStackTrace();
            }


    }

     public void loginChangeDash() {
        // "voltar" para a tela de cadastro
            reButton.setOnAction(event -> {
                dash_button.setText("Cadastrar");
                dash_button.setOnAction(event1 -> {
                    dashCreateUser();

                });
                reButton.setText("Já possui uma conta? Entre");
                reButton.setOnAction(event3 -> {
                    loginChangeDash();
                });

            });

            // ação de login + botão para voltar ao cadastro ( TEXTO TROCADO )
         reButton.setText("ainda não possui uma conta? Cadastrar");
                dash_button.setText("Entrar");
                dash_button.setOnAction(event -> {
                    try {
                        loginDash(event);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                });

     }



     public void dashCreateUser()  {
            if ( inputName.getText().length() >= 20 && inputPassword.getText().length() >= 8) {
                JSONObject jsonUser = new JSONObject();
                jsonUser.put("name" , inputName.getText());
                jsonUser.put("password" , inputPassword.getText());
                jsonUser.put("saldo" , 0);
                jsonUsers.put(jsonUser.get(inputName),jsonUser);

                try(FileWriter writer = new FileWriter("C:\\Users\\igore\\IdeaProjects\\Banco DIgital\\src\\main\\java\\com\\example\\bancodigital\\capiBas\\database.json" )) {
                    writer.write(jsonUsers.toJSONString());
                    System.out.println("Usuário criado!");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }

            else {
                dashText.setText("Nome ou senha inválidos");
            }

            }
     }


