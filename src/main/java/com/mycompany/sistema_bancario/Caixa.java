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
public class Caixa extends Usuario {

    private String numeroFuncionario;

    public Caixa(String nome, String cpf, String senha, String email, String tipo, String numeroFuncionario) {
        super(nome, cpf, senha, email, tipo);
        this.numeroFuncionario = numeroFuncionario;
    }

    public String getNumeroFuncionario() {
        return numeroFuncionario;
    }

    public void setNumeroFuncionario(String numeroFuncionario) {
        this.numeroFuncionario = numeroFuncionario;
    }

    public void deposito(double valor, String contaCliente) {
        if (valor <= 0) {
            System.out.println("Valor inválido para depósito.");
            return;
        }
        System.out.println("Depósito de R$" + valor + " na conta" + contaCliente + " foi realizado com sucesso.");
    }

    public boolean saque(double valor, String contaCliente, String senhaCliente) {
        if (valor <= 0) {
            System.out.println("Valor inválido para saque.");
            return false;
        }
        if (valor > 1000000) {
            System.out
                    .println("Valor máximo para saque é de R$1.000.000,00. Para saques maiores, procurar um gerente.");
            return false;
        }
        verificaSenha(senhaCliente);
        System.out.println("Saque de R$" + valor + " da conta " + contaCliente + " realizado com sucesso.");
        return true;
    }

    public boolean transferencia(double valor, String contaCliente, String senhaCliente, String contaDestino) {
        if (valor <= 0) {
            System.out.println("Valor inválido para transferência.");
            return false;
        }
        if (valor > 1000000) {
            System.out.println(
                    "Valor máximo para transferência é de R$1.000.000,00. Para transferências maiores, procurar um gerente.");
            return false;
        }
        System.out.println("Transferência de R$" + valor + " da conta " + contaCliente + " para a conta " + contaDestino
                + " realizada com sucesso.");
        return true;

    }
}