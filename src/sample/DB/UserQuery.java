package sample.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Contact;
import sample.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SQL Queries for User
 */
public class UserQuery {
    /**
     * A method to get a list of all users from the database
     * @return Returns a user list
     */
    public static ObservableList<User> getUserList() {
        ObservableList<User> userList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT User_ID, User_Name FROM Users";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int userId = rs.getInt("User_ID");
                String name = rs.getString("User_Name");
                User c = new User(userId, name);
                userList.add(c);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    /**
     * SQL Query for getting a users login info
     * @param username username
     * @param password password
     * @return Returns user login
     * @throws SQLException exception
     */
    public static boolean userLogin(String username, String password) throws SQLException {
        try {
            String sql = "SELECT * FROM Users WHERE BINARY User_Name = ? AND  Password = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * SQL Query for getting a user by id
     * @param username username
     * @return Returns a user id
     * @throws SQLException exception
     */
    public static int getUserId(String username) throws SQLException {
        int userId = 0;
        String sqlStatement = "select User_ID, User_Name from users where User_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sqlStatement);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            userId = rs.getInt("User_ID");
            username = rs.getString("User_Name");
        }
        return userId;
    }

    public static boolean usernameCheck(String username) {
        try {
            String sql = "SELECT * FROM Users WHERE  User_Name = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean passwordCheck(String password) {
        try {
            String sql = "SELECT * FROM Users WHERE Password = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
