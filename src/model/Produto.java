package model;

public class Produto extends Entidade {
    private String nome;
    private double preco;
    private int estoque;
    private String descricao;
    private String categoria;

    // Construtor
    public Produto(int id, String nome, double preco, int estoque, String descricao, String categoria) {
        super(id);  // Chama o construtor da classe pai
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Método para diminuir o estoque
    public boolean decrementarEstoque() {
        if (estoque > 0) {
            estoque--;
            return true;  // Estoque diminuído com sucesso
        }
        return false;  // Estoque insuficiente
    }
}
