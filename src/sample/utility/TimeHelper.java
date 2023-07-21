package sample.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeHelper {
    private static ObservableList<LocalTime> startTimes = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> endTimes = FXCollections.observableArrayList();

    public static ObservableList<LocalTime> getStartTimes() {
        if(startTimes.isEmpty()) {
            setTimeLists();
        }
        return startTimes;
    }

    public static ObservableList<LocalTime> getEndTimes() {
        if(endTimes.isEmpty()) {
            setTimeLists();
        }
        return endTimes;
    }


    private static void setTimeLists() {
        ZonedDateTime startEst = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8,0), ZoneId.of("America/New_York"));
        ZonedDateTime startLocal = startEst.withZoneSameInstant(ZoneId.systemDefault());
        ZonedDateTime endLocal = startLocal.plusHours(14); //14 hrs between 8am and 10pm

        while(startLocal.isBefore(endLocal)) {
            startTimes.add(startLocal.toLocalTime());
            startLocal = startLocal.plusMinutes(30);
            endTimes.add(startLocal.toLocalTime()); //using current startLocal to set end of all appts
        }
    }
}
