/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import Common.Patient;
import Common.UserRole;
import Doctor.Doctor;
import User.User;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * 2021/02/27 | PASSED
 * @author Vân
 */
public class PrintPatientTest {

    public PrintPatientTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Ignore vì hàm này chỉ gọi các hàm bên dưới, không có code nên không cần
     * test riêng
     */
    @Ignore
    @Test
    public void testCallPrintPatientByDiseaseType() {
        System.out.println("callPrintPatientByDiseaseType");
        ArrayList<User> userList = null;
        PrintPatient.callPrintPatientByDiseaseType(userList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printPatientsByDiseaseType method, of class PrintPatient.
     */
    @Test
    public void testPrintPatientsByDiseaseType() {
        System.out.println("printPatientsByDiseaseType");
        ArrayList<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient(1, "p1", "d1", java.sql.Date.valueOf(LocalDate.of(2020, 11, 10)), "patient 1"));
        patientList.add(new Patient(2, "p2", "d1", java.sql.Date.valueOf(LocalDate.of(2021, 1, 1)), "patient 2"));
        patientList.add(new Patient(3, "p3", "d3", java.sql.Date.valueOf(LocalDate.of(2021, 9, 9)), "patient 3"));
        patientList.add(new Patient(4, "p4", "d1", java.sql.Date.valueOf(LocalDate.of(2019, 8, 8)), "patient 4"));
        String expResult = "";
        expResult += "d1" + System.lineSeparator();
        expResult += "1 | p1 | d1 | 2020/11/10 | patient 1" + System.lineSeparator();
        expResult += "2 | p2 | d1 | 2021/01/01 | patient 2" + System.lineSeparator();
        expResult += "4 | p4 | d1 | 2019/08/08 | patient 4" + System.lineSeparator();
        expResult += "d3" + System.lineSeparator();
        expResult += "3 | p3 | d3 | 2021/09/09 | patient 3" + System.lineSeparator();
        String result = PrintPatient.printPatientsByDiseaseType(patientList);
    }

    /**
     * Test of getAllPatientsFromDoctors method, of class PrintPatient.
     */
    @Test
    public void testGetAllPatientsFromDoctors() {
        System.out.println("getAllPatientsFromDoctors");
        Doctor doc1 = new Doctor();
        doc1.setUserRole(UserRole.AUTHORIZED_DOCTOR);
        Doctor doc2 = new Doctor();
        doc2.setUserRole(UserRole.AUTHORIZED_DOCTOR);
        Doctor doc3 = new Doctor();
        doc3.setUserRole(UserRole.AUTHORIZED_DOCTOR);
        Doctor doc4 = new Doctor();
        doc4.setUserRole(UserRole.AUTHORIZED_DOCTOR);
        Patient p1 = new Patient(1, "p1", "d1", java.sql.Date.valueOf(LocalDate.of(2020, 11, 10)), "patient 1");
        Patient p2 = new Patient(2, "p2", "d1", java.sql.Date.valueOf(LocalDate.of(2021, 1, 1)), "patient 2");
        Patient p3 = new Patient(3, "p3", "d3", java.sql.Date.valueOf(LocalDate.of(2021, 9, 9)), "patient 3");
        Patient p4 = new Patient(4, "p4", "d1", java.sql.Date.valueOf(LocalDate.of(2019, 8, 8)), "patient 4");
        Patient p5 = new Patient(5, "p5", "d3", java.sql.Date.valueOf(LocalDate.of(2020, 11, 11)), "patient 5");
        Patient p6 = new Patient(6, "p6", "d4", java.sql.Date.valueOf(LocalDate.of(2021, 1, 12)), "patient 6");
        Patient p7 = new Patient(7, "p7", "d5", java.sql.Date.valueOf(LocalDate.of(2019, 8, 13)), "patient 7");
        Patient p8 = new Patient(8, "p8", "d5", java.sql.Date.valueOf(LocalDate.of(2021, 9, 14)), "patient 8");
        ArrayList<Patient> tempList = new ArrayList<>();
        tempList.add(p1);
        tempList.add(p3);
        doc1.setPatients(tempList);
        ArrayList<Patient> tempList2 = new ArrayList<>();
        tempList2.add(p8);
        tempList2.add(p6);
        tempList2.add(p4);
        tempList2.add(p2);
        tempList2.add(p5);
        doc2.setPatients(tempList2);
        ArrayList<Patient> tempList3 = new ArrayList<>();
        doc3.setPatients(tempList3);
        ArrayList<Patient> tempList4 = new ArrayList<>();
        tempList4.add(p7);
        doc4.setPatients(tempList4);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(doc1);
        userList.add(new User(UserRole.USER));
        userList.add(new User(UserRole.ADMIN));
        userList.add(doc2);
        userList.add(doc3);
        userList.add(new User(UserRole.USER));
        userList.add(doc4);
        ArrayList<Patient> expResult = new ArrayList<>();
        expResult.add(p1);
        expResult.add(p3);
        expResult.add(p8);
        expResult.add(p6);
        expResult.add(p4);
        expResult.add(p2);
        expResult.add(p5);
        expResult.add(p7);
        ArrayList<Patient> result = PrintPatient.getAllPatientsFromDoctors(userList);
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of sortByDiseaseType method, of class PrintPatient.
     */
    @Test
    public void testSortByDiseaseType() {
        System.out.println("sortByDiseaseType");
        Patient p1 = new Patient(1, "p1", "d1", java.sql.Date.valueOf(LocalDate.of(2020, 11, 10)), "patient 1");
        Patient p2 = new Patient(2, "p2", "d1", java.sql.Date.valueOf(LocalDate.of(2021, 1, 1)), "patient 2");
        Patient p3 = new Patient(3, "p3", "d3", java.sql.Date.valueOf(LocalDate.of(2021, 9, 9)), "patient 3");
        Patient p4 = new Patient(4, "p4", "d1", java.sql.Date.valueOf(LocalDate.of(2019, 8, 8)), "patient 4");
        Patient p5 = new Patient(5, "p5", "d3", java.sql.Date.valueOf(LocalDate.of(2020, 11, 11)), "patient 5");
        Patient p6 = new Patient(6, "p6", "d4", java.sql.Date.valueOf(LocalDate.of(2021, 1, 12)), "patient 6");
        Patient p7 = new Patient(7, "p7", "d5", java.sql.Date.valueOf(LocalDate.of(2019, 8, 13)), "patient 7");
        Patient p8 = new Patient(8, "p8", "d5", java.sql.Date.valueOf(LocalDate.of(2021, 9, 14)), "patient 8");
        ArrayList<Patient> patientList = new ArrayList<>();
        patientList.add(p8);
        patientList.add(p6);
        patientList.add(p2);
        patientList.add(p1);
        patientList.add(p5);
        patientList.add(p7);
        patientList.add(p4);
        patientList.add(p3);
        ArrayList<Patient> expResult = new ArrayList<>();
        expResult.add(p2);
        expResult.add(p1);
        expResult.add(p4);
        expResult.add(p5);
        expResult.add(p3);
        expResult.add(p6);
        expResult.add(p8);
        expResult.add(p7);
        ArrayList<Patient> result = PrintPatient.sortByDiseaseType(patientList);
        System.out.println("*****************************");
        System.out.println(result.toString());
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

}
