package sample.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Appointment;
import sample.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentQuery {

    /**
     * SQL Query to Read all appointments from Database
     * @return appointmentList
     */
    public static ObservableList<Appointment> getAppointmentList() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Type, appointments.Location, appointments.Contact_ID, appointments.Customer_ID, appointments.User_ID, appointments.Start, appointments.End FROM appointments JOIN Contacts ON appointments.Contact_ID = contacts.Contact_ID ORDER BY appointments.Appointment_ID ";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentType = rs.getString("Type");
                String appointmentLocation = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                Appointment a = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentType, appointmentLocation, contactId, customerId, userId, appointmentStart, appointmentEnd);
                appointmentList.add(a);
                System.out.println("*");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    public static void addAppointment(String appointmentTitle, String appointmentDescription, String appointmentType, String appointmentLocation, int contactId, int customerId, int userId, LocalDateTime appointmentStart, LocalDateTime appointmentEnd) throws SQLException{
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Type, Location, Contact_ID, Customer_ID, User_ID, Start, End) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                "";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, appointmentTitle);
        ps.setString(2, appointmentDescription);
        ps.setString(3, appointmentType);
        ps.setString(4, appointmentLocation);
        ps.setInt(4, contactId);
        ps.setInt(4, customerId);
        ps.setInt(4, userId);
        ps.setString(4, String.valueOf(appointmentStart));
        ps.setString(4, String.valueOf(appointmentEnd));
        ps.executeUpdate();
    }
}
