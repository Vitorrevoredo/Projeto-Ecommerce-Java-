package service;

import controller.AdministradorController;
import controller.ClienteController;
import model.Administrador;
import model.Cliente;

public class AutenticacaoService {

    private AdministradorController administradorController;
    private ClienteController clienteController;

    public AutenticacaoService(AdministradorController administradorController, ClienteController clienteController) {
        this.administradorController = administradorController;
        this.clienteController = clienteController;
    }

    public Administrador autenticarAdministrador(String email, String senha) throws IllegalArgumentException {
        try {
            if (email == null || senha == null || email.isEmpty() || senha.isEmpty()) {
                throw new IllegalArgumentException("Email e senha não podem ser vazios.");
            }
            return administradorController.autenticarAdministrador(email, senha);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
            return null;  // Retorna null em caso de erro
        } catch (Exception e) {
            System.out.println("Erro inesperado ao autenticar o administrador: " + e.getMessage());
            return null;  // Retorna null em caso de erro inesperado
        }
    }

    public Cliente autenticarCliente(String email, String senha) throws IllegalArgumentException {
        try {
            if (email == null || senha == null || email.isEmpty() || senha.isEmpty()) {
                throw new IllegalArgumentException("Email e senha não podem ser vazios.");
            }
            return clienteController.autenticarCliente(email, senha);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
            return null;  // Retorna null em caso de erro
        } catch (Exception e) {
            System.out.println("Erro inesperado ao autenticar o cliente: " + e.getMessage());
            return null;  // Retorna null em caso de erro inesperado
        }
    }
}
