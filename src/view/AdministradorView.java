package view;
import controller.AdministradorController;
import model.Administrador;

import java.util.Scanner;

public class AdministradorView {
    private AdministradorController administradorController = new AdministradorController();
    private Scanner scanner = new Scanner(System.in);

    public void menuAdministrador() {
        int opcao;
        do {
            System.out.println("\n--- Gestão de Administradores ---");
            System.out.println("1. Cadastrar Administrador");
            System.out.println("2. Listar Administradores");
            System.out.println("3. Atualizar Administrador");
            System.out.println("4. Remover Administrador");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1 -> cadastrarAdministrador();
                case 2 -> administradorController.listarAdministradores();
                case 3 -> atualizarAdministrador();
                case 4 -> removerAdministrador();
                case 5 -> System.out.println("Voltando ao Menu Principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private void cadastrarAdministrador() {
        int id = administradorController.obterProximoId();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Administrador administrador = new Administrador(id, nome, email, senha);
        administradorController.cadastrarAdministrador(administrador);
    }

    private void atualizarAdministrador() {
        System.out.print("ID do Administrador para atualização: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha
        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Email: ");
        String email = scanner.nextLine();
        System.out.print("Nova Senha: ");
        String senha = scanner.nextLine();

        administradorController.atualizarAdministrador(id, nome, email, senha);
    }

    private void removerAdministrador() {
        System.out.print("ID do Administrador para remoção: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha
        administradorController.removerAdministrador(id);
    }
}
