package view;

import controller.CarrinhoController;
import controller.ProdutoController;
import model.Produto;

import java.util.Scanner;

public class CarrinhoView {
    private CarrinhoController carrinhoController = new CarrinhoController();
    private ProdutoController produtoController = new ProdutoController();
    private Scanner scanner = new Scanner(System.in);

    public void menuCarrinho() {
        int opcao;
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
                case 1 -> adicionarProdutoAoCarrinho();
                case 2 -> carrinhoController.listarCarrinho();
                case 3 -> carrinhoController.finalizarCompra();
                case 4 -> System.out.println("Voltando ao Menu Principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    private void adicionarProdutoAoCarrinho() {
        System.out.println("\n--- Produtos Disponíveis ---");
        produtoController.listar();
        System.out.print("Digite o ID do produto para adicionar ao carrinho: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        carrinhoController.adicionarAoCarrinho(idProduto);
    }
}
