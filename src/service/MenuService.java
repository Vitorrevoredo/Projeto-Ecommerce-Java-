package service;

import java.util.Scanner;

public class MenuService {
    public int obterOpcaoMenu(Scanner scanner, String[] opcoes) {
        // Exibe as opções no menu
        for (int i = 0; i < opcoes.length; i++) {
            System.out.println((i + 1) + ". " + opcoes[i]);
        }

        // Captura a escolha do usuário
        int opcao = -1;
        while (opcao < 1 || opcao > opcoes.length) {
            System.out.print("Escolha uma opção: ");
            try {
                String input = scanner.nextLine(); // Captura a entrada do usuário

                opcao = Integer.parseInt(input); // Tenta converter a entrada para inteiro
                if (opcao < 1 || opcao > opcoes.length) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
        return opcao;
    }
}
