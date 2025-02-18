package model;

public class Produto extends Entidade {
    private String nome;
    private double preco;
    private int estoque;

    // Construtor
    public Produto(int id, String nome, double preco, int estoque) {
        super(id);
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
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

    // Setter para estoque
    public void setEstoque(int estoque) {
        this.estoque = estoque;
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
