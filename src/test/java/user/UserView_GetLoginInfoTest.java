/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import admin.Admin;
import common.DataIO;
import common.UserRole;
import common.Validate;
import doctor.Doctor;
import doctor.Specialization;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import junit.framework.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Admin
 */
@RunWith(PowerMockRunner.class)
@ExtendWith(MockitoExtension.class)
@PrepareForTest(Validate.class)
public class UserView_GetLoginInfoTest {

    static UserView instance;

    @Before
    public void setUp() {
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

        JUnitCore.runClasses(PowerMockRunner.class);
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
        instance = UserView.getInstance();
    }

    /**
     * Test of getLoginInfo method, of class UserView.
     *
     */
    @Test
    public void GetLoginInfoReturnsObject_1() throws IOException {
        //Arrange
        PowerMockito.mockStatic(Validate.class);
        BDDMockito.given(Validate.getString(any())).willReturn("testInputUserName", "testInputPassword");

        //Act
        User u = instance.getLoginInfo();

        //Assert
        assertNull(u.getUserCode());
        assertNotNull(u.getUserName());
        assertNotNull(u.getPassword());
        assertEquals(u.getUserName(), "testInputUserName");
        assertEquals(u.getPassword(), "testInputPassword");
    }
    
    @Test
    public void GetLoginInfoReturnsObject_2() throws IOException {
        //Arrange
        PowerMockito.mockStatic(Validate.class);
        BDDMockito.given(Validate.getString(any())).willReturn(" ", " ");

        //Act
        User u = instance.getLoginInfo();

        //Assert
        assertNull(u.getUserCode());
        assertEquals(u.getUserName(), " ");
        assertEquals(u.getPassword(), " ");
    }
    
    @Test
    public void GetLoginInfoReturnsObject_3() throws IOException {
        //Arrange
        PowerMockito.mockStatic(Validate.class);
        BDDMockito.given(Validate.getString(any())).willReturn(null, " ");

        //Act
        User u = instance.getLoginInfo();

        //Assert
        assertNull(u.getUserCode());
        assertNull(u.getUserName());
        assertNotNull(u.getPassword(), " ");
    }
    
    @Test
    public void GetLoginInfoReturnsObject_4() throws IOException {
        //Arrange
        PowerMockito.mockStatic(Validate.class);
        BDDMockito.given(Validate.getString(any())).willReturn(null, null);

        //Act
        User u = instance.getLoginInfo();

        //Assert
        assertNull(u.getUserCode());
        assertNull(u.getUserName());
        assertNull(u.getPassword());
    }
    
    @Test
    public void GetLoginInfoReturnsObject_5() throws IOException {
        //Arrange
        PowerMockito.mockStatic(Validate.class);
        BDDMockito.given(Validate.getString(any())).willReturn(null, "testInputPassword");

        //Act
        User u = instance.getLoginInfo();

        //Assert
        assertNull(u.getUserCode());
        assertNull(u.getUserName());
        assertNotNull(u.getPassword());
        assertEquals(u.getPassword(), "testInputPassword");
    }
    
}
