package sample.model;

/**
 * Method for Contact model
 */
public class Contact {
    private int contactId;
    private String contactName;
    private String contactEmail;

    /**
     * Getter for Contact ID
     * @return
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Setter for Contact ID
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /**
     * Getter for Contact Name
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * Setter for Contact Name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /**
     * Getter for Contact Email
     */
    public String getContactEmail() {
        return contactEmail;
    }
    /**
     * Setter for Contact Email
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    /**
     * Constructor for Contact
     */
    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;


    }

    @Override
    public String toString() {
        return contactName;
    }
}
