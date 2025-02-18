/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistema_bancario;

/**
 *
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 */
public class Main {

    public static void main(String[] args) {
        Caixa caixa = new Caixa("Darlan", "34014199002", "123456", "darlan@email.com", "caixa", "1234");
        Usuario usuario = new Usuario("Joao", "47712171068", "111111", "email@email.com", "cliente");
        String cpfInserido = "34014199002";
        String senhaInserida = "123456";
        boolean loginValido = caixa.login(cpfInserido, senhaInserida);

        if (loginValido)
            System.out.println("Login bem-sucedido! Bem-vindo, " + caixa.getNome() + ".");
        else
            System.out.println("Login falhou. Verifique seu CPF e senha.");

        caixa.deposito(1000, "123456");
        caixa.saque(500, "123456", "1234");
        caixa.transferencia(200, "123456", "1234", "654321");

    }
}