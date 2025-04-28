package pucgo.poobd.projetofinal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pucgo.poobd.projetofinal.dao.DoacaoDAO;
import pucgo.poobd.projetofinal.dao.DoadorDAO;
import pucgo.poobd.projetofinal.dao.EntidadeDAO;
import pucgo.poobd.projetofinal.model.Doacao;
import pucgo.poobd.projetofinal.model.Doador;
import pucgo.poobd.projetofinal.model.Entidade;
import pucgo.poobd.projetofinal.util.Alertas;
import pucgo.poobd.projetofinal.view.TelaInicial;

import java.time.LocalDate;

public class CadastroDoacaoController {
    @FXML
    private ComboBox<Doador> doadorComboBox;
    @FXML
    private ComboBox<Entidade> entidadeComboBox;
    @FXML
    private TextField itemField;
    @FXML
    private TextField quantidadeField;
    @FXML
    private DatePicker dataPicker;

    private DoacaoDAO doacaoDAO = new DoacaoDAO();
    private DoadorDAO doadorDAO = new DoadorDAO();
    private EntidadeDAO entidadeDAO = new EntidadeDAO();

    @FXML
    private void initialize() {
        doadorComboBox.getItems().setAll(doadorDAO.listarTodos());
        entidadeComboBox.getItems().setAll(entidadeDAO.listarTodos());
        dataPicker.setValue(LocalDate.now());
    }

    @FXML
    private void salvarDoacao() {
        Doador doador = doadorComboBox.getValue();
        Entidade entidade = entidadeComboBox.getValue();
        String item = itemField.getText().trim();
        String quantidadeStr = quantidadeField.getText().trim();
        LocalDate data = dataPicker.getValue();

        if (doador == null) {
            Alertas.mostrarErro("O doador é obrigatório.");
            return;
        }

        if (entidade == null) {
            Alertas.mostrarErro("A entidade é obrigatória.");
            return;
        }

        if (item.isEmpty()) {
            Alertas.mostrarErro("O item é obrigatório.");
            return;
        }

        if (!Alertas.validarQuantidade(quantidadeStr)) {
            Alertas.mostrarErro("Quantidade inválida. Deve ser um número maior que zero.");
            return;
        }

        if (data == null) {
            Alertas.mostrarErro("A data é obrigatória.");
            return;
        }

        try {
            Doacao doacao = new Doacao();
            doacao.setDoador(doador);
            doacao.setEntidade(entidade);
            doacao.setItem(item);
            doacao.setQuantidade(Integer.parseInt(quantidadeStr));
            doacao.setData(data);

            doacaoDAO.inserir(doacao);
            Alertas.mostrarSucesso("Doação registrada com sucesso!");
            voltar();
        } catch (Exception e) {
            Alertas.mostrarErro("Erro ao registrar doação: " + e.getMessage());
        }
    }

    @FXML
    private void voltar() {
        TelaInicial.carregarTela("/view/tela-inicial.fxml");
    }
} 