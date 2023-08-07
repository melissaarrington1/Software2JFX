package sample.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * SQL Queries for Contact
 */
public class ContactQuery {
    /**
     * SQL Query to read all contacts from Contact table in database
     *
     * @return contactList
     */
    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Contact_ID, Contact_Name, Email FROM Contacts";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                Contact c = new Contact(contactId, contactName, contactEmail);
                contactList.add(c);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contactList;
    }

    public static Contact getContactName(int contactId) {
        try{
            String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, contactId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int contactIdSearch = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String contactEmail = rs.getString("Email");
            Contact c = new Contact(contactIdSearch, contactName, contactEmail);
            return c;

        }
       catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  SQL Query to get the Contact ID for a selected Contact Name
     * @param contactName
     * @return
     * @throws SQLException
     */
    public static int getContactId(String contactName) throws SQLException {
        int contactId = 0;
        String sql = "SELECT * FROM contacts WHERE Contact_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, contactName);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            contactId = rs.getInt("Contact_ID");
        }
        return contactId;
    }

}
