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

        // Variável para controle de login
        Object usuarioLogado = null;

        // Menu de Login e Cadastro Inicial
        while (usuarioLogado == null) {
            System.out.println("\n--- E-commerce Gacessórios ---");
            int opcao = menuService.obterOpcaoMenu(scanner, new String[] {
                    "Fazer Login",
                    "Cadastrar Administrador (Primeiro Acesso)",
                    "Cadastrar Cliente",
                    "Sair"
            });

            switch (opcao) {
                case 1:
                    usuarioLogado = fazerLogin(scanner, autenticacaoService);
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
        }

        // Menu Principal - Só aparece depois de um login bem-sucedido
        if (usuarioLogado instanceof model.Administrador) {
            menuAdministrador(menuService, scanner, produtoView, administradorView, clienteView);
        } else if (usuarioLogado instanceof model.Cliente) {
            menuCliente(menuService, scanner, carrinhoView);
        }
    }
    // Método para autenticar o usuário
    private static Object fazerLogin(Scanner scanner, AutenticacaoService autenticacaoService) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (email.isEmpty() || senha.isEmpty()) {
            System.out.println("Email e senha não podem ser vazios.");
            return null;
        }

        // Tentando autenticar o cliente
        model.Cliente cliente = autenticacaoService.autenticarCliente(email, senha);
        // Tentando autenticar o administrador
        model.Administrador administrador = autenticacaoService.autenticarAdministrador(email, senha);

        // Se o cliente for encontrado
        if (cliente != null) {
            System.out.println("Login como cliente bem-sucedido!");
            return cliente; // Cliente logado
        }
        // Se o administrador for encontrado
        else if (administrador != null) {
            System.out.println("Login como administrador bem-sucedido!");
            return administrador; // Administrador logado
        } else {
            // Se nenhum for encontrado
            System.out.println("Email ou senha incorretos.");
            return null; // Login falhou
        }
    }

    // Menu para Administradores
    private static void menuAdministrador(MenuService menuService, Scanner scanner, ProdutoView produtoView,
                                          AdministradorView administradorView, ClienteView clienteView) {
        while (true) {
            int opcao = menuService.obterOpcaoMenu(scanner, new String[] {
                    "Gestão de Produtos",
                    "Gestão de Administradores",
                    "Gestão de Clientes",
                    "Carrinho de Compras",
                    "Sair"
            });

            switch (opcao) {
                case 1:
                    produtoView.menuProduto();
                    break;
                case 2:
                    administradorView.menuAdministrador();
                    break;
                case 3:
                    clienteView.menuCliente();
                    break;
                case 4:
                    System.out.println("Carrinho não disponível para administradores.");
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;  // Retorna e termina a execução do programa
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Menu para Clientes
    private static void menuCliente(MenuService menuService, Scanner scanner, CarrinhoView carrinhoView) {
        while (true) {
            int opcao = menuService.obterOpcaoMenu(scanner, new String[] {
                    "Visualizar Produtos",
                    "Carrinho de Compras",
                    "Sair"
            });

            switch (opcao) {
                case 1:
                    System.out.println("Produtos disponíveis:");
                    // Você pode chamar um método para listar produtos aqui
                    break;
                case 2:
                    carrinhoView.menuCarrinho();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;  // Retorna e termina a execução do programa
                default:
                    System.out.println("Opção inválida.");
            }
        }
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
            System.out.println("Administrador cadastrado com sucesso!");
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
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
}
