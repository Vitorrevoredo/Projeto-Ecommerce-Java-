package main;

import view.CarrinhoView;
import view.ProdutoView;
import view.AdministradorView;
import view.ClienteView;
import controller.AdministradorController;
import controller.ClienteController;
import service.AutenticacaoService;
import service.MenuService;
import service.ProdutosPadroesService;
import controller.ProdutoController;

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
        ProdutoController produtoController = new ProdutoController();  // Instancia o ProdutoController

        // Criando o serviço de produto e adicionando produtos padrão
        ProdutosPadroesService produtosPadroesService = new ProdutosPadroesService();
        produtosPadroesService.adicionarProdutosPadrao(produtoController.getProdutos());

        // Variável para controle de login
        Object usuarioLogado = null;

        // Menu de Login e Cadastro Inicial
        while (true) { // Continuar no menu principal até o usuário escolher sair
            System.out.println("\n--- E-commerce Gacessórios ---");
            int opcao = menuService.obterOpcaoMenu(scanner, new String[] {
                    "Fazer Login",
                    "Cadastrar Administrador (Necessário para acesso ao Login)",
                    "Cadastrar Cliente (Necessário para acesso ao Login)",
                    "Encerrar programa"
            });

            switch (opcao) {
                case 1:
                    usuarioLogado = fazerLogin(scanner, autenticacaoService);
                    if (usuarioLogado == null) {
                        break; // Volta ao menu inicial se o login falhar
                    }
                    // Após o login, entra no menu correspondente ao tipo de usuário
                    if (usuarioLogado instanceof model.Administrador) {
                        menuAdministrador(menuService, scanner, produtoView, administradorView, clienteView, usuarioLogado);
                    } else if (usuarioLogado instanceof model.Cliente) {
                        menuCliente(menuService, scanner, carrinhoView, produtoController, usuarioLogado);
                    }
                    break;
                case 2:
                    cadastrarAdministradorInicial(scanner, administradorController);
                    break;
                case 3:
                    cadastrarCliente(scanner, clienteController);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;  // Encerra o programa
                default:
                    System.out.println("Opção inválida.");
            }
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
                                          AdministradorView administradorView, ClienteView clienteView, Object usuarioLogado) {
        while (true) {
            int opcao = menuService.obterOpcaoMenu(scanner, new String[] {
                    "Gestão de Produtos",
                    "Gestão de Administradores",
                    "Gestão de Clientes",
                    "Carrinho de Compras",
                    "Deslogar"
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
                    System.out.println("Deslogando...");
                    usuarioLogado = null; // Desloga o usuário
                    return;  // Retorna ao menu principal após sair do menu do administrador
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Menu para os clientes
    private static void menuCliente(MenuService menuService, Scanner scanner, CarrinhoView carrinhoView,
                                    ProdutoController produtoController, Object usuarioLogado) {
        while (true) {
            int opcao = menuService.obterOpcaoMenu(scanner, new String[] {
                    "Visualizar Produtos",
                    "Pesquisar Produto",
                    "Carrinho de Compras",
                    "Deslogar"
            });

            switch (opcao) {
                case 1:
                    System.out.println("Produtos disponíveis:");
                    produtoController.listarProdutosDetalhado();
                    break;
                case 2:
                    pesquisarProduto(scanner, produtoController);
                    break;
                case 3:
                    // A chamada para o menuCarrinho deve ser feita para o cliente com o carrinho atual
                    carrinhoView.menuCarrinho(usuarioLogado);  // Enviar o usuário logado para o carrinho
                    break;
                case 4:
                    System.out.println("Deslogando...");
                    usuarioLogado = null;
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    // Método para pesquisar produto
    private static void pesquisarProduto(Scanner scanner, ProdutoController produtoController) {
        System.out.print("\nDigite o termo de pesquisa (nome, categoria ou descrição): ");
        String termo = scanner.nextLine();

        System.out.println("\n--- Resultados da Pesquisa ---");
        produtoController.pesquisarProdutos(termo);
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