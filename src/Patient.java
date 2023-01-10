public class Patient extends Person{
    private int uniqueId;

    public Patient(String name, String surName, String dateOfBirth, String  mobileNum, int uniqueId) {
        super(name, surName, dateOfBirth, mobileNum);
        this.uniqueId = uniqueId;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }
}
