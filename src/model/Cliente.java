package model;

public class Cliente extends Entidade {
    private String nome;
    private String email;
    private String senha;

    // Construtor da classe Cliente
    public Cliente(int id, String nome, String email, String senha) {
        super(id);  // Chama o construtor da classe Entidade com o parâmetro id
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
