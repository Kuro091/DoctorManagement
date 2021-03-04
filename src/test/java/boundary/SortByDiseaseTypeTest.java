/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import Common.Patient;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shado
 */
public class SortByDiseaseTypeTest {

    public SortByDiseaseTypeTest() {
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
     * Test of compare method, of class SortByDiseaseType.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Patient o1 = new Patient(8, "p8", "d5", java.sql.Date.valueOf(LocalDate.of(2021, 9, 14)), "patient 8");
        Patient o2 = new Patient(1, "p1", "d1", java.sql.Date.valueOf(LocalDate.of(2020, 11, 10)), "patient 1");
        SortByDiseaseType instance = new SortByDiseaseType();
        int result = instance.compare(o1, o2);
        System.out.println(result);
        assert (result > 0);
        result = instance.compare(o2, o1);
        System.out.println(result);
        assertTrue(result < 0);
        Patient o3 = new Patient(3, "p3", "d5", java.sql.Date.valueOf(LocalDate.of(2021, 9, 9)), "patient 3");
        result = instance.compare(o1, o3);
        System.out.println(result);
        assertTrue(result == 0);
    }

}
