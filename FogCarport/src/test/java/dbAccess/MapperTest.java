/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import static dbAccess.Mapper.login;
import functionLayer.entity.Customer;
import functionLayer.entity.CustomerCalculation;
import functionLayer.entity.Employee;
import functionLayer.entity.LineItem;
import functionLayer.entity.Order;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jesper
 */
public class MapperTest {
    
    public MapperTest() {
    }

    /**
     * Test of getProduct method, of class Mapper.
     */
    @Test
    public void testGetProduct() throws Exception {
    LineItem test = new LineItem(3, "Lægte 38x73 ubeh.", "m", 4395);
    LineItem itemFromDB = Mapper.getProduct(3);
    assertEquals(itemFromDB.getProductID(), test.getProductID());
    assertEquals(itemFromDB.getName(), test.getName());
    assertEquals(itemFromDB.getUom(), test.getUom());
    assertEquals(itemFromDB.getPricePerUnit(), 0.1 ,test.getPricePerUnit());

    
    }

    /**
     * Test of addCustCalc method, of class Mapper.
     */
    @Test
    public void testAddCustCalc() throws Exception {
    }

    /**
     * Test of login method, of class Mapper.
     */
    @Test
    public void testLogin() throws Exception {
    Employee emp = new Employee("lars", "per", "salesman", 2);
    Employee test = Mapper.login("lars", "per");
    assertEquals(emp.getUserID(), test.getUserID());
    assertEquals(emp.getRole(), test.getRole());
    }

    /**
     * Test of loginCustomer method, of class Mapper.
     */
    @Test
    public void testLoginCustomer() throws Exception {

Customer testCustomer = new Customer(1, "lars@lars.dk", "123", "lars", "per", "22464462", "customer");
Customer dbCustomer = Mapper.loginCustomer("lars@lars.dk", "123");
assertEquals(dbCustomer.getEmail(), testCustomer.getEmail());
assertTrue(dbCustomer.getID() == testCustomer.getID());
}

    /**
     * Test of registerEmp method, of class Mapper.
     */
    @Test
    public void testRegisterEmp() throws Exception {
    }

    /**
     * Test of registerCustomer method, of class Mapper.
     */
    @Test
    public void testRegisterCustomer() throws Exception {
    }

    /**
     * Test of getCustCalcs method, of class Mapper.
     */
    @Test
    public void testGetCustCalcs() throws Exception {
    List<CustomerCalculation> testList = Mapper.getCustCalcs();
    CustomerCalculation test = testList.get(2);
    CustomerCalculation cc = new CustomerCalculation(3, 330, 270, 0, 210, 150);
    
    assertEquals(test.getCalcID(), cc.getCalcID());
    assertEquals(test.getLength(), cc.getLength());
    assertFalse(test.getLength() == 300);
    assertTrue(test.getWidth() == test.getWidth());
    
    }

    /**
     * Test of addRequest method, of class Mapper.
     */
    @Test
    public void testAddRequest() throws Exception {
    }

    /**
     * Test of getOpenRequests method, of class Mapper.
     */
    @Test
    public void testGetOpenRequests() throws Exception {
    }

    /**
     * Test of setOrdered method, of class Mapper.
     */
    @Test
    public void testSetOrdered() throws Exception {
    }

    /**
     * Test of getOrders method, of class Mapper.
     */
    @Test
    public void testGetOrders() throws Exception {
    List<Order> test = Mapper.getOrders();
        Order orderTest = null;
        Order orderMade = new Order(46, 1, 100, 100, 100, 100, 100, 100, 1, true);
        for (Order order : test) {
            if(order.getOrderID() == 46){
               orderTest = order;
            }
        }
     assertEquals(orderTest.getOrderID(), orderMade.getOrderID());
     assertFalse(orderTest.getWidth() == 200);
    
    }

    /**
     * Test of deleteEmployee method, of class Mapper.
     */
    @Test
    public void testDeleteEmployee() throws Exception {
    }

    /**
     * Test of getAllEmployees method, of class Mapper.
     */
    @Test
    public void testGetAllEmployees() throws Exception {
    
       List<Employee> test = Mapper.getAllEmployees();
       Employee e = test.get(0);
       Employee testEmp = new Employee("test@test.dk", "test", "IT", 1);
       assertEquals(e.getUserID(), testEmp.getUserID());
       assertEquals(e.getUsername(), testEmp.getUsername());
        
    }

    /**
     * Test of editRequest method, of class Mapper.
     */
    @Test
    public void testEditRequest() throws Exception {
    }
    
}
