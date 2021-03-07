/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import admin.Admin;
import common.ConsoleColors;
import common.UserRole;
import doctor.Doctor;
import common.Validate;
import common.DataIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserController {

    final DataIO userDataIO;
    User newUser;
    private static UserController userController;

    public UserController() {
        userDataIO = new DataIO();
    }

    public static UserController getInstance() {
        if (userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    public Boolean login(User user) throws Exception {
        //Doc file
        List<User> users = UserView.getInstance().getUsers();

        users.forEach(u -> {
            if ((u.getUserRole() == UserRole.ADMIN || u.getUserRole() == UserRole.AUTHORIZED_DOCTOR)
                    && (u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword()))) {
                newUser = new User();
                newUser.setUserName(user.getUserName());
                newUser.setPassword(user.getPassword());
                newUser.setUserCode(u.getUserCode());
                newUser.setUserRole(u.getUserRole());
            }
        });

        return (newUser != null);
    }

    public void logout() {
        this.newUser = null;
    }

    public boolean validatePassword(String userCode, String oldPassword) throws Exception{
        List<User> users = userDataIO.readData();

        for (User u : users) {
            if (u.getUserCode() != null && u.getUserCode().equalsIgnoreCase(userCode)) {
                return newUser.getPassword().equals(oldPassword);
            }
        }

        return false;
    }

    public boolean changePassword(String userCode, String newPassword) throws Exception{
        User user = UserController.getInstance().getLoggedInUser();
        user.setPassword(newPassword);
        if (UserView.getInstance().updateUser(user)) {
            return true;
        }

        return false;

    }

    public String inputUserCode() throws IOException {
        UserView uv = new UserView();
        uv.users = uv.getUsers();
        while (true) {
            String code = Validate.getUsername("input new user code: ");
            for (User u : uv.users) {
                if (u.getUserCode() != null && u.getUserCode().equalsIgnoreCase(code)) {//chi check nhung user co usercode
                    code = null;
                    break;
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
            String userName = Validate.getUsername("Type in the new UserName: ");
            for (User u : uv.users) {
                if (u.getUserName() != null && u.getUserName().equals(userName)) {
                    userName = null;
                    break;
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
            choice = Validate.getIntLimit("Your choice: ", 0, 4);
            if (choice == 0) {
                return;
            }
            String userCode = inputUserCode();
            String userName = inputUserName();
            String password;
            switch (choice) {
                case 1://admin
                    password = Validate.getPassword(askPass);
                    Admin newAdmin = new Admin(userCode, userName, password, UserRole.ADMIN);
                    uv.addUser(newAdmin);
                    break;

                case 2://authDoctor
                    String authDocName = Validate.getUsername("Enter the doctor name: ");
                    password = Validate.getPassword(askPass);
                    int authDocId = uv.getNewDoctorHighestID();
                    Doctor newAuthDoctor = new Doctor(userCode, userName, password, UserRole.AUTHORIZED_DOCTOR);
                    newAuthDoctor.setDoctorId(authDocId);
                    newAuthDoctor.setName(authDocName);
                    System.out.print(askDoctorSpecialization);
                    newAuthDoctor.setSpecialization(Validate.selectSpecialization());
                    newAuthDoctor.setAvailability(Validate.getDateCurrent(askDoctorAvailability));
                    uv.addUser(newAuthDoctor);
                    break;

                case 3://doctor
                    String docName = Validate.getUsername("Enter the doctor name: ");
                    int docID = uv.getNewDoctorHighestID();
                    Doctor newDoctor = new Doctor(userCode, userName, null, UserRole.DOCTOR);
                    newDoctor.setDoctorId(docID);
                    newDoctor.setName(docName);
                    System.out.print(askDoctorSpecialization);
                    newDoctor.setSpecialization(Validate.selectSpecialization());
                    newDoctor.setAvailability(Validate.getDateCurrent(askDoctorAvailability));
                    uv.addUser(newDoctor);
                    break;

                case 4://normal user
                    password = Validate.getPassword("Type in your Password: ");
                    User u = new User(userName, password, UserRole.USER);
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
            String pass = Validate.getPassword("Type in this account new password: ");
            if (pass.equals(Validate.getPassword("Confirm account new password: "))) {
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
