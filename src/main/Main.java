package main;
import view.ProdutoView;
import view.ClienteView;
import view.CarrinhoView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoView produtoView = new ProdutoView();
        ClienteView clienteView = new ClienteView();
        CarrinhoView carrinhoView = new CarrinhoView();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- E-commerce Gacessórios ---");
            System.out.println("1. Gestão de Produtos");
            System.out.println("2. Gestão de Clientes");
            System.out.println("3. Carrinho de Compras");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1 -> produtoView.menuProduto();
                case 2 -> clienteView.menuCliente();
                case 3 -> carrinhoView.menuCarrinho();
                case 4 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
        System.out.println("Obrigado por utilizar o E-commerce Gacessórios!");
    }
}
