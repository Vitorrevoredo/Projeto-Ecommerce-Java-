package controller;

import model.Produto;
import model.ICrud;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController implements ICrud<Produto> {
    private static List<Produto> produtos = new ArrayList<>();  // Lista global de produtos

    @Override
    public void cadastrar(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    @Override
    public void listar() {
        if (produtos.isEmpty()) {
            System.out.println("Não há produtos cadastrados.");
        } else {
            for (Produto produto : produtos) {
                System.out.println("ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Preço: R$" + produto.getPreco());
            }
        }
    }

    @Override
    public void atualizar(int id, Produto produtoAtualizado) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produto.setEstoque(produtoAtualizado.getEstoque());
                System.out.println("Produto atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    @Override
    public void remover(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
        System.out.println("Produto removido com sucesso!");
    }

    @Override
    public Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }
    public int obterProximoId() {
        return produtos.size() + 1;  // ID é baseado no tamanho da lista
    }
}
