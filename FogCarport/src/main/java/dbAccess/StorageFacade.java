package dbAccess;

import dbAccess.OrderMapper;
import dbAccess.UserMapper;
import functionLayer.CarportException;
import functionLayer.entity.Customer;
import functionLayer.entity.CustomerCalculation;
import functionLayer.entity.Employee;
import functionLayer.entity.LineItem;
import functionLayer.entity.Order;
import java.util.ArrayList;
import java.util.List;

/**
 * Facade accessing the database though Mappers.
 *
 * @author Snøvsen
 */
public class StorageFacade {

    /**
     * is described in OrderMapper: {@link  OrderMapper#getProduct(int)}
     *
     * @param productID
     * @return
     * @throws CarportException
     */
    public static LineItem getProduct(int productID) throws CarportException {
        return OrderMapper.getProduct(productID);
    }

    /**
     * is described in OrderMapper:
     * {@link  OrderMapper#addCustCalc(int, int, int, int, int)}
     *
     * @param length
     * @param width
     * @param angle
     * @param shedLength
     * @param shedWidth
     * @throws CarportException
     */
    public static void addCustCalc(int length, int width, int angle, int shedLength, int shedWidth) throws CarportException {
        OrderMapper.addCustCalc(length, width, angle, shedLength, shedWidth);
    }

    /**
     * is described in OrderMapper: {@link  OrderMapper#getCustCalcs()}
     *
     * @return
     * @throws CarportException
     */
    public static ArrayList<CustomerCalculation> getCustCalcs() throws CarportException {
        return OrderMapper.getCustCalcs();
    }

    /**
     * is described in OrderMapper:
     * {@link  OrderMapper#addRequest(int, int, int, int, int, int, int)}
     *
     * @param customerID
     * @param length
     * @param width
     * @param angle
     * @param shedLength
     * @param shedWidth
     * @param price
     * @throws CarportException
     */
    public static void addRequest(int customerID, int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {
        OrderMapper.addRequest(customerID, length, width, angle, shedLength, shedWidth, price);
    }

    /**
     * is described in OrderMapper: {@link  OrderMapper#getOpenRequests()}
     *
     * @return
     * @throws CarportException
     */
    public static List<Order> getOpenRequests() throws CarportException {
        return OrderMapper.getOpenRequests();
    }

    /**
     * is described in OrderMapper: {@link  OrderMapper#getOrders()}
     *
     * @return
     * @throws CarportException
     */
    public static List<Order> getOrders() throws CarportException {
        return OrderMapper.getOrders();
    }

    /**
     * is described in OrderMapper:
     * {@link  OrderMapper#editRequest(int, int, int, int, int, int, int)}
     *
     * @param orderID
     * @param length
     * @param width
     * @param angle
     * @param shedLength
     * @param shedWidth
     * @param price
     * @throws CarportException
     */
    public static void editRequest(int orderID, int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {
        OrderMapper.editRequest(orderID, length, width, angle, shedLength, shedWidth, price);
    }

    /**
     * is described in OrderMapper: {@link  OrderMapper#getCustomerOrders(int)}
     *
     * @param customerID
     * @return
     * @throws CarportException
     */
    public static List<Order> getCustomerOrders(int customerID) throws CarportException {
        return OrderMapper.getCustomerOrders(customerID);
    }

    /**
     * is described in OrderMapper:
     * {@link  OrderMapper#updateTotalPrice(int, int)}
     *
     * @param price
     * @param orderID
     * @throws CarportException
     */
    public static void updateTotalPrice(int price, int orderID) throws CarportException {
        OrderMapper.updateTotalPrice(price, orderID);
    }

    /**
     * is described in OrderMapper:
     * {@link  OrderMapper#addBomToOrder(java.util.List, int, int)}
     *
     * @param listToBeSaved
     * @param orderID
     * @param empID
     * @throws CarportException
     */
    public static void addBomToOrder(List<LineItem> listToBeSaved, int orderID, int empID) throws CarportException {
        OrderMapper.addBomToOrder(listToBeSaved, orderID, empID);
    }

    /**
     * is described in OrderMapper: {@link  OrderMapper#getFinalBom(int)}
     *
     * @param orderID
     * @return
     * @throws CarportException
     */
    public static List<LineItem> getFinalBom(int orderID) throws CarportException {
        return OrderMapper.getFinalBom(orderID);
    }

    /**
     * is described in OrderMapper: {@link  OrderMapper#getOrder(int)}
     *
     * @param orderID
     * @return
     * @throws CarportException
     */
    public static Order getOrder(int orderID) throws CarportException {
        return OrderMapper.getOrder(orderID);
    }

    /**
     * is described in OrderMapper: {@link  OrderMapper#PayForOrder(int)}
     *
     * @param orderID
     * @throws CarportException
     */
    public static void PayForOrder(int orderID) throws CarportException {
        OrderMapper.PayForOrder(orderID);
    }

    /**
     * is described in UserMapper:
     * {@link  UserMapper#login(java.lang.String, java.lang.String)}
     *
     * @param username
     * @param password
     * @return
     * @throws CarportException
     */
    public static Employee login(String username, String password) throws CarportException {
        return UserMapper.login(username, password);
    }

    /**
     * is described in UserMapper:
     * {@link  UserMapper#loginCustomer(java.lang.String, java.lang.String)}
     *
     * @param username
     * @param password
     * @return
     * @throws CarportException
     */
    public static Customer loginCustomer(String username, String password) throws CarportException {
        return UserMapper.loginCustomer(username, password);
    }

    /**
     * is described in UserMapper:
     * {@link  UserMapper#registerEmp(java.lang.String, java.lang.String, java.lang.String)}
     *
     * @param username
     * @param password
     * @param role
     * @throws CarportException
     */
    public static void registerEmp(String username, String password, String role) throws CarportException {
        UserMapper.registerEmp(username, password, role);
    }

    /**
     * is described in UserMapper:
     * {@link  UserMapper#registerCustomer(functionLayer.entity.Customer)}
     *
     * @param customer
     * @throws CarportException
     */
    public static void registerCustomer(Customer customer) throws CarportException {
        UserMapper.registerCustomer(customer);
    }

    /**
     * is described in UserMapper: {@link  UserMapper#deleteEmployee(int)}
     *
     * @param userID
     * @throws CarportException
     */
    public static void deleteEmployee(int userID) throws CarportException {
        UserMapper.deleteEmployee(userID);
    }

    /**
     * is described in UserMapper: {@link  UserMapper#getAllEmployees()}
     *
     * @return
     * @throws CarportException
     */
    public static List<Employee> getAllEmployees() throws CarportException {
        return UserMapper.getAllEmployees();
    }

    /**
     * is described in UserMapper: {@link  UserMapper#getCustomer(int)}
     *
     * @param customerID
     * @return
     * @throws CarportException
     */
    public static Customer getCustomer(int customerID) throws CarportException {
        return UserMapper.getCustomer(customerID);
    }
}
