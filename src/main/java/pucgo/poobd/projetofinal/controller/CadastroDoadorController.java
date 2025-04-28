package pucgo.poobd.projetofinal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pucgo.poobd.projetofinal.dao.DoadorDAO;
import pucgo.poobd.projetofinal.model.Doador;
import pucgo.poobd.projetofinal.util.Alertas;
import pucgo.poobd.projetofinal.view.TelaInicial;

public class CadastroDoadorController {
    @FXML
    private TextField nomeCompletoField;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField emailField;

    private DoadorDAO doadorDAO = new DoadorDAO();

    @FXML
    private void salvarDoador() {
        String nome = nomeCompletoField.getText().trim();
        String telefone = telefoneField.getText().trim();
        String email = emailField.getText().trim();

        if (nome.isEmpty()) {
            Alertas.mostrarErro("O nome completo é obrigatório.");
            return;
        }

        if (!Alertas.validarTelefone(telefone)) {
            Alertas.mostrarErro("Telefone inválido. Deve conter 11 dígitos (exemplo: 62912345678).");
            return;
        }

        if (!Alertas.validarEmail(email)) {
            Alertas.mostrarErro("E-mail inválido.");
            return;
        }

        try {
            Doador doador = new Doador();
            doador.setNomeCompleto(nome);
            doador.setTelefone(telefone);
            doador.setEmail(email);

            doadorDAO.inserir(doador);
            Alertas.mostrarSucesso("Doador cadastrado com sucesso!");
            voltar();
        } catch (Exception e) {
            Alertas.mostrarErro("Erro ao cadastrar doador: " + e.getMessage());
        }
    }

    @FXML
    private void voltar() {
        TelaInicial.carregarTela("/view/tela-inicial.fxml");
    }
} 