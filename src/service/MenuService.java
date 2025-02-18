package service;

import java.util.Scanner;

public class MenuService {

    public int obterOpcaoMenu(Scanner scanner, String[] opcoes) {
        int opcao = -1;

        // Exibe as opções do menu
        while (true) {
            System.out.println("\n--- Menu ---");
            for (int i = 0; i < opcoes.length; i++) {
                System.out.println((i + 1) + ". " + opcoes[i]);
            }

            // Captura e valida a escolha do usuário
            System.out.print("Escolha uma opção: ");
            String input = scanner.nextLine().trim();

            try {
                opcao = Integer.parseInt(input);

                if (opcao >= 1 && opcao <= opcoes.length) {
                    break;
                } else {
                    System.out.println("Opção inválida. Selecione um número entre 1 e " + opcoes.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }

        return opcao;
    }
}
