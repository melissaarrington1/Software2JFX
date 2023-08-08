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

/**
 * New Appointment Class
 * Class for creating and saving a new appointments
 * @author Melissa Arrington
 */
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
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main2.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Method for initializing the ComboBox lists with information for creating an appointment.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentContactCombo.setItems(ContactQuery.getAllContacts());
        appointmentCustCombo.setItems(CustomerQuery.getCustomerList());
        appointmentUserCombo.setItems(UserQuery.getUserList());

        appointmentStartTimeCombo.setItems(TimeHelper.getStartTimes());
        appointmentEndTimeCombo.setItems(TimeHelper.getEndTimes());
    }

    /**
     * Method for saving a new appointment after filling out all required fields.
     * Calls method to check for an existing appointment.
     * Calls method to check for an overlapping appointment.
     * Included error handling if an appointment cannot be created.
     * @param actionEvent
     * @throws IOException
     */
    public void onActionSaveAppointment(ActionEvent actionEvent) throws IOException {
        try {
            String appointmentTitle = appointmentTitleField.getText();
            if(appointmentTitle.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter an appointment title.");
                alert.showAndWait();
                return;
            }
            String appointmentDescription = appointmentDescField.getText();
            if(appointmentDescription.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter an appointment description.");
                alert.showAndWait();
                return;
            }

            String appointmentLocation = appointmentLocationField.getText();
            if(appointmentLocation.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter an appointment location.");
                alert.showAndWait();
                return;
            }

            String appointmentType = appointmentTypeField.getText();
            if(appointmentType.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter an appointment type.");
                alert.showAndWait();
                return;
            }

            Contact contact = appointmentContactCombo.getValue();
            int contactId = contact.getContactId();
            if(contact == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a contact");
                alert.showAndWait();
                return;
            }

            Customer customer = appointmentCustCombo.getValue();
            int customerId = customer.getId();

            User user = appointmentUserCombo.getValue();
            int userId = user.getUserId();


            if(contact == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Contact cannot be blank.");
                alert.showAndWait();
                return;
            }
            if(customer == null) {
              Alert alert = new Alert(Alert.AlertType.WARNING, "Must have a customer to make an appointment");
                alert.showAndWait();
                return;
            }
            if(user == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Must have a user to make an appointment");
                alert.showAndWait();
                return;
            }

            LocalDateTime start = LocalDateTime.of(appointmentStartDate.getValue(), appointmentStartTimeCombo.getValue());
            if(start == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a start date to create an appointment");
                alert.showAndWait();
                return;
            }
            LocalDateTime end = LocalDateTime.of(appointmentEndDate.getValue(), appointmentEndTimeCombo.getValue());
            if(end == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an end date to create an appointment");
                alert.showAndWait();
                return;
            }

            if(Appointment.checkOverlapAppt(0, customerId, start, end)) {
                System.out.println("appointment overlapping. ");
                Alert alert = new Alert(Alert.AlertType.ERROR, "There is already an existing appointment for this time slot. Please choose another time slot.");
                alert.showAndWait();
                return;
            }

            AppointmentQuery.addAppointment(appointmentTitle, appointmentDescription, appointmentType, appointmentLocation, contactId, customerId, userId, start, end);

        }
        catch(NullPointerException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main2.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
