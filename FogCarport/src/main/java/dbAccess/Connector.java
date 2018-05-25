/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mette
 */
public class Connector {

//    private static final String URL = "jdbc:mysql://159.89.99.43:3306/carport?autoReconnect=true";
//    private static final String URL = "jdbc:mysql://159.89.99.43:3306/carport";
    private static final String HOST = "159.89.99.43";
    private static final String DBNAME = "carport";
    private static final String URL = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
    private static final String USERNAME = "foguser";
    private static final String PASSWORD = "FOG99carport";
    private static Connection singleton;

    public static void setConnection(Connection con) { 
        singleton = con;
    }

    public static Connection connection() throws ClassNotFoundException, SQLException {
        if (singleton == null) {
            Class.forName("com.mysql.jdbc.Driver");
            singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return singleton;
    }

}
