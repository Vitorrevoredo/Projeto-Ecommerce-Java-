package view;
import controller.ProdutoController;
import model.Produto;
import java.util.InputMismatchException;
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
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha
                switch (opcao) {
                    case 1 -> cadastrarProduto();
                    case 2 -> produtoController.listar();
                    case 3 -> atualizarProduto();
                    case 4 -> removerProduto();
                    case 5 -> System.out.println("Voltando ao Menu Principal...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número.");
                scanner.nextLine(); // Limpar buffer
                opcao = 0; // Reiniciar loop
            }
        } while (opcao != 5);
    }

    private void cadastrarProduto() {
        try {
            System.out.print("Nome do Produto: ");
            String nome = scanner.nextLine();
            System.out.print("Categoria: ");
            String categoria = scanner.nextLine();
            System.out.print("Preço: ");
            double preco = scanner.nextDouble();
            System.out.print("Estoque: ");
            int estoque = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            Produto produto = new Produto(nome, categoria, preco, estoque);
            produtoController.cadastrar(produto);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, tente novamente.");
            scanner.nextLine(); // Limpar buffer
        }
    }

    private void atualizarProduto() {
        try {
            System.out.print("ID do Produto para atualização: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Nova Categoria: ");
            String categoria = scanner.nextLine();
            System.out.print("Novo Preço: ");
            double preco = scanner.nextDouble();
            System.out.print("Novo Estoque: ");
            int estoque = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            Produto produtoAtualizado = new Produto(nome, categoria, preco, estoque);
            produtoController.atualizar(id, produtoAtualizado);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, tente novamente.");
            scanner.nextLine(); // Limpar buffer
        }
    }

    private void removerProduto() {
        try {
            System.out.print("ID do Produto para remoção: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            produtoController.remover(id);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, tente novamente.");
            scanner.nextLine(); // Limpar buffer
        }
    }
}
