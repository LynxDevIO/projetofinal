package pucgo.poobd.projetofinal.dao;

import pucgo.poobd.projetofinal.database.Conexao;
import pucgo.poobd.projetofinal.model.Entidade;
import pucgo.poobd.projetofinal.model.TipoEntidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntidadeDAO {
    private Connection conexao;

    public EntidadeDAO() {
        conexao = Conexao.getInstancia().getConexao();
    }

    public void inserir(Entidade entidade) {
        String sql = "INSERT INTO entidade_assistencial (nome, cnpj, tipo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getCnpj());
            stmt.setString(3, entidade.getTipo().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir entidade: " + e.getMessage());
        }
    }

    public List<Entidade> listarTodos() {
        List<Entidade> entidades = new ArrayList<>();
        String sql = "SELECT * FROM entidade_assistencial";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Entidade entidade = new Entidade();
                entidade.setId(rs.getInt("id"));
                entidade.setNome(rs.getString("nome"));
                entidade.setCnpj(rs.getString("cnpj"));
                entidade.setTipo(TipoEntidade.valueOf(rs.getString("tipo")));
                entidades.add(entidade);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar entidades: " + e.getMessage());
        }
        return entidades;
    }
} 