package dbAccess;

import functionLayer.CarportException;
import functionLayer.entity.Customer;
import functionLayer.entity.CustomerCalculation;
import functionLayer.entity.Employee;
import functionLayer.entity.LineItem;
import functionLayer.entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mapper {

    private static DBConnector dbc = new DBConnector();

    public static LineItem getProduct(int productID) throws CarportException {
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();

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

        }
        catch (SQLException e) {
            throw new CarportException("Error fetching product.", "index");
        }
    }

    public static void addCustCalc(int length, int width, int angle, int shedLength, int shedWidth) throws CarportException {
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "INSERT INTO customercalculations (cp_length, cp_width, roof_angle,"
                    + " shed_length, shed_width) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, length);
            ps.setInt(2, width);
            ps.setInt(3, angle);
            ps.setInt(4, shedLength);
            ps.setInt(5, shedWidth);
            ps.execute();
        }
        catch (SQLException ex) {
            throw new CarportException("Error adding calculation", "index");
        }
    }

    public static Employee login(String username, String password) throws CarportException {
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "select * from employees where username=? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("userID");
                String role = rs.getString("role");
                return new Employee(username, password, role, userID);
            }
            else {
                throw new CarportException("No user found.. Invalid input", "login");
            }
        }
        catch (SQLException ex) {
            throw new CarportException("something went wrong trying to login", "login");
        }
    }

    public static Customer loginCustomer(String email, String password) throws CarportException {
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "select * from customers where username=? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int ID = rs.getInt("customerID");
                String name = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String phonenumber = rs.getString("phonenumber");
                String role = rs.getString("role");
                return new Customer(ID, email, password, name, lastname, phonenumber, role);
            }
            else {
                throw new CarportException("No user found.. Invalid input", "login");
            }
        }
        catch (SQLException ex) {
            throw new CarportException("something went wrong trying to login", "login");
        }
    }

    public static void registerEmp(String username, String password, String role) throws CarportException {
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "insert into employees values (null, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            throw new CarportException("Noget gik galt, prøv igen!", "registeremployee");
        }
    }

    public static void registerCustomer(Customer customer) throws CarportException {
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "insert into customers (username, password, firstname, lastname, phonenumber) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getName());
            ps.setString(4, customer.getLastname());
            ps.setString(5, customer.getPassword());
            ps.executeUpdate();
            ResultSet gk = ps.getGeneratedKeys();
            gk.next();
            int id = gk.getInt(1);
            customer.setID(id);
        }
        catch (SQLException ex) {
            throw new CarportException("Noget gik galt, prøv igen!", "registercustomer");
        }
    }

    public static ArrayList<CustomerCalculation> getCustCalcs() throws CarportException {
        ArrayList<CustomerCalculation> custCalcs = new ArrayList<>();
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();

            String sql = "select * from customercalculations";
            ResultSet rs = dbc.query(sql);
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
        }
        catch (SQLException ex) {
            throw new CarportException("Error fetching calculations", "employee");
        }
        return custCalcs;
    }

    public static void addRequest(int customerID, int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {

        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "INSERT INTO orders (customer, length, width, roof_angle,"
                    + " shed_length, shed_width, price, employees_userID) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerID);
            ps.setInt(2, length);
            ps.setInt(3, width);
            ps.setInt(4, angle);
            ps.setInt(5, shedLength);
            ps.setInt(6, shedWidth);
            ps.setInt(7, price);
            ps.setInt(8, 1); // dette er en dummy!
            ps.execute();
        }
        catch (SQLException ex) {
            throw new CarportException("Error adding calculation", "index");
        }
    }

    public static List<Order> getOpenRequests() throws CarportException {
        ArrayList<Order> openRequests = new ArrayList<>();
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();

            String sql = "select * from orders where order_placed=0";
            ResultSet rs = dbc.query(sql);
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int customer = rs.getInt("customer");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int angle = rs.getInt("roof_angle");
                int shedLength = rs.getInt("shed_length");
                int shedWidth = rs.getInt("shed_width");
                int price = rs.getInt("price");
                int empID = rs.getInt("employees_userID");
                boolean placed = rs.getBoolean("order_placed");
                Order order = new Order(orderID, customer, length, width, angle, shedLength, shedWidth, price, empID, placed);
                openRequests.add(order);
            }
        }
        catch (SQLException ex) {
            throw new CarportException("Error fetching requests", "employee");
        }
        return openRequests;
    }

    public static void setOrdered(int orderID) throws CarportException {
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "UPDATE `carport`.`orders` SET `order_placed`='1' WHERE `orderID`=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderID);
            ps.executeUpdate();

        }
        catch (SQLException e) {
            throw new CarportException("Error setting sent status.", "employee");
        }
    }

    public static List<Order> getOrders() throws CarportException {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();

            String sql = "select * from orders where order_placed=1";
            ResultSet rs = dbc.query(sql);
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int customer = rs.getInt("customer");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int angle = rs.getInt("roof_angle");
                int shedLength = rs.getInt("shed_length");
                int shedWidth = rs.getInt("shed_width");
                int price = rs.getInt("price");
                int empID = rs.getInt("employees_userID");
                boolean placed = rs.getBoolean("order_placed");
                Order order = new Order(orderID, customer, length, width, angle, shedLength, shedWidth, price, empID, placed);
                orders.add(order);
            }
        }
        catch (SQLException ex) {
            throw new CarportException("Error fetching orders", "employee");
        }
        return orders;
    }

    public static void deleteEmployee(int userID) throws CarportException {

        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "delete from employees where userID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            throw new CarportException("Error - Cannot delete user", "employee");
        }
    }

    public static List<Employee> getAllEmployees() throws CarportException {
        List<Employee> res = new ArrayList<>();
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();

            String sql = "select * from employees";
            ResultSet rs = dbc.query(sql);

            while (rs.next()) {
                int userID = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String role = rs.getString(4);
                Employee emp = new Employee(username, password, role, userID);
                res.add(emp);
            }
            return res;
        }
        catch (SQLException ex) {
            throw new CarportException("Error - Cannot fetch all employees", "employee");
        }
    }

    public static void editRequest(int orderID, int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "update orders SET length = ? , width = ?, roof_angle= ?, shed_length= ?, shed_width= ?, price= ?  WHERE orderID= ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, length);
            ps.setInt(2, width);
            ps.setInt(3, angle);
            ps.setInt(4, shedLength);
            ps.setInt(5, shedWidth);
            ps.setInt(6, price);
            ps.setInt(7, orderID);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new CarportException("Error updating order", "employee");
        }
    }

    public static Customer getCustomer(int customerID) throws CarportException {
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "select * from customers where customerID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String email = rs.getString("username");
                String name = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String phonenumber = rs.getString("phonenumber");
                String role = rs.getString("role");
                return new Customer(customerID, email, name, lastname, phonenumber, role);
            }
            else {
                throw new CarportException("Kunne ikke finde kunden", "ordermanagement");
            }
        }
        catch (SQLException ex) {
            throw new CarportException("Noget gik galt.. Prøv igen", "ordermanagement");
        }
    }

    public static List<Order> getCustomerOrders(int customerID) throws CarportException {
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "select * from orders where customer=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList();
            while (rs.next()) {
                int orderID = rs.getInt(1);
                int length = rs.getInt(3);
                int width = rs.getInt(4);
                int shedLength = rs.getInt(5);
                int shedWidth = rs.getInt(6);
                int angle = rs.getInt(7);
                int price = rs.getInt(8);
                Boolean placed = rs.getBoolean(10);
                orders.add(new Order(orderID, length, width, angle, shedLength, shedWidth, price, placed));
            }
            return orders;
        }
        catch (SQLException ex) {
            throw new CarportException("Noget gik galt.. Prøv igen", "customer");
        }
    }

}
