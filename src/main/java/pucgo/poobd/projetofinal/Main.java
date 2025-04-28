package pucgo.poobd.projetofinal;

import javafx.application.Application;
import javafx.scene.control.Alert;
import pucgo.poobd.projetofinal.database.InicializadorBanco;
import pucgo.poobd.projetofinal.view.TelaInicial;

public class Main {
    public static void main(String[] args) {
        if (!InicializadorBanco.inicializar()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Conexão");
            alert.setHeaderText("Não foi possível conectar ao banco de dados");
            alert.setContentText("Verifique se o banco de dados existe e está rodando.");
            alert.showAndWait();
            System.exit(1);
        }

        Application.launch(TelaInicial.class, args);
    }
}
