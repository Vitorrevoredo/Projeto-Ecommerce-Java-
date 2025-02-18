package model;

public interface ICrud<T> {
    void cadastrar(T t);
    void listar();
    void atualizar(int id, T t);
    void remover(int id);
    T buscarPorId(int id);
}
