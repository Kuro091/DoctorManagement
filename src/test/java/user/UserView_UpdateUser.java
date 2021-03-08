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
public class UserView_UpdateUser {

    static UserView instance;

    @BeforeClass
    public static void setUpClass() {
        instance = UserView.getInstance();

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
    @Parameters(method="parametersForUpdateUser_True")
    public void testUpdateUserReturnsTrue(User user) {
        try {
            //Arrange
            Boolean expResult = Boolean.TRUE;

            //Act
            Boolean result = instance.updateUser(user);

            //Assert
            assertEquals(expResult, result);
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unused")
    private Object[] parametersForUpdateUser_True() {
        return $($(new User("admin01", "updatedUserName", "updatedPassword", UserRole.ADMIN)),
                $(new User("admin01", null, "updatedPassword", UserRole.ADMIN)),
                $(new User("admin01", "updatedUserName", null, UserRole.ADMIN)),
                $(new User("admin01", "updatedUserName", "updatedPassword", UserRole.AUTHORIZED_DOCTOR))
                
        );
    }
    

    /**
     * Test of validatePassword method, of class UserController.
     *
     * @param userCode
     * @param newPassword
     */
    @Test
    @Parameters(method = "parametersForUpdateUser_False")
    public void testUpdateUserReturnsFalse(User user) {
        try {
            //Arrange
            Boolean expResult = Boolean.FALSE;

            //Act
            Boolean result = instance.updateUser(user);

            //Assert
            assertEquals(expResult, result);
        } catch (Exception ex) {
            Logger.getLogger(UserView_UpdateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unused")
    private Object[] parametersForUpdateUser_False() {
        return $($(new User("adminNonExist", "updatedUserName", "updatedPassword", UserRole.ADMIN)),
                $(new User("adminNonExist", "updatedUserName", null, UserRole.ADMIN)),
                $(new User("adminNonExist", null, "updatedPassword", UserRole.ADMIN)),
                $(new User(null, "updatedUserName", "updatedPassword", UserRole.ADMIN))
        );
    }

//    @Test
//    @Parameters(method = "parametersForChangePassword_Exception")
//    public void testUpdateUserReturnsException(String userCode, String newPassword) {
//        try {
//            //Arrange
//            Boolean expResult = Boolean.FALSE;
//
//            //Act
//            Boolean result = instance.changePassword(userCode, newPassword);
//
//            //Assert
//            fail("Expected exception");
//        } catch (Exception ex) {
//            Logger.getLogger(UserView_UpdateUser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @SuppressWarnings("unused")
//    private Object[] parametersForChangePassword_Exception() {
//        return $($("admin01", null),
//                $("adminNonExist", null),
//                $(null, "adminNewPassword"),
//                $(null, null)
//        );
//    }
}
