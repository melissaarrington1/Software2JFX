package sample.model;

public class ReportItem {
    String apptType;
    String apptMonth;
    int apptTotal;

    public ReportItem(String apptType, String apptMonth, int apptTotal) {
        this.apptType = apptType;
        this.apptMonth = apptMonth;
        this.apptTotal = apptTotal;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public String getApptMonth() {
        return apptMonth;
    }

    public void setApptMonth(String apptMonth) {
        this.apptMonth = apptMonth;
    }

    public int getApptTotal() {
        return apptTotal;
    }

    public void setApptTotal(int apptTotal) {
        this.apptTotal = apptTotal;
    }
}
