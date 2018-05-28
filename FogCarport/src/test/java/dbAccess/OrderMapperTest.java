package dbAccess;

import functionLayer.CarportException;
import functionLayer.entity.CustomerCalculation;
import functionLayer.entity.LineItem;
import functionLayer.entity.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jesper
 */
public class OrderMapperTest {
    
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
                stmt.execute("drop table if exists orders");
                stmt.execute("create table orders like ordersTest");
                stmt.execute("insert into orders select * from ordersTest");
                
                stmt.execute("drop table if exists products");
                stmt.execute("create table products like productsTest");
                stmt.execute("insert into products select * from productsTest");
                
                stmt.execute("drop table if exists customercalculations");
                stmt.execute("create table customercalculations like customercalculationsTest");
                stmt.execute("insert into customercalculations select * from customercalculationsTest");
                
                stmt.execute("drop table if exists lineitems");
                stmt.execute("create table lineitems like lineitemsTest");
                stmt.execute("insert into lineitems select * from lineitemsTest");
                
                
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
    public void initiateClass(){
        OrderMapper o = new OrderMapper();
    }
    
    
    @Test
    public void testGetProduct() throws Exception {
    LineItem l = new LineItem(1, "Bræt 25x200 trykimp.", "m", 5495);
    LineItem test = OrderMapper.getProduct(1);
    assertEquals(l.getPricePerUnit(), test.getPricePerUnit(), 0.1);
    assertEquals(l.getName(), test.getName());
    }
    
    @Test(expected = CarportException.class)
    public void testGetProduct02() throws CarportException {
        try {
            testConnection.close();
           LineItem test = OrderMapper.getProduct(1);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }

    /**
     * Test of addCustCalc method, of class OrderMapper.
     */
    @Test
    public void testAddAndGetCustCalc() throws Exception {
    List<CustomerCalculation> list1 = OrderMapper.getCustCalcs();
    OrderMapper.addCustCalc(1, 1, 1, 1, 1);
    List<CustomerCalculation> list2 = OrderMapper.getCustCalcs();
    assertFalse(list1.size() == list2.size());
    assertTrue(list1.size() != list2.size());
    
    }
    
    @Test(expected = CarportException.class)
    public void testAddCalcSQL() throws CarportException {
        try {
            testConnection.close();
             OrderMapper.addCustCalc(1, 1, 1, 1, 1);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }
    @Test(expected = CarportException.class)
    public void testGetCalcSQL() throws CarportException {
        try {
            testConnection.close();
           List<CustomerCalculation> list1 = OrderMapper.getCustCalcs();
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }


    /**
     * Test of addRequest method, of class OrderMapper.
     */
    @Test
    public void testAddAndGetRequest() throws Exception {
    List<Order> list1 = OrderMapper.getOpenRequests();
    OrderMapper.addRequest(1, 1, 1, 1, 1, 1, 1);
    List<Order> list2 = OrderMapper.getOpenRequests();
    assertFalse(list1.size() == list2.size());
    assertTrue(list1.size() != list2.size());
    }

     @Test(expected = CarportException.class)
    public void testAddRequestSQL() throws CarportException {
        try {
            testConnection.close();
            OrderMapper.addRequest(1, 1, 1, 1, 1, 1, 1);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }
    @Test(expected = CarportException.class)
    public void testGetRequestSQL() throws CarportException {
        try {
            testConnection.close();
          List<Order> list2 = OrderMapper.getOpenRequests();
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }
   
    
    

    /**
     * Test of getOrders method, of class OrderMapper.
     */
    @Test
    public void testGetOrders() throws Exception {
     List<Order> list2 = OrderMapper.getOrders();
     assertTrue(list2.size() == 2);
    
    }
    
    @Test(expected = CarportException.class)
    public void testGetOrdersSQL() throws CarportException {
        try {
            testConnection.close();
              List<Order> list2 = OrderMapper.getOrders();
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }

    /**
     * Test of editRequest method, of class OrderMapper.
     */
    @Test
    public void testEditRequest() throws Exception {
    List<Order> list1 = OrderMapper.getOpenRequests();
    OrderMapper.editRequest(2, 1, 1, 1, 1, 1, 1);
      List<Order> list2 = OrderMapper.getOpenRequests();  
    assertFalse(list1.get(0).getLength() == list2.get(0).getLength());
    assertTrue(list1.get(0).getWidth() != list2.get(0).getWidth());
    }
    
    @Test(expected = CarportException.class)
    public void testEditRequestSQL() throws CarportException {
        try {
            testConnection.close();
            OrderMapper.editRequest(2, 1, 1, 1, 1, 1, 1);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }

    /**
     * Test of getCustomerOrders method, of class OrderMapper.
     */
    @Test
    public void testGetCustomerOrders() throws Exception {
    List<Order> list2 = OrderMapper.getCustomerOrders(1);
     assertTrue(list2.size() == 3);
    
    }
    
    @Test(expected = CarportException.class)
    public void testGetCustomerOrdersSQL() throws CarportException {
        try {
            testConnection.close();
             List<Order> list2 = OrderMapper.getCustomerOrders(1);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }

    /**
     * Test of addBomToOrder method, of class OrderMapper.
     */
    @Test
    public void testAddAndGetFinalBomOrder() throws Exception {
        int orderID = 2;
        int empID = 4;
        List<LineItem> l = new ArrayList();
        LineItem lineitem1 = OrderMapper.getProduct(1);
        LineItem lineitem2 = OrderMapper.getProduct(2);
        LineItem lineitem3 = OrderMapper.getProduct(3);
        LineItem lineitem4 = OrderMapper.getProduct(4);
        LineItem lineitem5 = OrderMapper.getProduct(5);
        l.add(lineitem1);
        l.add(lineitem2);
        l.add(lineitem3);
        l.add(lineitem4);
        l.add(lineitem5);
        OrderMapper.addBomToOrder(l, orderID, empID);
        
       List<LineItem> finalbom = OrderMapper.getFinalBom(orderID);
       assertTrue(finalbom.get(0).getName().equals(l.get(0).getName()));
        
    
    }
    
    @Test(expected = CarportException.class)
    public void testAddBomToOrderSQL() throws CarportException {
        try {
            testConnection.close();
             int orderID = 2;
        int empID = 4;
        List<LineItem> l = new ArrayList();
        
            OrderMapper.addBomToOrder(l, orderID, empID);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }
    @Test(expected = CarportException.class)
    public void testGetFinalBomSQL() throws CarportException {
        try {
            testConnection.close();
            List<LineItem> finalbom = OrderMapper.getFinalBom(1);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }

    /**
     * Test of updateTotalPrice method, of class OrderMapper.
     */
    @Test
    public void testUpdateTotalPrice() throws Exception {
    Order o = OrderMapper.getOrder(2);
    int price = o.getPrice();
    OrderMapper.updateTotalPrice(200, 2);
    Order compareOrder = OrderMapper.getOrder(2);
    int comparePrice = compareOrder.getPrice();
    assertFalse(price == comparePrice);
    }
    
     @Test(expected = CarportException.class)
    public void testUpdateTotalPriceSQL() throws CarportException {
        try {
            testConnection.close();
             OrderMapper.updateTotalPrice(200, 3);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }
    @Test(expected = CarportException.class)
    public void testGetOrderSQL() throws CarportException {
        try {
            testConnection.close();
             Order o = OrderMapper.getOrder(2);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }
    /**
     * Test of PayForOrder method, of class OrderMapper.
     */
    @Test
    public void testPayForOrder() throws Exception {
        
    Order o = OrderMapper.getOrder(3);    
    int orderplaced = o.isPlaced();
    OrderMapper.PayForOrder(3);
    Order compareOrder = OrderMapper.getOrder(3);
    int orderplacedCompare = compareOrder.isPlaced();
  
//    assertEquals(orderplaced == orderplacedCompare);
    assertFalse(orderplaced == orderplacedCompare);
//    assertTrue();
    
    
    }
    
    @Test(expected = CarportException.class)
    public void testPayForOrderSQL() throws CarportException {
        try {
            testConnection.close();
            OrderMapper.PayForOrder(3);
        } catch (CarportException | SQLException ex) {
            testConnection = null;
            throw new CarportException(ex.getMessage(), "page");
        }
        
    }
    
}
