/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consult;

import Common.Patient;
import User.User;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author shado
 */
public class ConsultManagerTest {
    
    public ConsultManagerTest() {
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
     * Test of callPrintPatientByDiseaseType method, of class ConsultManager.
     */
    @org.junit.Test
    public void testCallPrintPatientByDiseaseType() {
        System.out.println("callPrintPatientByDiseaseType");
        ArrayList<User> userList = null;
        ConsultManager.callPrintPatientByDiseaseType(userList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printPatientsByDiseaseType method, of class ConsultManager.
     */
    @org.junit.Test
    public void testPrintPatientsByDiseaseType() {
        System.out.println("printPatientsByDiseaseType");
        ArrayList<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient(0, "p1", "d1", new Date(2020, 11, 10), "patient 1"));
        patientList.add(new Patient(2, "p2", "d1", new Date(2021, 1, 1), "patient 2"));
        patientList.add(new Patient(3, "p3", "d3", new Date(2021, 9, 9), "patient3"));
        patientList.add(new Patient(4, "p4", "d1", new Date(2019, 8, 8), "patient 4"));
        String expResult = "";
        String result = ConsultManager.printPatientsByDiseaseType(patientList);
        System.out.print("RRRResult:" + result);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPatientsFromDoctors method, of class ConsultManager.
     */
    @org.junit.Test
    public void testGetAllPatientsFromDoctors() {
        System.out.println("getAllPatientsFromDoctors");
        ArrayList<User> userList = null;
        ArrayList<Patient> expResult = null;
        ArrayList<Patient> result = ConsultManager.getAllPatientsFromDoctors(userList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortByDiseaseType method, of class ConsultManager.
     */
    @org.junit.Test
    public void testSortByDiseaseType() {
        System.out.println("sortByDiseaseType");
        ArrayList<Patient> patientList = null;
        ArrayList<Patient> expResult = null;
        ArrayList<Patient> result = ConsultManager.sortByDiseaseType(patientList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
