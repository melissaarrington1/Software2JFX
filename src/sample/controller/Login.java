package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.*;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Login implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private Button onActionLoginBtn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Locale france = new Locale("fr", "FR");
        Locale espanol = new Locale("es", "ES");

//        Scanner keyboard = new Scanner(System.in);
//        System.out.println("enter a language, en or fr:    ");

        rb = ResourceBundle.getBundle("sample/resources/Nat", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("de") || Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("fr"))
        System.out.println(rb.getString("hello") + " " + rb.getString("world"));
    }

    @FXML
    void onActionLoginBtn(ActionEvent event) throws IOException {
        System.out.println("login button clicked");
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
