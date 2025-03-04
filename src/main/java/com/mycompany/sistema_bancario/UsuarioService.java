package com.mycompany.sistema_bancario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private List<Usuario> usuarios;
    private JsonHandler<Usuario> jsonHandler;

    public UsuarioService(String filePath) {
        jsonHandler = new JsonHandler<>(filePath);
        try {
            // Tentar carregar os usuários existentes do JSON
            usuarios = jsonHandler.loadFromJson(Usuario.class);
            if (usuarios == null) {
                // Se o arquivo estiver vazio ou não contiver usuários, inicializar uma lista vazia
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

    // Método para buscar usuário por CPF
    public Usuario buscarUsuarioPorCPF(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        throw new IllegalArgumentException("Usuário não encontrado para o CPF: " + cpf);
    }

    // Método para adicionar um novo usuário
    public void adicionarUsuario(Usuario usuario) {
        // Verificar se o usuário já existe na lista para evitar duplicações
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(usuario.getCpf())) {
                throw new IllegalArgumentException("Usuário com esse CPF já existe.");
            }
        }

        // Adiciona o novo usuário à lista
        usuarios.add(usuario);

        // Tenta salvar a lista completa de usuários no arquivo JSON
        try {
            jsonHandler.saveToJson(usuarios); // Salvando a lista inteira de volta no arquivo
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
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
