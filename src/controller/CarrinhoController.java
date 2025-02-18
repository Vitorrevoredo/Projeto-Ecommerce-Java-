package controller;

import model.Carrinho;
import model.Produto;
import java.util.List;

public class CarrinhoController {
    private Carrinho carrinho = new Carrinho();

    public void adicionarAoCarrinho(int idProduto) {
        Produto produto = new ProdutoController().buscarPorId(idProduto);
        if (produto != null) {
            carrinho.adicionarProduto(produto);
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
