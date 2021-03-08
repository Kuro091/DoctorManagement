/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.util.logging.Level;
import java.util.logging.Logger;
import junitparams.JUnitParamsRunner;
import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Admin
 */
@RunWith(JUnitParamsRunner.class)
public class UserController_validatePasswordTest {
    
    /**
     * Test of validatePassword method, of class UserController.
     *
     * @param userCode
     * @param oldPassword
     */
    @Test
    @Parameters({
        "admin01, admin01",
    })
    public void testValidatePasswordReturnsTrue(String userCode, String oldPassword) {
        try {
            //Arrange
            user.UserController instance = new user.UserController();
            Boolean expResult = Boolean.TRUE;

            //Act
            Boolean result = instance.validatePassword(userCode, oldPassword);

            //Assert
            assertEquals(expResult, result);
        } catch (Exception ex) {
            Logger.getLogger(UserController_validatePasswordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test of validatePassword method, of class UserController.
     *
     * @param userCode
     * @param oldPassword
     * @param user
     */
    @Test
    @Parameters(method = "parametersForValidatePassword_False")
    public void testValidatePasswordReturnsFalse(String userCode, String oldPassword) {
        try {
            //Arrange
            user.UserController instance = new user.UserController();
            Boolean expResult = Boolean.FALSE;

            //Act
            Boolean result = instance.validatePassword(userCode, oldPassword);

            //Assert
            assertEquals(expResult, result);
        } catch (Exception ex) {
            Logger.getLogger(UserController_validatePasswordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unused")
    private Object[] parametersForValidatePassword_False() {
        return $($("admin01", "adminWrongPassword"),
                $("admin01", null),
                $("adminNonExist", "adminWrongPassword"),
                $("adminNonExist", "admin01"),
                $("adminNonExist", null),
                $(null, "admin01"),
                $(null, "adminWrongPassword"),
                $(null, null)
        );
    }
}
