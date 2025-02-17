package com.mycompany.sistema_bancario;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario{
    private String contaBancaria;
    private String endereco;
    private double saldo;
    private List<String> extrato;

    public Cliente(String nome, String cpf, String senha, String email, String tipo, String contaBancaria, String endereco, double saldoInicial){
        super(nome, cpf, senha, email, tipo);
        this.contaBancaria = contaBancaria;
        this.endereco = endereco;
        this.saldo = saldoInicial;
        this.extrato = new ArrayList<>();
        registrarExtrato("Abertura da conta com saldo inicial: R$" + saldoInicial);
    } 

    public double consultarSaldo(){
        return this.saldo;
    }

    public transferir(double valor, cliente destinatario){
        if(valor <= this.saldo){
            this.saldo -= valor;
            destinatario.receberTransferencia(valor, this);
            registrarExtrato("Transferencia de R$" + valor + "para" + destinatario.getNome());
        }
        else{
            System.out.println("Saldo insuficiênte para esse valor de transferência");
        }
    }

    public receberTransferencia(double valor, cliente remetente){
        this.saldo += valor;
        registrarExtrato("Recebido R$" + valor + "de" + remetente.getNome());
    }

    public void sacar(double valor){
        if(valor <= this.saldo){
            this.saldo -=valor;
            registrarExtrato("Saque de R$" + valor);
            System.out.println("Saque de R$" + valor + "realizado com sucesso.");
        }
        else{
            System.out.println("Saldo insuficiente para esse valor para saque");
        }
    }


    public void consultarExtrato(){
        System.out.println("Extrato da conta " + this.contaBancaria + ":");
        for(String registro : extrato){
            System.out.println(registro);
        }
    }

    public void registrarExtrato(String transacao){
        extrato.add(transacao);
    }

    public String getContaBancaria(){
        return contaBancaria;
    }

    public String getEndereco(){
        return endereco;
    }

    public String setEndereco(String endereco){
        this.endereco = endereco;
    }


}