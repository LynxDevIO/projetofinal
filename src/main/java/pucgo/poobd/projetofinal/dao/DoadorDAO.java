package pucgo.poobd.projetofinal.dao;

import pucgo.poobd.projetofinal.database.Conexao;
import pucgo.poobd.projetofinal.model.Doador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoadorDAO {
    private Connection conexao;

    public DoadorDAO() {
        conexao = Conexao.getInstancia().getConexao();
    }

    public void inserir(Doador doador) {
        String sql = "INSERT INTO doador (nome_completo, telefone, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, doador.getNomeCompleto());
            stmt.setString(2, doador.getTelefone());
            stmt.setString(3, doador.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir doador: " + e.getMessage());
        }
    }

    public List<Doador> listarTodos() {
        List<Doador> doadores = new ArrayList<>();
        String sql = "SELECT * FROM doador";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Doador doador = new Doador();
                doador.setId(rs.getInt("id"));
                doador.setNomeCompleto(rs.getString("nome_completo"));
                doador.setTelefone(rs.getString("telefone"));
                doador.setEmail(rs.getString("email"));
                doadores.add(doador);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar doadores: " + e.getMessage());
        }
        return doadores;
    }
} 