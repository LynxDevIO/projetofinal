package pucgo.poobd.projetofinal.database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

public class InicializadorBanco {
    private static final String SCHEMA_FILE = "/database/schema.sql";

    public static boolean inicializar() {
        try {
            Connection conexao = Conexao.getInstancia().getConexao();
            if (conexao == null) {
                return false;
            }

            String schema;
            try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(InicializadorBanco.class.getResourceAsStream(SCHEMA_FILE)))) {
                schema = reader.lines().collect(Collectors.joining("\n"));
            }
            
            try (Statement stmt = conexao.createStatement()) {
                stmt.execute(schema);
            }

            return true;
        } catch (Exception e) {
            System.err.println("Erro ao inicializar banco de dados: " + e.getMessage());
            return false;
        }
    }
} 