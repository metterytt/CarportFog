package dbAccess;

import functionLayer.CarportException;
import functionLayer.entity.Customer;
import functionLayer.entity.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserMapperTest {

    private static Connection testConnection;
    private static final String USER = "foguser";
    private static final String USERPW = "FOG99carport";
    private static final String DBNAME = "carportTest";
    private static final String HOST = "159.89.99.43";

    @Before
    public void setUp() {
        try {
            // avoid making a new connection for each test
            if (testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test 
                Connector.setConnection(testConnection);
            }

            // reset test database
            try (Statement stmt = testConnection.createStatement()) {
                stmt.execute("drop table if exists customers");
                stmt.execute("create table customers like customersTest");
                stmt.execute("insert into customers select * from customersTest");
                stmt.execute("drop table if exists employees");
                stmt.execute("create table employees like employeesTest");
                stmt.execute("insert into employees select * from employeesTest");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Test
    public void testSetUpOK() {
        assertNotNull(testConnection);
    }

    @Test
    public void testClass(){
        UserMapper m = new UserMapper();
    }
    
    @Test
    public void testLoginCustomer01() throws CarportException {
        Customer customer = UserMapper.loginCustomer("lars@lars.dk", "larsPW");
        assertTrue(customer != null);
        
        
    }

    @Test(expected = CarportException.class)
    public void testLoginCustomer02() throws CarportException {
        Customer customer = UserMapper.loginCustomer("anna@anna.com", "larsPW");
    }

    @Test
    public void testLoginCustomer03() throws CarportException {
        Customer customer = UserMapper.loginCustomer("lars@lars.dk", "larsPW");
        assertEquals("12345678", customer.getPhoneNumber());
    }

    @Test(expected = CarportException.class)
    public void testLoginCustomer04() throws CarportException {
        try {
            testConnection.close();
            UserMapper.loginCustomer("anna@anna.djk", "annaPW");
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }

    @Test
    public void testRegisterCustomer() throws CarportException {
        Customer customer = new Customer("lotte@lotte.dk", "lottePW", "lotte", "jensen", "45674567");
        UserMapper.registerCustomer(customer);
        assertEquals("45674567", customer.getPhoneNumber());
    }
     @Test(expected = CarportException.class)
    public void testRegisterCustomer02() throws CarportException {
        try {
            testConnection.close();
            Customer customer = new Customer("lotte@lotte.dk", "lottePW", "lotte", "jensen", "45674567");
        UserMapper.registerCustomer(customer);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }
    

    @Test
    public void testGetCustomer() throws CarportException {
        int customerID = 1;
        Customer customer = UserMapper.getCustomer(customerID);
        assertEquals("12345678", customer.getPhoneNumber());
    }
    
    @Test(expected = CarportException.class)
    public void testGetCustomer02() throws CarportException {
        int customerID = 400;
        Customer customer = UserMapper.getCustomer(customerID);
    }
    @Test(expected = CarportException.class)
    public void testGetCustomer03() throws CarportException {
        try {
            testConnection.close();
            int customerID = 1;
        Customer customer = UserMapper.getCustomer(customerID);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }
    

    @Test
    public void testRegisterEmp() throws CarportException {
//        Employee emp = new Employee("kirsten@kirsten.dk", "kirsten", "jensen", 3);
        UserMapper.registerEmp("kirsten@kirsten.dk", "kirsten", "IT");
        assertEquals(UserMapper.getAllEmployees().size(), 3);
    }
    
     @Test(expected = CarportException.class)
    public void testRegisterEmp02() throws CarportException {
        try {
            testConnection.close();
            UserMapper.registerEmp("kirsten@kirsten.dk", "kirsten", "IT");
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }
    
    

    @Test
    public void testGetAllEmployees() throws CarportException {
        List<Employee> result = UserMapper.getAllEmployees();
        assertEquals(result.size(), 2);
    }
    
     @Test(expected = CarportException.class)
    public void testGetAllEmployees02() throws CarportException {
        try {
            testConnection.close();
              List<Employee> result = UserMapper.getAllEmployees();
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }

    @Test
    public void testDeleteEmployee() throws CarportException {
        int empID = 2;
        UserMapper.deleteEmployee(empID);
        assertEquals(UserMapper.getAllEmployees().size(), 1);
    }
    
     @Test(expected = CarportException.class)
    public void testDeleteEmployee02() throws CarportException {
        try {
            testConnection.close();
            int empID = 2;
                UserMapper.deleteEmployee(empID);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }

    @Test
    public void testLoginEmp() throws CarportException {
        Employee e = UserMapper.login("testemp@testemp.dk", "testPW");
        assertTrue(e != null);
    }

    @Test(expected = CarportException.class)
    public void testLoginEmp02() throws CarportException {
        Employee e = UserMapper.login("testemp@testemp.dk", "testPW2");
    }
    
     @Test(expected = CarportException.class)
    public void testLoginEmp03() throws CarportException {
        try {
            testConnection.close();
            Employee e = UserMapper.login("testemp@testemp.dk", "testPW");
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }

}
