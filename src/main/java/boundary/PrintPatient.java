package boundary;

import Common.Patient;
import Common.UserRole;
import Doctor.Doctor;
import User.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dùng class để in danh sách bệnh nhân ra màn hình: gọi hàm
 * callPrintPatientByDiseaseType
 *
 * @author Vân
 */
public class PrintPatient {

    private PrintPatient() {
    }

    /**
     * Call print function: print to console list of all patients sort by
     * disease type
     *
     * @param userList list user
     */
    public static void callPrintPatientByDiseaseType(List<User> userList) {
        Logger theLogger = Logger.getLogger(PrintPatient.class.getName());
        theLogger.log(Level.INFO, "{0}", printPatientsByDiseaseType(getAllPatientsFromDoctors(userList)));
    }

    /**
     * Return a string list of all patients sort by disease type
     *
     * @param patientList
     * @return
     */
    public static String printPatientsByDiseaseType(List<Patient> patientList) {
        if (patientList != null) {
            if (!patientList.isEmpty()) {
                String print = "";
                patientList = sortByDiseaseType(patientList);
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
        }
        return "No patient found";
    }

    /**
     * Get all patients of all doctors
     *
     * @param userList
     * @return
     */
    public static List<Patient> getAllPatientsFromDoctors(List<User> userList) {
        ArrayList<Patient> patientList = new ArrayList<>();
        if (userList != null) {
            if (!userList.isEmpty()) {
                for (User currentUser : userList) {
                    if ((currentUser.getUserRole() == UserRole.AUTHORIZED_DOCTOR) && (currentUser instanceof Doctor)) {
                        Doctor currentDoctor = (Doctor) currentUser;
                        ArrayList<Patient> tempPatientList = currentDoctor.getPatients();
                        if (tempPatientList != null) {
                            for (Patient patient : tempPatientList) {
                                patientList.add(patient);
                            }
                        }

                    }
                }
            }
        }
        return patientList;
    }

    /**
     * Sort patient list by disease type
     *
     * @param patientList
     * @return
     */
    public static List<Patient> sortByDiseaseType(List<Patient> patientList) {
        if (patientList != null) {
            if (!patientList.isEmpty()) {
                patientList.sort(new SortByDiseaseType());
            }
        }
        return patientList;
    }
}
// ******************* Sorting class *******************

class SortByDiseaseType implements Comparator<Patient> {

    @Override
    public int compare(Patient o1, Patient o2) {
        return o1.getDiseaseType().compareTo(o2.getDiseaseType());
    }

}
