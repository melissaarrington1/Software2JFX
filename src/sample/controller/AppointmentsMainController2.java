package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DB.AppointmentQuery;
import sample.DB.ContactQuery;
import sample.DB.CustomerQuery;
import sample.DB.ReportQuery;
import sample.model.Appointment;
import sample.model.Customer;
import sample.model.ReportItem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Appointments Main Class
 * This class shows a table to all created Appointments
 * @author Melissa Arrington
 */
public class AppointmentsMainController2 implements Initializable {
    public TableView<Customer> mainCustomerTable;
    public TableColumn customerIdCol;
    public TableColumn customerNameCol;
    public TableColumn customerAddressCol;
    public TableColumn customerPostalCodeCol;
    public TableColumn customerPhoneCol;
    public TableColumn customerCountryCol;
    public TableColumn customerStateCol;

    //contacts table
    public TableView<Appointment> apptContactTable;
    public ComboBox contactCombo;
    public TableColumn apptIdCol;
    public TableColumn apptTitleCol;
    public TableColumn apptType1Col;
    public TableColumn apptDescCol;
    public TableColumn apptStartCol;
    public TableColumn apptEndCol;
    public TableColumn apptCustIdCol;

    //country total table
    public TableView<ReportItem> countryTotalTable;
    public TableColumn apptCountryCol;
    public TableColumn totalCountryCol;

    //appt totals table
    public TableView<ReportItem> monthTypeTable;
    public TableColumn apptTypeCol;
    public TableColumn apptMonthCol;
    public TableColumn apptTotalCol;

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
    private RadioButton filterByWeek;
    @FXML
    private RadioButton filterByMonth;
    @FXML
    private ToggleGroup appointmentToggleGroup;


    /**
     * Method for button that takes you to New Appointment screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCreateAppointment(ActionEvent event) throws IOException {
        System.out.println("create appointment button clicked");
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/New_Appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Method for button that takes you to Modify existing Appointment screen.
     * If there is no appointment selected, an error will occur
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionModifyAppointment(ActionEvent event) throws IOException {
        Appointment selectedAppointment = (Appointment) mainAppointmentsTable.getSelectionModel().getSelectedItem();

        if(selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an appointment to modify.");
            alert.showAndWait();
        }
        if(selectedAppointment != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/views/Modify_Appointment.fxml"));

            loader.load();

            Stage stage1= (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent root = loader.getRoot();

            AppointmentModifyController AMController = loader.getController();
            System.out.println(selectedAppointment.getAppointmentTitle());
            AMController.appointmentInfo(selectedAppointment);

            stage1.setScene(new Scene(root));
            stage1.show();
        }
    }

    /**
     * Initializes each screen of the application with the appointment, customer, or report data.
     * @param url url
     * @param resourceBundle resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //APPOINTMENTS TAB
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

        //CUSTOMERS TAB
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("countries"));
        customerStateCol.setCellValueFactory(new PropertyValueFactory<>("division"));

        //REPORTS TAB

        //appointment by Month, selecting a contact & totals for specified user
        monthTypeTable.setPlaceholder(new Label("Please select a user."));
        monthTypeTable.setItems(ReportQuery.getAppointmentTypeMonthReport());
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        apptMonthCol.setCellValueFactory(new PropertyValueFactory<>("apptMonth"));
        apptTotalCol.setCellValueFactory(new PropertyValueFactory<>("apptTotal"));

        //country and total appointments by country
        countryTotalTable.setItems(ReportQuery.getCountryTotalReport());
        apptCountryCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        totalCountryCol.setCellValueFactory(new PropertyValueFactory<>("apptTotal"));

        //View Appointments By Contact
        contactCombo.setItems(ContactQuery.getAllContacts());
        apptContactTable.setPlaceholder(new Label("Please select a contact to view their appointments"));
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        apptType1Col.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        apptDescCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        apptCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));


        loadAppointmentsTab();
        loadReportsTab();

    }

    /**
     * Method for the Reports Tab, that includes appointment data for each user
     * @param actionEvent action event
     * @throws SQLException sql exception
     */
    public void contactAppointmentTable(ActionEvent actionEvent) throws SQLException {
        //takes in a CONTACT name and converts to contact id to ge all their appointments
        String contactName = String.valueOf(contactCombo.getValue());
        int contactId = ContactQuery.getContactId(contactName);
        if(AppointmentQuery.getContactAppointments(contactId).isEmpty()) {
            apptContactTable.setPlaceholder(new Label(contactName + " has no appointments"));
            apptContactTable.refresh();
            for (int i = 0; i < apptContactTable.getItems().size(); i++) {
                apptContactTable.getItems().clear();
                apptContactTable.setPlaceholder(new Label(contactName + " has no appointments."));
            }
        } else {
            apptContactTable.setItems(AppointmentQuery.getContactAppointments(contactId));
        }
    }

    /**
     * Method for deleting an existing appointment.
     * Includes validation for deleting or not deleting an appointment
     * @param actionEvent action event
     * @throws SQLException sql exception
     */
    public void onActionDeleteAppointment(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointment> appointmentList = AppointmentQuery.getAppointmentList();
        Appointment appointment = mainAppointmentsTable.getSelectionModel().getSelectedItem();
        if(appointment == null) {
            Alert notDeletedAlert = new Alert(Alert.AlertType.ERROR, "Please select an appointment to delete!");
            notDeletedAlert.show();
            return;
        }
        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete appointment ID: " + mainAppointmentsTable.getSelectionModel().getSelectedItem().getAppointmentId() + "?");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            alert.showAndWait();

            if(alert.getResult() == ButtonType.OK) {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setContentText("Appointment ID: " + mainAppointmentsTable.getSelectionModel().getSelectedItem().getAppointmentId() + " of Type: "
                        + mainAppointmentsTable.getSelectionModel().getSelectedItem().getAppointmentType() + " has been deleted.");
                confirmation.getButtonTypes().clear();
                confirmation.getButtonTypes().addAll(ButtonType.OK);
                confirmation.showAndWait();

                AppointmentQuery.deleteAppointment(mainAppointmentsTable.getSelectionModel().getSelectedItem().getAppointmentId());
                appointmentList = AppointmentQuery.getAppointmentList();
                mainAppointmentsTable.setItems(appointmentList);
                mainAppointmentsTable.refresh();
            }

        }
    }

    /**
     * Method for filtering all appointments by week
     * @param actionEvent action event
     */
    public void onActionFilterByWeek(ActionEvent actionEvent) {
        if(filterByWeek.isSelected()) {
            try {
                //for loop going through all appt observ. list. Same thing for month. use system.now()
                //if condition to check start dates week
                //if is currentweek, move appt to filtered observable list
                //after for loop, populate table view with this filtered list
                mainAppointmentsTable.setItems(AppointmentQuery.getWeeklyAppointments());
                mainAppointmentsTable.setPlaceholder(new Label("No appointment exists for next week!"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for filtering all appointments by month
     * @param actionEvent action event
     */
    public void onActionFilterByMonth(ActionEvent actionEvent) {
        mainAppointmentsTable.setItems(AppointmentQuery.getMonthlyAppointments());
        mainAppointmentsTable.setPlaceholder(new Label("No appointment exists for next month!"));
    }


    /**
     * Method to reset filtering
     * @param actionEvent action event
     */
    public void onActionFilterAll(ActionEvent actionEvent) {
        mainAppointmentsTable.setItems(AppointmentQuery.getAppointmentList());
    }


    /**
     * Button for Modifying Customers. Redirects to the CustomerModify page when clicked.
     * If there is no customer selected, a Warning Error will occur.
     * @param event event
     * @throws IOException exception
     */
    public void onActionModifyCustomer(ActionEvent event) throws SQLException, IOException {

        Customer selectedCustomer = (Customer) mainCustomerTable.getSelectionModel().getSelectedItem();

        if(selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a customer to modify.");
            alert.showAndWait();
        }
        if(selectedCustomer != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/views/Customer_Modify.fxml"));

            loader.load();

            Stage stage1= (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent root = loader.getRoot();

            CustomerModifyController CMController = loader.getController();
            System.out.println(selectedCustomer.getName());
            CMController.customerInfo(selectedCustomer);

            stage1.setScene(new Scene(root));
            stage1.show();
        }
    }


    /***
     * Event that takes you to the Add Customer screen.
     *
     * @param event event
     * @throws IOException exception
     */
    public void onActionCreateCustomer(ActionEvent event) throws IOException {
        System.out.println("lets create a new customer button clicked");
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/New_Customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Method to delete a customer.
     * Will delete existing appointment for a customer if a customer is deleted.
     * @param actionEvent event
     * @throws SQLException exception
     */
    public void onActionDeleteCustomer(ActionEvent actionEvent) throws SQLException {
        ObservableList<Customer> customerList = CustomerQuery.getCustomerList();

        int count = 0;
        ObservableList<Appointment> appointmentList = AppointmentQuery.getAppointmentList();
        Customer customer = mainCustomerTable.getSelectionModel().getSelectedItem();
        //checking to see if customer selected
        if(customer == null) {
            Alert deleteAlert = new Alert(Alert.AlertType.ERROR, "Please select a customer to delete!");
            deleteAlert.show();
            return;
        }
        //searching for customer to delete by ID
        int selectedCustomer = mainCustomerTable.getSelectionModel().getSelectedItem().getId();

        Alert delete = new Alert(Alert.AlertType.WARNING);
        delete.setTitle("Alert");
        delete.setContentText("This customer and all associated appointments will be deleted. Are you sure you want to delete this customer?");
        delete.getButtonTypes().clear();
        delete.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        delete.showAndWait();

        if (delete.getResult() == ButtonType.OK) {
            AppointmentQuery.deleteAppointmentByCustomer(mainCustomerTable.getSelectionModel().getSelectedItem().getId());
            CustomerQuery.deleteCustomer(mainCustomerTable.getSelectionModel().getSelectedItem().getId());

            customerList = CustomerQuery.getCustomerList();
            mainCustomerTable.setItems(customerList);
            mainCustomerTable.refresh();
        } else if (delete.getResult() == ButtonType.CANCEL) {
            delete.close();
        }


    }
    private void loadAppointmentsTab() {
        mainAppointmentsTable.setItems(AppointmentQuery.getAppointmentList());

    }

    public void onCustomerTab(Event event) {
        mainCustomerTable.setItems(CustomerQuery.getCustomerList());

    }

    public void onAppointmentsTab(Event event) {
        loadAppointmentsTab();
    }

    private void loadReportsTab() {

    }
    public void onReportsTab(Event event) {
        loadReportsTab();
    }

    public void contactList(ActionEvent actionEvent) throws SQLException {
        String name = String.valueOf(contactCombo.getValue());
        int contactId = ContactQuery.getContactId(name);
        if(AppointmentQuery.getContactAppointments(contactId).isEmpty()) {
            apptContactTable.setPlaceholder(new Label(name + " has no appointments."));
            apptContactTable.refresh();
            for(int i = 0; i < apptContactTable.getItems().size(); i++) {
                apptContactTable.getItems().clear();
                apptContactTable.setPlaceholder(new Label(name + " has no appointments."));
            }
        } else {
            apptContactTable.setItems(AppointmentQuery.getContactAppointments(contactId));
        }
    }

    /**
     * Method for logging out of application. Sends you back to the login screen.
     * @param event event
     * @throws IOException exception
     */
    public void onActionLogOut(ActionEvent event) throws IOException {
        System.out.println("logging out");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/views/Login.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    /**
     * Method for going back to the main appointments screen
     * @param event event
     * @throws IOException exception
     */
    public void backToAppointments(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/views/Appointments_Main2.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
