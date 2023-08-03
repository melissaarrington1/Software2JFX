package sample.controller;

import javafx.collections.ObservableList;
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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import sample.DB.AppointmentQuery;
import sample.DB.UserQuery;
import sample.model.Appointment;

import static sample.DB.UserQuery.*;

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
    @FXML
    private Button onActionLoginBtn;

    Stage stage;
    Parent scene;





    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rb = ResourceBundle.getBundle("sample/resources/Nat", Locale.getDefault());
        ZoneId zoneId = ZoneId.systemDefault();
        String location = ZoneId.systemDefault().toString();
        //title.setText(rb.getString("title"));

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


    public void onActionLoginBtn(ActionEvent event) throws IOException, SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a username.");
            alert.showAndWait();
            return;
        }
        if (password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a password.");
            alert.showAndWait();
            return;
        }
        //verifying if both username and password are correct
         if(!userLogin(username, password)){
            int userId = getUserId(username);
            ObservableList<Appointment> userAppointments = AppointmentQuery.getUserAppointments(userId);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username or password is incorrect.");
            alert.showAndWait();
            return;
        }
         else {
             int userId = getUserId(username);
             System.out.println("userId = " + userId + " username = " + username + ".");
             ObservableList<Appointment> userAppointments = AppointmentQuery.getUserAppointments(userId);
             System.out.println("number of appt retreived = " + userAppointments.size());
             LocalDateTime currentTime = LocalDateTime.now();
             ZonedDateTime conversionUTC = currentTime.atZone(ZoneId.systemDefault());
             LocalDateTime currentTime15Min = LocalDateTime.now().plusMinutes(15);

             boolean apptStatus = false;
             for (Appointment a : userAppointments) {
                 LocalDateTime start = a.getAppointmentStart();
                 System.out.println("current time = " + currentTime);
                 System.out.println("appt time = " + start);
                 System.out.println("current time 15 = " + currentTime15Min + "\n");
                 if (!(start.isBefore(currentTime) || start.isAfter(currentTime15Min))) {
                     Alert alert = new Alert(Alert.AlertType.WARNING, "There are current appointments within 15 minutes: id= " + a.getAppointmentId() + " at " + a.getAppointmentStart());
                     alert.showAndWait();
                     apptStatus = true;
                 }
//                 if((start.isAfter(currentTime) || start.isEqual(currentTime)) && ((start.isBefore(currentTime15Min) || start.isEqual(currentTime15Min)))) {
//                     Alert alert = new Alert(Alert.AlertType.WARNING, "There are current appointments within 15 minutes: id= " + a.getAppointmentId() + " at " + a.getAppointmentStart());
//                     alert.showAndWait();
//                     apptStatus = true;
//                 }
             }
            if(!apptStatus) {
                 Alert alert = new Alert(Alert.AlertType.WARNING, "There are no appointments starting within 15 minutes");
                 alert.showAndWait();
             }
             System.out.println("login button clicked");
             stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
             scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main2.fxml"));
             stage.setScene(new Scene(scene));
             stage.centerOnScreen();
             stage.show();
         }
    }


    public void onActionExit(ActionEvent actionEvent) {
        //Alert alert = new Alert(Alert.AlertType.WARNING, rb.getString("Cancel"));

    }
}
