/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import patient.Patient;
import common.UserRole;
import user.User;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ValidationAdminManager {

    public Patient getPatientByPatientID(int patientid, List<Patient> patients) {
        for (Patient patient : patients) {
            if (patient.getPatientID() == patientid) {
                return patient;
            }
        }
        return null;
    }

    public User getDoctorByUserCode(String usercode, List<User> users) {
        for (User user : users) {
            if (user.getUserRole() == UserRole.AUTHORIZED_DOCTOR
                    && usercode.equals(user.getUserCode())) {
                return user;
            }
        }
        return null;
    }
}
