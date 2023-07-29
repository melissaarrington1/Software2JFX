package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DB.CountryQuery;
import sample.DB.CustomerQuery;
import sample.DB.DivisionQuery;
import sample.model.Country;
import sample.model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the Add Customer class, which provides a FORM to create each CUSTOMER
 * @author Melissa Arrington
 */

public class NewCustomerController implements Initializable {
    Stage stage;
    Parent scene;


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
    private ComboBox<Division> customerDivisionCombo;
    @FXML
    private ComboBox<Country> customerCountryCombo;


    /** Cancels without creating a customer and sends back to main screen
     * @param event for cancel button
     * @throws IOException for the unhandled exception
     */
    public void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main2.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     *  Initialize method for setting combobox list of countries
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCountryCombo.setItems(CountryQuery.getCountryList());
    }

    /**
     * Method for Saving a new customer after entering all required fields
     * @param event
     * @throws IOException
     */
    public void onActionSaveCustomer(ActionEvent event) throws IOException {
        try {
            String customerName = customerNameField.getText();
            if(customerName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a customer name!");
                alert.showAndWait();
                return;
            }
            String customerAddress = customerAddressField.getText();
            if(customerAddress.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a customer address!");
                alert.showAndWait();
                return;
            }
            String customerPhoneNumber = customerPhoneNumberField.getText();
            if(customerPhoneNumber.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a customer phone number!");
                alert.showAndWait();
                return;
            }
            String customerPostalCode = customerPostalCodeField.getText();
            if(customerPostalCode.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a customer postal code!");
                alert.showAndWait();
                return;
            }
            Country customerCountry = customerCountryCombo.getValue();

            int customerDivision = customerDivisionCombo.getValue().getDivisionId();
//            if(customerDivision.isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a customer postal code!");
//                alert.showAndWait();
//            }
            CustomerQuery.addCustomer(customerName, customerAddress, customerPostalCode, customerPhoneNumber, customerCountry, customerDivision);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }


        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main2.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Method for selecting a customer's country using ComboBoxes
     * @param actionEvent
     */
    public void onActionCountryChange(ActionEvent actionEvent) {
        Country country = customerCountryCombo.getValue();
        customerDivisionCombo.setItems(DivisionQuery.getCountryDivisions(country.getCountryId()));
    }
}
