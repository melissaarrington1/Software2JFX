package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateTimeStringConverter;
import sample.DB.AppointmentQuery;
import sample.DB.ContactQuery;
import sample.DB.CustomerQuery;
import sample.DB.UserQuery;
import sample.model.*;
import sample.utility.TimeHelper;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class NewAppointmentController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TextField appointmentIDField;
    @FXML
    private TextField appointmentTitleField;
    @FXML
    private TextField appointmentDescField;
    @FXML
    private TextField appointmentTypeField;
    @FXML
    private TextField appointmentLocationField;
    @FXML
    private ComboBox<Contact> appointmentContactCombo;
    @FXML
    private ComboBox<Customer> appointmentCustCombo;
    @FXML
    private ComboBox<User> appointmentUserCombo;
    @FXML
    private DatePicker appointmentStartDate;
    @FXML
    private DatePicker appointmentEndDate;
    @FXML
    private ComboBox<LocalTime> appointmentStartTimeCombo;
    @FXML
    private ComboBox<LocalTime> appointmentEndTimeCombo;

    /**
     * Button for Canceling without creating a new appointment.
     * @param event
     * @throws IOException
     */
    public void onActionCancel(ActionEvent event) throws IOException {
        System.out.println("create appointment button clicked");
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentContactCombo.setItems(ContactQuery.getAllContacts());
        appointmentCustCombo.setItems(CustomerQuery.getCustomerList());
        appointmentUserCombo.setItems(UserQuery.getUserList());

        appointmentStartTimeCombo.setItems(TimeHelper.getStartTimes());
        appointmentEndTimeCombo.setItems(TimeHelper.getEndTimes());
    }


    public void onActionSaveAppointment(ActionEvent actionEvent) throws IOException {
        try {
            String appointmentTitle = appointmentTitleField.getText();
            String appointmentDescription = appointmentDescField.getText();
            String appointmentLocation = appointmentLocationField.getText();
            String appointmentType = appointmentTypeField.getText();

            Contact contact = appointmentContactCombo.getValue();
            int contactId = contact.getContactId();

            Customer customer = appointmentCustCombo.getValue();
            int customerId = customer.getId();

            User user = appointmentUserCombo.getValue();
            int userId = user.getUserId();


            System.out.println("clicked appointment");

            if(contact == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Contact cannot be blank.");
                return;
            }
            if(customer == null) {
               // Alert alert = new Alert();
                return;
            }
            if(user == null) {
                //Alert alert = new Alert();
                return;
            }

            LocalDateTime start = LocalDateTime.of(appointmentStartDate.getValue(), appointmentStartTimeCombo.getValue());
            LocalDateTime end = LocalDateTime.of(appointmentEndDate.getValue(), appointmentEndTimeCombo.getValue());

            // todo: check that end is after start

            //todo: check if within business hours (fill combo box list with times - limit to business hours) 2 private lists (start/end + getters) and method to load lists

            //todo: check for appointment overlap
            AppointmentQuery.addAppointment(appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, contactId, customerId, userId, start, end);

        }
        catch(NullPointerException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    public void onActionSave(ActionEvent actionEvent) {
    }
}
