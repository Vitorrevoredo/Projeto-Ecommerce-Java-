package controller;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();
    private Cliente clienteLogado;  // Armazena o cliente autenticado

    // CRUD: Create
    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
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

    // CRUD: Delete
    public void removerCliente(int id) {
        clientes.removeIf(cliente -> cliente.getId() == id);
        System.out.println("Cliente removido com sucesso!");
    }

    // Método de autenticação do cliente
    public Cliente autenticarCliente(String email, String senha) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)) {
                clienteLogado = cliente;  // Define o cliente logado
                return cliente;  // Retorna o cliente autenticado
            }
        }
        return null;  // Retorna null caso o cliente não seja encontrado
    }

    // Verifica se um cliente está logado
    public boolean isClienteLogado() {
        return clienteLogado != null;
    }

    // Método para deslogar o cliente
    public void deslogarCliente() {
        clienteLogado = null;
        System.out.println("Cliente deslogado com sucesso.");
    }

    // Gera o próximo ID para cadastro
    public int obterProximoId() {
        return clientes.size() + 1;
    }

    // Obtém a lista de clientes
    public List<Cliente> getClientes() {
        return clientes;
    }

    // Obtém o cliente logado
    public Cliente getClienteLogado() {
        return clienteLogado;
    }
}
