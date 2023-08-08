package sample.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.ReportItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SQL Queries for Reports
 */
public class ReportQuery {
    /**
     * SQL query to get the type and total per month for reports
     * @return Returns an appointment total by month
     */
    public static ObservableList<ReportItem> getAppointmentTypeMonthReport() {
        ObservableList<ReportItem> appointmentTypeMonth = FXCollections.observableArrayList();
        try {
            String sql = "SELECT MonthName(Start) AS AMONTH, MONTH(Start), TYPE, Count(*) AS Total FROM appointments " +
                    "GROUP BY MonthName(Start) , MONTH(Start), TYPE " +
                    "ORDER BY MONTH(Start), TYPE";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String apptType = rs.getString("Type");
                String apptMonth = rs.getString("AMonth");
                int apptTotal = rs.getInt("Total");
                ReportItem result = new ReportItem(apptType, apptMonth, apptTotal);
                appointmentTypeMonth.add(result);
                System.out.println("saved");
            }

    } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);
        }
        return appointmentTypeMonth;

        //getCountryTotalReport
    }

    /**
     * SQL Query for getting list of each country and the total amount of appointments for each country
     * @return Returns a country total in reports
     */
    public static ObservableList<ReportItem> getCountryTotalReport() {
        ObservableList<ReportItem> list = FXCollections.observableArrayList();
        try {
            String sql = "SELECT C.Country, Count(CU.Customer_ID) AS Total FROM countries AS C INNER JOIN first_level_divisions AS D ON " +
                    "C.Country_ID = D.Country_ID INNER JOIN Customers AS CU ON " +
                    "D.Division_ID = CU.Division_ID " +
                    "GROUP BY C.Country ";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String country = rs.getString("Country");
                int totalCountry = rs.getInt("Total");
                ReportItem result = new ReportItem(country ,"", totalCountry);
                list.add(result);
                System.out.println("saved");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);
        }
        return list;
    }
}
