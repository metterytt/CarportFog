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

public class OrderMapper {

//    private static DBConnector dbc = new DBConnector();
    public static LineItem getProduct(int productID) throws CarportException {
        try {
//            dbc.setDataSource(new DataSourceFog().getDataSource());
//            dbc.open();
//            Connection con = dbc.getConnector();
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

    public static void addCustCalc(int length, int width, int angle, int shedLength, int shedWidth) throws CarportException {
        try {
//            dbc.setDataSource(new DataSourceFog().getDataSource());
//            dbc.open();
//            Connection con = dbc.getConnector();
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

    public static ArrayList<CustomerCalculation> getCustCalcs() throws CarportException {
        ArrayList<CustomerCalculation> custCalcs = new ArrayList<>();
        try {
            Connection con = Connector.connection();
//            dbc.setDataSource(new DataSourceFog().getDataSource());
//            dbc.open();

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

    public static void addRequest(int customerID, int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {

        try {
//            dbc.setDataSource(new DataSourceFog().getDataSource());
//            dbc.open();
//            Connection con = dbc.getConnector();
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
            ps.setInt(8, 1); // empID bliver sat til 1 i udgangspunktet
            ps.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Error adding calculation", "index");
        }
    }

    public static List<Order> getOpenRequests() throws CarportException {
        ArrayList<Order> openRequests = new ArrayList<>();
        try {
//            dbc.setDataSource(new DataSourceFog().getDataSource());
//            dbc.open();
            Connection con = Connector.connection();
            String sql = "select * from orders where order_placed=0";
//            ResultSet rs = dbc.query(sql);
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

    public static List<Order> getOrders() throws CarportException {
        ArrayList<Order> orders = new ArrayList<>();
        try {

//            dbc.setDataSource(new DataSourceFog().getDataSource());
//            dbc.open();
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

    public static void editRequest(int orderID, int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {
        try {
//            dbc.setDataSource(new DataSourceFog().getDataSource());
//            dbc.open();
//            Connection con = dbc.getConnector();
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

    public static List<Order> getCustomerOrders(int customerID) throws CarportException {
        try {
//            dbc.setDataSource(new DataSourceFog().getDataSource());
//            dbc.open();
//            Connection con = dbc.getConnector();
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

    // sætter ordren til bestilt og gemmer bom i lineitems tabel
    public static void addBomToOrder(List<LineItem> listToBeSaved, int orderID, int empID) throws CarportException {
//        dbc.setDataSource(new DataSourceFog().getDataSource());
//        Connection con = dbc.getConnector();
        try {
//            dbc.open();
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

    public static List<LineItem> getFinalBom(int orderID) throws CarportException {
        try {
//            dbc.setDataSource(new DataSourceFog().getDataSource());
//            dbc.open();
//            Connection con = dbc.getConnector();
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

    public static void updateTotalPrice(int price, int orderID) throws CarportException {
        try {
//            dbc.setDataSource(new DataSourceFog().getDataSource());
//            dbc.open();
//            Connection con = dbc.getConnector();
            Connection con = Connector.connection();
            String sql = "update orders SET price = ? WHERE orderID = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, price);
            ps.setInt(2, orderID);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Noget gik galt, da du prøvede at opdatere totalprisen", "ordermanagement");
//            Logger.getLogger(OrderMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static int getOrderTotalPrice(int orderID) throws CarportException {
//        try {
////            dbc.setDataSource(new DataSourceFog().getDataSource());
////            dbc.open();
////            Connection con = dbc.getConnector();
//            Connection con = Connector.connection();
//            String sql = "select price from orders where orderID=?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, orderID);
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            int price = rs.getInt("price");
//            return price;
//        } catch (SQLException | ClassNotFoundException ex) {
//            throw new CarportException("Noget gik galt.. Prøv igen", "index");
//        }
//    }
    public static void PayForOrder(int orderID) throws CarportException {

//        dbc.setDataSource(new DataSourceFog().getDataSource());
//        Connection con = dbc.getConnector();
        try {
//            dbc.open();
            Connection con = Connector.connection();
            String setOrdered = "UPDATE orders SET order_placed=2 WHERE orderID=?";
            PreparedStatement psSet = con.prepareStatement(setOrdered);
            psSet.setInt(1, orderID);
            psSet.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new CarportException("Fejl ved betaling!", "customer");
        }
    }

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
