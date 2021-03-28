/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.newpackage;
import common.DataIOForTest;
import common.UserRole;
import doctor.DoctorView;
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
import user.User;
import user.UserController_LoginTest;

/**
 *
 * @author nam35
 */
@RunWith(JUnitParamsRunner.class)
public class DoctorView_UpdateDoctor {

    static DoctorView instance;

    @BeforeClass
    public static void setUpClass() {
        instance = new DoctorView();

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
    public void testUpdateDoctorReturnsTrue(User user) {
        try {
            Boolean expResult = Boolean.TRUE;

            Boolean result = instance.updateUser(user);

            assertEquals(expResult, result);
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unused")
    private Object[] parametersForUpdateDoctor_True() {
        return $($(new User("admin01", "updatedDoctorName", "updatedPassword", UserRole.ADMIN)),
                $(new User("admin01", null, "updatedPassword", UserRole.ADMIN)),
                $(new User("admin01", "updatedDoctorame", null, UserRole.ADMIN)),
                $(new User("admin01", "updatedDoctorName", "updatedPassword", UserRole.AUTHORIZED_DOCTOR))
                
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
            Boolean expResult = Boolean.FALSE;

            Boolean result = instance.updateUser(user);

            assertEquals(expResult, result);
        } catch (Exception ex) {
           
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
}
