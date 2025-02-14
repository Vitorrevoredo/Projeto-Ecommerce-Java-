package model;

// Interface para padronizar operações CRUD
public interface ICrud<T> {
    void cadastrar(T obj);
    void listar();
    void atualizar(int id, T obj);
    void remover(int id);
}
