/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darlan
 */
/**
 *
 * @author Rômulo Amaral
 * @matricula 202335015
 */

public class UsuarioService {
    private List<Usuario> usuarios;
    private JsonHandler<Usuario> jsonHandler;

    public UsuarioService(String filePath) {
        jsonHandler = new JsonHandler<>(filePath);
        try {
            usuarios = jsonHandler.loadFromJson(Usuario.class);
        } catch (IOException e) {
            usuarios = new ArrayList<>();
            System.out.println("Erro ao carregar usuários do arquivo: " + e.getMessage());
        }
    }

    public UsuarioService(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void adicionarUsuario(Usuario usuario) {
        try {
            // Carrega os usuários existentes
            List<Usuario> usuariosExistentes = jsonHandler.loadFromJson(Usuario.class);

            // Adiciona o novo usuário à lista
            usuariosExistentes.add(usuario);

            // Salva a lista atualizada no arquivo JSON
            jsonHandler.saveToJson(usuariosExistentes);
        } catch (IOException e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    public List<Usuario> loadFromJson() {
        try {
            return jsonHandler.loadFromJson(Usuario.class);
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Cliente buscarClientePorNumeroConta(String numeroConta) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente) {
                Cliente cliente = (Cliente) usuario;
                if (cliente.getContaBancaria().equals(numeroConta)) {
                    return cliente;
                }
            }
        }
        throw new IllegalArgumentException("Cliente não encontrado para a conta: " + numeroConta);
    }

    public Usuario buscarUsuarioPorCPF(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        throw new IllegalArgumentException("Usuário não encontrado para o CPF: " + cpf);
    }
}
