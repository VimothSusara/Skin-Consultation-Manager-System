public class Doctor extends Person {
    private String medicalLicenseNumber;
    private String specialisation;

    public Doctor(String name, String surName, String dateOfBirth, String mobileNum, String medicalLicenseNumber, String specialisation) {
        super(name,surName,dateOfBirth,mobileNum);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialisation = specialisation;
    }

    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }
    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String printDetails(){
        return "Doctor name :"+this.getName()+
                "\nDoctor surname: "+this.getSurName()+
                "\nDoctor date of birth: "+this.getDateOfBirth()+
                "\nDoctor mobile number: "+this.getMobileNum()+
                "\nDoctor medical license number: "+this.medicalLicenseNumber+
                "\nDoctor specialisation: "+this.specialisation+"\n---------------------------------\n";
    }

}
