import java.util.ArrayList;

public interface SkinConsultationManager {

     void addNewDoctor(ArrayList<Doctor> doctors,char[] chars);
     boolean nameValidation(String name,char[] chars);
     boolean dateValidation(String docDOB);
     boolean mobiNoValidation(String mobile);
     void deleteDoctor(ArrayList<Doctor> doctors);
     void printDoctorList();
     void saveDetails(ArrayList<Doctor> doctors);
     void readDetails();
}
