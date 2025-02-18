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
            System.out.println("3. Pesquisar Produto");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    cadastrarProduto();
                    break;
                case 3:
                    pesquisarProduto();  // Chama o método para pesquisar produtos
                    break;
                case 4:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    // Método para listar produtos
    private void listarProdutos() {
        System.out.println("\n--- Listagem de Produtos ---");
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
        System.out.println("Produto cadastrado com sucesso!");
    }

    // Método para pesquisar produtos
    private void pesquisarProduto() {
        System.out.print("\nDigite o termo de pesquisa (nome, categoria ou descrição): ");
        String termo = scanner.nextLine();

        // Chama o método de pesquisa do ProdutoController
        System.out.println("\n--- Resultados da Pesquisa ---");
        produtoController.pesquisarProdutos(termo);
    }
}
