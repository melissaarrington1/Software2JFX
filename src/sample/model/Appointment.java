package sample.model;

import java.time.LocalDateTime;
import sample.DB.AppointmentQuery;

public class Appointment {
    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentType;
    private String appointmentLocation;
    private int appointmentContact;
    private int customerId;
    private int userId;
    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;

    /**
     *
     * @param appointmentId
     * @param appointmentTitle
     * @param appointmentDescription
     * @param appointmentType
     * @param appointmentLocation
     * @param appointmentContact
     * @param customerId
     * @param userId
     * @param appointmentStart
     * @param appointmentEnd
     */
    public Appointment(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentType, String appointmentLocation, int appointmentContact, int customerId, int userId, LocalDateTime appointmentStart, LocalDateTime appointmentEnd) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentContact = appointmentContact;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.customerId = customerId;
        this.userId = userId;
        this.appointmentLocation = appointmentLocation;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public int getAppointmentContact() {
        return appointmentContact;
    }

    public void setAppointmentContact(int appointmentContact) {
        this.appointmentContact = appointmentContact;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }

    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }
}
