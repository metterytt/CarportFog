package functionLayer;

import dbAccess.OrderMapper;
import dbAccess.UserMapper;
import functionLayer.entity.Customer;
import functionLayer.entity.CustomerCalculation;
import functionLayer.entity.Employee;
import functionLayer.entity.LineItem;
import functionLayer.entity.Order;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StorageFacade {

    public static LineItem getProduct(int productID) throws CarportException {
        return OrderMapper.getProduct(productID);
    }

    public static void addCustCalc(int length, int width, int angle, int shedLength, int shedWidth) throws CarportException {

        OrderMapper.addCustCalc(length, width, angle, shedLength, shedWidth);
    }

    public static ArrayList<CustomerCalculation> getCustCalcs() throws CarportException {
        return OrderMapper.getCustCalcs();
    }

    public static void addRequest(int customerID, int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {
        OrderMapper.addRequest(customerID, length, width, angle, shedLength, shedWidth, price);
    }

    public static List<Order> getOpenRequests() throws CarportException {
        return OrderMapper.getOpenRequests();
    }

    public static List<Order> getOrders() throws CarportException {
        return OrderMapper.getOrders();
    }

    public static void editRequest(int orderID, int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {
        OrderMapper.editRequest(orderID, length, width, angle, shedLength, shedWidth, price);
    }

    public static List<Order> getCustomerOrders(int customerID) throws CarportException {
        return OrderMapper.getCustomerOrders(customerID);
    }

    public static void updateTotalPrice(int price, int orderID) throws CarportException {
        OrderMapper.updateTotalPrice(price, orderID);
    }

    public static void addBomToOrder(List<LineItem> listToBeSaved, int orderID) throws CarportException {
        OrderMapper.addBomToOrder(listToBeSaved, orderID);
    }

    public static List<LineItem> getFinalBom(int orderID) throws CarportException {
        return OrderMapper.getFinalBom(orderID);
    }

    public static int getOrderTotalPrice(int orderID) throws CarportException {
        return OrderMapper.getOrderTotalPrice(orderID);
    }

    public static Employee login(String username, String password) throws CarportException {
        return UserMapper.login(username, password);
    }

    public static Customer loginCustomer(String username, String password) throws CarportException {
        return UserMapper.loginCustomer(username, password);
    }

    public static void registerEmp(String username, String password, String role) throws CarportException {
        UserMapper.registerEmp(username, password, role);
    }

    public static void registerCustomer(Customer customer) throws CarportException {
        UserMapper.registerCustomer(customer);
    }

    public static void deleteEmployee(int userID) throws CarportException {
        UserMapper.deleteEmployee(userID);
    }

    public static List<Employee> getAllEmployees() throws CarportException {
        return UserMapper.getAllEmployees();
    }

    public static Customer getCustomer(int customerID) throws CarportException {
        return UserMapper.getCustomer(customerID);
    }
    
    public static void PayForOrder(int orderID) throws CarportException {
        OrderMapper.PayForOrder(orderID);
    }

}
