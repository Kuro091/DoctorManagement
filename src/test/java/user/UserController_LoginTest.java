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
            instance.login(user);
            
            //Assert
            //assertEquals(expResult, result);
            fail( "My method didn't throw when I expected it to" );
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Object[] parametersForLogin_True(){
        return $($(new User(null, null, null, UserRole.USER)),
                $(new User("", "", UserRole.ADMIN))
        );
    }
    
    
}
