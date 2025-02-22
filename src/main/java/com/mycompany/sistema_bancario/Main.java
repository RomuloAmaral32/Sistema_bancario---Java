package com.mycompany.sistema_bancario;

import java.util.Scanner;

public class Main {                                                             //********erro em transferencias e saques acima de 1M, nao desconta do saldo  nem refistra no extrato
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando clientes e gerente
        Cliente cliente1 = new Cliente("João", "161.586.406-70", "senha123", "joao@email.com", "cliente", "12345", "Rua A", 5000000.0);
        Cliente cliente2 = new Cliente("Maria", "161.586.406-70", "senha456", "maria@email.com", "cliente", "67890", "Rua B", 3000000.0);
        Gerente gerente = new Gerente("Carlos", "161.586.406-70", "gerente123", "carlos@email.com", "gerente", 2000000.0);

        // Menu interativo
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Consultar Extrato");
            System.out.println("3. Transferir Dinheiro");
            System.out.println("4. Sacar Dinheiro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer de nova linha após nextInt()

            switch (opcao) {
                case 1:
                    // Consultar saldo
                    cliente1.consultarSaldo();
                    break;

                case 2:
                    // Consultar extrato
                    cliente1.consultarExtrato();
                    break;

                case 3:
                    // Transferir dinheiro
                    System.out.print("Digite o valor a ser transferido: ");
                    double valorTransferencia = scanner.nextDouble();
                    scanner.nextLine();  // Limpar o buffer
                    cliente1.transferir(valorTransferencia, cliente2, gerente);
                    break;

                case 4:
                    // Sacar dinheiro
                    System.out.print("Digite o valor a ser sacado: ");
                    double valorSaque = scanner.nextDouble();
                    scanner.nextLine();  // Limpar o buffer
                    cliente1.sacar(valorSaque, gerente);
                    break;

                case 5:
                    // Sair do programa
                    continuar = false;
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}
