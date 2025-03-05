package com.mycompany.sistema_bancario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private List<Usuario> usuarios;
    private JsonHandler<Usuario> jsonHandler;

    public UsuarioService(String filePath) {
    public UsuarioService(String filePath) {
        jsonHandler = new JsonHandler<>(filePath);
        try {
            // Tentar carregar os usuários existentes do JSON
            usuarios = jsonHandler.loadFromJson(Usuario.class);
            // Se o arquivo JSON estiver vazio ou não existir, inicializa uma lista vazia
            if (usuarios == null || usuarios.isEmpty()) {
                usuarios = new ArrayList<>();
            }
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
    }

    // Método para buscar cliente por número da conta
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

    public Usuario validarLogin(String cpf, String senha) {
        for (Usuario usuario : usuarios) {
            // Verifica o CPF e a senha
            if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
                return usuario; // Retorna o usuário se o CPF e a senha estiverem corretos
            }
        }
        throw new IllegalArgumentException("CPF ou senha inválidos."); // Lança exceção se não encontrar
    }
}
