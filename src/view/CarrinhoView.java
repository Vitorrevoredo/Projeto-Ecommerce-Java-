package view;

import controller.CarrinhoController;
import controller.ProdutoController;
import model.Cliente;
import model.Pagamento;
import model.Pagamento.FormaDePagamento;

import java.util.Scanner;

public class CarrinhoView {
    private ProdutoController produtoController = new ProdutoController();
    private Scanner scanner = new Scanner(System.in);

    public void menuCarrinho(Object usuarioLogado) {
        if (!(usuarioLogado instanceof Cliente)) {
            System.out.println("Acesso não autorizado. Apenas clientes podem acessar o carrinho.");
            return; // Retorna caso o usuário não seja cliente
        }

        Cliente cliente = (Cliente) usuarioLogado;
        CarrinhoController carrinhoController = new CarrinhoController(cliente);

        int opcao;
        boolean compraFinalizada = false;  // Controle para verificar se a compra foi finalizada

        do {
            System.out.println("\n--- Carrinho de Compras ---");
            System.out.println("1. Adicionar Produto ao Carrinho");
            System.out.println("2. Listar Itens do Carrinho");
            System.out.println("3. Finalizar Compra");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    adicionarProdutoAoCarrinho(carrinhoController);
                    break;
                case 2:
                    carrinhoController.listarCarrinho();  // Passa o cliente para listar o carrinho
                    break;
                case 3:
                    if (!compraFinalizada) {
                        finalizarCompra(carrinhoController, cliente); // Chama o método não estático
                        System.out.println("Compra realizada com sucesso! Voltando ao menu principal...");
                        compraFinalizada = true;  // Marca que a compra foi finalizada
                    } else {
                        System.out.println("A compra já foi finalizada. Retornando ao menu principal...");
                    }
                    return;  // Após finalizar a compra ou verificar que já foi finalizada, sai do menu
                case 4:
                    System.out.println("Voltando ao Menu Principal...");
                    return;  // Sai do menu e retorna ao menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!compraFinalizada);  // O loop só será interrompido após finalizar a compra
    }

    private void adicionarProdutoAoCarrinho(CarrinhoController carrinhoController) {
        System.out.println("\n--- Produtos Disponíveis ---");
        produtoController.listar();
        System.out.print("Digite o ID do produto para adicionar ao carrinho: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        if (produtoController.buscarPorId(idProduto) != null) {
            carrinhoController.adicionarAoCarrinho(idProduto);
        } else {
            System.out.println("ID do produto inválido. Tente novamente.");
        }
    }

    // Método para finalização de compra no menu do carrinho
    private void finalizarCompra(CarrinhoController carrinhoController, Cliente cliente) {
        // Exibe o total da compra no carrinho
        double total = carrinhoController.calcularTotal();
        System.out.println("Total calculado dentro do carrinho: R$" + total);

        // Exibe os itens do carrinho
        carrinhoController.listarCarrinho();

        // Exibe a opção de pagamento
        System.out.println("\nEscolha a forma de pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Cartão de Débito");
        System.out.println("3. PIX");
        System.out.println("4. Pagamento na Entrega");

        int opcaoPagamento = scanner.nextInt();  // Leitura da opção de pagamento

        // Processamento do pagamento
        switch (opcaoPagamento) {
            case 1:
                System.out.println("Pagamento de R$" + total + " realizado com Cartão de Crédito.");
                break;
            case 2:
                System.out.println("Pagamento de R$" + total + " realizado com Cartão de Débito.");
                break;
            case 3:
                System.out.println("Pagamento de R$" + total + " realizado via PIX.");
                break;
            case 4:
                System.out.println("Pagamento de R$" + total + " será feito na entrega.");
                break;
            default:
                System.out.println("Opção inválida de pagamento.");
                return;  // Retorna após o erro
        }

        // Finaliza a compra
        System.out.println("Compra finalizada com sucesso!");
        System.out.println("Total a pagar: R$" + total);
        System.out.println("Itens no carrinho:");
        carrinhoController.listarCarrinho();

        // Agora, retornamos ao menu principal ou ao carrinho de compras.
    }
}
