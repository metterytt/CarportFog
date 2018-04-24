package dbAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

public class DBConnector {

    private DataSource dataSource;
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    public DBConnector() {

    }

    public DBConnector(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnector() {
        return connection;
    }

    public void open() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
        }
    }

    public void close() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null || !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }

    public ResultSet query(String sql) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }
}
