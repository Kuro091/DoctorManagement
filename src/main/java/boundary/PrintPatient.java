package boundary;

import Common.Patient;
import Common.UserRole;
import Doctor.Doctor;
import User.User;
import boundary.Validate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Dùng class để in danh sách bệnh nhân ra màn hình: gọi hàm callPrintPatientByDiseaseType
 * @author Vân
 */
public class PrintPatient {
    /**
     * Call print function: print to console list of all patients sort by disease type
     * @param userList list user
     */
    public static void callPrintPatientByDiseaseType(ArrayList<User> userList) {
        System.out.println(printPatientsByDiseaseType(getAllPatientsFromDoctors(userList)));
    }
    
    /**
     * Return a string list of all patients sort by disease type
     * @param patientList
     * @return 
     */
    public static String printPatientsByDiseaseType(ArrayList<Patient> patientList) {
        patientList = sortByDiseaseType(patientList);
        String print = "";
        String currentDisease = patientList.get(0).getDiseaseType();
        print += currentDisease + System.lineSeparator();
        for (Patient currentPatient : patientList) {
            if (!currentPatient.getDiseaseType().equals(currentDisease)) {
                currentDisease = currentPatient.getDiseaseType();
                print += currentDisease + System.lineSeparator();
            }
            print += currentPatient.toString() + System.lineSeparator();
        }
        return print;
    }

    /**
     * Get all patients of all doctors
     * @param userList
     * @return 
     */
    public static ArrayList<Patient> getAllPatientsFromDoctors(ArrayList<User> userList) {
        // Get all patients of all doctors
        ArrayList<Patient> patientList = new ArrayList<>();
        for (User currentUser : userList) {
            if (currentUser.getUserRole() == UserRole.AUTHORIZED_DOCTOR) {
                System.out.println("Found a doctor! now checking patient list");
                Doctor currentDoctor = (Doctor) currentUser;
                for (Patient patient : currentDoctor.getPatients()) {
                    System.out.println("Found patient ID = " + patient.getPatientId());
                    patientList.add(patient);
                }
            }
        }
        return patientList;
    }

    /**
     * Sort patient list by disease type
     * @param patientList
     * @return 
     */
    public static ArrayList<Patient> sortByDiseaseType(ArrayList<Patient> patientList) {
        patientList.sort(new sortByDiseaseType());
        return patientList;
    }
}
// ******************* Sorting class *******************

class sortByDiseaseType implements Comparator<Patient> {

    @Override
    public int compare(Patient o1, Patient o2) {
        return o1.getDiseaseType().compareTo(o2.getDiseaseType());
    }

}
