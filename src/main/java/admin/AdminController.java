package admin;

import common.ConsoleColors;
import patient.Patient;
import common.UserRole;
import common.Validate;
import java.io.IOException;
import doctor.Doctor;
import user.User;
import common.DataIO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminController {

    ValidationAdminManager adminManager;
    DataIO userDataIO;
    List<User> listUsers;
    List<Patient> listPatients;
    Doctor doctorGotByUserCode;
    SimpleDateFormat dateFormat;

    public AdminController() {
        adminManager = new ValidationAdminManager();
        userDataIO = new DataIO();
        dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        initMemoryData();
    }
    
    public List<User> getListUsers() {
        return listUsers;
    }
    
    public void addUpPatient() throws IOException {
        //--------Đọc data, xóa sau
        initMemoryData();
        while (true) {
            listUsers = userDataIO.readData();

            System.out.println(ConsoleColors.BLUE_BOLD + "LIST DOCTOR");
            System.out.println(String.format("%-10s|%-20s", "USERCODE", "NAME"));
            for (User user : listUsers) {
                if (user.getUserRole() == UserRole.AUTHORIZED_DOCTOR) {
                    System.out.println(user);
                }
            }
            System.out.println("");

            doctorGotByUserCode = getDoctorByUserCode();
            listPatients = doctorGotByUserCode.getPatients();

            if (listPatients.isEmpty()) {
                System.out.println(ConsoleColors.RED + "List patient's this doctor is emty");
            } else {
                System.out.println(ConsoleColors.BLUE_BOLD + "LIST PATIENT");
                System.out.println(String.format("%-10s|%-15s|%-15s|%-15s|%-15s", "ID", "NAME", "DESEASE TYPE", "CONSULT DATE", "CONSULT NOTE"));
                listPatients.forEach(patient -> System.out.println(patient.toString(dateFormat)));
                System.out.println("");
            }

            menuAddUpdatePatient();
            int selection = Validate.getIntLimit("Enter selection: ", 1, 2);
            switch (selection) {
                case 1:
                    addNewPatient();
                    userDataIO.writeData(listUsers);
                    break;
                case 2:
                    if (!listPatients.isEmpty()) {
                        updateAPatient();
                        userDataIO.writeData(listUsers);
                    } else {
                        System.out.println("This doctor is not in charge of any patient");
                    }
                    break;
                default:
                    break;
            }
            break;
        }
    }

    private void menuAddUpdatePatient() {
        System.out.println(ConsoleColors.BLUE_BOLD + "-----------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Add new a patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Update a patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "-----------------------------------");
    }

    private void addNewPatient() throws IOException {
        while (true) {
            int patientid = Validate.getIntLimit("Enter patient id: ", 1, Integer.MAX_VALUE);
            Patient patient = adminManager.getPatientByPatientID(patientid, listPatients);
            if (patient != null) {
                System.out.println(ConsoleColors.RED + "ID exist");
            } else {
                String name = Validate.getString("Enter name: ");
                String diseaseType = Validate.getString("Enter diseaseType: ");
                Date consultDate = Validate.getDateCurrent("Enter consultDate: ");
                String consultNote = Validate.getString("Enter consultNote: ");
                listPatients.add(new Patient(patientid, name, diseaseType, consultDate, consultNote));
                return;
            }
        }
    }

    private void updateAPatient() throws IOException {
        while (true) {
            int patientid = Validate.getIntLimit("Enter patient id: ", 1, Integer.MAX_VALUE);
            Patient patient = adminManager.getPatientByPatientID(patientid, listPatients);
            if (patient == null) {
                System.out.println(ConsoleColors.RED + "ID is not exist");
            } else {
                String newName = Validate.getString("Enter name: ");
                String newDiseaseType = Validate.getString("Enter diseaseType: ");
                Date newConsultDate = Validate.getDateCurrent("Enter consultDate: ");
                String newConsultNote = Validate.getString("Enter consultNote: ");

                patient.setPatientName(newName);
                patient.setDiseaseType(newDiseaseType);
                patient.setConsultDate(newConsultDate);
                patient.setConsultNote(newConsultNote);
                return;
            }
        }
    }

    public void queryDoctorInfo() throws IOException {
        while (true) {
            listUsers = userDataIO.readData();
            System.out.println(ConsoleColors.BLUE_BOLD + "List of all doctors: ");
            listUsers.forEach(u -> {
                if (u.getUserRole() == UserRole.DOCTOR || u.getUserRole() == UserRole.AUTHORIZED_DOCTOR) {
                    Doctor doc = (Doctor) u;
                    System.out.println(doc.getDoctorId() + " | " + doc.getName());
                }
            });

            int doctorCode = Validate.getIntLimit("Enter doctor code (Enter 0 to exit): ", 0, Integer.MAX_VALUE);

            if (doctorCode == 0) {
                break;
            }

            listUsers.forEach(u -> {
                if (u.getUserRole() == UserRole.DOCTOR || u.getUserRole() == UserRole.AUTHORIZED_DOCTOR) {
                    Doctor doc = (Doctor) u;
                    if (doc.getDoctorId() == doctorCode) {
                        System.out.println(ConsoleColors.BLUE_BOLD + "DoctorCode : " + doc.getDoctorId() + "| DoctorName " + doc.getName());
                        System.out.println(ConsoleColors.BLUE_BOLD + "Availability : " + doc.getAvailability() + "| Spec: " + doc.getSpecialization());
                        System.out.println(ConsoleColors.BLUE_BOLD + "Patients : ");

                        doc.getPatients().forEach(p -> {
                            System.out.println(ConsoleColors.PURPLE_BOLD + "PatientName: " + p.getPatientName() + " |PatientDisease: " + p.getDiseaseType() + " |Note: " + p.getConsultNote());
                            System.out.println(ConsoleColors.PURPLE_BOLD + "Note: " + p.getConsultNote());
                            System.out.println("***");
                        });

                        System.out.println("------------");
                    }
                }
            });
        }
    }

    private Doctor getDoctorByUserCode() throws IOException {
        while (true) {
            String usercode = Validate.getString("Enter usercode: ");
            Doctor doctor = (Doctor) adminManager.getDoctorByUserCode(usercode, listUsers);
            if (doctor == null) {
                System.out.println(ConsoleColors.RED + "This usercode does not exist,  enter a new usercode ");
            } else {
                return doctor;
            }
        }
    }

    private void initMemoryData() {
        listUsers = new ArrayList<>();
        listPatients = new ArrayList<>();
        doctorGotByUserCode = null;
    }

}
