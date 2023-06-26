package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DB.CustomerQuery;
import sample.DB.JDBC;
import sample.model.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
        primaryStage.setTitle("Appointment Management");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        //launch(args);
        LocalDateTime cDate = LocalDateTime.now();
        Timestamp uDate = Timestamp.valueOf(LocalDateTime.now());
        int rowsAffected = CustomerQuery.insert("dad", "yankee", "33333", "4044444444", cDate, uDate, 12);
        if(rowsAffected > 0) {
            System.out.println("Insert successful");
        }
        else {
            System.out.println("Insert Failed!");
        }
        JDBC.closeConnection();
//        ResourceBundle rb = ResourceBundle.getBundle("/resources/Nat", Locale.getDefault());
//
//        if(Locale.getDefault().getLanguage().equals("de") || Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("fr"))
//            System.out.println(rb.getString("hello") + " " + rb.getString("world"));
    }
}
