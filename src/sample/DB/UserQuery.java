package sample.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Contact;
import sample.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {
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
}
