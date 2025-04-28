package pucgo.poobd.projetofinal.controller;

import javafx.fxml.FXML;
import pucgo.poobd.projetofinal.view.TelaInicial;

public class TelaInicialController {
    @FXML
    private void abrirCadastroDoador() {
        TelaInicial.carregarTela("/view/cadastro-doador.fxml");
    }

    @FXML
    private void abrirCadastroEntidade() {
        TelaInicial.carregarTela("/view/cadastro-entidade.fxml");
    }

    @FXML
    private void abrirCadastroDoacao() {
        TelaInicial.carregarTela("/view/cadastro-doacao.fxml");
    }

    @FXML
    private void abrirListagemDoacoes() {
        TelaInicial.carregarTela("/view/listagem-doacoes.fxml");
    }
} 