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
                System.out.println("ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Preço: R$" + produto.getPreco() + " | Estoque: " + produto.getEstoque());
            }
        }
    }

    @Override
    public void atualizar(int id, Produto produtoAtualizado) {
        try {
            boolean produtoEncontrado = false;
            for (Produto produto : produtos) {
                if (produto.getId() == id) {
                    produto.setEstoque(produtoAtualizado.getEstoque());
                    produtoEncontrado = true;
                    System.out.println("Produto atualizado com sucesso!");
                    break;
                }
            }
            if (!produtoEncontrado) {
                throw new IllegalArgumentException("Erro: Produto não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remover(int id) {
        try {
            boolean produtoEncontrado = false;
            for (Produto produto : produtos) {
                if (produto.getId() == id) {
                    produtos.remove(produto);
                    produtoEncontrado = true;
                    System.out.println("Produto removido com sucesso!");
                    break;
                }
            }
            if (!produtoEncontrado) {
                throw new IllegalArgumentException("Erro: Produto não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para retornar a lista de produtos
    public List<Produto> getProdutos() {
        return produtos;
    }

    // Método para buscar um produto pelo ID
    @Override
    public Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    // Método para obter o próximo ID
    public int obterProximoId() {
        return produtos.size() + 1;  // ID é baseado no tamanho da lista
    }

    // Método para listar os produtos com detalhes
    public void listarProdutosDetalhado() {
        if (produtos.isEmpty()) {
            System.out.println("Não há produtos cadastrados.");
        } else {
            System.out.println("\n--- Produtos Disponíveis ---");
            for (Produto produto : produtos) {
                System.out.println("ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Preço: R$" + produto.getPreco() + " | Estoque: " + produto.getEstoque());
            }
        }
    }

    // Método para pesquisar produtos por nome, categoria ou descrição
    public void pesquisarProdutos(String termo) {
        boolean encontrouProduto = false;

        for (Produto produto : produtos) {
            if (produto.getNome().toLowerCase().contains(termo.toLowerCase()) ||
                    produto.getCategoria().toLowerCase().contains(termo.toLowerCase()) ||
                    produto.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
                System.out.println("ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Preço: R$" + produto.getPreco() + " | Estoque: " + produto.getEstoque() + " | Descrição: " + produto.getDescricao() + " | Categoria: " + produto.getCategoria());
                encontrouProduto = true;
            }
        }

        if (!encontrouProduto) {
            System.out.println("Nenhum produto encontrado com o termo: " + termo);
        }
    }
}
