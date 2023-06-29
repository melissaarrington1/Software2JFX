package sample.DB;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Country;
import sample.model.Customer;
import sample.model.Division;

import java.sql.*;
import java.time.LocalDateTime;

public class CustomerQuery {

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
                String createdBy = rs.getString("Created_By");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerDivisionId = rs.getInt("Division_ID");
                String customerDivisionName = rs.getString("Division");
                int customerCountryId = rs.getInt("Country_ID");
                String customerCountryName = rs.getString("Country");
                Customer c = new Customer(customerName, customerAddress, customerPostalCode, customerPhone, createdBy, lastUpdatedBy, customerDivisionId, customerDivisionName, customerCountryId, customerCountryName, customerId);
                customerList.add(c);
                System.out.println("*");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    /**
     * This is SQL Query to insert a NEW customer into the Customers Table in the database
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhoneNumber
     * @param customerCountry
     * @param
     * @return
     * @throws SQLException
     */

//    public static void addCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, String createDate, Timestamp lastUpdate, Country customerCountry, int divisionID) throws SQLException {
////        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Create_Date, Last_Update, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";
////        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
////        ps.setString(1, customerName);
////        ps.setString(2, customerAddress);
////        ps.setString(3, customerPostalCode);
////        ps.setString(4, customerPhoneNumber);
////        ps.setTimestamp(5, Timestamp.valueOf(createDate));
////        ps.setTimestamp(6, lastUpdate);
////        ps.setString(5, String.valueOf(customerCountry));
////        ps.setInt(6, divisionID);
////        int rowsAffected = ps.executeUpdate();
////                if(rowsAffected > 0) {
////            System.out.println("Insert successful");
////        }
////        else {
////            System.out.println("Insert Failed!");
////        //return rowsAffected;
////        }


    public static void addCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, Country customerCountry, Division customerDivision) throws SQLException {
//JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Country, Division) VALUES (?, ?, ?, ?, ?, ?)" +
                "";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, customerAddress);
        ps.setString(3, customerPostalCode);
        ps.setString(4, customerPhoneNumber);
        //ps.setTimestamp(5, Timestamp.valueOf(createDate));
        //ps.setTimestamp(6, lastUpdate);
        ps.setString(5, String.valueOf(customerCountry));
        ps.setString(6, String.valueOf(customerDivision));
        ps.executeUpdate();
//        if(rowsAffected > 0) {
//            System.out.println("Insert successful");
//        }
//        else {
//            System.out.println("Insert Failed!");
//            return rowsAffected;
//        }
    }
}
