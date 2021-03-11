/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor;

import common.UserRole;
import user.User;
import common.Validate;
import common.DataIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DoctorView {

    Scanner in = new Scanner(System.in);
    List<User> users;
    DataIO userDataIO;

    public DoctorView() {
        users = new ArrayList<>();
        userDataIO = new DataIO();
    }

    public List<User> getUsers() {
        return userDataIO.readData();
    }

    public void addDoctor(User user) {
        users = userDataIO.readData();
        users.add(user);
        userDataIO.writeData(users);
    }

    public void deleteDoctor(String userCode) {
        users = userDataIO.readData();
        for (User u : users) {
            if (u.getUserCode().equals(userCode)) {
                users.remove(u);
                break;
            }
        }
        userDataIO.writeData(users);
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
                u.setUserRole(userUpdate.getUserRole());
            }
        });
        userDataIO.writeData(users);
        return (count > 0);
    }

    public String inputUserCode() throws IOException {
        while (true) {
            String code = Validate.getUsername("input new user code: ");
            for (User u : users) {
                if (u.getUserCode() != null && u.getUserCode().equalsIgnoreCase(code)) {
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
            String userName = Validate.getUsername("Type in the new doctor UserName: ");
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

    // function4.2
    public void inputNewDoctor() {
        users = getUsers();
        String askPass = "Type in the Password: ";
        String askDoctorSpecialization = "Enter doctor Specialization: ";
        String askDoctorAvailability = "Enter availability: ";
        int choice;
        try {
            System.out.println("what doctor type do you want to create\n"
                    + "1.Authorized_Doctor\n"
                    + "2.Doctor\n"
                    + "0.Cancel");
            choice = Validate.getIntLimit("Your choice: ", 0, 2);
            if (choice == 0) {
                return;
            }
            System.out.print("Enter the doctor name: ");
            String docName = in.nextLine();
            switch (choice) {
                case 1:
                    String userCode = inputUserCode();
                    String userName = inputUserName();
                    String password = Validate.getPassword(askPass);

                    int authDocId = getNewDoctorHighestID();
                    Doctor newAuthDoctor = new Doctor(userCode, userName, password, UserRole.AUTHORIZED_DOCTOR);
                    newAuthDoctor.setDoctorId(authDocId);
                    newAuthDoctor.setName(docName);
                    System.out.print(askDoctorSpecialization);
                    newAuthDoctor.setSpecialization(Validate.selectSpecialization());
                    newAuthDoctor.setAvailability(Validate.getDateCurrent(askDoctorAvailability));

                    addDoctor(newAuthDoctor);
                    break;

                case 2:
                    int docID = getNewDoctorHighestID();
                    Doctor newDoctor = new Doctor(null, null, null, UserRole.DOCTOR);
                    newDoctor.setDoctorId(docID);
                    newDoctor.setName(docName);
                    System.out.print(askDoctorSpecialization);
                    newDoctor.setSpecialization(Validate.selectSpecialization());
                    newDoctor.setAvailability(Validate.getDateCurrent(askDoctorAvailability));

                    addDoctor(newDoctor);
                    break;

                case 0:
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            System.out.println("error inputNewDoctor");
            System.out.println(e);
        }
    }

    public User askUpdate(User updateMe) throws IOException {
        if (updateMe.getUserRole().equals(UserRole.AUTHORIZED_DOCTOR)) {
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
        }
        System.out.print("Enter the doctor update name: ");
        String docName = in.nextLine();
        ((Doctor) updateMe).setName(docName);
        System.out.print("Enter the doctor update Specialization: ");
        ((Doctor) updateMe).setSpecialization(Validate.selectSpecialization());
        ((Doctor) updateMe).setAvailability(Validate.getDateCurrent("Enter doctor update availability: "));

        return updateMe;
    }

    void updateDoc(User docUpdate) {
        users = userDataIO.readData();
        users.forEach(u -> {
            if (u instanceof Doctor && ((Doctor) u).getDoctorId() == ((Doctor) docUpdate).getDoctorId()) {
                u = docUpdate;
            }
        });
        userDataIO.writeData(users);
    }

    // function4.3
    public void findAndUpdateByDoctorID() throws IOException {
        users = getUsers();
        while (true) {
            int id = Validate.getIntLimit("Enter doctorID that needed to be update", 0, Integer.MAX_VALUE);
            users = userDataIO.readData();
            for (User find : users) {
                if (find instanceof Doctor && ((Doctor) find).getDoctorId() == id) {
                    //in all user if that a doctor check id
                    find = askUpdate(find);
                    updateDoc(find);
                    return;
                }
            }
            System.out.println("Can't find the Doctor: " + id);
            System.out.println("please re-Enter the userCode that need to be update");
        }
    }

    // function4.4
    public void findAndDeletedByDoctorID() throws IOException {
        users = getUsers();
        int id = Validate.getIntLimit("Enter doctorID that needed to be deleted", 0, Integer.MAX_VALUE);
        users = userDataIO.readData();
        for (User user : users) {
            if (user instanceof Doctor && ((Doctor) user).getDoctorId() == id) {
                users.remove(user);
                return;
            }
        }
        userDataIO.writeData(users);
    }

    public void doFunction1() throws IOException {
        while (true) {
            System.out.println("1. View list of all doctor");
            System.out.println("2. Add new doctor");
            System.out.println("3. Update doctor");
            System.out.println("4. Delete doctor");
            System.out.println("0. Back to main menu");
            int choice = Validate.getIntLimit("Choose: ", 0, 4);
            switch (choice) {
                case 1:
                    users = getUsers();
                    System.out.println("List of all Doctor");
                    for (User u : users) {
                        if (u instanceof Doctor) {//check class so only print doctor
                            System.out.print("DoctorID:" + ((Doctor) u).getDoctorId() + "; ");
                            System.out.println(u.toString());
                        }
                    }
                    System.out.println();
                    break;
                case 2:
                    inputNewDoctor();
                    break;
                case 3:
                    findAndUpdateByDoctorID();
                    break;
                case 4:
                    findAndDeletedByDoctorID();
                    break;
                case 0:
                    return;
                default:
                    break;
            }
            userDataIO.writeData(users);
        }
    }
  
}
