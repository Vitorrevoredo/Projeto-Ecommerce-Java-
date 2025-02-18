package model;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class Carrinho {
    private List<ItemCarrinho> itens = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        for (ItemCarrinho item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                item.setQuantidade(item.getQuantidade() + 1);
                return;
            }
        }
        itens.add(new ItemCarrinho(produto, 1));  // Adiciona novo item com quantidade 1
    }

    public void listarItens() {
        DecimalFormat df = new DecimalFormat("#.00");
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio.");
        } else {
            System.out.println("--- Itens no Carrinho ---");
            for (ItemCarrinho item : itens) {
                System.out.println("Nome: " + item.getProduto().getNome() +
                        " | Preço: R$" + df.format(item.getProduto().getPreco()) +
                        " | Quantidade: " + item.getQuantidade());
            }
        }
    }

    public void finalizarCompra() {
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio. Não é possível finalizar a compra.");
        } else {
            double total = itens.stream()
                    .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                    .sum();
            System.out.println("\nCompra finalizada com sucesso!");
            System.out.println("Total a pagar: R$" + total);
            itens.clear(); // Limpar o carrinho após a compra
        }
    }
}
