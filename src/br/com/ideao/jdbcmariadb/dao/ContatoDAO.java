package br.com.ideao.jdbcmariadb.dao;

import br.com.ideao.jdbcmariadb.modelo.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContatoDAO {
    private Connection connection;

    public ContatoDAO(Connection c) {
        this.connection = c;
    }

    public void salvar(Contato contato) {
        String sql = "INSERT INTO contato " +
                "(nome, email, endereco, dataNascimento) " +
                "values ( ?, ?, ?, ?) ";

        try(PreparedStatement pstmt = this.connection.prepareStatement(sql)) {

            pstmt.setString(1, contato.getNome());
            pstmt.setString(2, contato.getEmail());
            pstmt.setString(3, contato.getEndereco());
            pstmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contato> listar() {
        ArrayList<Contato> contatos = new ArrayList<>();
        try(PreparedStatement pstmt = this.connection.prepareStatement("SELECT * FROM contato")) {
            pstmt.execute();
            trasformarResultSetEmContato(contatos, pstmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contatos;
    }

    public Contato buscar(long id) {
        String sql = "SELECT * FROM contato WHERE id=?";
        Contato c = new Contato();
        Calendar data = Calendar.getInstance();

        try(PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);

            try(ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setEmail(rs.getString("email"));
                    c.setEndereco(rs.getString("endereco"));
                    data.setTime(rs.getDate("dataNascimento"));
                    c.setDataNascimento(data);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;

    }
    public void alterar(Contato contato) {
        String sql = "UPDATE contato SET nome=?, email=?, endereco=?, dataNascimento=? WHERE id=?";
        try(PreparedStatement pstmt = this.connection.prepareStatement(sql)) {

            pstmt.setString(1, contato.getNome());
            pstmt.setString(2, contato.getEmail());
            pstmt.setString(3, contato.getEndereco());
            pstmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
            pstmt.setLong(5, contato.getId());

            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void remover(Contato contato) {
        String sql = "DELETE FROM contato WHERE id=?";

        try(PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setLong(1, contato.getId());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void trasformarResultSetEmContato(List<Contato> contatos, PreparedStatement pstmt) {
        try(ResultSet rs = pstmt.getResultSet()) {
            Calendar data = Calendar.getInstance();
            while(rs.next()){
                Contato contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setEndereco(rs.getString("endereco"));
                data.setTime(rs.getDate("dataNascimento"));
                contato.setDataNascimento(data);

                contatos.add(contato);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
