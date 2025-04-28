package pucgo.poobd.projetofinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pucgo.poobd.projetofinal.dao.DoacaoDAO;
import pucgo.poobd.projetofinal.model.Doacao;
import pucgo.poobd.projetofinal.view.TelaInicial;
import java.util.Arrays;

public class ListagemDoacoesController {
    @FXML
    private TableView<Doacao> doacoesTableView;
    @FXML
    private TableColumn<Doacao, String> doadorColumn;
    @FXML
    private TableColumn<Doacao, String> entidadeColumn;
    @FXML
    private TableColumn<Doacao, String> itemColumn;
    @FXML
    private TableColumn<Doacao, Integer> quantidadeColumn;
    @FXML
    private TableColumn<Doacao, String> dataColumn;
    @FXML
    private ComboBox<String> filtroComboBox;
    @FXML
    private TextField filtroTextField;

    private DoacaoDAO doacaoDAO = new DoacaoDAO();
    private ObservableList<Doacao> doacoes;

    @FXML
    private void initialize() {
        filtroComboBox.setItems(FXCollections.observableArrayList("Doador", "Entidade"));
        filtroComboBox.getSelectionModel().selectFirst();
        
        doadorColumn.setCellValueFactory(cellData -> {
            Doacao doacao = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(doacao.getDoador().getNomeCompleto());
        });

        entidadeColumn.setCellValueFactory(cellData -> {
            Doacao doacao = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(doacao.getEntidade().getNome());
        });

        itemColumn.setCellValueFactory(cellData -> {
            Doacao doacao = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(doacao.getItem());
        });

        quantidadeColumn.setCellValueFactory(cellData -> {
            Doacao doacao = cellData.getValue();
            return new javafx.beans.property.SimpleIntegerProperty(doacao.getQuantidade()).asObject();
        });

        dataColumn.setCellValueFactory(cellData -> {
            Doacao doacao = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(doacao.getData().toString());
        });

        doacoesTableView.getColumns().setAll(Arrays.asList(doadorColumn, entidadeColumn, itemColumn, quantidadeColumn, dataColumn));

        atualizarTabela();
    }

    @FXML
    private void filtrarDoacoes() {
        String filtro = filtroTextField.getText().toLowerCase();
        String tipoFiltro = filtroComboBox.getValue();

        if (filtro.isEmpty()) {
            doacoesTableView.setItems(doacoes);
            return;
        }

        ObservableList<Doacao> doacoesFiltradas = FXCollections.observableArrayList();
        for (Doacao doacao : doacoes) {
            if (tipoFiltro.equals("Doador") && 
                doacao.getDoador().getNomeCompleto().toLowerCase().contains(filtro)) {
                doacoesFiltradas.add(doacao);
            } else if (tipoFiltro.equals("Entidade") && 
                      doacao.getEntidade().getNome().toLowerCase().contains(filtro)) {
                doacoesFiltradas.add(doacao);
            }
        }
        doacoesTableView.setItems(doacoesFiltradas);
    }

    @FXML
    private void resetarFiltro() {
        filtroTextField.clear();
        doacoesTableView.setItems(doacoes);
    }

    private void atualizarTabela() {
        doacoes = FXCollections.observableArrayList(doacaoDAO.listarTodos());
        doacoesTableView.setItems(doacoes);
    }

    @FXML
    private void voltar() {
        TelaInicial.carregarTela("/view/tela-inicial.fxml");
    }
}