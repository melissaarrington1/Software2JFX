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
    public int selectedIndex;

    /**
     * Method used to Initialize the list of countries
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // customerCountryCombo.getValue();
        //customerDivisionCombo.getValue();
        //System.out.println(selectedCustomer.getName());
        System.out.println(customerNameField.getText());
    }



    /**
     * Method for canceling without modifying customer
     * @param event
     * @throws IOException
     */
    public void onActionCancel(ActionEvent event) throws IOException {
        System.out.println("cancel modifying customer button clicked");
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Customers_Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionSaveCustomer(ActionEvent event) {
        try {
            //int customerId = Integer.parseInt(customerIDField.getText());
            String customerName = customerNameField.getText();
            if (customerName.isEmpty()) {
                System.out.println("Empty");
            }
            String customerAddress = customerAddressField.getText();
            String customerPostalCode = customerPostalCodeField.getText();
            String customerPhone = customerAddressField.getText();
            int customerCountry = customerCountryCombo.getValue().getCountryId();
            int customerDivision = customerDivisionCombo.getValue().getDivisionId();
            CustomerQuery.updateCustomer(customerName, customerAddress, customerPostalCode, customerPhone, customerCountry, customerDivision);
            System.out.println("Saved");
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (Exception e) {
            System.out.println("Did not save");
            throw new RuntimeException(e);

        }

    }

    public void onActionCountryChange(ActionEvent actionEvent) {
        Country C = customerCountryCombo.getValue();

        customerDivisionCombo.setItems(DivisionQuery.getCountryDivisions(C.getCountryId()));

    }
    public void customerInfo(Customer customer, int selectedIndex) throws SQLException {
        try{
            selectedCustomer = customer;
            selectedIndex = selectedIndex;
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
