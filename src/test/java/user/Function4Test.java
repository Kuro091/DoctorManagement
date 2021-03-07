///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package user;
//
//import user.UserView;
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.util.ArrayList;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author Long
// */
//public class Function4Test {
//    
//    public Function4Test() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
//    @Test
//    public void testShowAllUser() {
//        System.out.println("showAllUser");
//        UserView instance = new UserView();
//        assertEquals(true, instance.showAllUser());
//    }
//    
//    @Test
//    public void testDeletedUser(){
//        System.out.println("");
//        UserView uv = new UserView();
//        int startSize = uv.getUsers().size();
//        uv.deleteUser("admin02");
//        assertEquals(startSize-1, uv.getUsers().size());
//    }
//}
