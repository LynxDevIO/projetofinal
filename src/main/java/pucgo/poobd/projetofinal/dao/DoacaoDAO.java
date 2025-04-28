package pucgo.poobd.projetofinal.dao;

import pucgo.poobd.projetofinal.database.Conexao;
import pucgo.poobd.projetofinal.model.Doacao;
import pucgo.poobd.projetofinal.model.Doador;
import pucgo.poobd.projetofinal.model.Entidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoacaoDAO {
    private Connection conexao;
    private DoadorDAO doadorDAO;
    private EntidadeDAO entidadeDAO;

    public DoacaoDAO() {
        conexao = Conexao.getInstancia().getConexao();
        doadorDAO = new DoadorDAO();
        entidadeDAO = new EntidadeDAO();
    }

    public void inserir(Doacao doacao) {
        String sql = "INSERT INTO doacao (doador_id, entidade_id, item, quantidade, data) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, doacao.getDoador().getId());
            stmt.setInt(2, doacao.getEntidade().getId());
            stmt.setString(3, doacao.getItem());
            stmt.setInt(4, doacao.getQuantidade());
            stmt.setDate(5, java.sql.Date.valueOf(doacao.getData()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir doação: " + e.getMessage());
        }
    }

    public List<Doacao> listarTodos() {
        List<Doacao> doacoes = new ArrayList<>();
        String sql = "SELECT * FROM doacao";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Doacao doacao = new Doacao();
                doacao.setId(rs.getInt("id"));
                
                int doadorId = rs.getInt("doador_id");
                Doador doador = doadorDAO.listarTodos().stream()
                    .filter(d -> d.getId() == doadorId)
                    .findFirst()
                    .orElse(null);
                doacao.setDoador(doador);
                
                int entidadeId = rs.getInt("entidade_id");
                Entidade entidade = entidadeDAO.listarTodos().stream()
                    .filter(e -> e.getId() == entidadeId)
                    .findFirst()
                    .orElse(null);
                doacao.setEntidade(entidade);
                
                doacao.setItem(rs.getString("item"));
                doacao.setQuantidade(rs.getInt("quantidade"));
                doacao.setData(rs.getDate("data").toLocalDate());
                
                doacoes.add(doacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar doações: " + e.getMessage());
        }
        return doacoes;
    }
} 