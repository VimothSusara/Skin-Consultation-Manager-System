import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    public static WestminsterSkinConsultationManager program = new WestminsterSkinConsultationManager();
    public static ArrayList<Doctor> sortedDocs;
    public static char[] chars = {'1','2','3','4','5','6','7','8','9','-','/'};
    public static ArrayList<Doctor> doctors = new ArrayList<>();
    public static ArrayList<String> doctorIdArray = new ArrayList<>();

    //Adding a new doctor to the list.
    public void addNewDoctor(ArrayList<Doctor> doctors,char[] chars) {

        Scanner sc = new Scanner(System.in);

        //Adding doctor name.
        String docName;
        //Validation check for the 'docName' input.
        // Checks if the input contains any numbers or specific characters other than string.
        while (true) {
            System.out.println("Enter doctor name:");
            docName = sc.next();
            boolean validName = nameValidation(docName, chars);
            if (validName) {
                break;
            }
            else{
                System.out.println("Invalid name input! Please try again.");
            }
        }

        //Adding doctor surname.
        String docSurname;
        while (true) {
            System.out.println("Enter doctor surname:");
            docSurname = sc.next();
            boolean validSurname = nameValidation(docSurname, chars);
            if (validSurname) {
                break;
            }
            else{
                System.out.println("Invalid surname input! Please try again.");
            }
        }

        //Adding Date of Birth of the doctor.
        String docDOB;
        while (true){
            System.out.println("Enter doctor date of birth(dd-MM-yyyy):");
            docDOB = sc.next();
            if(!dateValidation(docDOB)){
                System.out.println("Invalid input for the date!");
            }
            else{
                break;
            }
        }

        //Adding Mobile number of doctor.
        String docMobiNo;
        while (true){
            System.out.println("Enter doctor mobile number:");
            docMobiNo = sc.next();
            //Checking if the first number is always a '0' in the number.
            if(docMobiNo.charAt(0)!='0'){
                System.out.println("Mobile number should start with '0' ");
            }
            else if(!mobiNoValidation(docMobiNo)){
                System.out.println("Invalid mobile number!");
            }
            else{
                break;
            }
        }

        //Adding Medical License number of doctor.
        String docLicNo;
        while(true) {
            String medValid="";
            System.out.println("Enter doctor medical license number:");
            docLicNo = sc.next();
            for (Doctor doctor : doctors) {
                //Two doctors cannot have same medical license numbers.
                if (doctor.getMedicalLicenseNumber().equals(docLicNo)) {
                    System.out.println("This medical license number already exists. Please try a different number.");
                    medValid = "done";
                    break;
                }
            }
            if (medValid.equals("")){
                break;
            }
        }

        //Adding the specialisation of doctor.
        String docSpecial;
        while (true) {
            System.out.println("Enter doctor specialisation:");
            docSpecial = sc.next();
            boolean validSpecial = nameValidation(docSpecial,chars);
            if(validSpecial){
                break;
            }
            else{
                System.out.println("Invalid specialisation input! Please try again.");
            }
        }

        doctorIdArray.add(docLicNo);
        Doctor doc = new Doctor(docName, docSurname, docDOB, docMobiNo, docLicNo, docSpecial);
        doctors.add(doc);
        System.out.println("Doctor added successfully.");

    }

    //validating the input strings(name,surname,...)
    public boolean nameValidation(String name,char[] chars){
        for (int i=0;i<name.length();i++){
            char chr = name.charAt(i);
            for (char aChar : chars) {
                if (aChar == chr) {
                    return false;
                }
            }
        }
        return true;
    }

    //checking if the date has the formatted pattern in the input and if not it notifies the user.
    public boolean dateValidation(String docDOB){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try{
            Date dob = dateFormat.parse(docDOB);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Validating mobile number input.
    //Checking if the input contains 10 digits by regular expression.
    public boolean mobiNoValidation(String mobile){
        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(mobile);
        return (m.matches());
    }

    //Deleting a doctor if a doctor has the same medical license number as the input number.
    public void deleteDoctor(ArrayList<Doctor> doctors){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter medical license number to delete the doctor:");
        String mediLicNoToDelete = sc.next();
        for (int i = 0; i < doctors.size(); i++) {
            //Checking if the medical numbers match.
            if (doctors.get(i).getMedicalLicenseNumber().equals(mediLicNoToDelete)) {
                System.out.println("Doctor Details :\n");
                System.out.println(doctors.get(i).printDetails());
                doctors.remove(i);
                System.out.println("Doctor deleted!!!!!!");
                System.out.println("Now the total number of doctors in the center is " + doctors.size());
                break;
            }
        }
    }

    //Printing the sorted doctor list
    public void printDoctorList(){
        sortedDocs = (ArrayList<Doctor>)doctors.clone();
        System.out.println("Sorting the doctor list..........\n");
        sortedDocs.sort(new SurnameCompare());
        for(Doctor docs : sortedDocs){
            System.out.println(docs.printDetails());
        }
    }

    //Saving the doctors to a file as objects.
    public void saveDetails(ArrayList<Doctor> doctors){

        try {
            FileOutputStream fos = new FileOutputStream("myFile.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(doctors);

            oos.close();
            fos.close();

            System.out.println("Successfully saved to the file.");

        } catch (IOException e) {
            System.out.println("Failed to save to the file!");
        }

    }

    //Reading the saved doctor objects from the file and storing them in 'doctors' arraylist.
    public void readDetails() {

        try {
            FileInputStream fis = new FileInputStream("myFile.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            doctors = (ArrayList<Doctor>)ois.readObject();
            System.out.println("Read the file successfully.");
            System.out.println(doctors.size() + "  doctors have been allocated to the system from the external file.\n");

            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e){
            System.out.println("File reading failed!. File is empty.\n");
        }
    }

    //Comparing surname of the doctors to sort the doctors.
    class SurnameCompare implements Comparator<Doctor>{
        @Override
        public int compare(Doctor o1,Doctor o2){
            return o1.getSurName().toLowerCase().compareTo(o2.getSurName().toLowerCase());
        }
    }

    //Main method
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------Skin Consultation Center------------------------\n");
        System.out.println("Type 'start' to start the program.");
        System.out.println("Press 'A' or 'a' to add a doctor.");
        System.out.println("Press 'D' or 'd' to delete a doctor.");
        System.out.println("Press 'P' or 'p' to show the doctor list.");
        System.out.println("Press 'S' or 's' to save doctor details.");
        System.out.println("Press 'R' or 'r' to read doctors.");
        System.out.println("Press 'C' or 'c' to clear all the doctors.");
        System.out.println("Press 'Q' or 'q' to quit from the program.");
        System.out.println("Press 'I' or 'i' for Consultations.\n");

        //Calling method to read doctor data which is stored in the file.
        program.readDetails();

        String start;
        while (true){
            System.out.println("Enter 'start' to Start the program.");
            start = sc.next();
            if(start.equals("start") || start.equals("Start")){
                System.out.println("Program Starting.............");
                break;
            }
            else{
                System.out.println("Invalid input to start the program");
            }
        }
        while (start.equals("start")){
            System.out.println("Select an option:");
            String select = sc.next();

            switch (select) {
                //adding a new doctor
                case "A", "a" -> {
                    if (doctors.size() <= 10) {
                        program.addNewDoctor(doctors, chars);
                    } else {
                        System.out.println("Doctor list is full!");
                    }
                }

                //deleting a doctor
                case "D", "d" -> {
                    if (doctors.size() > 0) {
                        program.deleteDoctor(doctors);
                    } else {
                        System.out.println("No doctors to remove!");
                    }
                }

                //printing doctors
                case "P", "p" -> {
                    if (doctors.size() == 0) {
                        System.out.println("No doctors available to print.");
                    } else {
                        program.printDoctorList();
                    }
                }

                //saving doctors to an external file.
                case "S", "s" -> program.saveDetails(doctors);

                //reading stored data from the external file.
                case "R", "r" -> program.readDetails();

                //clearing all the doctors available.
                case "C", "c" -> {
                    doctors.clear();
                    System.out.println("Cleared all the doctors......");
                }

                //calling the GUI main method
                case "I", "i" -> GUIDesign.main(null);

                //Exit from the program.
                case "Q", "q" -> {
                    System.out.println("Ending the program................");
                    start = "end";
                }

                default -> System.out.println("Enter a valid option!");

            }
        }
    }
}
