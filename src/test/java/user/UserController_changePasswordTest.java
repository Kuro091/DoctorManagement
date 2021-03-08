/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import common.DataIOForTest;
import common.UserRole;
import java.util.ArrayList;
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
 * @author Admin
 */
@RunWith(JUnitParamsRunner.class)
public class UserController_changePasswordTest {

    static UserController instance;
   
    @BeforeClass
    public static void setUpClass() {
        try {
            instance = UserController.getInstance();
            instance.login(new User("admin01", "admin01", UserRole.ADMIN));
            
            
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
