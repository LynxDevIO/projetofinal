package pucgo.poobd.projetofinal.model;

import java.time.LocalDate;

public class Doacao {
    private int id;
    private Doador doador;
    private Entidade entidade;
    private String item;
    private int quantidade;
    private LocalDate data;

    public Doacao() {
    }

    public Doacao(Doador doador, Entidade entidade, String item, int quantidade, LocalDate data) {
        this.doador = doador;
        this.entidade = entidade;
        this.item = item;
        this.quantidade = quantidade;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return doador.getNomeCompleto();
    }
} 