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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
//            if (rs.next()) {
            String name = rs.getString("name");
            String uom = rs.getString("uom");
            int price = rs.getInt("price");
            Product product = new Product(productID, name, uom, price);
            return product;
//            } else {
//                throw new CarportException("Could not validate user", "page");
//            }

        } catch (SQLException e) {
            throw new CarportException("Error fetching product.", "page");//WTF
        }
    }
//
//    public static List<Order> getUserOrders(int userID) throws LegoHusException {
//        ArrayList<Order> userOrders = new ArrayList<>();
//        try {
//            dbc.setDataSource(new DataSourceLego().getDataSource());
//            dbc.open();
//            Connection con = dbc.getConnector();
//            String sql = "SELECT * FROM orders where user_id=?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, userID);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int orderID = rs.getInt("order_id");
//                int height = rs.getInt("height");
//                int length = rs.getInt("length");
//                int width = rs.getInt("width");
//                int sent = rs.getInt("sent");
//                Order order = new Order(userID, height, length, width);
//                order.setOrderID(orderID);
//                if (sent == 1) {
//                    order.setSent(true);
//                }
//                userOrders.add(order);
//            }
//            ps.close();
//        } catch (SQLException e) {
//            throw new LegoHusException("Error getting orders.");
//        }
//        return userOrders;
//    }
//
//    public static List<Order> getAllOrders() throws LegoHusException {
//        ArrayList<Order> allOrders = new ArrayList<>();
//        try {
//            dbc.setDataSource(new DataSourceLego().getDataSource());
//            dbc.open();
//            String sql = "SELECT * FROM orders";
//            ResultSet rs = dbc.query(sql);
//            while (rs.next()) {
//                int orderID = rs.getInt("order_id");
//                int userID = rs.getInt("user_id");
//                int height = rs.getInt("height");
//                int length = rs.getInt("length");
//                int width = rs.getInt("width");
//                int sent = rs.getInt("sent");
//                Order order = new Order(userID, height, length, width);
//                order.setOrderID(orderID);
//                if (sent == 1) {
//                    order.setSent(true);
//                }
//                allOrders.add(order);
//            }
//        } catch (SQLException e) {
//            throw new LegoHusException("Error getting orders.");
//        }
//        return allOrders;
//    }
//
//    public static void setSent(int orderID) throws LegoHusException {
//        try {
//            dbc.setDataSource(new DataSourceLego().getDataSource());
//            dbc.open();
//            Connection con = dbc.getConnector();
//            String sql = "UPDATE `legohus`.`orders` SET `sent`='1' WHERE `order_id`=?;";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, orderID);
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new LegoHusException("Error setting sent status.");
//        }
//    }
}
