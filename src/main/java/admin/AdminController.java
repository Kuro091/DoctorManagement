/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Admin
 */
public class AdminController {

    ValidationAdminManager adminManager;
    DataIO<User> userDataIO;
    ArrayList<User> listUsers;
    ArrayList<Patient> listPatients;
    Doctor doctorGotByUserCode;
    SimpleDateFormat dateFormat;

    public AdminController() {
        adminManager = new ValidationAdminManager();
        userDataIO = new DataIO<>("users.dat");
        initMemoryData();
        dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
    }

    public void processing() throws IOException {
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

            while (true) {
                String usercode = Validate.getString("Enter usercode: ");
                doctorGotByUserCode = (Doctor) adminManager.getDoctorByUserCode(usercode, listUsers);
                if (doctorGotByUserCode == null) {
                    System.out.println(ConsoleColors.RED + "This usercode does not exist,  enter a new usercode ");
                } else {
                    break;
                }
            }

            listPatients = doctorGotByUserCode.getPatients();

            if (listPatients.isEmpty()) {
                System.out.println(ConsoleColors.RED + "List patient's this doctor is emty");
            } else {
                System.out.println(ConsoleColors.BLUE_BOLD + "LIST PATIENT");
                System.out.println(String.format("%-10s|%-15s|%-15s|%-15s|%-15s", "ID", "NAME", "DESEASE TYPE", "CONSULT DATE", "CONSULT NOTE"));
                listPatients.forEach((patient) -> {
                    System.out.println(patient.toString(dateFormat));
                });
                System.out.println("");
            }

            printMENU_AddUpdatePatient();
            int selection = Validate.getINT_LIMIT("Enter selection: ", 1, 2);
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
            }
            break;
        }
    }

    private void printMENU_AddUpdatePatient() {
        System.out.println(ConsoleColors.BLUE_BOLD + "-----------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Add new a patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Update a patient");
        System.out.println(ConsoleColors.BLUE_BOLD + "-----------------------------------");
    }

    private void addNewPatient() throws IOException {
        while (true) {
            int patientid = Validate.getINT_LIMIT("Enter patient id: ", 1, Integer.MAX_VALUE);
            Patient patient = adminManager.getPatientByPatientID(patientid, listPatients);
            if (patient != null) {
                System.out.println(ConsoleColors.RED + "ID exist");
                continue;
            }

            String name = Validate.getString("Enter name: ");
            String diseaseType = Validate.getString("Enter diseaseType: ");
            Date consultDate = Validate.getDate_LimitToCurrent("Enter consultDate: ");
            String consultNote = Validate.getString("Enter consultNote: ");
            listPatients.add(new Patient(patientid, name, diseaseType, consultDate, consultNote));
            break;
        }

    }

    private void updateAPatient() throws IOException {
        while (true) {
            int patientid = Validate.getINT_LIMIT("Enter patient id: ", 1, Integer.MAX_VALUE);
            Patient patient = adminManager.getPatientByPatientID(patientid, listPatients);
            if (patient == null) {
                System.out.println(ConsoleColors.RED + "ID is not exist");
                continue;
            }

            String newName = Validate.getString("Enter name: ");
            String newDiseaseType = Validate.getString("Enter diseaseType: ");
            Date newConsultDate = Validate.getDate_LimitToCurrent("Enter consultDate: ");
            String newConsultNote = Validate.getString("Enter consultNote: ");

            patient.setPatientName(newName);
            patient.setDiseaseType(newDiseaseType);
            patient.setConsultDate(newConsultDate);
            patient.setConsultNote(newConsultNote);
            break;
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

            int doctorCode = Validate.getINT("Enter doctor code (Enter 0 to exit): ");

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

    private void initMemoryData() {
        listUsers = new ArrayList<>();
        listPatients = new ArrayList<>();
        doctorGotByUserCode = null;
    }

}
