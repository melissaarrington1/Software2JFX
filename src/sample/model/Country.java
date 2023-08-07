package sample.model;

import javafx.collections.ObservableList;

/**
 * Country Model
 */

public class Country {

    private int countryId;
    private String countryName;

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * Getter for Country
     * @return
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Setter for Country
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return countryName;
    }
}
