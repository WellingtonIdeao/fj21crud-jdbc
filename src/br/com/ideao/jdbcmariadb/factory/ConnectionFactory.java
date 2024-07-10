package br.com.ideao.jdbcmariadb.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mariadb://localhost:3306/fj21?user=root&password=dbsql123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
