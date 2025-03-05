/*package com.mycompany.sistema_bancario;

import java.util.List;


 
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 *
 * @author Rômulo Ferreira do Amaral
 * @matricula 202335015
 *
 * @author  Ian Nakamura Okano Preste
 * @matricula 202335038
 

public class ClienteController {
    private Cliente cliente;

    // Construtor: Carrega o cliente do XML pelo CPF
    public ClienteController(String cpf) {
        this.cliente = carregarClientePorCpf(cpf);
    }

    private Cliente carregarClientePorCpf(String cpf) {
        List<Usuario> usuarios = XMLHandler.carregarDeXml("src/file/java/com/mycompany/sistema_bancario/usuarios.xml");
        if (usuarios != null) {
            for (Usuario usuario : usuarios) {
                if (usuario instanceof Cliente && usuario.getCpf().equals(cpf)) {
                    return (Cliente) usuario;
                }
            }
        }
        return null;
    }

    // Métodos para acessar informações do cliente
    public double consultarSaldo() {
        if (cliente != null) {
            return cliente.getSaldo();
        }
        return -1; // Retorna -1 caso o cliente não seja encontrado
    }

    public boolean transferir(double valor, Cliente destinatario, Gerente gerente, String senha) {
        if (cliente != null) {
            cliente.transferir(valor, destinatario, gerente, senha);
            return true;
        }
        return false;
    }

    public boolean sacar(double valor, Gerente gerente, String senha) {
        if (cliente != null) {
            cliente.sacar(valor, gerente, senha);
            return true;
        }
        return false;
    }
} */