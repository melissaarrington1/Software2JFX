package sample.DB;

//import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Contact;
import sample.model.Country;
import sample.model.Customer;
import sample.model.Division;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * SQL Queries for Customer
 */
public class CustomerQuery {
    /**
     * SQL Query to Read a list of all customers from the database
     * @return
     */
    public static ObservableList<Customer> getCustomerList() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Create_Date, customers.Last_Update, customers.Created_By, customers.Last_Updated_By, customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Division, first_level_divisions.Country_ID, countries.Country FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID ORDER BY customers.Customer_ID ";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int customerDivisionId = rs.getInt("Division_ID");
                String customerDivisionName = rs.getString("Division");
                int customerCountryId = rs.getInt("Country_ID");
                String customerCountryName = rs.getString("Country");
                //                           int id, String name, String address, String phoneNumber, String postalCode, String countries, String division, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy, int divisionID, int countryId)
                Customer c = new Customer(customerId, customerName, customerAddress, customerPhone, customerPostalCode, customerCountryName, customerDivisionName, customerDivisionId, customerCountryId);
                customerList.add(c);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }


    /**
     * SQL Query to add a new customer.
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhoneNumber
     * @param customerCountry
     * @param customerDivision
     * @throws SQLException
     */
    public static void addCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, Country customerCountry, int customerDivision) throws SQLException {
//JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)" +
                "";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, customerAddress);
        ps.setString(3, customerPostalCode);
        ps.setString(4, customerPhoneNumber);
        ps.setInt(5, customerDivision);
        ps.executeUpdate();
    }

    /**
     * SQL Query to update an existing customer
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhone
     * @param customerDivision
     * @param customerId
     */
    public static void updateCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivision, int customerId) {
        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?,  Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement updateCustomer = JDBC.connection.prepareStatement(sql);
            updateCustomer.setString(1, customerName);
            updateCustomer.setString(2, customerAddress);
            updateCustomer.setString(3, customerPostalCode);
            updateCustomer.setString(4, customerPhone);
            updateCustomer.setInt(5, customerDivision);
            updateCustomer.setInt(6, customerId);
            updateCustomer.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Customer getCustomerName(int customerId) throws SQLException {
        try{
            String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");

            Customer c = new Customer(id, name);
            return c;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SQL Query to delete a customer from the database
     * @param customerId
     */
    public static void deleteCustomer(int customerId) {
        try {
            String sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement deleteCust = JDBC.connection.prepareStatement(sql);
            deleteCust.setInt(1, customerId);
            deleteCust.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
