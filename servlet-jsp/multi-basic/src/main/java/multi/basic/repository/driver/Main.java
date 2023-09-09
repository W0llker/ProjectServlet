package multi.basic.repository.driver;

import multi.basic.repository.db.ClientDb;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        PostgresConnection postgresConnection = new PostgresConnection();
        Connection connection = postgresConnection.getConnection();
    }
}
