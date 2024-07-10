package br.com.ideao.jdbcmariadb.teste;

import br.com.ideao.jdbcmariadb.dao.ContatoDAO;
import br.com.ideao.jdbcmariadb.factory.ConnectionFactory;
import br.com.ideao.jdbcmariadb.modelo.Contato;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteListar {
    public static void main(String[] args) {
        try(Connection c = new ConnectionFactory().getConnection()) {
            ContatoDAO contatoDAO = new ContatoDAO(c);

            for (Contato contato: contatoDAO.listar()){
                System.out.println(contato);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
