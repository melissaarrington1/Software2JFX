package sample.model;

import java.time.LocalDateTime;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String countries;
    private String division;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;
    private int countryId;

    public Customer(int id, String name, String address, String phoneNumber, String postalCode, String countries, String division, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy, int divisionID, int countryId) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.postalCode = postalCode;
    this.countries = countries;
    this.division = division;
    this.createDate = createDate;
    this.createdBy = createdBy;
    this.lastUpdate = lastUpdate;
    this.lastUpdatedBy = lastUpdatedBy;
    this.divisionID = divisionID;
    this.countryId = countryId;
}

    public Customer( String name, String address, String postalCode, String phoneNumber, String createdBy, String lastUpdatedBy, int divisionID, String division, int countryId, String countries, int id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.countries = countries;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
        this.countryId = countryId;
    }

//    public Customer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, String createdBy, String lastUpdatedBy, int customerDivisionId, String customerDivisionName, int customerCountryId, String customerCountryName, int customerID) {
//        this.customerID = customerID;
//        this.customerName = customerName;
//        this.customerAddress = customerAddress;
//        this.customerPostalCode = customerPostalCode;
//        this.customerPhone = customerPhone;
//        this.countries = countries;
//        this.customerDivisionName = customerDivisionName;
//        this.createDate = createDate;
//        this.createdBy = createdBy;
//        this.lastUpdate = lastUpdate;
//        this.lastUpdatedBy = lastUpdatedBy;
//        this.customerDivisionId = customerDivisionId;
//        this.customerCountryId = customerCountryId;
//    }


    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}
