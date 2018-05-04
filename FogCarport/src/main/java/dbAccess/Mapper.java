package dbAccess;

import functionLayer.CarportException;
import functionLayer.entity.Employee;
import functionLayer.entity.LineItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            int price = rs.getInt("price");
            LineItem product = new LineItem(productID, name, uom, price);
            return product;

        } catch (SQLException e) {
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
        } catch (SQLException ex) {
            throw new CarportException("Error adding calculation", "index");
        }
    }
        public static Employee login(String username, String password) throws CarportException {
        try {
            dbc.setDataSource(new DataSourceFog().getDataSource());
            dbc.open();
            Connection con = dbc.getConnector();
            String sql = "select * from employee where username=? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
        }
        catch (SQLException ex) {
            throw new CarportException("something went wrong trying to login", "index");
        }
            return null;
    }

}
