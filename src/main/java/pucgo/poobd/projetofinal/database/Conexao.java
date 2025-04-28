package pucgo.poobd.projetofinal.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/doafacil";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";
    private static Conexao instancia;
    private Connection conexao;

    private Conexao() {
        conectar();
    }

    private void conectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public static Conexao getInstancia() {
        if (instancia == null) {
            instancia = new Conexao();
        }
        return instancia;
    }

    public Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conectar();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar conexão: " + e.getMessage());
            conectar();
        }
        return conexao;
    }

    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
} 