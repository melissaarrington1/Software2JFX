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
import sample.DB.CountryQuery;
import sample.model.Country;
import sample.model.Customer;
import sample.model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    /**
     * Method used to Initialize the list of countries
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCountryCombo.setItems(CountryQuery.getCountryList());
    }

    public void customerInfo(Customer customer) throws IOException{
        customerIDField.setText(Integer.toString(customer.getId()));
        customerNameField.setText(customer.getName());
        customerAddressField.setText(customer.getAddress());
        customerPhoneNumberField.setText(customer.getPhoneNumber());
        customerPostalCodeField.setText(customer.getPostalCode());
    }


    /**
     * Method for canceling without modifying customer
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

    public void onActionSaveCustomer(ActionEvent actionEvent) {
    }

    public void onActionCountryChange(ActionEvent actionEvent) {

    }
}
