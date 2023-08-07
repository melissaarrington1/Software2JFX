package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import sample.DB.AppointmentQuery;
import sample.DB.UserQuery;
import sample.model.Appointment;

import static sample.DB.UserQuery.*;

/**
 * Login Class
 * @author Melissa Arrington
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

    ResourceBundle rb = ResourceBundle.getBundle("sample/resources/Nat", Locale.getDefault());

    Stage stage;
    Parent scene;

    /**
     * Method that sets initial login info.
     * Will change language from English to French, depending on the locale of the user
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rb = ResourceBundle.getBundle("sample/resources/Nat", Locale.getDefault());
        ZoneId zoneId = ZoneId.systemDefault();
        String location = ZoneId.systemDefault().toString();
        //title.setText(rb.getString("title"));
        title.setText(rb.getString("appointmentscheduler"));
        langLabel.setText(location);
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        loginBtn.setText(rb.getString("login"));
        exitButton.setText(rb.getString("exit"));
        
        Locale france = new Locale("fr", "FR");
        Locale espanol = new Locale("es", "ES");



//        if(Locale.getDefault().getLanguage().equals("de") || Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("fr"))
//        System.out.println(rb.getString("hello") + " " + rb.getString("world"));
    }

    /**
     * Method for button that logs into application.
     * Includes validation for username/password.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
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
             recordLogin(false);
             Alert langAlert = new Alert(Alert.AlertType.ERROR, rb.getString("langAlert"));
            langAlert.showAndWait();
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
             }
            if(!apptStatus) {
                 Alert alert = new Alert(Alert.AlertType.WARNING, rb.getString("apptAlert"));
                 alert.showAndWait();
             }
             System.out.println("login button clicked");
            recordLogin(true);
             stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
             scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main2.fxml"));
             stage.setScene(new Scene(scene));
             stage.centerOnScreen();
             stage.show();
         }
    }
    
    /**
     * Method for recording the dates/times that each user tries to log in.
     * Record is user was successful or unsuccessful in logging in.
     * @throws IOException
     */
    public void recordLogin(boolean loginSuccess) throws IOException {
        LocalDateTime currentTime = LocalDateTime.now();
        //boolean loginSuccess = false;
        FileWriter f = new FileWriter("loginActivity.txt", true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyy hh:mm:ss");
        ZoneId zone = ZoneId.systemDefault();

        if(loginSuccess) {
            f.write(usernameField.getText() + "has logged in on " + formatter.format(currentTime) + "\n");
            //return "logins.text";
        }
        else {
            f.write(usernameField.getText() + "attempted to login but failed on " + formatter.format(currentTime) + "\n");
        }
        f.write("\n");
        f.close();
    }

    /**
     * Method for exiting applications
     * @param actionEvent
     */
    public void onActionExit(ActionEvent actionEvent) {
        System.out.println("exit clicked");
        Alert alert = new Alert(Alert.AlertType.WARNING, rb.getString("exitApp"));
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK) {
            System.out.println("exiting");
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.close();


        }
        else if(alert.getResult() == ButtonType.CANCEL) {
            System.out.println("cancel");
            alert.close();
        }

    }
}
