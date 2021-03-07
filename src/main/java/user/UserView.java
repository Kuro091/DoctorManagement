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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserView {

    DataIO userDataIO;
    Scanner in = new Scanner(System.in);
    List<User> users;
    private static UserView userView = null;

    public UserView() {
        users = new ArrayList<>();
        userDataIO = new DataIO();
    }

    public static UserView getInstance() {
        if (userView == null) {
            userView = new UserView();
        }
        return userView;
    }

    public List<User> getUsers() {
        return userDataIO.readData();
    }

    public int getUsersSize() {
        return userDataIO.readData().size();
    }

    public void addUser(User user) {
        users = userDataIO.readData();
        users.add(user);
        userDataIO.writeData(users);
    }

    public void deleteUser(String userCode) {
        users = userDataIO.readData();
        for (User u : users) {
            if (u.getUserCode() != null && u.getUserCode().equals(userCode)) {
                users.remove(u);
                break;
            }
        }
        userDataIO.writeData(users);
    }

    User user;

    public User getLoginInfo() {

        try {
            //Read userInput
            String userName;
            String password;

            userName = Validate.getString("Input username: ");
            password = Validate.getString("Input password: ");

            return new User(userName, password, UserRole.USER);

        } catch (IOException ex) {
            Logger.getLogger(UserView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void getChangePasswordInfo(){
        while (true) {
            try {
                System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");
                System.out.println(ConsoleColors.BLUE_BOLD + "CHANGE PASSWORD");
                System.out.println(ConsoleColors.BLUE_BOLD + "1. Change Password");
                System.out.println(ConsoleColors.BLUE_BOLD + "0. Cancel");
                System.out.println(ConsoleColors.BLUE_BOLD + "--------------------------------");

                int choice = Validate.getIntLimit("Your choice: ", 0, 1);

                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        User user = UserController.getInstance().getLoggedInUser();
                        if (user != null) {
                            String oldPassword = Validate.getString("Enter old password: ");
                            if (UserController.getInstance().validatePassword(user.getUserCode(), oldPassword)) {
                                String newPassword = Validate.getPassword("Enter new password: ");
                                String confirmNewPassword = Validate.getPassword("Confirm new password: ");
                                if (confirmNewPassword.equals(newPassword)) {
                                    if(UserController.getInstance().changePassword(user.getUserCode(), newPassword)){
                                        System.out.println(ConsoleColors.GREEN_BOLD + "Password changed successfully!!");
                                    }else{
                                        System.out.println(ConsoleColors.GREEN_BOLD + "Password changed failed!!");
                                    }
                                } else {
                                    System.out.println(ConsoleColors.RED + "Passwords don't match!!");
                                }
                            } else {
                                System.out.println(ConsoleColors.RED + "Wrong password!!");
                            }
                        }
                        break;
                    default:
                        break;
                }

            } catch (IOException ex) {
                Logger.getLogger(UserView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    int count;

    public boolean updateUser(User userUpdate) {
        users = userDataIO.readData();
        count = 0;
        users.forEach(u -> {
            if (u.getUserCode() != null && u.getUserCode().equalsIgnoreCase(userUpdate.getUserCode())) {
                count++;
                u.setUserName(userUpdate.getUserName());
                u.setPassword(userUpdate.getPassword());
            }
        });
        userDataIO.writeData(users);
        return (count > 0);
    }

    public String inputUserCode() throws IOException {
        while (true) {
            String code = Validate.getUsername("input new user code: ");
            for (User u : users) {
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
        while (true) {
            String userName = Validate.getUsername("Type in the new UserName: ");
            for (User u : users) {
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

    public int getNewDoctorHighestID() {
        int id = 0;
        for (User u : users) {
            if (u.getUserRole().equals(UserRole.DOCTOR) || u.getUserRole().equals(UserRole.AUTHORIZED_DOCTOR)) {
                int checkID = ((Doctor) u).getDoctorId();
                if (checkID >= id) {
                    id = checkID;
                }
            }
        }
        return id + 1;
    }

    // function4.3
    public void findAndUpdateByUserCode() throws IOException {
        users = getUsers();
        while (true) {
            String code = Validate.getUsername("Enter userCode needed to be update: ");
            users = userDataIO.readData();
            for (User find : users) {
                if (find.getUserCode() != null && find.getUserCode().equals(code)) {
                    UserController uc = new UserController();
                    find = uc.askUpdate(find);
                    updateUser(find);
                    return;
                }
            }
            System.out.println("Can't find the userCode: " + code);
            System.out.println("please re-Enter the userCode that need to be update");
        }
    }

    // function4.4
    public void findAndDeletedByUserCode() throws IOException {
        users = getUsers();
        String code = Validate.getUsername("Enter usercode needed to be deleted: ");
        deleteUser(code);
    }

    //function4.1
    public boolean showAllUser() {
        users = getUsers();
        System.out.println("List of all User");
        for (User u : users) {
            if (u instanceof Doctor) {
                System.out.print("DoctorID: " + ((Doctor) u).getDoctorId() + "; ");
            } else if (u instanceof Admin) {
                System.out.print("Admin user: ");
            } else {
                System.out.print("Normal user: ");
            }
            System.out.println(u.showUserInfo());
        }
        System.out.println("");
        return true;
    }

}
