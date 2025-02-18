package com.mycompany.sistema_bancario;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Gerente gerente = new Gerente("Carlos Silva", "987.654.321-00", "senha123", "carlos@email.com", "Gerente", 1500000.0);

        System.out.println("Cadastro de Renda Fixa:");
        gerente.cadastrarRendaFixa();

        System.out.println("\nCadastro de Renda Variável:");
        gerente.cadastrarRendaVariavel();

        System.out.println("\nExibindo os produtos de Renda Fixa:");
        gerente.exibirRendaFixa();

        System.out.println("\nExibindo os produtos de Renda Variável:");
        gerente.exibirRendaVariavel();
    }
}
