package dbAccess;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.New;

public class DataSourceFog {

    private final MysqlDataSource dataSource = new MysqlDataSource();

    public DataSourceFog() {
        dataSource.setServerName("159.89.99.43");
        dataSource.setPort(3306);
        dataSource.setUser("foguser");
        dataSource.setPassword("FOG99carport");
        dataSource.setDatabaseName("carport");
    }
    
    
    public MysqlDataSource getDataSource() {
        return dataSource;
    }

}

