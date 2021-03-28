/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.newpackage;
import common.DataIOForTest;
import common.UserRole;
import doctor.Doctor;
import doctor.DoctorView;
import static doctor.newpackage.DoctorView_UpdateDoctor.instance;
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
public class DoctorView_DeleteDoctor {
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
    @Parameters(method="parametersForDeleteDoctor_True")
    
    public void testDeleteDoctorReturnTrue(Doctor doctor)  {
       try{
            Boolean expResult = Boolean.TRUE;

            Boolean result = instance.updateUser(doctor);

            assertEquals(expResult, result);
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @SuppressWarnings("unused")
    private Object[] parametersForDeleteDoctor_True() {
        return $($(new Doctor("admin01", "DeleteDoctorName", "DeletePassword", UserRole.DOCTOR)),
                $(new Doctor("admin01", null, "DeletePassword", UserRole.DOCTOR)),
                $(new Doctor("admin01", "DeleteDoctorName", null, UserRole.DOCTOR)),
                $(new Doctor("admin01", "DeleteDoctorName", "DeletePassword", UserRole.AUTHORIZED_DOCTOR))
                
        );
    }
    
    /**
     * Test of validatePassword method, of class UserController.
     *
     * @param userCode
     * @param newPassword
     */
    @Test
    @Parameters(method = "parametersForDeleteDoctor_False")
    public void testDeleteDoctorReturnsFalse(User user) {
        try {
            Boolean expResult = Boolean.FALSE;

            Boolean result = instance.updateUser(user);

            assertEquals(expResult, result);
        } catch (Exception ex) {
           
        }
    }

    @SuppressWarnings("unused")
    private Object[] parametersForDeleteDoctor_False() {
        return $($(new Doctor("admin01NonExist", "DeleteDoctorName", "deletePassword", UserRole.DOCTOR)),
                $(new Doctor("admin01NonExist", "DeleteDoctorName", null, UserRole.DOCTOR)),
                $(new Doctor("admin01NonExist", null, "deletePassword", UserRole.DOCTOR)),
                $(new Doctor(null, "DeleteDoctorName", "deletePassword", UserRole.DOCTOR))
        );
    }
    
    
    
}
