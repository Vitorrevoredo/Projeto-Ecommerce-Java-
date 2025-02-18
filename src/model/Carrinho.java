package model;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class Carrinho {
    private List<ItemCarrinho> itens = new ArrayList<>();

    // Método getter para a lista de itens
    public List<ItemCarrinho> getItens() {
        return itens;
    }

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

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();
    }

    public void limparCarrinho() {
        itens.clear(); // Limpar o carrinho após o pagamento
    }

    public boolean isCarrinhoVazio() {
        return itens.isEmpty();
    }
}
