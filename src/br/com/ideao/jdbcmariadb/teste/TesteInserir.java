package br.com.ideao.jdbcmariadb.teste;

import br.com.ideao.jdbcmariadb.dao.ContatoDAO;
import br.com.ideao.jdbcmariadb.factory.ConnectionFactory;
import br.com.ideao.jdbcmariadb.modelo.Contato;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

public class TesteInserir {
    public static void main(String[] args) {
        try (Connection connection = new ConnectionFactory().getConnection()) {
            ContatoDAO contatoDAO = new ContatoDAO(connection);

            Contato contato = new Contato();
            contato.setNome("Caelum");
            contato.setEmail("contato@caelum.com.br");
            contato.setEndereco("R. Vergueiro 3185 cj57");
            contato.setDataNascimento(Calendar.getInstance());
            contatoDAO.salvar(contato);
            System.out.println("Gravado!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
