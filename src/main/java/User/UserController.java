/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Common.ConsoleColors;
import Common.UserRole;
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

    public User getLoggedInUser() {
        return newUser;
    }

}
