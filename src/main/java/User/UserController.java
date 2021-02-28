/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Admin.Admin;
import Common.ConsoleColors;
import Common.UserRole;
import Doctor.Doctor;
import static User.UserView.validate;
import Utilities.UserDataIO;
import Utilities.Validate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserController {

    public static UserController userController = null;
    private UserDataIO userDataIO;
    private Validate validate;

    public UserController() {
        userDataIO = new UserDataIO();
        validate = new Validate();
    }

    public static UserController getInstance() {
        if (userController == null) {
            userController = new UserController();
        }

        return userController;
    }

    //Return true if log in successfully
    //Return false if not
    
    User newUser;
    public Boolean login(User user) {
        
        try {
            //Doc file
            ArrayList<User> users = UserView.getInstance().getUsers();

            users.forEach((u) -> {
                if (u.getUserRole() == UserRole.ADMIN || u.getUserRole() == UserRole.AUTHORIZED_DOCTOR) {
                    if (u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())) {
                        newUser = new User();
                        newUser.setUserName(user.getUserName());
                        newUser.setPassword(user.getPassword());
                        newUser.setUserCode(u.getUserCode());
                        newUser.setUserRole(u.getUserRole());
                    }
                }
            });

            return (newUser!=null);

        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public void logout() {
        this.newUser = null;
    }

    public void changePassword() {

        while (true) {
            try {
                System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
                System.out.println(ConsoleColors.BLUE_BOLD + "CHANGE PASSWORD");
                System.out.println(ConsoleColors.BLUE_BOLD + "1. Change Password");
                System.out.println(ConsoleColors.BLUE_BOLD + "0. Cancel");
                System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");

                int choice = validate.getINT_LIMIT("Your choice: ", 0, 1);

                switch (choice) {
                    case 0:

                        return;

                    case 1:
                        if (newUser != null) {

                            String oldPassword = validate.getString("Enter old password: ");
                            if (newUser.getPassword().equals(oldPassword)) {

                                String newPassword = validate.getPassword("Enter new password: ");
                                String confirmNewPassword = validate.getPassword("Confirm new password: ");

                                if (confirmNewPassword.equals(newPassword)) {
                                    newUser.setPassword(newPassword);
                                    UserView.getInstance().updateUser(newUser);

                                    System.out.println(ConsoleColors.GREEN_BOLD + "Password changed successfully!!");
                                } else {
                                    System.out.println(ConsoleColors.RED + "Passwords don't match!!");
                                }

                            } else {
                                System.out.println(ConsoleColors.RED + "Wrong password!!");
                            }

                        }
                        break;
                }

            } catch (IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String inputUserCode() throws IOException {
        UserView uv = new UserView();
        uv.users = uv.getUsers();
        while (true) {
            String code = validate.getUsername("input new user code: ");
            for (User u : uv.users) {
                if (u.getUserCode() != null) {//chi check nhung user co usercode
                    if (u.getUserCode().equalsIgnoreCase(code)) {
                        code = null;
                        break;
                    }
                }
            }
            if (code == null) {
                System.out.println("this code already exist pls input another one");
            } else {
                return code;
            }
        }
    }

    public String inputUserName() throws IOException {
        UserView uv = new UserView();
        uv.users = uv.getUsers();
        while (true) {
            String userName = validate.getUsername("Type in the new UserName: ");
            for (User u : uv.users) {
                if (u.getUserName() != null) {
                    if (u.getUserName().equals(userName)) {
                        userName = null;
                        break;
                    }
                }
            }
            if (userName == null) {
                System.out.println("this userName already exist pls input a different one");
            } else {
                return userName;
            }
        }
    }
    
    // function4.2
    public void inputNewUser() {
        UserView uv = new UserView();
        String askPass = "Type in your Password: ";
        String askDoctorSpecialization = "Enter doctor Specialization: ";
        String askDoctorAvailability = "Enter availability: ";
        int choice;
        try {
            System.out.println("what account you want to create\n" + "1.Admin\n" + "2.Authorized_Doctor\n"
                    + "3.Doctor\n" + "4.Normal User\n" + "0.Cancel");
            choice = validate.getINT_LIMIT("Your choice: ", 0, 4);
            if (choice == 0) {
                return;
            }
            String UserCode = inputUserCode();
            String UserName = inputUserName();
            String password;
            switch (choice) {
                case 1://admin
                    password = validate.getPassword(askPass);
                    Admin newAdmin = new Admin(UserCode, UserName, password, UserRole.ADMIN);
                    uv.addUser(newAdmin);
                    break;

                case 2://authDoctor
                    String authDocName = validate.getUsername("Enter the doctor name: ");
                    password = validate.getPassword(askPass);
                    int AuthDocID = uv.getNewDoctorHighestID();
                    Doctor newAuthDoctor = new Doctor(UserCode, UserName, password, UserRole.AUTHORIZED_DOCTOR);
                    newAuthDoctor.setDoctorId(AuthDocID);
                    newAuthDoctor.setName(authDocName);
                    System.out.print(askDoctorSpecialization);
                    newAuthDoctor.setSpecialization(Doctor.selectSpecialization());
                    newAuthDoctor.setAvailability(validate.getDate_LimitToCurrent(askDoctorAvailability));
                    uv.addUser(newAuthDoctor);
                    break;

                case 3://doctor
                    String docName = validate.getUsername("Enter the doctor name: ");
                    int docID = uv.getNewDoctorHighestID();
                    Doctor newDoctor = new Doctor(UserCode, UserName, null, UserRole.DOCTOR);
                    newDoctor.setDoctorId(docID);
                    newDoctor.setName(docName);
                    System.out.print(askDoctorSpecialization);
                    newDoctor.setSpecialization(Doctor.selectSpecialization());
                    newDoctor.setAvailability(validate.getDate_LimitToCurrent(askDoctorAvailability));
                    uv.addUser(newDoctor);
                    break;

                case 4://normal user
                    password = validate.getPassword("Type in your Password: ");
                    User u = new User(UserName, password, UserRole.USER);
                    uv.addUser(u);
                    break;
                case 0:
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            System.out.println("error inputNewUser");
            System.out.println(e);
        }
    }

    public User askUpdate(User updateMe) throws IOException {
        updateMe.setUserName(inputUserName());
        while (true) {
            String pass = validate.getPassword("Type in this account new password: ");
            if (pass.equals(validate.getPassword("Confirm account new password: "))) {
                updateMe.setPassword(pass);
                break;
            } else {
                System.out.println("confirm new password is wrong! pls retype new password");
            }
        }
        return updateMe;
    }
    
    public User getLoggedInUser() {
        return newUser;
    }

}
