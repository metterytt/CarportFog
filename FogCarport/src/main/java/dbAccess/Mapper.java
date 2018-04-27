/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import functionLayer.CarportException;
import functionLayer.entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mette
 */
public class Mapper {

    private static DBConnector dbc = new DBConnector();

    public static Product getProduct(int productID) throws CarportException {
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
            Product product = new Product(productID, name, uom, price);
            return product;

        } catch (SQLException e) {
            throw new CarportException("Error fetching product.", "page");
        }
    }

}
