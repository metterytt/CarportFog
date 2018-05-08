
package functionLayer;

import dbAccess.Mapper;
import functionLayer.entity.Customer;
import functionLayer.entity.CustomerCalculation;
import functionLayer.entity.Employee;
import functionLayer.entity.LineItem;
import functionLayer.entity.Order;
import java.util.ArrayList;
import java.util.List;

public class StorageFacade {
    
    public static LineItem getProduct(int productID) throws CarportException{
        return Mapper.getProduct(productID);
    }

    public static void addCustCalc(int length, int width, int angle, int shedLength, int shedWidth) throws CarportException {
        Mapper.addCustCalc(length, width, angle, shedLength, shedWidth);
    }
    
    public static Employee login(String username, String password) throws CarportException {
        return Mapper.login(username, password);
    }
    
    public static Customer loginCustomer(String username, String password) throws CarportException{
        return Mapper.loginCustomer(username, password);
    }

    public static ArrayList<CustomerCalculation> getCustCalcs() throws CarportException {
        return Mapper.getCustCalcs();
    }

    public static void addRequest(int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {
        Mapper.addRequest(length, width, angle, shedLength, shedWidth, price);
    }
    
    public static void registerEmp(String username, String password, String role) throws CarportException{
        Mapper.registerEmp(username, password, role);
    }

     public static void registerCustomer(String username, String password) throws CarportException{
         Mapper.registerCustomer(username, password);
     }
    
    public static List<Order> getOpenRequests() throws CarportException {
        return Mapper.getOpenRequests();
    }

    public static void setOrdered(int orderID) throws CarportException {
        Mapper.setOrdered(orderID);
    }

    public static List<Order> getOrders() throws CarportException {
        return Mapper.getOrders();
    }
    
}
