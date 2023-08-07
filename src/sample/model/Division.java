package sample.model;

/**
 * Division Model Class
 */
public class Division {
    int divisionId;
    String divisionName;
    int countryId;

    /**
     * Division Constructor
     * @param divisionId
     * @param divisionName
     * @param countryId
     */
    public Division(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * gets a division id
     * @return
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * sets a division id
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * gets division name
     * @return
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * sets division name
     * @param divisionName
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * gets country id
     * @return
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * sets country id
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return divisionName;
    }
}
