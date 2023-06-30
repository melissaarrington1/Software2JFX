package sample.controller;

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
import sample.DB.CustomerQuery;
import sample.model.Customer;
import sample.model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMainController implements Initializable {
    public TableColumn customerCountryCol;
    Stage stage;
    Parent scene;

    @FXML
    private TableView<Customer> mainCustomerTable;
    @FXML
    private TableColumn<Customer, Integer> customerIdCol;
    @FXML
    private TableColumn<Customer, String> customerNameCol;
    @FXML
    private TableColumn<Customer, String> customerAddressCol;
    @FXML
    private TableColumn<Customer, String> customerPostalCodeCol;
    @FXML
    private TableColumn<Customer, Integer> customerPhoneCol;
    @FXML
    private TableColumn<Customer, Integer> customerCreatedDateCol;
    @FXML
    private TableColumn<Customer, Integer> customerCreatedByCol;
    @FXML
    private TableColumn<Customer, Integer> customerStateCol;

    /***
     * Event that takes you to the Add Customer screen.
     *
     * @param event
     * @throws IOException
     */
    public void onActionCreateCustomer(ActionEvent event) throws IOException {
        System.out.println("lets create a new customer button clicked");
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/New_Customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionModifyCustomer(ActionEvent actionEvent) {
    }

    public void onActionDeleteCustomer(ActionEvent actionEvent) {
    }

    /**
     *  Method to Initialize the list of customers on the main customer screen
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainCustomerTable.setItems(CustomerQuery.getCustomerList());
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("countries"));
        //customerCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        customerStateCol.setCellValueFactory(new PropertyValueFactory<>("division"));
    }


}
