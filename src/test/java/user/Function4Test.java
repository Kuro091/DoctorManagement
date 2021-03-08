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
import user.UserView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Long
 */
public class Function4Test {

    public Function4Test() {
    }

    @BeforeClass
    public static void setUpClass() {
        //------------------ADD TAM DATA VAO FILE USERS.DAT DE TEST, XOA SAU
        ArrayList users = new ArrayList<>();
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

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testShowAllUser() {
        try {
            //System.out.println("showAllUser");
            UserView instance = new UserView();
            assertEquals("testShowAllUser",true, instance.showAllUser());
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testCheckExistUserCode() {
        //System.out.println("testCheckExistUser");
        UserView uv = new UserView();
        try {
            assertEquals(true, uv.checkExistUserCode("admin01"));
            assertEquals(false, uv.checkExistUserCode("qweasd"));
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testCheckExistUserName() {
        //System.out.println("testCheckExistUser");
        UserView uv = new UserView();
        try {
            assertEquals("testCheckExistUserName_trueExpect", true, uv.checkExistUserName("admin01"));
            assertEquals("testCheckExistUserName_falseExpect", false, uv.checkExistUserName("qweasd"));
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testAddUser() {
        UserView uv = new UserView();
        try{
            assertEquals("testAddUser",true,uv.addUser(new User("qwerty", "qwerty", "qwe123", UserRole.USER)));
            assertEquals("testAddAdmin",true,uv.addUser(new User("newadmin", "newadmin", "qwe123", UserRole.ADMIN)));
            assertEquals("testAddAuthDoctor",true,uv.addUser(new User("newAuthDoc", "newAuthDoc", "qwe123", UserRole.AUTHORIZED_DOCTOR)));
            assertEquals("testAddUser",true,uv.addUser(new User("newNormalDoc", "newNormalDoc", "qwe123", UserRole.USER)));
        }catch(Exception ex){
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUpdateUser() {
        UserView uv = new UserView();
        try{
            assertEquals("testUpdateUser",true,uv.updateUser(new User("qwerty", "newname", "123456", UserRole.USER)));
        }catch(Exception ex){
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testDeletedUser() {
        //System.out.println("test deleted user by user code");
        try {
            UserView uv = new UserView();
            int startSize = uv.getUsers().size();
            uv.deleteUser("admin02");
            assertEquals("testDeletedUser", startSize - 1, uv.getUsers().size());
        } catch (Exception ex) {
            Logger.getLogger(UserController_LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
