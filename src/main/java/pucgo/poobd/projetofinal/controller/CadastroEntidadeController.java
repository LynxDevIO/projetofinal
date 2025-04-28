package pucgo.poobd.projetofinal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pucgo.poobd.projetofinal.dao.EntidadeDAO;
import pucgo.poobd.projetofinal.model.Entidade;
import pucgo.poobd.projetofinal.model.TipoEntidade;
import pucgo.poobd.projetofinal.util.Alertas;
import pucgo.poobd.projetofinal.view.TelaInicial;

public class CadastroEntidadeController {
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cnpjField;
    @FXML
    private ComboBox<TipoEntidade> tipoComboBox;

    private EntidadeDAO entidadeDAO = new EntidadeDAO();

    @FXML
    private void initialize() {
        tipoComboBox.getItems().setAll(TipoEntidade.values());
    }

    @FXML
    private void salvarEntidade() {
        String nome = nomeField.getText().trim();
        String cnpj = cnpjField.getText().trim();
        TipoEntidade tipo = tipoComboBox.getValue();

        if (nome.isEmpty()) {
            Alertas.mostrarErro("O nome é obrigatório.");
            return;
        }

        if (!Alertas.validarCNPJ(cnpj)) {
            Alertas.mostrarErro("CNPJ inválido. Deve conter 14 dígitos. Exemplo: 00123456000789");
            return;
        }

        if (tipo == null) {
            Alertas.mostrarErro("O tipo da entidade é obrigatório.");
            return;
        }

        try {
            Entidade entidade = new Entidade();
            entidade.setNome(nome);
            entidade.setCnpj(cnpj);
            entidade.setTipo(tipo);

            entidadeDAO.inserir(entidade);
            Alertas.mostrarSucesso("Entidade cadastrada com sucesso!");
            voltar();
        } catch (Exception e) {
            Alertas.mostrarErro("Erro ao cadastrar entidade: " + e.getMessage());
        }
    }

    @FXML
    private void voltar() {
        TelaInicial.carregarTela("/view/tela-inicial.fxml");
    }
}