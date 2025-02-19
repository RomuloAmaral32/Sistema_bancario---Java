/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

import java.util.List;

/**
 *
 * @author Darlan
 */
public class UsuarioService {
    private List<Usuario> usuarios;

    public UsuarioService(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario buscarUsuarioPorCPF(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        throw new IllegalArgumentException("Usuário não encontrado para o CPF: " + cpf);
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
}
