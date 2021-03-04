/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import user.UserView;
import user.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class UserViewTest {
    
    public UserViewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of getUsers method, of class UserView.
     */
    @Test
    public void testGetUsers() {
        UserView instance = new UserView();
        ArrayList<User> expResult = new ArrayList<User>();
        ArrayList<User> result = instance.getUsers();
        assertEquals(instance.getUsersSize(), result.size());
    }

    /**
     * Test of updateUser method, of class UserView.
     */
    @Test
    public void testUpdateUser() {
        User userUpdate = new User();
        userUpdate.setUserCode("doctor02");
        userUpdate.setUserName("doctor02");
        userUpdate.setPassword("doctor02");
        
        UserView instance = new UserView();
        boolean expResult = true;
        boolean result = instance.updateUser(userUpdate);
        assertEquals(expResult, result);
    }

   
}
