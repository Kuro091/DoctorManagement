/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import admin.Admin;
import common.DataIO;
import common.DataIOForTest;
import common.UserRole;
import doctor.Doctor;
import doctor.Specialization;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junitparams.JUnitParamsRunner;
import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author MinhLC
 */
@RunWith(JUnitParamsRunner.class)
public class UserController_changePasswordTest {

    static UserController instance;

    @BeforeClass
    public static void setUpClass() {
        try {
            instance = UserController.getInstance();
            instance.login(new User("admin01", "admin01", UserRole.ADMIN));
 ArrayList<User> users = new ArrayList<>();
        users.add(new Admin("admin01", "admin01", "admin01", UserRole.ADMIN));
        users.add(new Admin("admin02", "admin02", "admin02", UserRole.ADMIN));
        users.add(new Admin("admin03", "admin03", "admin03", UserRole.ADMIN));
        users.add(new Doctor(1, "doctor01", Specialization.TIM_MACH, new Date(), new ArrayList<>(), "doctor01", "doctor01", "doctor01", UserRole.AUTHORIZED_DOCTOR));
        users.add(new Doctor(2, "doctor02", Specialization.DA_LIEU, new Date(), new ArrayList<>(), "doctor02", "doctor02", "doctor02", UserRole.AUTHORIZED_DOCTOR));
        users.add(new Doctor(3, "doctor03", Specialization.NHA_KHOA, new Date(), new ArrayList<>(), "doctor03", "doctor03", "doctor03", UserRole.AUTHORIZED_DOCTOR));
        users.add(new Doctor(4, "doctor04", Specialization.UNG_BUOU, new Date(), new ArrayList<>(), "doctor04", "doctor04", "doctor04", UserRole.AUTHORIZED_DOCTOR));
        users.add(new Doctor(5, "doctor05", Specialization.TIM_MACH, new Date(), new ArrayList<>(), UserRole.DOCTOR));
        users.add(new Doctor(6, "doctor06", Specialization.TIM_MACH, new Date(), new ArrayList<>(), UserRole.DOCTOR));
        users.add(new Doctor(7, "doctor07", Specialization.NHA_KHOA, new Date(), new ArrayList<>(), UserRole.DOCTOR));
        users.add(new Doctor(8, "doctor08", Specialization.TIM_MACH, new Date(), new ArrayList<>(), UserRole.DOCTOR));

        //------------WRITE META DATA TO FILE
        new DataIO().writeData(users);
           
        } catch (Exception ex) {
            Logger.getLogger(UserController_changePasswordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * Test of validatePassword method, of class UserController.
     *
     * @param userCode
     * @param newPassword
     */
    @Test
    @Parameters({
        "admin01, newPassword",})
    public void testChangePasswordReturnsTrue(String userCode, String newPassword) {
        try {
            //Arrange
            Boolean expResult = Boolean.TRUE;

            //Act
            Boolean result = instance.changePassword(userCode, newPassword);

            //Assert
            assertEquals(expResult, result);
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of validatePassword method, of class UserController.
     *
     * @param userCode
     * @param newPassword
     */
    @Test
    @Parameters(method = "parametersForChangePassword_False")
    public void testChangePasswordReturnsFalse(String userCode, String newPassword) {
        try {
            //Arrange
            Boolean expResult = Boolean.FALSE;

            //Act
            Boolean result = instance.changePassword(userCode, newPassword);

            //Assert
            assertEquals(expResult, result);
        } catch (Exception ex) {
            Logger.getLogger(UserController_changePasswordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unused")
    private Object[] parametersForChangePassword_False() {
        return $($("admin01", null),
                $("adminNonExist", "adminNewPassword"),
                $("adminNonExist", null),
                $(null, "adminNewPassword"),
                $(null, null)
        );
    }

    @Test
    @Parameters(method = "parametersForChangePassword_Exception")
    public void testChangePasswordReturnsException(String userCode, String newPassword) {
        try {
            //Arrange
            Boolean expResult = Boolean.FALSE;

            //Act
            Boolean result = instance.changePassword(userCode, newPassword);

            //Assert
            fail("Expected exception");
        } catch (Exception ex) {
            Logger.getLogger(UserController_changePasswordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unused")
    private Object[] parametersForChangePassword_Exception() {
        return $($("admin01", null),
                $("adminNonExist", null),
                $(null, "adminNewPassword"),
                $(null, null)
        );
    }
}
