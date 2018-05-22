package dbAccess;

import functionLayer.CarportException;
import functionLayer.entity.Customer;
import functionLayer.entity.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mette
 */
public class UserMapper {

    public static Employee login(String username, String password) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "select * from employees where username=? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("userID");
                String role = rs.getString("role");
                return new Employee(username, password, role, userID);
            } else {
                throw new CarportException("No user found.. Invalid input", "login");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("something went wrong trying to login", "login");
        }
    }

    public static void registerEmp(String username, String password, String role) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "insert into employees values (null, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Noget gik galt, prøv igen!", "registeremployee");
        }
    }

    public static void deleteEmployee(int userID) throws CarportException {

        try {
            Connection con = Connector.connection();
            String sql = "delete from employees where userID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Error - Cannot delete user", "employee");
        }
    }

    public static List<Employee> getAllEmployees() throws CarportException {
        List<Employee> res = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String sql = "select * from employees";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String role = rs.getString(4);
                Employee emp = new Employee(username, password, role, userID);
                res.add(emp);
            }
            return res;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Error - Cannot fetch all employees", "employee");
        }
    }

    public static Customer loginCustomer(String email, String password) throws CarportException {
        try {
            Connection con = Connector.connection();
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
                new CarportLog("User " + email  +" Logged in", "LoginCustomer");
                return new Customer(ID, email, password, name, lastname, phonenumber);
            } else {
                new CarportLog("User " + email  +" entered wrong info", "LoginCustomer");
                throw new CarportException("No user found.. Invalid input", "login");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("something went wrong trying to login", "login");
        }
    }

    public static Customer getCustomer(int customerID) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "select * from customers where customerID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String email = rs.getString("username");
                String name = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String phonenumber = rs.getString("phonenumber");
                return new Customer(customerID, email, name, lastname, phonenumber);
            } else {
                throw new CarportException("Kunne ikke finde kunden", "ordermanagement");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Noget gik galt.. Prøv igen", "ordermanagement");
        }
    }

    public static void registerCustomer(Customer customer) throws CarportException {
        try {
            Connection con = Connector.connection();
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
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Noget gik galt, prøv igen!", "registercustomer");
        }
    }

}
