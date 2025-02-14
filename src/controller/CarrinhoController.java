package controller;
import model.Carrinho;
import model.Produto;
import java.util.List;

public class CarrinhoController {
    private Carrinho carrinho = new Carrinho();

    public void adicionarAoCarrinho(Produto produto) {
        carrinho.adicionarProduto(produto);
    }

    public void listarCarrinho() {
        carrinho.listarItens();
    }

    public void finalizarCompra() {
        carrinho.finalizarCompra();
    }

    public void adicionarProdutosAoCarrinho(List<Produto> produtosDisponiveis, int idProduto) {
        for (Produto produto : produtosDisponiveis) {
            if (produto.getId() == idProduto) {
                adicionarAoCarrinho(produto);
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }
}
