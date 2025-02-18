package model;

public abstract class Entidade {
    private int id;

    // Construtor que exige um argumento 'int'
    public Entidade(int id) {
        this.id = id;
    }

    // Getter e Setter para o id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
