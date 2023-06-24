package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the Add Customer class, which provides a FORM to create each CUSTOMER
 * @author Melissa Arrington
 */

public class NewCustomerController implements Initializable {
    Stage stage;
    Parent scene;

    //private Button
    @FXML
    private TextField customerIDField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerAddressField;
    @FXML
    private TextField customerPhoneNumberField;
    @FXML
    private TextField customerPostalCodeField;
    @FXML
    private ComboBox customerDivisionCombo;
    @FXML
    private ComboBox customerCountryCombo;


    /** Cancels without creating a customer and sends back to main screen
     * @param event for cancel button
     * @throws IOException for the unhandled exception
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

    }


    public void onActionSaveCustomer(ActionEvent event) throws IOException {
        System.out.println("new customer saved clicked");
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
