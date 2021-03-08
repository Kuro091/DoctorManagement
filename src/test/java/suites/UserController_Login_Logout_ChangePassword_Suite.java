/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suites;

import common.DataIOForTest;
import common.UserRole;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import user.User;

/**
 *
 * @author Admin
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
    user.UserController_LoginTest.class,
    user.UserController_changePasswordTest.class,
    user.UserController_validatePasswordTest.class})
public class UserController_Login_Logout_ChangePassword_Suite {

    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        List<User> users = new ArrayList<User>();
        users.add(new User("admin01", "admin01", "admin01", UserRole.ADMIN));
        new DataIOForTest().writeData(users);
    }

    @After
    public void tearDown() throws Exception {
    }

}
