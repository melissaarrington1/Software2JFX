package sample.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Country;
import sample.model.Division;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * SQL Query to get a list of all countries in DB
 */
public class CountryQuery {
    public static ObservableList<Country> getCountryList() {
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country_ID, Country FROM Countries";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country c = new Country(countryId, countryName);
                countryList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryList;
    }

    /**
     * Finding a country by ID
     * @param countryId country id
     * @return Returns country id
     */
    public static Country findById(int countryId) {
        for(Country c:getCountryList()) {
            if(c.getCountryId() == countryId) {
                return c;
            }
        }
        return null;
    }
}
