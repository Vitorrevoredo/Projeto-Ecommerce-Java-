package main;

import view.ProdutoView;
import view.AdministradorView;
import view.ClienteView;
import view.CarrinhoView;
import controller.AdministradorController;
import controller.ClienteController;
import service.AutenticacaoService;
import service.MenuService;
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

        // Criando serviços para centralizar a lógica de autenticação e menu
        AutenticacaoService autenticacaoService = new AutenticacaoService(administradorController, clienteController);
        MenuService menuService = new MenuService();

        boolean autenticado = false;
        boolean isAdmin = false;

        // Menu de Login e Cadastro Inicial
        do {
            System.out.println("\n--- E-commerce Gacessórios ---");
            int opcao = menuService.obterOpcaoMenu(scanner, new String[]{
                    "Fazer Login",
                    "Cadastrar Administrador (Primeiro Acesso)",
                    "Cadastrar Cliente",
                    "Sair"
            });

            System.out.println("Opção selecionada: " + opcao);  // Debugging

            switch (opcao) {
                case 1:
                    autenticado = fazerLogin(scanner, autenticacaoService);
                    isAdmin = autenticado && administradorController.isAdminLogado();
                    break;
                case 2:
                    cadastrarAdministradorInicial(scanner, administradorController);
                    break;
                case 3:
                    cadastrarCliente(scanner, clienteController);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;  // Retorna e termina a execução do programa
                default:
                    System.out.println("Opção inválida.");
            }
        } while (!autenticado);  // Continua até que o login seja bem-sucedido

        // Menu Principal
        while (true) {
            int opcao = menuService.obterOpcaoMenu(scanner, new String[]{
                    "Gestão de Produtos",
                    "Gestão de Administradores",
                    "Gestão de Clientes",
                    "Carrinho de Compras",
                    "Sair"
            });

            System.out.println("Opção selecionada: " + opcao);  // Debugging

            switch (opcao) {
                case 1:
                    if (isAdmin) {
                        produtoView.menuProduto();
                    } else {
                        System.out.println("Acesso negado! Somente administradores.");
                    }
                    break;
                case 2:
                    if (isAdmin) {
                        administradorView.menuAdministrador();
                    } else {
                        System.out.println("Acesso negado! Somente administradores.");
                    }
                    break;
                case 3:
                    if (isAdmin) {
                        clienteView.menuCliente();
                    } else {
                        System.out.println("Acesso negado! Somente administradores.");
                    }
                    break;
                case 4:
                    carrinhoView.menuCarrinho();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;  // Retorna e termina a execução do programa
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Método para autenticar o usuário
    private static boolean fazerLogin(Scanner scanner, AutenticacaoService autenticacaoService) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        boolean autenticado = false;
        try {
            autenticado = autenticacaoService.autenticarAdministrador(email, senha) || autenticacaoService.autenticarCliente(email, senha);
        } catch (Exception e) {
            System.out.println("Erro ao tentar autenticar: " + e.getMessage());
        }

        return autenticado;
    }

    // Método para cadastrar um administrador inicial
    private static void cadastrarAdministradorInicial(Scanner scanner, AdministradorController administradorController) {
        try {
            System.out.print("Nome do Administrador: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            int id = administradorController.obterProximoId();
            administradorController.cadastrarAdministrador(new model.Administrador(id, nome, email, senha));
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar administrador: " + e.getMessage());
        }
    }

    // Método para cadastrar um cliente
    private static void cadastrarCliente(Scanner scanner, ClienteController clienteController) {
        try {
            System.out.print("Nome do Cliente: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            int id = clienteController.obterProximoId();
            clienteController.cadastrarCliente(new model.Cliente(id, nome, email, senha));
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
}
