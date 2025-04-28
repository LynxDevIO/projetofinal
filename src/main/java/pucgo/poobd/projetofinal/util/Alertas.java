package pucgo.poobd.projetofinal.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alertas {
    public static void mostrarAlerta(String titulo, String cabecalho, String mensagem, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void mostrarErro(String mensagem) {
        mostrarAlerta("Erro", "Ocorreu um erro", mensagem, AlertType.ERROR);
    }

    public static void mostrarSucesso(String mensagem) {
        mostrarAlerta("Sucesso", "Operação realizada com sucesso", mensagem, AlertType.INFORMATION);
    }

    public static boolean validarEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean validarTelefone(String telefone) {
        return telefone != null && telefone.matches("^[0-9]{11}$");
    }

    public static boolean validarCNPJ(String cnpj) {
        return cnpj != null && cnpj.matches("^[0-9]{14}$");
    }

    public static boolean validarQuantidade(String quantidade) {
        try {
            int qtd = Integer.parseInt(quantidade);
            return qtd > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
} 