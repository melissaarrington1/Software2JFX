package sample.model;

import java.time.LocalDateTime;

/**
 * Customer Model
 */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String countries;
    private String division;
    private int divisionID;
    private int countryId;

    public Customer(int id, String name, String address, String phoneNumber, String postalCode, String countries, String division, int divisionID, int countryId) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.postalCode = postalCode;
    this.countries = countries;
    this.division = division;
    this.divisionID = divisionID;
    this.countryId = countryId;
}

    public Customer(int customerIdSearch, String customerName) {
    }

    /**
     * Gets customer id
     * @return Returns id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets customer id
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Gets customer name
     * @return Returns name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets customer name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets customer address
     * @return Returns address
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets customer address
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets customer phone number
     * @return Returns phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * sets customer phone number
     * @param phoneNumber phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * gets customer postal code
     * @return Returns postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * sets customer postal
     * @param postalCode postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * gets customer country
     * @return Returns country list
     */
    public String getCountries() {
        return countries;
    }

    /**
     * sets customer country
     * @param countries country
     */
    public void setCountries(String countries) {
        this.countries = countries;
    }

    /**
     * gets customer division
     * @return Returns a division
     */
    public String getDivision() {
        return division;
    }

    /**
     * sets customer division
     * @param division division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * gets division id customer
     * @return Returns division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * sets division id customer
     * @param divisionID division id
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return name;
    }
}
