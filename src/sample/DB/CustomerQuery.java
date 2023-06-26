package sample.DB;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomerQuery {
    public static int insert(String customer, String address, String postalCode, String phone, LocalDateTime createDate, Timestamp lastUpdate, int divisionID) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Create_Date, Last_Update, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customer);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setTimestamp(5, Timestamp.valueOf(createDate));
        ps.setTimestamp(6, lastUpdate);
        ps.setInt(7, divisionID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

//    public static int insert(String customer, String yankee, String s, String s1, int i, int i1, int i2, int i3) throws SQLException {
//        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Create_Date, Last_Update, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ps.setString(1, customer);
//        ps.setString(2, yankee);
//        ps.setString(3, s);
//        ps.setString(4, s1);
//        ps.setDate(5, 2023-01-04);
//        ps.setTimestamp(6, 2023-01-01 00:00);
//        ps.setInt(7, 1);
//        int rowsAffected = ps.executeUpdate();
//        return rowsAffected;
//    }
}
