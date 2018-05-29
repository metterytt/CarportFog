package dbAccess;

import functionLayer.CarportException;
import functionLayer.entity.CustomerCalculation;
import functionLayer.entity.LineItem;
import functionLayer.entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gives access to the tables customercalculations, orders, lineitems and products.
 * @author Snøvsen
 */
public class OrderMapper {

    /**
     * Finds a LineItem in the products table in the database.
     * The product/LineItem is used in the calculator classes.
     * @param productID int type always 1 or higher and should match one of the existing productID's.
     * @return a LineItem entity type with the given productID.
     * @throws CarportException if something goes wrong trying to fetch the product from the products table.
     */
    public static LineItem getProduct(int productID) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "SELECT * FROM carport.products "
                    + "WHERE productID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String name = rs.getString("name");
            String uom = rs.getString("uom");
            int pricePerUnit = rs.getInt("price");
            LineItem product = new LineItem(productID, name, uom, pricePerUnit);
            return product;

        } catch (SQLException | ClassNotFoundException e) {
            throw new CarportException("Error fetching product.", "index");
        }
    }

    /**
     * Inserts a calculation of a carport to the database.
     * Every time someone make a calculation on the site, it will be inserted into the customercalculations table.
     * @param length type int cannot be 0
     * @param width type int cannot be 0
     * @param angle type int can be 0 if no angle is wanted
     * @param shedLength type int can be 0 if no shed is wanted
     * @param shedWidth type int can be 0 if no shed is wanted
     * @throws CarportException if something goes wrong trying ot insert the calculations into the database.
     */
    public static void addCustCalc(int length, int width, int angle, int shedLength, int shedWidth) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "INSERT INTO customercalculations (cp_length, cp_width, roof_angle,"
                    + " shed_length, shed_width) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, length);
            ps.setInt(2, width);
            ps.setInt(3, angle);
            ps.setInt(4, shedLength);
            ps.setInt(5, shedWidth);
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Error adding calculation", "index");
        }
    }

    /**
     * Makes a list of all calculations from the customercalculations table.
     * @return a list of calculations that has ever been made.
     * @throws CarportException if something goes wrong trying to fetch all the calculations from the database.
     */
    public static ArrayList<CustomerCalculation> getCustCalcs() throws CarportException {
        ArrayList<CustomerCalculation> custCalcs = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String sql = "select * from customercalculations";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ccID = rs.getInt("customercalculations_id");
                int length = rs.getInt("cp_length");
                int width = rs.getInt("cp_width");
                int angle = rs.getInt("roof_angle");
                int shedLength = rs.getInt("shed_length");
                int shedWidth = rs.getInt("shed_width");
                CustomerCalculation custCalc = new CustomerCalculation(ccID, length, width, angle, shedLength, shedWidth);
                custCalcs.add(custCalc);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Error fetching calculations", "employee");
        }
        return custCalcs;
    }

    /**
     * Inserts an order request into the database.
     * With the given parameters a new request is inserted into the orders table.
     * @param customerID int type always 1 or higher, matching the customer currently logged in.
     * @param length int type
     * @param width int type
     * @param angle int type
     * @param shedLength int type
     * @param shedWidth int type
     * @param price int type. Total price estimate of the request.
     * @throws CarportException if something goes wrong when inserting the request.
     */
    public static void addRequest(int customerID, int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {

        try {
            Connection con = Connector.connection();
            String sql = "INSERT INTO orders (customerID, length, width, roof_angle,"
                    + " shed_length, shed_width, price, empID) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerID);
            ps.setInt(2, length);
            ps.setInt(3, width);
            ps.setInt(4, angle);
            ps.setInt(5, shedLength);
            ps.setInt(6, shedWidth);
            ps.setInt(7, price);
            ps.setInt(8, 1); // empID is set to 1 as default, and will be set to the ID of the salesman managing the specific request, when it is turned into an order.
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Error adding calculation", "index");
        }
    }

    /**
     * Gets a list of all requests from the database.
     * All order requests from the database will be found with the boolean order_placed in the orders table.
     * @return a list of all requests in the database, that is not placed yet.
     * @throws CarportException if something goes wrong trying to fetch all requests from the database.
     */
    public static List<Order> getOpenRequests() throws CarportException {
        List<Order> openRequests = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String sql = "select * from orders where order_placed=0";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int customer = rs.getInt("customerID");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int angle = rs.getInt("roof_angle");
                int shedLength = rs.getInt("shed_length");
                int shedWidth = rs.getInt("shed_width");
                int price = rs.getInt("price");
                int empID = rs.getInt("empID");
                int placed = rs.getInt("order_placed");
                Order order = new Order(orderID, customer, length, width, angle, shedLength, shedWidth, price, empID, placed);
                openRequests.add(order);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Error fetching requests", "employee");
        }
        return openRequests;
    }

    /**
     * Gets a list of all orders from the database.
     * All orders from the database will be found with the boolean order_placed in the orders table.
     * @return a list of all orders in the database, that is a placed order.
     * @throws CarportException if something goes wrong trying to fetch all orders from the database. 
     */
    public static List<Order> getOrders() throws CarportException {
        List<Order> orders = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String sql = "select * from orders where order_placed=1 or order_placed=2";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int customer = rs.getInt("customerID");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int angle = rs.getInt("roof_angle");
                int shedLength = rs.getInt("shed_length");
                int shedWidth = rs.getInt("shed_width");
                int price = rs.getInt("price");
                int empID = rs.getInt("empID");
                int placed = rs.getInt("order_placed");
                Order order = new Order(orderID, customer, length, width, angle, shedLength, shedWidth, price, empID, placed);
                orders.add(order);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Error fetching orders", "employee");
        }
        return orders;
    }

    /**
     * Updates an order from the database orders table.
     * Finds a specific order with the unique orderID and updates it with the new paramaters in the database.
     * @param orderID int type. Is unique to 1 order, and is always 1 or higher.
     * @param length int type
     * @param width int type
     * @param angle int tyep
     * @param shedLength int type
     * @param shedWidth int type
     * @param price int type
     * @throws CarportException if something goes wrong trying to update the order.
     */
    public static void editRequest(int orderID, int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "update orders SET length = ? , width = ?, roof_angle= ?, shed_length= ?, shed_width= ?, price= ?  WHERE orderID= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, length);
            ps.setInt(2, width);
            ps.setInt(3, angle);
            ps.setInt(4, shedLength);
            ps.setInt(5, shedWidth);
            ps.setInt(6, price);
            ps.setInt(7, orderID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CarportException("Error updating order", "employee");
        }
    }

    /**
     * Gets a list of all orders/requests that a specific customer has placed.
     * @param customerID int type that matches an existing customer.
     * @return a list of orders/requests to be displayed in customerview.jsp
     * @throws CarportException if something goes wrong trying to fetch all orders.
     */
    public static List<Order> getCustomerOrders(int customerID) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "select * from orders where customerID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList();
            while (rs.next()) {
                int orderID = rs.getInt(1);
                int customer = rs.getInt(2);
                int length = rs.getInt(3);
                int width = rs.getInt(4);
                int angle = rs.getInt(5);
                int shedLength = rs.getInt(6);
                int shedWidth = rs.getInt(7);
                int price = rs.getInt(8);
                int empID = rs.getInt(9);
                int placed = rs.getInt(10);
                orders.add(new Order(orderID, customer, length, width, angle, shedLength, shedWidth, price, empID, placed));
            }
            return orders;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Noget gik galt.. Prøv igen", "customer");
        }
    }

    /**
     * Sets an order to placed and insets a bill of material to the database.
     * When the request has been paid for, the boolean order_placed is changed to 1 on the given order, in the orders table.
     * A salesman's ID will also be added to the order, to display which salesman has managed the order.
     * Furthermore lineitems will be added to the lineitems table.
     * @param listToBeSaved bill of material being added as lineitems in the database. Not null!
     * @param orderID int type and matches an existing order request in the orders table.
     * @param empID int type. Matches an existing employee from the employees table.
     * @throws CarportException if something goes wrong trying to update orders table or inserting into the lineitems table.
     */
    public static void addBomToOrder(List<LineItem> listToBeSaved, int orderID, int empID) throws CarportException {
        try {
            Connection con = Connector.connection();
            con.setAutoCommit(false);
            String setOrdered = "UPDATE orders SET order_placed=1, empID=? WHERE orderID=?";
            String addLineItem = "INSERT INTO lineitems (orderID, productID, use_in, quantity)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement psSet = con.prepareStatement(setOrdered);
            PreparedStatement psAdd = con.prepareStatement(addLineItem);
            psSet.setInt(1, empID);
            psSet.setInt(2, orderID);
            psSet.executeUpdate();
            for (LineItem li : listToBeSaved) {
                psAdd.setInt(1, orderID);
                psAdd.setInt(2, li.getProductID());
                psAdd.setString(3, li.getUseInContext());
                psAdd.setDouble(4, li.getQuantity());
                psAdd.executeUpdate();
            }
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Fejl ved lagring af stykliste", "employee");
        }
    }

    /**
     * Makes a list of LineItems to display the final bill of material.
     * @param orderID int type. Is unique to a single order in the database.
     * @return a list of LineItems connected to an order.
     * @throws CarportException if something goes wrong trying to fetch the lineitems or products.
     */
    public static List<LineItem> getFinalBom(int orderID) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "select lineitems.productID, lineitems.use_in, lineitems.quantity, products.name, products.uom, products.price"
                    + " from lineitems inner join products on lineitems.productID = products.productID where lineitems.orderID=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            List<LineItem> finalBom = new ArrayList();
            while (rs.next()) {
                int productID = rs.getInt(1);
                String useInContext = rs.getString(2);
                double quantity = rs.getDouble(3);
                String name = rs.getString(4);
                String uom = rs.getString(5);
                int pricePerUnit = rs.getInt(6);
                LineItem li = new LineItem(productID, name, uom, pricePerUnit);
                li.setUseInContext(useInContext);
                li.setQuantity(quantity);
                finalBom.add(li);
            }
            return finalBom;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Noget gik galt.. Prøv igen", "customer");
        }
    }

    /**
     * Update the total price of an order.
     * @param price int type. Comes from an inputfrom.
     * @param orderID int type. Matches an existing order. Always 1 or higher.
     * @throws CarportException if something goes wrong trying to update the price in the orders table.
     */
    public static void updateTotalPrice(int price, int orderID) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "update orders SET price = ? WHERE orderID = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, price);
            ps.setInt(2, orderID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Noget gik galt, da du prøvede at opdatere totalprisen", "ordermanagement");
        }
    }

    /**
     * Updates the order to payed.
     * With the parameter the order's order_placed is changed to 2, to show that it has been paid for.
     * @param orderID type int. Matches an existing order.
     * @throws CarportException if something goes wrong trying to update the order.
     */
    public static void PayForOrder(int orderID) throws CarportException {
        try {
            Connection con = Connector.connection();
            String setOrdered = "UPDATE orders SET order_placed=2 WHERE orderID=?";
            PreparedStatement psSet = con.prepareStatement(setOrdered);
            psSet.setInt(1, orderID);
            psSet.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Fejl ved betaling!", "customer");
        }
    }

    /**
     * Finds a specific order from the database.
     * With the given parameter, a unique order should be fetched from the database orders table.
     * @param orderID int type. Is a unique number matching a specific order.
     * @return Order entity with the given orderID from the orders table
     * @throws CarportException if something goes wrong trying to fetch the order from the database.
     */
    public static Order getOrder(int orderID) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "select * from orders where orderID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int customer = rs.getInt(2);
            int length = rs.getInt("length");
            int width = rs.getInt("width");
            int angle = rs.getInt("roof_angle");
            int shedLength = rs.getInt("shed_length");
            int shedWidth = rs.getInt("shed_width");
            int price = rs.getInt("price");
            int empID = rs.getInt("empID");
            int placed = rs.getInt("order_placed");
            Order order = new Order(orderID, customer, length, width, angle, shedLength, shedWidth, price, empID, placed);
            return order;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Fejl ved hentning af ordre", "employee");
        }
    }

}
