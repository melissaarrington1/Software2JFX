package sample.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Country;
import sample.model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * SQL Query for getting a list of Divisions
 */
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
                Division d = new Division(divisionId, divisionName, countryId);
                divisionList.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisionList;
    }


    //Lambda Function
    //getCountryDivisions or (add where clause & pass countryID to filter divisionList by countryID)
    // do the same except reuse the method above Lambda expression

    /**
     * Method returns a list of all divisions for a selected country
     * LAMBDA Function #2 for getting Associated country and Division by the country ID
     * @param countryId id
     * @return Returns division list
     */
    public static ObservableList<Division> getCountryDivisions(int countryId) {
        ObservableList<Division> divisionList = getDivisionList().stream()
                .filter(d -> d.getCountryId() == countryId)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        return divisionList;

    }

    /**
     * SQL Query for finding a division by country id
     * @param divisionId id
     * @return Returns d
     */
    public static Division findById(int divisionId) {
        for(Division d:getDivisionList()) {
            if(d.getDivisionId() == divisionId) {
                return d;
            }
        }
        return null;
    }

    /**
     * SQL Query for finding a country by division id
     * @param divisionId division id
     * @return Returns country by id
     */
    public static Country findCountry(int divisionId) {
        for(Division d:getDivisionList()) {
            if(d.getDivisionId() == divisionId) {
                return CountryQuery.findById(d.getCountryId());
            }
        }
        return null;
    }
}
