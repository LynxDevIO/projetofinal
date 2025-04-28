package pucgo.poobd.projetofinal.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaInicial extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        carregarTela("/view/tela-inicial.fxml");
        primaryStage.setTitle("DoaFácil - Sistema de Doações Comunitárias");
        centralizarJanela();
        primaryStage.show();
    }

    public static void carregarTela(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TelaInicial.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            centralizarJanela();
        } catch (Exception e) {
            System.err.println("Erro ao carregar tela: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void centralizarJanela() {
        primaryStage.centerOnScreen();
    }
} 