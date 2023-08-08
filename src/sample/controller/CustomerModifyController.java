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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DB.CountryQuery;
import sample.DB.CustomerQuery;
import sample.DB.DivisionQuery;
import sample.model.Country;
import sample.model.Customer;
import sample.model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Customer Modify Class
 * @author Melissa Arrington
 */
public class CustomerModifyController implements Initializable {
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
    private ComboBox<Division> customerDivisionCombo;
    @FXML
    private ComboBox<Country> customerCountryCombo;

    public Customer selectedCustomer;


    /**
     * Method used to Initialize the list of countries
     * @param url url
     * @param resourceBundle resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(customerNameField.getText());
    }



    /**
     * Method for canceling without modifying customer
     * @param event event
     * @throws IOException exception
     */
    public void onActionCancel(ActionEvent event) throws IOException {
        System.out.println("cancel modifying customer button clicked");
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main2.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Method that takes in all of the updated customer info and saves it.
     * Includes error handling if updating a customer is not successful.
     * @param event event
     */
    public void onActionSaveCustomer(ActionEvent event) {
        try {
            int customerId = Integer.parseInt(customerIDField.getText());
            String customerName = customerNameField.getText();
            if (customerName.isEmpty()) {
                System.out.println("Empty");
            }
            String customerAddress = customerAddressField.getText();
            String customerPostalCode = customerPostalCodeField.getText();
            String customerPhone = customerPhoneNumberField.getText();
            int customerCountry = customerCountryCombo.getValue().getCountryId();
            int customerDivision = customerDivisionCombo.getValue().getDivisionId();
            CustomerQuery.updateCustomer(customerName, customerAddress, customerPostalCode, customerPhone, customerDivision, customerId);
            System.out.println("Saved");
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main2.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (Exception e) {
            System.out.println("Did not save");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that loads an existing customer country and can update the country
     * @param actionEvent event
     */
    public void onActionCountryChange(ActionEvent actionEvent) {
        Country C = customerCountryCombo.getValue();
        customerDivisionCombo.setItems(DivisionQuery.getCountryDivisions(C.getCountryId()));
    }

    /**
     * Method for sending customer info over to the Modify customer screen.
     * @param customer customer
     * @throws SQLException exception
     */
    public void customerInfo(Customer customer) throws SQLException {
        try{
            selectedCustomer = customer;
            customerIDField.setText(Integer.toString(this.selectedCustomer.getId()));
            customerNameField.setText(selectedCustomer.getName());
            customerAddressField.setText(selectedCustomer.getAddress());
            customerPhoneNumberField.setText(selectedCustomer.getPhoneNumber());
            customerPostalCodeField.setText(selectedCustomer.getPostalCode());
            customerCountryCombo.setItems(CountryQuery.getCountryList());
            customerDivisionCombo.setItems(DivisionQuery.getDivisionList());
            customerCountryCombo.setValue(DivisionQuery.findCountry(customer.getDivisionID()));
            customerDivisionCombo.setValue(DivisionQuery.findById(customer.getDivisionID()));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
