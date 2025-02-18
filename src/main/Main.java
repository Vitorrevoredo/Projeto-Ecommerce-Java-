package main;

import view.ProdutoView;
import view.AdministradorView;
import view.ClienteView;
import view.CarrinhoView;
import controller.AdministradorController;
import controller.ClienteController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoView produtoView = new ProdutoView();
        AdministradorView administradorView = new AdministradorView();
        ClienteView clienteView = new ClienteView();
        CarrinhoView carrinhoView = new CarrinhoView();
        AdministradorController administradorController = new AdministradorController();
        ClienteController clienteController = new ClienteController();
        Scanner scanner = new Scanner(System.in);
        boolean autenticado = false;
        boolean isAdmin = false;

        // Menu de Login e Cadastro Inicial
        do {
            System.out.println("\n--- E-commerce Gacessórios ---");
            System.out.println("1. Fazer Login");
            System.out.println("2. Cadastrar Administrador (Primeiro Acesso)");
            System.out.println("3. Cadastrar Cliente");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1 -> {
                    autenticado = fazerLogin(administradorController, clienteController);
                    // Verificando se o login foi de administrador
                    isAdmin = autenticado && administradorController.isAdminLogado();
                }
                case 2 -> cadastrarAdministradorInicial(administradorController);
                case 3 -> cadastrarCliente(clienteController);
                case 4 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!autenticado);

        // Menu Principal
        int opcao;
        do {
            System.out.println("\n--- E-commerce Gacessórios ---");
            System.out.println("1. Gestão de Produtos");
            System.out.println("2. Gestão de Administradores");
            System.out.println("3. Gestão de Clientes");
            System.out.println("4. Carrinho de Compras");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1 -> {
                    if (isAdmin) {
                        produtoView.menuProduto();  // Apenas administradores podem acessar
                    } else {
                        System.out.println("Acesso negado! Somente administradores podem acessar essa opção.");
                    }
                }
                case 2 -> {
                    if (isAdmin) {
                        administradorView.menuAdministrador();  // Só admin pode gerenciar administradores
                    } else {
                        System.out.println("Acesso negado! Somente administradores podem acessar essa opção.");
                    }
                }
                case 3 -> {
                    if (isAdmin) {
                        clienteView.menuCliente();  // Só admin pode gerenciar clientes
                    } else {
                        System.out.println("Acesso negado! Somente administradores podem acessar essa opção.");
                    }
                }
                case 4 -> carrinhoView.menuCarrinho();  // Clientes e admins podem acessar o carrinho
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
        System.out.println("Obrigado por utilizar o E-commerce Gacessórios!");
    }

    // Método para cadastrar o primeiro administrador
    private static void cadastrarAdministradorInicial(AdministradorController administradorController) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome do Administrador: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        int id = administradorController.obterProximoId();
        administradorController.cadastrarAdministrador(new model.Administrador(id, nome, email, senha));
    }

    // Método para cadastrar cliente
    private static void cadastrarCliente(ClienteController clienteController) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        int id = clienteController.obterProximoId();
        clienteController.cadastrarCliente(new model.Cliente(id, nome, email, senha));
    }

    // Método para fazer login como Administrador ou Cliente
    private static boolean fazerLogin(AdministradorController administradorController, ClienteController clienteController) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (administradorController.autenticarAdministrador(email, senha)) {
            System.out.println("Login como Administrador bem-sucedido!");
            return true;
        } else if (clienteController.autenticarCliente(email, senha)) {
            System.out.println("Login como Cliente bem-sucedido!");
            return true;
        } else {
            System.out.println("Credenciais inválidas.");
            return false;
        }
    }
}
