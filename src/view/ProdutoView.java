package view;

import controller.ProdutoController;
import model.Produto;

import java.util.List;
import java.util.Scanner;

public class ProdutoView {
    private ProdutoController produtoController = new ProdutoController();
    private Scanner scanner = new Scanner(System.in);

    public void menuProduto() {
        int opcao;
        do {
            System.out.println("\n--- Gestão de Produtos ---");
            System.out.println("1. Listar Produtos");
            System.out.println("2. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 2);
    }

    // Método para listar produtos
    private void listarProdutos() {
        List<Produto> produtos = produtoController.getProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto disponível.");
        } else {
            System.out.println("Produtos disponíveis:");
            for (Produto produto : produtos) {
                System.out.println("ID: " + produto.getId() + " | Nome: " + produto.getNome() + " | Preço: R$" + produto.getPreco());
            }
        }
    }
}
