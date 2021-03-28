/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import admin.Admin;
import common.DataIO;
import common.UserRole;
import doctor.Doctor;
import doctor.Specialization;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import junitparams.JUnitParamsRunner;
import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author MinhLC
 */
@RunWith(JUnitParamsRunner.class)
public class UserController_LoginTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
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
    }

    /**
     * Test of login method, of class UserController.
     *
     * @param user
     */
    @Test
    @Parameters(method = "parametersForLogin_True")
    public void testLoginReturnsTrue(User user) {
        try {
            //Arrange
            UserController instance = new UserController();
            Boolean expResult = Boolean.TRUE;

            //Act
            Boolean result = instance.login(user);

            //Assert
            assertEquals(expResult, result);
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unused")
    private Object[] parametersForLogin_True() {
        return $($(new User("admin01", "admin01", "admin01", UserRole.USER)),
                $(new User("admin01", "admin01", "admin01", UserRole.AUTHORIZED_DOCTOR)),
                $(new User("admin01", "admin01", "admin01", UserRole.DOCTOR)),
                $(new User("admin01", "admin01", "admin01", UserRole.ADMIN)),
                $(new User("adminNonExist", "admin01", "admin01", UserRole.USER)), //invalid userCode
                $(new User(null, "admin01", "admin01", UserRole.USER)) //null userCode
        );
    }

    /**
     * Test of login method, of class UserController.
     *
     * @param user
     */
    @Test
    @Parameters(method = "parametersForLogin_False")
    public void testLoginReturnsFalse(User user) {
        try {
            //Arrange
            UserController instance = new UserController();
            Boolean expResult = Boolean.FALSE;

            //Act
            Boolean result = instance.login(user);

            //Assert
            assertEquals(expResult, result);
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unused")
    private Object[] parametersForLogin_False() {
        return $(
                $(new User("admin01", "adminNonExist", "admin01", UserRole.USER)), //invalid userName
                $(new User("admin01", "admin01", "adminWrongPass", UserRole.USER)), //invalid password
                $(new User("admin01", null, "admin01", UserRole.USER)), //null userName
                $(new User("admin01", "admin01", null, UserRole.USER)), //null password
                $(new User(null, null, null, UserRole.USER)) //null all

        );
    }

    /**
     * Test of login method, of class UserController.
     *
     * @param user
     */
    //@Test(expected = Exception.class)
    @Parameters(method = "parametersForLogin_Exception")
    public void testLoginReturnsException(User user) throws Exception {
        //Arrange
        UserController instance = new UserController();

        //Act
        instance.login(user);

        //Assert
        //assertEquals(expResult, result);
        fail("Expected Exception");
    }

    private Object[] parametersForLogin_Exception() {
        return $(
                $(new User("admin01", "admin01", null, UserRole.USER)) //null password
        );
    }

}
