package controller;
import model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();

    // CRUD: Create
    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    // CRUD: Read
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Email: " + cliente.getEmail());
            }
        }
    }

    // CRUD: Update
    public void atualizarCliente(int id, String nome, String email, String senha) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setSenha(senha);
                System.out.println("Cliente atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }

    // CRUD: Delete
    public void removerCliente(int id) {
        clientes.removeIf(cliente -> cliente.getId() == id);
        System.out.println("Cliente removido com sucesso!");
    }
}
