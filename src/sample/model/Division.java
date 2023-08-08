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
     * @param divisionId division id
     * @param divisionName division name
     * @param countryId country name
     */
    public Division(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * gets a division id
     * @return Returns division id
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * sets a division id
     * @param divisionId division id
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * gets division name
     * @return Returns division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * sets division name
     * @param divisionName division name
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * gets country id
     * @return Returns country id
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * sets country id
     * @param countryId country id
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return divisionName;
    }
}
