package sample.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Appointment;
import sample.model.Customer;
import java.sql.Timestamp;

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
               // System.out.println("*");
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
        ps.setInt(5, contactId);
        ps.setInt(6, customerId);
        ps.setInt(7, userId);
        ps.setTimestamp(8, Timestamp.valueOf(appointmentStart));
        ps.setTimestamp(9, Timestamp.valueOf(appointmentEnd));
        ps.executeUpdate();
    }

    public static void updateAppointment(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentType, String appointmentLocation, int contactId, int customerId, int userId, LocalDateTime appointmentStart, LocalDateTime appointmentEnd) throws SQLException {
        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Type = ?, Location = ?, Contact_ID = ?, Customer_ID = ?, User_ID = ?, Start = ?, End = ? WHERE Appointment_ID =  ?" +
                    "";
            PreparedStatement updateAppointment = JDBC.connection.prepareStatement(sql);
            updateAppointment.setString(1, appointmentTitle);
            updateAppointment.setString(2, appointmentDescription);
            updateAppointment.setString(3, appointmentType);
            updateAppointment.setString(4, appointmentLocation);
            updateAppointment.setInt(5, contactId);
            updateAppointment.setInt(6, customerId);
            updateAppointment.setInt(7, userId);
            updateAppointment.setTimestamp(8, Timestamp.valueOf(appointmentStart));
            updateAppointment.setTimestamp(9, Timestamp.valueOf(appointmentEnd));
            updateAppointment.setInt(10, appointmentId);
            updateAppointment.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAppointment(int appointmentId) throws SQLException {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement deleteAppt = JDBC.connection.prepareStatement(sql);
            deleteAppt.setInt(1, appointmentId);
            deleteAppt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAppointmentByCustomer(int customerId) throws SQLException {
        try {
            String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
            PreparedStatement deleteAppt = JDBC.connection.prepareStatement(sql);
            deleteAppt.setInt(1, customerId);
            deleteAppt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        public static ObservableList<Appointment> getWeeklyAppointments() {
        ObservableList<Appointment> weekly = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE YEARWEEK(START) = YEARWEEK(NOW()) ORDER BY appointments.Appointment_ID";
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
                weekly.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return weekly;
    }

    public static ObservableList<Appointment> getMonthlyAppointments() {
        ObservableList<Appointment> monthly = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE MONTH(START) = MONTH(NOW()) ORDER BY appointments.Appointment_ID";
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
                monthly.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return monthly;
    }

    /**
     * SQL Query to get all appointments for users during login
     * @param userId
     * @return userAppointments
     */
    public static ObservableList<Appointment> getUserAppointments(int userId) {
        ObservableList<Appointment> userAppointments = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM Appointments WHERE User_ID = ?" ;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentType = rs.getString("Type");
                String appointmentLocation = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                int customerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                Appointment a = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentType, appointmentLocation, contactId, customerId, appointmentUserId, appointmentStart, appointmentEnd);
                userAppointments.add(a);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userAppointments;
    }

    public static ObservableList<Appointment> getContactAppointments(int contactId) {
        ObservableList<Appointment> contactAppointment = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE appointments.Contact_ID  = '" + contactId + "'";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentType = rs.getString("Type");
                String appointmentLocation = rs.getString("Location");
                int appointmentUserId = rs.getInt("User_ID");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                Appointment a = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentType, appointmentLocation, contactId, customerId, appointmentUserId, appointmentStart, appointmentEnd);
                contactAppointment.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactAppointment;
    }
}
