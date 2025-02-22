package com.mycompany.sistema_bancario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente extends Usuario {
    private String contaBancaria;
    private String endereco;
    private double saldo;
    private List<String> extrato;
    private Scanner scanner;  

    public Cliente(String nome, String cpf, String senha, String email, String tipo, String contaBancaria, String endereco, double saldoInicial) {
        super(nome, cpf, senha, email, tipo);
        this.contaBancaria = contaBancaria;
        this.endereco = endereco;
        this.saldo = saldoInicial;
        this.extrato = new ArrayList<>();
        this.scanner = new Scanner(System.in);  
        registrarExtrato("Abertura da conta com saldo inicial: R$" + saldoInicial);
    }

    // Método de validação de senha
    private boolean validarSenha() {
        System.out.print("Digite sua senha para validar a operação: ");
        String senhaDigitada = scanner.nextLine();  
        return senhaDigitada.equals(super.getSenha());
    }

    public void consultarSaldo() {
        if (validarSenha()) {
            System.out.println("Seu saldo é: R$"+ this.saldo);
        } else {
            System.out.println("Senha incorreta."); 
        }
    }

    public void transferir(double valor, Cliente destinatario, Gerente gerente) {
        if (validarSenha()) {
            if (valor >= 1000000.0) {
                if (valor <= this.saldo){
                    boolean veracidade;
                    veracidade = gerente.acompanharTransacao(this, destinatario, valor);
                    if(veracidade){
                        this.saldo -= valor;
                        destinatario.receberTransferencia(valor, this);
                        registrarExtrato("Transferência de R$" + valor + " para " + destinatario.getContaBancaria());
                        System.out.println("Transferência realizada com sucesso.");
                    }else{
                        System.out.println("O gerente nao autorizou a sua transferência");
                    }
                }
                else {
                    System.out.println("Saldo insuficiente para essa transferência.");
                }
            } else if (valor <= this.saldo) {
                this.saldo -= valor;
                destinatario.receberTransferencia(valor, this);
                registrarExtrato("Transferência de R$" + valor + " para " + destinatario.getNome());
                System.out.println("Transferência realizada com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para essa transferência.");
            }
        } else {
            System.out.println("Senha incorreta. Operação cancelada.");
        }
    }

    public void receberTransferencia(double valor, Cliente remetente) {
        this.saldo += valor;
        registrarExtrato("Recebido R$" + valor + " de " + remetente.getNome());
    }

    public void sacar(double valor, Gerente gerente) {
        if (validarSenha()) {
            if (valor >= 1000000.0) {
                gerente.acompanharSaque(this, valor);
            } else if (valor <= this.saldo) {
                this.saldo -= valor;
                registrarExtrato("Saque de R$" + valor);
                System.out.println("Saque de R$" + valor + " realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para esse saque.");
            }
        } else {
            System.out.println("Senha incorreta. Operação cancelada.");
        }
    }

    public void consultarExtrato() {
        if (validarSenha()) {
            System.out.println("Extrato da conta " + this.contaBancaria + ":");
            for (String registro : extrato) {
                System.out.println(registro);
            }
        } else {
            System.out.println("Senha incorreta. Operação cancelada.");
        }
    }

    private void registrarExtrato(String transacao) {
        extrato.add(transacao);
    }

    public String getContaBancaria() {
        return contaBancaria;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
