package service;

import controller.AdministradorController;
import controller.ClienteController;
import model.Cliente;
import model.Administrador;

public class AutenticacaoService {
    private final AdministradorController administradorController;
    private final ClienteController clienteController;

    public AutenticacaoService(AdministradorController administradorController, ClienteController clienteController) {
        this.administradorController = administradorController;
        this.clienteController = clienteController;
    }

    public Cliente autenticarCliente(String email, String senha) {
        for (Cliente cliente : clienteController.getClientes()) {
            if (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)) {
                return cliente;
            }
        }
        return null;  // Cliente não encontrado
    }

    public Administrador autenticarAdministrador(String email, String senha) {
        for (Administrador administrador : administradorController.getAdministradores()) {
            if (administrador.getEmail().equals(email) && administrador.getSenha().equals(senha)) {
                return administrador;
            }
        }
        return null;  // Administrador não encontrado
    }
}
