package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import sample.DB.AppointmentQuery;

/**
 * Login Class
 */
public class Login implements Initializable {
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    @FXML
    Label langLabel;
    @FXML
    Label usernameLabel;
    @FXML
    Label passwordLabel;
    @FXML
    Button loginBtn;
    @FXML
    Button exitButton;
    @FXML
    Label title;

    Stage stage;
    Parent scene;
    @FXML
    private Button onActionLoginBtn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rb = ResourceBundle.getBundle("sample/resources/Nat", Locale.getDefault());
        ZoneId zoneId = ZoneId.systemDefault();
        String location = ZoneId.systemDefault().toString();
        title.setText(rb.getString("title"));
        langLabel.setText(location);
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        loginBtn.setText(rb.getString("login"));
        exitButton.setText(rb.getString("exit"));


        Locale france = new Locale("fr", "FR");
        Locale espanol = new Locale("es", "ES");



        if(Locale.getDefault().getLanguage().equals("de") || Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("fr"))
        System.out.println(rb.getString("hello") + " " + rb.getString("world"));
    }

    @FXML
    void onActionLoginBtn(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a username.");
            alert.showAndWait();
            return;
        }
//        if (password.isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a password.");
//            alert.showAndWait();
//            return;
//        }

        System.out.println("login button clicked");
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main2.fxml"));
        stage.setScene(new Scene(scene));
        stage.centerOnScreen();
        stage.show();
    }
}
