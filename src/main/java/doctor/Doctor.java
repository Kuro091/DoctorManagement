package doctor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import patient.Patient;
import common.UserRole;
import user.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Doctor extends User implements Serializable {

    private int doctorId;
    private String name;
    private Specialization specialization;
    private Date availability; //
    private List<Patient> patients;

    public Doctor(String userName, String password, UserRole userRole) {
        super(userName, password, userRole);
    }

    public Doctor(String userCode, String userName, String password, UserRole userRole) {
        super(userCode, userName, password, userRole);
        this.patients = new ArrayList<>();
    }

    public Doctor(int doctorId, String name, Specialization specialization, Date availability, List<Patient> patients, UserRole userRole) {

        this(doctorId, name, specialization, availability, patients, null, null, null, userRole);
    }

    public Doctor(int doctorId, String name, Specialization specialization, Date availability, List<Patient> patients, String userCode, String userName, String password, UserRole userRole) {
        super(userCode, userName, password, userRole);
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
        this.patients = patients;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Date getAvailability() {
        return availability;
    }

    public void setAvailability(Date availability) {
        this.availability = availability;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%-10s|%-20s", getUserCode(), getName());
    }

}
