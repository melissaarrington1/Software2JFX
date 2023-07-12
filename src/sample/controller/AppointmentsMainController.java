package sample.controller;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DB.AppointmentQuery;
import sample.DB.CustomerQuery;
import sample.model.Appointment;
import sample.model.Customer;


public class AppointmentsMainController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TableView<Appointment> mainAppointmentsTable = new TableView<>();
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdCol;
    @FXML
    private TableColumn<Appointment, String> appointmentTitleCol;
    @FXML
    private TableColumn<Appointment, String> appointmentDescCol;
    @FXML
    private TableColumn<Appointment, String> appointmentTypeCol;
    @FXML
    private TableColumn<Appointment, String> appointmentLocationCol;
    @FXML
    private TableColumn<Appointment, Integer> appointmentContactCol;
    @FXML
    private TableColumn<Appointment, Integer> appointmentCustCol;
    @FXML
    private TableColumn<Appointment, Integer> appointmentUserCol;
    @FXML
    private TableColumn<Appointment, Timestamp> appointmentStartCol;
    @FXML
    private TableColumn<Appointment, Timestamp> appointmentEndCol;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;





    @FXML
    void onActionCreateAppointment(ActionEvent event) throws IOException {



        System.out.println("create appointment button clicked");
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/New_Appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyAppointment(ActionEvent event) throws IOException {
        System.out.println("modify appointment button clicked");
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/New_Customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainAppointmentsTable.setItems(AppointmentQuery.getAppointmentList());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentDescCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        appointmentCustCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentUserCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
    }

    public void onActionDeleteAppointment(ActionEvent actionEvent) {
    }
}
