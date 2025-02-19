/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

import java.io.IOException;
import java.util.List;
/**
 *
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 */

public class LoginService {
    private final JsonHandler<Usuario> jsonHandler;

    public LoginService(String filePath) {
        this.jsonHandler = new JsonHandler<>(filePath);
    }

    public boolean verificarLogin(String cpfInserido, String senhaInserida) {
        try {
            List<Usuario> usuarios = jsonHandler.loadFromJson(Usuario.class);

            for (Usuario usuario : usuarios) {
                if (usuario.getCpf().equals(cpfInserido)) {
                    return usuario.getSenha().equals(senhaInserida);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }

        return false;
    }
}
