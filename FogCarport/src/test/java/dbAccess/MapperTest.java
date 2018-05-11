/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import static dbAccess.Mapper.login;
import functionLayer.entity.Employee;
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
    }

    /**
     * Test of loginCustomer method, of class Mapper.
     */
    @Test
    public void testLoginCustomer() throws Exception {
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
    }

    /**
     * Test of editRequest method, of class Mapper.
     */
    @Test
    public void testEditRequest() throws Exception {
    }
    
}
