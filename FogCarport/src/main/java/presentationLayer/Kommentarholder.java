///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package presentationLayer;
//
///**
// *
// * @author mette
// */
//public class Kommentarholder {
//    
//    package dbAccess;
//
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//import functionLayer.CarportException;
//import functionLayer.entity.Customer;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author mette
// */
//public class UserMapperTest {
//
//    private static Connection testCon;
//    private final MysqlDataSource dataSource = new MysqlDataSource();
//    private static String USER = "foguser";
//    private static String USERPW = "FOG99carport";
//    private static String DBNAME = "carportTest";
//    private static String HOST = "159.89.99.43";
//
//    @Before
//    public void setUp() {
//        try {
//            // awoid making a new connection for each test
//            if (testCon == null) {
//                dataSource.setServerName("159.89.99.43");
//                dataSource.setPort(3306);
//                dataSource.setDatabaseName("carportTest");
//                testCon = dataSource.getConnection(USER, USERPW);
//                // Make mappers use test 
//                Connector.setConnection(testCon);
//            }
//
//            // reset test database
//            try (Statement stmt = testCon.createStatement()) {
//                stmt.execute("drop table if exists customers");
//                stmt.execute("create table customers like customersTest");
//                stmt.execute("insert into customers select * from customersTest");
//            }
//        } catch (SQLException ex) {
//            testCon = null;
//            System.out.println("Could not open connection to database: " + ex.getMessage());
//        }
//    }
//
//    @Test
//    public void testSetUpOK() {
//        // Just check that we have a connection.
//        assertNotNull(testCon);
//    }
//
//    @Test
//    public void testLogin01() throws CarportException {
//        // Can we log in
//        Customer customer = UserMapper.loginCustomer("lars@lars.dk", "larsPW");
//        assertTrue(customer != null);
//    }
//
////    @Test
////    public void testLogin() throws Exception {
////        System.out.println("login");
////        String username = "";
////        String password = "";
////        Employee expResult = null;
////        Employee result = UserMapper.login(username, password);
////        assertEquals(expResult, result);
////        fail("The test case is a prototype.");
////    }
////
////    @Test
////    public void testLoginCustomer() throws Exception {
////        System.out.println("loginCustomer");
////        String email = "";
////        String password = "";
////        Customer expResult = null;
////        Customer result = UserMapper.loginCustomer(email, password);
////        assertEquals(expResult, result);
////        fail("The test case is a prototype.");
////    }
////
////    @Test
////    public void testRegisterEmp() throws Exception {
////        System.out.println("registerEmp");
////        String username = "";
////        String password = "";
////        String role = "";
////        UserMapper.registerEmp(username, password, role);
////        fail("The test case is a prototype.");
////    }
////
////    @Test
////    public void testRegisterCustomer() throws Exception {
////        System.out.println("registerCustomer");
////        Customer customer = null;
////        UserMapper.registerCustomer(customer);
////        fail("The test case is a prototype.");
////    }
////
////    @Test
////    public void testDeleteEmployee() throws Exception {
////        System.out.println("deleteEmployee");
////        int userID = 0;
////        UserMapper.deleteEmployee(userID);
////        fail("The test case is a prototype.");
////    }
////
////    @Test
////    public void testGetAllEmployees() throws Exception {
////        System.out.println("getAllEmployees");
////        List<Employee> expResult = null;
////        List<Employee> result = UserMapper.getAllEmployees();
////        assertEquals(expResult, result);
////        fail("The test case is a prototype.");
////    }
////
////    @Test
////    public void testGetCustomer() throws Exception {
////        System.out.println("getCustomer");
////        int customerID = 0;
////        Customer expResult = null;
////        Customer result = UserMapper.getCustomer(customerID);
////        assertEquals(expResult, result);
////        fail("The test case is a prototype.");
////    }
//    public void teardown() {
////        testCon = null;
//        try {
//            Connector.connection().close();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserMapperTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserMapperTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
//
//}

//public class Connector {
//
//    private static final String USERNAME = "foguser";
//    private static final String PASSWORD = "FOG99carport";
//    private static DataSource ds =  new MysqlDataSource();
//    private static Connection singleton;
//    
//    public static void setConnection( Connection con ) {
//        singleton = con;
//    }
//    
//    public static Connection connection() throws ClassNotFoundException, SQLException {
//        if ( singleton == null ) {
//            singleton = ds.getConnection(USERNAME, PASSWORD);
//        }
//        return singleton;
//    }
