package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DB.AppointmentQuery;
import sample.DB.ContactQuery;
import sample.DB.CustomerQuery;
import sample.DB.UserQuery;
import sample.model.*;
import sample.utility.TimeHelper;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AppointmentModifyController implements Initializable {
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

    public Appointment selectedAppointment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentContactCombo.setItems(ContactQuery.getAllContacts());
        appointmentCustCombo.setItems(CustomerQuery.getCustomerList());
        appointmentUserCombo.setItems(UserQuery.getUserList());
        appointmentStartTimeCombo.setItems(TimeHelper.getStartTimes());
        appointmentEndTimeCombo.setItems(TimeHelper.getEndTimes());
    }

    public void appointmentInfo(Appointment appointment) {
        try {
            selectedAppointment = appointment;
            appointmentIDField.setText(Integer.toString(this.selectedAppointment.getAppointmentId()));
            appointmentTitleField.setText(selectedAppointment.getAppointmentTitle());
            appointmentDescField.setText(selectedAppointment.getAppointmentDescription());
            appointmentTypeField.setText(selectedAppointment.getAppointmentType());
            appointmentLocationField.setText(selectedAppointment.getAppointmentLocation());

            for(Contact c: appointmentContactCombo.getItems()) {
                if(selectedAppointment.getAppointmentContact() == c.getContactId()) {
                    appointmentContactCombo.setValue(c);
                }
            }
            for(Customer cust: appointmentCustCombo.getItems()) {
                if(selectedAppointment.getAppointmentContact() == cust.getId()) {
                    appointmentCustCombo.setValue(cust);
                }
            }
            for(User u: appointmentUserCombo.getItems()) {
                if(selectedAppointment.getAppointmentContact() == u.getUserId()) {
                    appointmentUserCombo.setValue(u);
                }
            }



            appointmentStartDate.setValue(selectedAppointment.getAppointmentStart().toLocalDate());
            appointmentStartTimeCombo.setValue(selectedAppointment.getAppointmentStart().toLocalTime());





        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onActionSaveAppointment(ActionEvent actionEvent) throws IOException {
        try {
            int appointmentId = Integer.parseInt(appointmentIDField.getText());
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


            if(Appointment.checkOverlapAppt(appointmentId, customerId, start, end)) {
                System.out.println("appointment overlapping. ");
                Alert alert = new Alert(Alert.AlertType.ERROR, "There is already an existing appointment for this time slot. Please choose another time slot.");
                alert.showAndWait();
                return;
            }

            AppointmentQuery.updateAppointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, contactId, customerId, userId, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Customers_Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
