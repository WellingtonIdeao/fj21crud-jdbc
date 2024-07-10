package br.com.ideao.jdbcmariadb.teste;

import br.com.ideao.jdbcmariadb.dao.ContatoDAO;
import br.com.ideao.jdbcmariadb.factory.ConnectionFactory;
import br.com.ideao.jdbcmariadb.modelo.Contato;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteAlterar {

    public static void main(String[] args) {
        try(Connection con = new ConnectionFactory().getConnection()){
            ContatoDAO dao = new ContatoDAO(con);
            Contato c1 = dao.buscar(2);
            c1.setEmail("teste@caelum.com.br");
            dao.alterar(c1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
