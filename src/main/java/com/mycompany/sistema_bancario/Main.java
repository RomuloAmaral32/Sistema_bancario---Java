package com.mycompany.sistema_bancario;

public class Main {

    public static void main(String[] args) {                        //Main feita somente para visualizar operaçoes e validacoes--(nao é o resultado final)

        Cliente cliente1 = new Cliente("Kauã", "161.586.406-70", "senha123", "kaua@email.com", "Cliente", "12345-6", "Rua A, 123", 1000.0);
        Cliente cliente2 = new Cliente("João", "161.586.406-70", "senha456", "joao@email.com", "Cliente", "54321-0", "Rua B, 321", 500.0);
        
        System.out.println("---- Cliente 1: Kauã ----");
        cliente1.consultarSaldo(); 
        
        System.out.println("\n---- Transferindo de Kauã para João ----");
        cliente1.transferir(200.0, cliente2); 
       
        System.out.println("\n---- Consultando extrato de Kauã ----");
        cliente1.consultarExtrato(); 
        
        System.out.println("\n---- Consultando extrato de João ----");
        cliente2.consultarExtrato(); 

        System.out.println("\n---- Sacando da conta de Kauã ----");
        cliente1.sacar(300.0); 

        System.out.println("\n---- Consultando saldo de Kauã ----");
        cliente1.consultarSaldo();

        System.out.println("\n---- Consultando extrato de Kauã ----");
        cliente1.consultarExtrato();
    }
}
