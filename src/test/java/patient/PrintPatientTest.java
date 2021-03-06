/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient;

import common.UserRole;
import doctor.Doctor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import user.User;

/**
 * 2021/02/27 | PASSED
 *
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

    @Ignore("Vì hàm này chỉ dùng để gọi các hàm còn lại, không có code riêng nên không cần test")
    @Test
    public void testCallPrintPatientByDiseaseType() {
        List<User> userList = null;
        PrintPatient.callPrintPatientByDiseaseType(userList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printPatientsByDiseaseType method, of class PrintPatient.
     */
    @Test
    public void testPrintPatientsByDiseaseType() {

        // ↓  UTCID1 | ArrayList đầu vào null 
        List<Patient> patientList = null;
        String expResult = "No patient found";
        String result = PrintPatient.printPatientsByDiseaseType(patientList);
        assertEquals(expResult, result);
        // ↑  UTCID1 | ArrayList đầu vào null 

        // ↓  UTCID2 | ArrayList size = 0 
        patientList = new ArrayList<>();
        result = PrintPatient.printPatientsByDiseaseType(patientList);
        assertEquals(expResult, result);
        // ↑  UTCID2 | ArrayList size = 0 

        // ↓  UTCID3 | Trường hợp bình thường 
        patientList = new ArrayList<>();
        patientList.add(new Patient(1, "p1", "d1", java.sql.Date.valueOf(LocalDate.of(2020, 11, 10)), "patient 1"));
        patientList.add(new Patient(2, "p2", "d1", java.sql.Date.valueOf(LocalDate.of(2021, 1, 1)), "patient 2"));
        patientList.add(new Patient(3, "p3", "d3", java.sql.Date.valueOf(LocalDate.of(2021, 9, 9)), "patient 3"));
        patientList.add(new Patient(4, "p4", "d1", java.sql.Date.valueOf(LocalDate.of(2019, 8, 8)), "patient 4"));
        expResult = "";
        expResult += "d1" + System.lineSeparator();
        expResult += "1 | p1 | d1 | 2020/11/10 | patient 1" + System.lineSeparator();
        expResult += "2 | p2 | d1 | 2021/01/01 | patient 2" + System.lineSeparator();
        expResult += "4 | p4 | d1 | 2019/08/08 | patient 4" + System.lineSeparator();
        expResult += "d3" + System.lineSeparator();
        expResult += "3 | p3 | d3 | 2021/09/09 | patient 3" + System.lineSeparator();
        result = PrintPatient.printPatientsByDiseaseType(patientList);
        assertEquals(expResult, result);
        // ↑  UTCID3 | Trường hợp bình thường 
    }

    /**
     * Test of getAllPatientsFromDoctors method, of class PrintPatient.
     */
    @Test
    public void testGetAllPatientsFromDoctors() {
        // --- UTCID1 | ArrayList đầu vào null ↓
        List<User> userList = null;
        List<Patient> expResult = new ArrayList<>();
        List<Patient> result = PrintPatient.getAllPatientsFromDoctors(userList);
        assertArrayEquals(expResult.toArray(), result.toArray());
        // --- UTCID1 | ArrayList đầu vào null ↑

        // --- UTCID2 | ArrayList đầu vào không null nhưng rỗng ↓
        userList = new ArrayList<>();
        result = PrintPatient.getAllPatientsFromDoctors(userList);
        assertArrayEquals(expResult.toArray(), result.toArray());
        // --- UTCID2 | ArrayList đầu vào không null nhưng rỗng ↑

        // --- UTCID3 | ArrayList đầu vào size > 0 nhưng không có bệnh nhân ↓
        userList.add(new User(UserRole.USER));
        userList.add(new User(UserRole.ADMIN));
        userList.add(new User(UserRole.DOCTOR));
        userList.add(new User(UserRole.AUTHORIZED_DOCTOR));
        result = PrintPatient.getAllPatientsFromDoctors(userList);
        assertArrayEquals(expResult.toArray(), result.toArray());
        // --- UTCID3 | ArrayList đầu vào size > 0 nhưng không có bệnh nhân ↑

        // --- UTCID4 | ArrayList size > 0 và có bệnh nhân bình thường ↓
        Doctor doc1 = new Doctor("doc1", "doctor01", UserRole.AUTHORIZED_DOCTOR);
        doc1.setUserRole(UserRole.AUTHORIZED_DOCTOR);
        Doctor doc2 = new Doctor("doc2", "doctor02", UserRole.DOCTOR);
        doc2.setUserRole(UserRole.AUTHORIZED_DOCTOR);
        Doctor doc3 = new Doctor("doc3", "doctor03", UserRole.AUTHORIZED_DOCTOR);
        doc3.setUserRole(UserRole.AUTHORIZED_DOCTOR);
        Doctor doc4 = new Doctor("doc4", "doctor04", UserRole.DOCTOR);
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
        userList = new ArrayList<>();
        userList.add(doc1);
        userList.add(new User(UserRole.USER));
        userList.add(new User(UserRole.ADMIN));
        userList.add(doc2);
        userList.add(doc3);
        userList.add(new User(UserRole.USER));
        userList.add(doc4);
        expResult = new ArrayList<>();
        expResult.add(p1);
        expResult.add(p3);
        expResult.add(p8);
        expResult.add(p6);
        expResult.add(p4);
        expResult.add(p2);
        expResult.add(p5);
        expResult.add(p7);
        result = PrintPatient.getAllPatientsFromDoctors(userList);
        assertArrayEquals(expResult.toArray(), result.toArray());
        // --- UTCID4 | ArrayList size > 0 và có bệnh nhân bình thường ↑
    }

    /**
     * Test of sortByDiseaseType method, of class PrintPatient.
     */
    @Test
    public void testSortByDiseaseType() {
        // ↓  UTCID 1 | ArrayList đầu vào null 
        ArrayList<Patient> patientList = null;
        List<Patient> expResult = new ArrayList<>();
        List<Patient> result = PrintPatient.sortByDiseaseType(patientList);
        assertArrayEquals(expResult.toArray(), result.toArray());
        // ↑  UTCID 1 | ArrayList đầu vào null 

        // ↓  UTCID 2 | ArrayList đầu vào size = 0 
        patientList = new ArrayList<>();
        result = PrintPatient.sortByDiseaseType(patientList);
        assertArrayEquals(expResult.toArray(), result.toArray());
        // ↑  UTCID 2 | ArrayList đầu vào size = 0 

        // ↓  UTCID 4 | ArrayList đầu vào size > 0, các bệnh nhân đều bình thường 
        Patient p1 = new Patient(1, "p1", "d1", java.sql.Date.valueOf(LocalDate.of(2020, 11, 10)), "patient 1");
        Patient p2 = new Patient(2, "p2", "d1", java.sql.Date.valueOf(LocalDate.of(2021, 1, 1)), "patient 2");
        Patient p3 = new Patient(3, "p3", "d3", java.sql.Date.valueOf(LocalDate.of(2021, 9, 9)), "patient 3");
        Patient p4 = new Patient(4, "p4", "d1", java.sql.Date.valueOf(LocalDate.of(2019, 8, 8)), "patient 4");
        Patient p5 = new Patient(5, "p5", "d3", java.sql.Date.valueOf(LocalDate.of(2020, 11, 11)), "patient 5");
        Patient p6 = new Patient(6, "p6", "d4", java.sql.Date.valueOf(LocalDate.of(2021, 1, 12)), "patient 6");
        Patient p7 = new Patient(7, "p7", "d5", java.sql.Date.valueOf(LocalDate.of(2019, 8, 13)), "patient 7");
        Patient p8 = new Patient(8, "p8", "d5", java.sql.Date.valueOf(LocalDate.of(2021, 9, 14)), "patient 8");
        patientList = new ArrayList<>();
        patientList.add(p8);
        patientList.add(p6);
        patientList.add(p2);
        patientList.add(p1);
        patientList.add(p5);
        patientList.add(p7);
        patientList.add(p4);
        patientList.add(p3);
        expResult = new ArrayList<>();
        expResult.add(p2);
        expResult.add(p1);
        expResult.add(p4);
        expResult.add(p5);
        expResult.add(p3);
        expResult.add(p6);
        expResult.add(p8);
        expResult.add(p7);
        result = PrintPatient.sortByDiseaseType(patientList);
        assertArrayEquals(expResult.toArray(), result.toArray());
        // ↑  UTCID 4 | ArrayList đầu vào size > 0, các bệnh nhân đều bình thường 

        // ↓  UTCID3 | ArrayList đầu vào size > 0, có bệnh nhân diseaseType = null và bệnh nhân = null
        Patient p9 = new Patient(9, "p9", null, java.sql.Date.valueOf(LocalDate.of(2021, 9, 14)), "patient 9");
        patientList = new ArrayList<>();
        patientList.add(p8);
        patientList.add(p6);
        patientList.add(p2);
        patientList.add(p1);
        patientList.add(p5);
        patientList.add(p7);
        patientList.add(p4);
        patientList.add(p3);
        patientList.add(p9);
        patientList.add(null);
        expResult = new ArrayList<>();
        expResult.add(p9);
        expResult.add(null);
        expResult.add(p2);
        expResult.add(p1);
        expResult.add(p4);
        expResult.add(p5);
        expResult.add(p3);
        expResult.add(p6);
        expResult.add(p8);
        expResult.add(p7);
        result = PrintPatient.sortByDiseaseType(patientList);
        assertArrayEquals(expResult.toArray(), result.toArray());
        // ↑  UTCID3 | ArrayList đầu vào size > 0, có bệnh nhân diseaseType = null và bệnh nhân = null
    }

}
