package sample.model;

import java.time.LocalDateTime;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
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

    public Appointment(int appointmentId, String appointmentTitle, String appointmentType, String appointmentDescription, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int customerId) {
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

    public static boolean checkOverlapAppt(int appointmentId, int customerId, LocalDateTime start, LocalDateTime end) {
        ObservableList<Appointment> appointments = AppointmentQuery.getAppointmentList();
        ObservableList<Appointment> customerAppointments = FXCollections.observableArrayList();

        /**
         * Lambda Function for filtering through customer appointments by Id
         */
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
