package main;

import admin.Admin;
import admin.AdminController;
import common.ConsoleColors;
import common.UserRole;
import patient.PrintPatient;
import doctor.Specialization;
import doctor.Doctor;
import doctor.DoctorController;
import doctor.DoctorView;
import user.User;
import user.UserController;
import user.UserView;
import common.Validate;
import common.DataIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Main {

    static List<User> users;
    static UserController userController;
    static AdminController adminController;
    static DoctorController doctorController;

    
    public static void main(String[] args) {
        adminController = new AdminController();
        doctorController = new DoctorController();
        userController = UserController.getInstance();

        //------------------ADD TAM DATA VAO FILE USERS.DAT DE TEST, XOA SAU
        users = new ArrayList<>();
        users.add(new Admin("admin01", "admin01", "admin01", UserRole.ADMIN));
        users.add(new Admin("admin02", "admin02", "admin02", UserRole.ADMIN));
        users.add(new Admin("admin03", "admin03", "admin03", UserRole.ADMIN));
        users.add(new Doctor(1, "doctor01", Specialization.TIM_MACH, new Date(), new ArrayList<>(), "doctor01", "doctor01", "doctor01", UserRole.AUTHORIZED_DOCTOR));
        users.add(new Doctor(2, "doctor02", Specialization.DA_LIEU, new Date(), new ArrayList<>(), "doctor02", "doctor02", "doctor02", UserRole.AUTHORIZED_DOCTOR));
        users.add(new Doctor(3, "doctor03", Specialization.NHA_KHOA, new Date(), new ArrayList<>(), "doctor03", "doctor03", "doctor03", UserRole.AUTHORIZED_DOCTOR));
        users.add(new Doctor(4, "doctor04", Specialization.UNG_BUOU, new Date(), new ArrayList<>(), "doctor04", "doctor04", "doctor04", UserRole.AUTHORIZED_DOCTOR));
        users.add(new Doctor(5, "doctor05", Specialization.TIM_MACH, new Date(), new ArrayList<>(), UserRole.DOCTOR));
        users.add(new Doctor(6, "doctor06", Specialization.TIM_MACH, new Date(), new ArrayList<>(), UserRole.DOCTOR));
        users.add(new Doctor(7, "doctor07", Specialization.NHA_KHOA, new Date(), new ArrayList<>(), UserRole.DOCTOR));
        users.add(new Doctor(8, "doctor08", Specialization.TIM_MACH, new Date(), new ArrayList<>(), UserRole.DOCTOR));

        //------------WRITE META DATA TO FILE
        new DataIO().writeData(users);

        loginMenu();
        mainMenu();

    }

    private static void loginMenu() {
        int choice;
        while (true) {
            try {
                printLoginMenu();
                choice = Validate.getIntLimit("Your choice: ", 0, 1);
                boolean isLoggedIn = false;
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        User u = UserView.getInstance().getLoginInfo();
                        isLoggedIn = userController.login(u);
                        break;
                    default:
                }

                if (isLoggedIn) {
                    System.out.println(ConsoleColors.PURPLE_BOLD + "LOGGED IN SUCCESSFULLY!!");
                    break;
                } else {
                    System.out.println(ConsoleColors.RED_BOLD + "LOGGED IN FAILED!!");
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void mainMenu() {
        User user = userController.getLoggedInUser();
        if (user != null) {
            if (user.getUserRole() == UserRole.ADMIN) {
                adminMenu();
            } else if (user.getUserRole() == UserRole.AUTHORIZED_DOCTOR) {
                doctorMenu();
            }
        }
    }

    private static void adminMenu() {
        int choice;
        while (true) {
            try {
                printAdminMenu();
                choice = Validate.getIntLimit("Your choice: ", 1, 7);
                switch (choice) {
                    case 1:
                        DoctorView d = new DoctorView();
                        d.doFunction1();
                        break;
                    case 2:
                        adminController.addUpPatient();
                        break;
                    case 3:
                        adminController.queryDoctorInfo();
                        break;
                    case 4:
                        doFunction4();
                        break;
                    case 5:
                        PrintPatient.callPrintPatientByDiseaseType(users);
                        break;
                    case 6:
                        UserView.getInstance().getChangePasswordInfo();
                        break;
                    case 7:
                        userController.logout();
                        loginMenu();
                        mainMenu();
                        return;
                    default:
                        break;

                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }

    }

    private static void doctorMenu() {
        int choice;
        while (true) {
            try {
                printDoctorMenu();
                choice = Validate.getIntLimit("Your choice: ", 1, 4);

                switch (choice) {
                    case 1:
                        doctorController.processing(userController.getLoggedInUser());
                        break;
                    case 2:
                        adminController.queryDoctorInfo();
                        break;
                    case 3:
                        UserView.getInstance().getChangePasswordInfo();
                        break;
                    case 4:
                        userController.logout();
                        loginMenu();
                        mainMenu();
                        return;
                    default:
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }

    public static void doFunction4() throws IOException {
        UserView uv = new UserView();
        int choice;
        while (true) {
            System.out.println("1. View list of all user");
            System.out.println("2. Add user");
            System.out.println("3. Update user");
            System.out.println("4. Delete user");
            System.out.println("0. Back to main menu");
            choice = Validate.getIntLimit("Choose: ", 0, 4);
            switch (choice) {
                case 1:
                    uv.showAllUser();
                    break;
                case 2:
                    userController.inputNewUser();
                    break;
                case 3:
                    uv.findAndUpdateByUserCode();
                    break;
                case 4:
                    uv.findAndDeletedByUserCode();
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }
    }

    private static void printLoginMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "Welcome to Doctor Management Program");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Login");
        System.out.println(ConsoleColors.BLUE_BOLD + "0. Exit");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    private static void printAdminMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "ADMIN PANEL");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. View/Add/Update/Delete Doctor");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. Add/Update Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. View Doctor info incl. Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. View/Add/Update/Delete User; Change User's Password");
        System.out.println(ConsoleColors.BLUE_BOLD + "5. Query & Print out patients info");
        System.out.println(ConsoleColors.BLUE_BOLD + "6. Change password");
        System.out.println(ConsoleColors.BLUE_BOLD + "7. Logout");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

    private static void printDoctorMenu() {
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
        System.out.println(ConsoleColors.BLUE_BOLD + "DOCTOR PANEL");
        System.out.println(ConsoleColors.BLUE_BOLD + "1. Add/Update Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "2. View Doctor info incl. Patient info");
        System.out.println(ConsoleColors.BLUE_BOLD + "3. Change password");
        System.out.println(ConsoleColors.BLUE_BOLD + "4. Log out");
        System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
    }

}
