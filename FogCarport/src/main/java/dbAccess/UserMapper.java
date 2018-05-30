package dbAccess;

import functionLayer.CarportException;
import functionLayer.CarportLog;
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
 * The Usermapper gives access to the employees and customers table in the
 * database.
 *
 * @author Snøvsen
 */
public class UserMapper {

    /**
     * Finds an employee in the database. If the Employee exists in the
     * employees table, it will be returned, so an entity can be created and
     * returned.
     *
     * @param username String type not null.
     * @param password String type not null.
     * @return An Employee Object if the given Employee exists in the database.
     * @throws CarportException If the Employee does not exist in the database.
     */
    public static Employee login(String username, String password) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "select * from employees where username=? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("empID");
                String role = rs.getString("role");
                return new Employee(username, password, role, userID);
            } else {
                throw new CarportException("No user found.. Invalid input", "login");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("something went wrong trying to login", "login");
        }
    }

    /**
     * Inserts a new employee to the database. With the given parameters a new
     * employee will be created in the employees table, in the database.
     *
     * @param username String type not null.
     * @param password String type not null.
     * @param role String type not null.
     * @throws CarportException If something goes wrong, trying to insert into
     * the database, an exception will be thrown to be displayed on
     * registeremployee.jsp.
     */
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
            throw new CarportException("Brugeren eksistere i forvejen eller fejl med connection til DB", "registeremployee");
        }
    }

    /**
     * An employee will get deleted from the database. With the given parameter,
     * the employee with the unique userID gets removed from the employees
     * table, in the database.
     *
     * @param userID int type always 1 or higher, to match one of the userID's
     * in the database.
     * @throws CarportException If something fails trying to delete the given
     * employee from the database, an exception will be thrown and displayed on
     * employee.jsp.
     */
    public static void deleteEmployee(int userID) throws CarportException {

        try {
            Connection con = Connector.connection();
            String sql = "delete from employees where empID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Error - Cannot delete user", "employee");
        }
    }

    /**
     * Creates a list of all employees in the database. For every row in the
     * database, a new Employee entity will be created and added to a list.
     *
     * @return a List of all Employees in the database.
     * @throws CarportException if something goes wrong trying to fetch all of
     * the employees.
     */
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

    /**
     * Finds a customer from the database. With the given parameters a customer
     * might be found from the customers table, to login on the site. Every time
     * a customer succesfully logs in or fail to do so, it will be logged.
     *
     * @param email type string, not null.
     * @param password type string, not null.
     * @return a Customer entity object, to be used when logged in.
     * @throws CarportException if no user exists, with the given parameters or
     * if something goes wrong trying to fetch one.
     */
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
                new CarportLog("User " + email + " Logged in", "LoginCustomer");
                return new Customer(ID, email, password, name, lastname, phonenumber);
            } else {
                new CarportLog("User " + email + " entered wrong info", "LoginCustomer");
                throw new CarportException("No user found.. Invalid input", "login");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("something went wrong trying to login", "login");
        }
    }

    /**
     * Finds a customer with the given unique customerID. If the customer is in
     * the customers table, it will be used, to display contact info on
     * ordermanagement.jsp, using the include file Userinfo.jspf.
     *
     * @param customerID int type always 1 or higher, to match one of the
     * existing customers in the database.
     * @return a Customer entity object.
     * @throws CarportException if the customer cannot be found in the database
     * or if something goes wrong trying to fetch it.
     */
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

    /**
     * A customer will be inserted into the database. With the given parameter,
     * a new customer will be inserted into the customers table.
     *
     * @param customer Customer entity type, which gets created in
     * RegisterCustomer.java
     * @throws CarportException if something goes wrong trying to insert the
     * customer to the customers table.
     */
    public static void registerCustomer(Customer customer) throws CarportException {
        try {
            Connection con = Connector.connection();
            String sql = "insert into customers (username, password, firstname, lastname, phonenumber) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getName());
            ps.setString(4, customer.getLastname());
            ps.setString(5, customer.getPhoneNumber());
            ps.executeUpdate();
            ResultSet gk = ps.getGeneratedKeys();
            gk.next();
            int id = gk.getInt(1);
            customer.setID(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Ikke muligt at oprette brugeren, prøv evt. andet brugernavn, eller kontakt kundeservice!", "registercustomer");
        }
    }

}
