package view;
import controller.ClienteController;
import model.Cliente;
import java.util.Scanner;

public class ClienteView {
    private ClienteController clienteController = new ClienteController();
    private Scanner scanner = new Scanner(System.in);

    public void menuCliente() {
        int opcao;
        do {
            System.out.println("\n--- Gestão de Clientes ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Remover Cliente");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> clienteController.listarClientes();
                case 3 -> atualizarCliente();
                case 4 -> removerCliente();
                case 5 -> System.out.println("Voltando ao Menu Principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Cliente cliente = new Cliente(nome, email, senha);
        clienteController.cadastrarCliente(cliente);
    }

    private void atualizarCliente() {
        System.out.print("ID do Cliente para atualização: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha
        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Email: ");
        String email = scanner.nextLine();
        System.out.print("Nova Senha: ");
        String senha = scanner.nextLine();

        clienteController.atualizarCliente(id, nome, email, senha);
    }

    private void removerCliente() {
        System.out.print("ID do Cliente para remoção: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha
        clienteController.removerCliente(id);
    }
}
