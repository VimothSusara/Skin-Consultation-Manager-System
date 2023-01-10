public class Consultation {
    private String consultStartTime;
    private String date;
    private double cost;
    private String note;
    private String imagePath;
    private int consultDuration;
    private String consultEndTime;
    private String consultDocId;
    private String consultPatientId;
    private int consultationId;

    public Consultation(String consultStartTime, String date, double cost, String note, String imagePath,
                        int consultDuration,String consultEndTime, String consultDocId, String consultPatientId,int consultationId) {
        this.consultStartTime = consultStartTime;
        this.date = date;
        this.cost = cost;
        this.note = note;
        this.imagePath = imagePath;
        this.consultDuration = consultDuration;
        this.consultEndTime = consultEndTime;
        this.consultDocId = consultDocId;
        this.consultPatientId = consultPatientId;
        this.consultationId = consultationId;
    }

    public String getConsultStartTime() {
        return consultStartTime;
    }

    public void setConsultStartTime(String time) {
        this.consultStartTime = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getConsultDuration() {
        return consultDuration;
    }

    public void setConsultDuration(int consultDuration) {
        this.consultDuration = consultDuration;
    }

    public String getConsultEndTime() {
        return consultEndTime;
    }

    public void setConsultEndTime(String consultEndTime) {
        this.consultEndTime = consultEndTime;
    }

    public String getConsultDocId() {
        return consultDocId;
    }

    public void setConsultDocId(String consultDocId) {
        this.consultDocId = consultDocId;
    }

    public String getConsultPatientId() {
        return consultPatientId;
    }

    public void setConsultPatientId(String consultPatientId) {
        this.consultPatientId = consultPatientId;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

}
