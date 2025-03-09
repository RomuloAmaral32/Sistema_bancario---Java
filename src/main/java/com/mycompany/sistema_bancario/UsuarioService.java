package com.mycompany.sistema_bancario;

import java.io.IOException;
import java.util.ArrayList;
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
 * @author Ian Nakamura Okano Preste
 * @matricula 202335038
 */

public class UsuarioService {
    private List<Usuario> usuarios;
    private JsonHandler<Usuario> jsonHandler;

    public UsuarioService(String filePath) {
        filePath = "src/main/java/com/mycompany/sistema_bancario/usuarios.json";
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

            // Verifica se o CPF já está em uso pelo mesmo tipo
            isCpfDuplicado(usuario, usuariosExistentes);

            // Adiciona o novo usuário à lista
            usuariosExistentes.add(usuario);

            // Salva a lista atualizada no arquivo JSON
            jsonHandler.saveToJson(usuariosExistentes);

            System.out.println("Usuário adicionado com sucesso!");
        } catch (CpfDuplicadoException e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao acessar o arquivo JSON: " + e.getMessage());
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

    // Método para buscar cliente por número da conta
// Método para buscar cliente por número da conta
public Cliente buscarClientePorNumeroConta(String numeroConta) {
    System.out.println("Procurando cliente com a conta: " + numeroConta); // Log para verificar

    for (Usuario usuario : usuarios) {
        if (usuario instanceof Cliente) {  // Verifica se o usuário é um Cliente
            Cliente cliente = (Cliente) usuario;  // Cast para Cliente
            System.out.println("Verificando cliente: " + cliente.getContaBancaria()); // Log para cada cliente

            if (cliente.getContaBancaria().equals(numeroConta)) {
                System.out.println("Cliente encontrado: " + cliente.getNome());
                return cliente;  // Retorna o cliente se a conta bancária for encontrada
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

    private void isCpfDuplicado(Usuario novoUsuario, List<Usuario> usuariosExistentes) throws CpfDuplicadoException {
        for (Usuario usuario : usuariosExistentes) {
            if (usuario.getCpf().equals(novoUsuario.getCpf())) {
                if (usuario.getTipo().equals(novoUsuario.getTipo())) {
                    throw new CpfDuplicadoException("CPF já está em uso por um usuário do mesmo tipo.");
                }
                return;
            }
        }
    }

    public String gerarNovaContaBancaria() {
        try {
            List<Usuario> usuariosExistentes = jsonHandler.loadFromJson(Usuario.class);
            int maiorConta = 0;

            for (Usuario usuario : usuariosExistentes) {
                if (usuario.getTipo().equals("cliente")) {
                    Cliente cliente = (Cliente) usuario;
                    String contaBancaria = cliente.getContaBancaria();
                    int numeroConta = Integer.parseInt(contaBancaria);
                    if (numeroConta > maiorConta) {
                        maiorConta = numeroConta;
                    }
                }
            }

            int novaConta = maiorConta + 1;

            return String.format("%01d", novaConta);
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários para gerar conta bancária: " + e.getMessage());
            return null;
        }
    }
    public void atualizarCliente(Cliente clienteAtualizado) {
        try {
            // Carregar a lista de usuários do arquivo JSON
            List<Usuario> usuariosExistentes = jsonHandler.loadFromJson(Usuario.class);
    
            // Atualizar o cliente na lista
            for (int i = 0; i < usuariosExistentes.size(); i++) {
                if (usuariosExistentes.get(i).getCpf().equals(clienteAtualizado.getCpf())) {
                    usuariosExistentes.set(i, clienteAtualizado);
                    break;
                }
            }
    
            // Salvar a lista atualizada no arquivo JSON
            jsonHandler.saveToJson(usuariosExistentes);
    
            System.out.println("Cliente atualizado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao atualizar cliente no arquivo JSON: " + e.getMessage());
        }
    }
}