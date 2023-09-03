package multi.basic.repository.driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection implements DriverDataBases{
    @Override
    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb",
                    "postgres","nik981ita413");
            return connection;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
