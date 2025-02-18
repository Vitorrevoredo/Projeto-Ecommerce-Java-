package controller;

import model.Carrinho;
import model.Pagamento;
import model.Produto;
import model.Pagamento.FormaDePagamento;
import model.Cliente;

import java.util.Scanner;

public class CarrinhoController {
    private Carrinho carrinho = new Carrinho();
    private Cliente cliente;  // Adicionando o campo cliente

    // Modificado: Construtor agora aceita um Cliente
    public CarrinhoController(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarAoCarrinho(int idProduto) {
        Produto produto = new ProdutoController().buscarPorId(idProduto);
        if (produto != null && produto.getEstoque() > 0) {
            carrinho.adicionarProduto(produto);
            produto.decrementarEstoque();  // Supondo que o Produto tenha esse método
        } else {
            System.out.println("Produto não encontrado ou fora de estoque.");
        }
    }

    public void listarCarrinho() {
        carrinho.listarItens();
    }

    public void finalizarCompra() {
        if (carrinho.getItens().isEmpty()) {
            System.out.println("Carrinho vazio. Não é possível finalizar a compra.");
        } else {
            // Calculando o total da compra
            double total = calcularTotal();
            System.out.println("\nCompra finalizada com sucesso!");
            System.out.println("Total a pagar: R$" + total);

            // Adicionando print para depuração
            System.out.println("Itens no carrinho: ");
            carrinho.listarItens();  // Verifique os itens do carrinho

            // Agora passa o total para o pagamento
            realizarPagamento(total);

            carrinho.limparCarrinho(); // Limpar o carrinho após a compra
        }
    }

    // Método para calcular o total da compra
    public double calcularTotal() {
        double total = carrinho.getItens().stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();

        System.out.println("Total calculado dentro do carrinho: R$" + total);  // Imprime o total calculado
        return total;
    }

    // Método para realizar o pagamento com o total correto
    public void realizarPagamento(double total) {
        // Solicitar escolha da forma de pagamento
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEscolha a forma de pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Cartão de Débito");
        System.out.println("3. PIX");
        System.out.println("4. Pagamento na Entrega");
        System.out.print("Escolha uma opção: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        FormaDePagamento formaPagamento = null;
        switch (escolha) {
            case 1:
                formaPagamento = FormaDePagamento.CARTAO_CREDITO;
                break;
            case 2:
                formaPagamento = FormaDePagamento.CARTAO_DEBITO;
                break;
            case 3:
                formaPagamento = FormaDePagamento.PIX;
                break;
            case 4:
                formaPagamento = FormaDePagamento.PAGAMENTO_ENTREGA;
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        // Criar objeto de pagamento com o total da compra
        Pagamento pagamento = new Pagamento(formaPagamento);
        pagamento.realizarPagamento(total); // Passar o total correto para o pagamento
    }

    // Método para retornar o total da compra (usado para pagamento)
    public double getTotalCompra() {
        return calcularTotal();
    }

    // Método para obter o cliente associado ao carrinho (caso precise acessar o cliente)
    public Cliente getCliente() {
        return this.cliente;
    }
}
