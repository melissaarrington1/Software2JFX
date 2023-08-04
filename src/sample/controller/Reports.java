package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Reports {
    Stage stage;
    Parent scene;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableColumn<?, ?> apptCountry;
    @FXML
    private TableView<?> apptCountryTable;
    @FXML
    private TableColumn<?, ?> apptCustomerTotal;
    @FXML
    private TableColumn<?, ?> apptMonth;
    @FXML
    private TableColumn<?, ?> apptTotal;
    @FXML
    private TableView<?> apptTotalTable;
    @FXML
    private TableColumn<?, ?> apptType;

    @FXML
    public void initialize() {
apptTotalTable.setPlaceholder(new Label("Please select info."));
    }

}
