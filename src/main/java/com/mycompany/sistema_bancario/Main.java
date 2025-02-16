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
        Usuario usuario = new Usuario("Darlan", "12345678900", "123456", "darlan@emaill.com", "cliente");
        String cpfInserido = "12345678900";
        String senhaInserida = "123456"; 
        boolean loginValido = usuario.login(cpfInserido, senhaInserida);

        if (loginValido) 
            System.out.println("Login bem-sucedido! Bem-vindo, " + usuario.getNome() + ".");
        else
            System.out.println("Login falhou. Verifique seu CPF e senha.");

    }
}