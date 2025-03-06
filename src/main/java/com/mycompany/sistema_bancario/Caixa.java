/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Caixa extends Usuario {

    private String numeroFuncionario;
    @XmlTransient
    private UsuarioService usuarioService;

    public Caixa() {
        super();
    }

    public Caixa(String nome, String cpf, String senha, String email, String tipo, String cep, String numero,
            String numeroFuncionario, UsuarioService usuarioService) {
        super(nome, cpf, senha, email, tipo, cep, numero);
        this.numeroFuncionario = numeroFuncionario;
        this.usuarioService = usuarioService;
    }

    @XmlElement
    public String getNumeroFuncionario() {
        return numeroFuncionario;
    }

    public void setNumeroFuncionario(String numeroFuncionario) {
        this.numeroFuncionario = numeroFuncionario;
    }

    public void deposito(double valor, String numeroContaCliente) {
        if (valor <= 0)
            throw new IllegalArgumentException("Valor inválido para depósito.");

        Cliente cliente = usuarioService.buscarClientePorNumeroConta(numeroContaCliente);
        System.out.println(
                "Depósito de R$" + valor + " na conta de " + cliente.getNome() + " foi realizado com sucesso.");
    }

    public boolean saque(double valor, String numeroContaCliente, String senhaCliente) {
        // Busca o cliente pelo número da conta
        Cliente cliente = usuarioService.buscarClientePorNumeroConta(numeroContaCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return false;
        }

        // Validações
        if (valor <= 0) {
            System.out.println("Valor inválido para saque.");
            return false;
        }

        if (valor > 1000000) {
            System.out.println("Valor máximo para saque é de R$1.000.000,00. Para saques maiores, procure um gerente.");
            return false;
        }

        if (!cliente.verificaSenha(senhaCliente)) {
            System.out.println("Senha incorreta.");
            return false;
        }

        if (cliente.getSaldo() < valor) {
            System.out.println("Saldo insuficiente.");
            return false;
        }

        // Realiza o saque
        cliente.setSaldo(cliente.getSaldo() - valor);
        System.out.println("Saque de R$" + valor + " da conta " + numeroContaCliente + " realizado com sucesso.");

        return true;
    }

    public boolean transferencia(double valor, String numeroContaCliente, String senhaCliente, String contaDestino) {
        Cliente cliente = usuarioService.buscarClientePorNumeroConta(numeroContaCliente);

        if (valor <= 0) {
            System.out.println("Valor inválido para transferência.");
            return false;
        }
        if (valor > 1000000) {
            System.out.println(
                    "Valor máximo para transferência é de R$1.000.000,00. Para transferências maiores, procurar um gerente.");
            return false;
        }
        if (!cliente.verificaSenha(senhaCliente)) {
            System.out.println("Senha incorreta.");
            return false;
        }
        System.out.println(
                "Transferência de R$" + valor + " da conta " + numeroContaCliente + " para a conta " + contaDestino
                        + " realizada com sucesso.");
        return true;
    }
}