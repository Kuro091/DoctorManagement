/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suites;

import admin.Admin;
import common.DataIO;
import common.DataIOForTest;
import common.UserRole;
import doctor.Doctor;
import doctor.Specialization;
import java.util.ArrayList;
import java.util.Date;
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
        ArrayList<User> users = new ArrayList<>();
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
