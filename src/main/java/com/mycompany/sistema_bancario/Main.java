package com.mycompany.sistema_bancario;

import java.util.Scanner;

public class Main {                                                     //main meremente para testes
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Cliente cliente1 = new Cliente("João", "161.586.406-70", "senha123", "joao@email.com", "cliente", "12345", "Rua A", 5000000.0);
        Cliente cliente2 = new Cliente("Maria", "161.586.406-70", "senha456", "maria@email.com", "cliente", "67890", "Rua B", 3000000.0);
        Gerente gerente = new Gerente("Carlos", "161.586.406-70", "gerente123", "carlos@email.com", "gerente", 2000000.0);

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Consultar Extrato");
            System.out.println("3. Transferir Dinheiro");
            System.out.println("4. Sacar Dinheiro");
            System.out.println("5. Cadastrar Investimento de Renda Fixa");
            System.out.println("6. Cadastrar Investimento de Renda Variável");
            System.out.println("7. Exibir Produtos de Renda Fixa");
            System.out.println("8. Exibir Produtos de Renda Variável");
            System.out.println("9. Escolher Investimento");
            System.out.println("10. Sair");
            System.out.println("11. Solicitar Crédito"); 
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cliente1.consultarSaldo();
                    break;

                case 2:
                    cliente1.consultarExtrato();
                    break;

                case 3:
                    System.out.print("Digite o valor a ser transferido: ");
                    double valorTransferencia = scanner.nextDouble();
                    scanner.nextLine();  
                    cliente1.transferir(valorTransferencia, cliente2, gerente);
                    break;

                case 4:
                    System.out.print("Digite o valor a ser sacado: ");
                    double valorSaque = scanner.nextDouble();
                    scanner.nextLine(); 
                    cliente1.sacar(valorSaque, gerente);
                    break;

                case 5:
                    gerente.cadastrarRendaFixa();
                    break;

                case 6:
                    gerente.cadastrarRendaVariavel();
                    break;

                case 9:
                    gerente.exibirRendaFixa();
                    gerente.exibirRendaVariavel();
                    cliente1.escolherInvestimento(gerente);
                    break;

                case 10:
                    continuar = false;
                    System.out.println("Saindo do sistema...");
                    break;

                    case 11:
                    System.out.print("Digite o valor do crédito solicitado: ");
                    double valorCredito = scanner.nextDouble();
                    scanner.nextLine(); 
                    cliente1.solicitarCredito(valorCredito, gerente);
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}
