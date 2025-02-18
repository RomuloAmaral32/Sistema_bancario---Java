/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistema_bancario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 */
public class Main {

    public static void main(String[] args) {
        JsonHandler<Usuario> jsonHandler = new JsonHandler<>("usuarios.json");

        try {
            List<Usuario> usuarios = jsonHandler.loadFromJson(Usuario.class);
            System.out.println("Usuários carregados:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getNome() + " - " + usuario.getCpf());
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }


        // salvar usuarios
        // List<Usuario> usuarios = new ArrayList<>();
        // usuarios.add(new Usuario("Darlan Silva", "34014199002", "senha123", "darlan@email.com", "cliente"));
        // usuarios.add(new Usuario("Maria Julia", "47712171068", "senha456", "mj@email.com", "caixa"));

        // JsonHandler<Usuario> jsonHandler = new JsonHandler<>("src/file/java/com/mycompany/sistema_bancario/usuarios.json");
        // try {
        //     jsonHandler.saveToJson(usuarios);
        //     System.out.println("Usuários salvos com sucesso!");
        // } catch (IOException e) {
        //     System.out.println("Erro ao salvar usuários: " + e.getMessage());
        // }

        Caixa caixa = new Caixa("Darlan", "34014199002", "123456", "darlan@email.com", "caixa", "1234");
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