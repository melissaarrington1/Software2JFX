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
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets customer id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Gets customer name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets customer name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets customer address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets customer address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets customer phone number
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * sets customer phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * gets customer postal code
     * @return
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * sets customer postal
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * gets customer country
     * @return
     */
    public String getCountries() {
        return countries;
    }

    /**
     * sets customer country
     * @param countries
     */
    public void setCountries(String countries) {
        this.countries = countries;
    }

    /**
     * gets customer division
     * @return
     */
    public String getDivision() {
        return division;
    }

    /**
     * sets customer division
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * gets division id customer
     * @return
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * sets division id customer
     * @param divisionID
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
