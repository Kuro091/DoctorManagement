package patient;

import common.UserRole;
import doctor.Doctor;
import user.User;
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
        if ((patientList != null) && (!patientList.isEmpty())) {
            StringBuilder print = new StringBuilder("");
            sortByDiseaseType(patientList);
            String currentDisease = patientList.get(0).getDiseaseType();
            print.append(currentDisease).append(System.lineSeparator());
            for (Patient currentPatient : patientList) {
                if (!currentPatient.getDiseaseType().equals(currentDisease)) {
                    currentDisease = currentPatient.getDiseaseType();
                    print.append(currentDisease).append(System.lineSeparator());
                }
                print.append(currentPatient.toString()).append(System.lineSeparator());
            }
            return print.toString();
        }
        return "No patient found";
    }

    /**
     * Get patient form one doctor into the list
     *
     * @param currentDoctor
     * @param patientList
     */
    private static void getPatientFromOneDoctor(Doctor currentDoctor, List<Patient> patientList) {
        ArrayList<Patient> tempPatientList = (ArrayList<Patient>) currentDoctor.getPatients();
        if (tempPatientList != null) {
            for (Patient patient : tempPatientList) {
                patientList.add(patient);
            }
        }
    }

    /**
     * Get all patients of all doctors
     *
     * @param userList
     * @return
     */
    public static List<Patient> getAllPatientsFromDoctors(List<User> userList) {
        ArrayList<Patient> patientList = new ArrayList<>();
        if ((userList != null) && (!userList.isEmpty())) {
            for (User currentUser : userList) {
                if ((currentUser.getUserRole() == UserRole.AUTHORIZED_DOCTOR) && (currentUser instanceof Doctor)) {
                    Doctor currentDoctor = (Doctor) currentUser;
                    getPatientFromOneDoctor(currentDoctor, patientList);
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
        if ((patientList != null) && (!patientList.isEmpty())) {
            patientList.sort(new SortByDiseaseType());
        }
        return patientList;
    }
}
// ******************* Sorting class *******************

class SortByDiseaseType implements Comparator<Patient> {

    @Override
    public int compare(Patient o1, Patient o2) {
        if (o1.getDiseaseType() == null) {
            o1.setDiseaseType("");
        }
        if (o2.getDiseaseType() == null) {
            o2.setDiseaseType("");
        }
        return o1.getDiseaseType().compareTo(o2.getDiseaseType());
    }

}
