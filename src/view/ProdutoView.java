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
            System.out.println("1. Listar Produtos");
            System.out.println("2. Cadastrar Novo Produto");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    cadastrarProduto();  // Chama o método para cadastrar um produto
                    break;
                case 3:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
    }

    // Método para listar produtos
    private void listarProdutos() {
        produtoController.listarProdutosDetalhado();
    }

    // Método para cadastrar um novo produto
    private void cadastrarProduto() {
        System.out.println("\n--- Cadastrar Novo Produto ---");
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição do Produto: ");
        String descricao = scanner.nextLine();
        System.out.print("Categoria do Produto: ");
        String categoria = scanner.nextLine();
        System.out.print("Preço do Produto (R$): ");
        double preco = scanner.nextDouble();
        System.out.print("Estoque do Produto: ");
        int estoque = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        // Gerar um novo ID baseado no tamanho da lista de produtos (ou outra lógica que você preferir)
        int id = produtoController.obterProximoId();  // Definindo ID sequencial
        Produto novoProduto = new Produto(id, nome, preco, estoque, descricao, categoria);

        // Adiciona o produto ao controlador
        produtoController.cadastrar(novoProduto);
    }
}
