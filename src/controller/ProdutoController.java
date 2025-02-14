package controller;
import model.Produto;
import model.ICrud;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController implements ICrud<Produto> {
    private List<Produto> produtos = new ArrayList<>();

    // CRUD: Create
    @Override
    public void cadastrar(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    // CRUD: Read
    @Override
    public void listar() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println("ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Categoria: " + produto.getCategoria() + " | Preço: R$" + produto.getPreco() + " | Estoque: " + produto.getEstoque());
            }
        }
    }

    // CRUD: Update
    @Override
    public void atualizar(int id, Produto produtoAtualizado) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produto.setNome(produtoAtualizado.getNome());
                produto.setCategoria(produtoAtualizado.getCategoria());
                produto.setPreco(produtoAtualizado.getPreco());
                produto.setEstoque(produtoAtualizado.getEstoque());
                System.out.println("Produto atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    // CRUD: Delete
    @Override
    public void remover(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
        System.out.println("Produto removido com sucesso!");
    }

    // Método para obter a lista de produtos
    public List<Produto> getProdutos() {
        return produtos;
    }
}
