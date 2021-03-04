/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Patient implements Serializable {

    private int patientID;
    private String patientName;
    private String diseaseType;
    private Date consultDate;
    private String consultNote;

    public Patient() {
    }

    public Patient(int patientId, String name, String diseaseType, Date consultDate, String consultNote) {
        this.patientID = patientId;
        this.patientName = name;
        this.diseaseType = diseaseType;
        this.consultDate = consultDate;
        this.consultNote = consultNote;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public Date getConsultDate() {
        return consultDate;
    }

    public void setConsultDate(Date consultDate) {
        this.consultDate = consultDate;
    }

    public String getConsultNote() {
        return consultNote;
    }

    public void setConsultNote(String consultNote) {
        this.consultNote = consultNote;
    }

    public String toString(SimpleDateFormat dateFormat) {
        return (patientID + " | " + patientName + " | " + diseaseType + " | " + dateFormat.format(consultDate) + " | " + consultNote);
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        return toString(df);
    }

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Patient other = (Patient) obj;
        return this.patientID == other.patientID;
    }

}
