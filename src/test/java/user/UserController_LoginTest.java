/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import common.UserRole;
import java.util.logging.Level;
import java.util.logging.Logger;
import junitparams.JUnitParamsRunner;
import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Admin
 */
@RunWith(JUnitParamsRunner.class)
public class UserController_LoginTest {

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
                $(new User("adminNonExist", "admin01", "admin01", UserRole.USER)),       //invalid userCode
                $(new User(null, "admin01", "admin01", UserRole.USER))          //null userCode
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
                $(new User("admin01", "adminNonExist", "admin01", UserRole.USER)),       //invalid userName
                $(new User("admin01", "admin01", "adminWrongPass", UserRole.USER)),      //invalid password
                $(new User("admin01", null, "admin01", UserRole.USER)),       //null userName
                $(new User("admin01", "admin01", null, UserRole.USER)),       //null password
                $(new User(null, null, null, UserRole.USER))       //null all
                
        );
    }

    /**
     * Test of login method, of class UserController.
     *
     * @param user
     */
    @Test
    @Parameters(method = "parametersForLogin_Exception")
    public void testLoginReturnsException(User user) {
        try {
            //Arrange
            UserController instance = new UserController();

            //Act
            instance.login(user);

            //Assert
            //assertEquals(expResult, result);
            fail("Expected Exception");
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Object[] parametersForLogin_Exception() {
        return $(
                $(new User("admin01", "admin01", null, UserRole.USER))       //null password
        );
    }

}
