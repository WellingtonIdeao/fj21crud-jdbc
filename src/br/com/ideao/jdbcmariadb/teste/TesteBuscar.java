package br.com.ideao.jdbcmariadb.teste;

import br.com.ideao.jdbcmariadb.dao.ContatoDAO;
import br.com.ideao.jdbcmariadb.factory.ConnectionFactory;

import java.sql.Connection;

public class TesteBuscar {
    public static void main(String[] args) {
        Connection connection = new ConnectionFactory().getConnection();
        ContatoDAO contatoDAO = new ContatoDAO(connection);
        System.out.println(contatoDAO.buscar(2));
    }
}
