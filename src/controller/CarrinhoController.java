package controller;

import model.Carrinho;
import model.Produto;

public class CarrinhoController {
    private Carrinho carrinho = new Carrinho();

    public void adicionarAoCarrinho(int idProduto) {
        Produto produto = new ProdutoController().buscarPorId(idProduto);
        if (produto != null) {
            if (produto.getEstoque() > 0) {
                carrinho.adicionarProduto(produto);
                produto.decrementarEstoque(); // Supondo que o Produto tenha esse método
            } else {
                System.out.println("Produto fora de estoque.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void listarCarrinho() {
        carrinho.listarItens();
    }

    public void finalizarCompra() {
        carrinho.finalizarCompra();
    }
}
