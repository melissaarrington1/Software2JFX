package sample.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Country;
import sample.model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class DivisionQuery {
    public static ObservableList<Division> getDivisionList() {
        ObservableList<Division> divisionList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM First_Level_Divisions";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                int countryId = rs.getInt("Country_ID");
                String divisionName = rs.getString("Division");
//                LocalDateTime createDate = rs.getDate("Create_Date");
//                String createdBy = rs.getString("Created_By");
//                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
//                String lastUpdatedBy = rs.getString("Last_Updated_By");

                Division d = new Division(divisionId, divisionName, countryId);
                divisionList.add(d);
                System.out.println("*");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisionList;
    }


    //getCountryDivisions or (add where clause & pass countryID to filter divisionList by countryID)
    // do the same except reuse the method above Lambda expression
    public static ObservableList<Division> getCountryDivisions(int countryId) {
        ObservableList<Division> divisionList = getDivisionList().stream()
                .filter(d -> d.getCountryId() == countryId)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        return divisionList;

    }


}
