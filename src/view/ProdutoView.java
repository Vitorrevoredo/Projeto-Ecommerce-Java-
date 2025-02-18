package view;

import controller.ProdutoController;
import model.Produto;

import java.util.Scanner;

public class ProdutoView {
    private ProdutoController produtoController = new ProdutoController();
    private Scanner scanner = new Scanner(System.in);

    public void menuProduto() {
        int opcao;
        do {
            System.out.println("\n--- Gestão de Produtos ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> produtoController.listar();
                case 3 -> System.out.println("Voltando ao Menu Principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
    }

    private void cadastrarProduto() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Preço: ");
            double preco = scanner.nextDouble();

            System.out.print("Estoque: ");
            int estoque = scanner.nextInt();

            scanner.nextLine(); // Consumir a quebra de linha
            int id = produtoController.getProdutos().size() + 1;  // Usando o método getProdutos()

            Produto produto = new Produto(id, nome, preco, estoque);
            produtoController.cadastrar(produto);
        } catch (java.util.InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Por favor, insira os dados corretamente.");
            scanner.nextLine(); // Limpar o buffer
        }
    }

}
