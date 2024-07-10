package br.com.ideao.jdbcmariadb.teste;

import br.com.ideao.jdbcmariadb.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) throws SQLException {
        try (Connection c = new ConnectionFactory().getConnection()){
            System.out.println("conectado");
        }
    }


}
