package pucgo.poobd.projetofinal.model;

public class Entidade {
    private int id;
    private String nome;
    private String cnpj;
    private TipoEntidade tipo;

    public Entidade() {
    }

    public Entidade(String nome, String cnpj, TipoEntidade tipo) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public TipoEntidade getTipo() {
        return tipo;
    }

    public void setTipo(TipoEntidade tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nome;
    }
} 