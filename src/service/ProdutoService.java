package service;

import exception.ExcecaoArmazenamento;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) throws ExcecaoArmazenamento {
        if (produto == null) {
            throw new ExcecaoArmazenamento("O produto não pode ser nulo.");
        }
        produtos.add(produto);
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }
}

