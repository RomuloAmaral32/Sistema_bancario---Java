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
/**
 *
 * @author Rômulo Ferreira do Amaral
 * @matricula 202335015
 */
/**
 *
 * @author  Ian Nakamura Okano Preste
 * @matricula 202335038
 */

public class LoginService {
    private final String filePath = "src/main/java/com/mycompany/sistema_bancario/usuarios.json";
    private final JsonHandler<Usuario> jsonHandler;

    public LoginService(String filePath) {
        this.jsonHandler = new JsonHandler<>(filePath);
    }

public  Usuario verificarLogin(String cpfInserido, String senhaInserida) throws IllegalArgumentException {
    try {
        List<Usuario> usuarios = jsonHandler.loadFromJson(Usuario.class);
        for (Usuario usuario : usuarios) {
            // Adicionando prints para depuração
            System.out.println("Verificando CPF no arquivo JSON: " + usuario.getCpf());  // CPF do arquivo JSON
            System.out.println("CPF inserido: " + cpfInserido);  // CPF inserido pelo usuário

            if (usuario.getCpf().trim().equals(cpfInserido.trim())) {  // Removendo espaços extras
                System.out.println("CPF encontrado no arquivo: " + usuario.getCpf());  // CPF encontrado

                // Verificando senha com remoção de espaços extras
                if (usuario.getSenha().trim().equals(senhaInserida.trim())) {
                    return usuario;  // Senha correta, retorna o usuário
                } else {
                    throw new IllegalArgumentException("Senha incorreta.");
                }
            }
        }
        throw new IllegalArgumentException("Usuário não encontrado.");
    } catch (IOException e) {
        System.out.println("Erro ao carregar usuários: " + e.getMessage());
        throw new IllegalArgumentException("Erro ao carregar usuários.");
    }
}

}
