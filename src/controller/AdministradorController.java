package controller;

import model.Administrador;
import java.util.ArrayList;
import java.util.List;

public class AdministradorController {
    private List<Administrador> administradors = new ArrayList<>(); // Nome padronizado
    private Administrador adminLogado = null;  // Armazena o administrador autenticado

    // CRUD: Create
    public void cadastrarAdministrador(Administrador administrador) {
        administradors.add(administrador);
        System.out.println("Administrador cadastrado com sucesso!");
    }

    // CRUD: Read
    public void listarAdministradores() {
        if (administradors.isEmpty()) {
            System.out.println("Nenhum administrador cadastrado.");
        } else {
            for (Administrador administrador : administradors) {
                System.out.println("ID: " + administrador.getId() + " | Nome: " + administrador.getNome() + " | Email: " + administrador.getEmail());
            }
        }
    }

    // CRUD: Update
    public void atualizarAdministrador(int id, String nome, String email, String senha) {
        for (Administrador administrador : administradors) {
            if (administrador.getId() == id) {
                administrador.setNome(nome);
                administrador.setEmail(email);
                administrador.setSenha(senha);
                System.out.println("Administrador atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Administrador não encontrado.");
    }

    // CRUD: Delete
    public void removerAdministrador(int id) {
        administradors.removeIf(administrador -> administrador.getId() == id);
        System.out.println("Administrador removido com sucesso!");
    }

    // Método para autenticar Administrador
    public Administrador autenticarAdministrador(String email, String senha) {
        for (Administrador administrador : administradors) {
            if (administrador.getEmail().equals(email) && administrador.getSenha().equals(senha)) {
                adminLogado = administrador;  // Define o administrador logado
                return administrador;  // Retorna o administrador autenticado
            }
        }
        return null;  // Retorna null caso o administrador não seja encontrado
    }

    // Método para verificar se é Admin
    public boolean isAdminLogado() {
        return adminLogado != null;
    }

    // Gera o próximo ID para cadastro
    public int obterProximoId() {
        return administradors.size() + 1;
    }

    // Obtém a lista de administradores
    public List<Administrador> getAdministradores() {
        return administradors;
    }

    // Obtém o administrador logado
    public Administrador getAdminLogado() {
        return adminLogado;
    }
}
