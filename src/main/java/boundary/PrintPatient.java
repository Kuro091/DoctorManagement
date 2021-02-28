package boundary;

import Common.Patient;
import Common.UserRole;
import Doctor.Doctor;
import User.User;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Dùng class để in danh sách bệnh nhân ra màn hình: gọi hàm
 * callPrintPatientByDiseaseType
 *
 * @author Vân
 */
public class PrintPatient {

    /**
     * Call print function: print to console list of all patients sort by
     * disease type
     *
     * @param userList list user
     */
    public static void callPrintPatientByDiseaseType(ArrayList<User> userList) {
        System.out.println(printPatientsByDiseaseType(getAllPatientsFromDoctors(userList)));
    }

    /**
     * Return a string list of all patients sort by disease type
     *
     * @param patientList
     * @return
     */
    public static String printPatientsByDiseaseType(ArrayList<Patient> patientList) {
        if (patientList != null) {
            if (patientList.size() > 0) {
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
            } else {
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
    public static ArrayList<Patient> getAllPatientsFromDoctors(ArrayList<User> userList) {
        ArrayList<Patient> patientList = new ArrayList<>();
        if (userList != null) {
            if (userList.size() > 0) {
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
    public static ArrayList<Patient> sortByDiseaseType(ArrayList<Patient> patientList) {
        if (patientList != null) {
            if (patientList.size() > 0) {
                patientList.sort(new sortByDiseaseType());
            }
        }
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
