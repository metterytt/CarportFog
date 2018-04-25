package dbAccess;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

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

<<<<<<< HEAD
yo yo det er yzma her

yoyo kronk is also here
=======
yo yo det er yzma her igen hehe
>>>>>>> 85963e1fefd42b2f07fde9d7397d33801555ec49
