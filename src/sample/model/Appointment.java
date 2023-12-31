package sample.model;

import java.time.LocalDateTime;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.DB.AppointmentQuery;

/**
 * Appointments Model
 */
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
     * Appointments constructor
     * @param appointmentId id
     * @param appointmentTitle title
     * @param appointmentDescription description
     * @param appointmentType type
     * @param appointmentLocation location
     * @param appointmentContact contact
     * @param customerId customer id
     * @param userId user id
     * @param appointmentStart appointment start
     * @param appointmentEnd appointment end
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

    public Appointment(int appointmentId, String appointmentTitle, String appointmentType, String appointmentDescription, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int customerId) {
    }

    /**
     * AppointmentID Getter
     * @return Returns appointment id
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * AppointmentID Setter
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    /**
     * Appointment Title Getter
     * @return Returns appointment title
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }
    /**
     * Appointment Title Setter
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }
    /**
     * Appointment Description Getter
     * @return Returns appointment description
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }
    /**
     * Appointment Description Setter
     */
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    /**
     * Appointment Type Getter
     * @return Returns appointment type
     */
    public String getAppointmentType() {
        return appointmentType;
    }
    /**
     * Appointment Type Setter
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }
    /**
     * Appointment Location Getter
     * @return Returns appointment location
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }
    /**
     * Appointment Location Setter
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /**
     * Appointment Contact Getter
     * @return Returns appointment contact
     */
    public int getAppointmentContact() {
        return appointmentContact;
    }
    /**
     * Appointment Contact Setter
     */
    public void setAppointmentContact(int appointmentContact) {
        this.appointmentContact = appointmentContact;
    }
    /**
     * Appointment Customer ID Getter
     * @return Returns appointment customer id
     */
    public int getCustomerId() {
        return customerId;
    }
    /**
     * Appointment Customer ID Setter
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**
     * Appointment User ID Getter
     * @return Returns appointment user id
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Appointment User ID Setter
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * Appointment Start Time Getter
     * @return Returns appointment start
     */
    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }
    /**
     * Appointment Start Time Setter
     */
    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }
    /**
     * Appointment End Time Getter
     * @return Returns appointment end
     */
    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }
    /**
     * Appointment Start Time Setter
     */
    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    /**
     * Method for checking for overlap when creating a new appointment
     * Lambda Function #1 for filtering through customer appointments by Id
     * @param appointmentId id
     * @param customerId id
     * @param start start
     * @param end end
     * @return Returns true or false for overlap information
     */
    public static boolean checkOverlapAppt(int appointmentId, int customerId, LocalDateTime start, LocalDateTime end) {
        ObservableList<Appointment> appointments = AppointmentQuery.getAppointmentList();
        ObservableList<Appointment> customerAppointments = FXCollections.observableArrayList();

        customerAppointments = appointments.filtered(apt -> {
            if(apt.getCustomerId() == customerId && apt.getAppointmentId() != appointmentId) {
                return true;
            }
            return false;
        });


        for(Appointment a : customerAppointments) {
            System.out.println(a.getAppointmentStart());
            System.out.println(start);
            System.out.println(a.getAppointmentEnd());
            System.out.println(end);

            // a.start > start && a.start < end
            if (a.getAppointmentStart().isAfter(start) && a.getAppointmentStart().isBefore(end)) {
                return true;
            }
            // a.end > start && a.end < end
            if (a.getAppointmentEnd().isAfter(start) && a.getAppointmentEnd().isBefore(end)) {
                return true;
            }

            // a. start < start && a.end > end
            if(a.getAppointmentStart().isBefore(start) && a.getAppointmentEnd().isAfter(end)) {
                return true;
            }
            // a.start == start
            if(a.getAppointmentStart().isEqual(start)) {
                return true;
            }
            // a.end == end
            if(a.getAppointmentEnd().isEqual(end)) {
                return true;
            }
        }
        return false;
    }

}
