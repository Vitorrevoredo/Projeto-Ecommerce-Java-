package service;

import controller.AdministradorController;
import controller.ClienteController;

public class AutenticacaoService {

    private AdministradorController administradorController;
    private ClienteController clienteController;

    public AutenticacaoService(AdministradorController administradorController, ClienteController clienteController) {
        this.administradorController = administradorController;
        this.clienteController = clienteController;
    }

    public boolean autenticarAdministrador(String email, String senha) {
        try {
            // Verifica se o email e a senha não são vazios
            if (email == null || senha == null || email.isEmpty() || senha.isEmpty()) {
                System.out.println("Email e senha não podem ser vazios.");
                return false;
            }

            // Tenta autenticar o administrador
            boolean autenticado = administradorController.autenticarAdministrador(email, senha);
            if (!autenticado) {
                System.out.println("Email ou senha incorretos.");
            }
            return autenticado;
        } catch (Exception e) {
            e.printStackTrace();  // Imprime a stack trace para depuração
            System.out.println("Erro inesperado ao autenticar o administrador.");
            return false;
        }
    }

    public boolean autenticarCliente(String email, String senha) {
        try {
            // Verifica se o email e a senha não são vazios
            if (email == null || senha == null || email.isEmpty() || senha.isEmpty()) {
                System.out.println("Email e senha não podem ser vazios.");
                return false;
            }

            // Tenta autenticar o cliente
            boolean autenticado = clienteController.autenticarCliente(email, senha);
            if (!autenticado) {
                System.out.println("Email ou senha incorretos.");
            }
            return autenticado;
        } catch (Exception e) {
            e.printStackTrace();  // Imprime a stack trace para depuração
            System.out.println("Erro inesperado ao autenticar o cliente.");
            return false;
        }
    }
}
