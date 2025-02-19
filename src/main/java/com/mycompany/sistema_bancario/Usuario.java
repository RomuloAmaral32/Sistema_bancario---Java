/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

import java.util.InputMismatchException;

/**
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 */
public class Usuario {

    private String nome;
    private String cpf;
    private String senha;
    private String email;
    private String tipo; // "cliente", "caixa" ou "gerente"

    public Usuario(String nome, String cpf, String senha, String email, String tipo) {
        this.nome = nome;

        if (validaCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF invalido");
        }
        this.senha = senha;
        this.email = email;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (validaCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF invalido");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo.equals("cliente") || tipo.equals("caixa") || tipo.equals("gerente")) {
            this.tipo = tipo;
        } else {
            throw new IllegalArgumentException("Tipo de usuário inválido.");
        }
    }

    public boolean login(String cpfInserido, String senhaInserida) {
        return this.cpf.equals(cpfInserido) && this.senha.equals(senhaInserida);
    }

    private boolean validaCPF(String CPF) {
        CPF = CPF.replace(".", "").replace("-", "");

        if (CPF.length() != 11 || CPF.matches("(\\d)\\1{10}")) {
            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));
        } catch (InputMismatchException e) {
            return false;
        }
    }

    public boolean verificaSenha(String senhaInserida) {
        if (this.senha.equals(senhaInserida)) {
            System.out.println("Senha correta.");
            return true;
        } else {
            System.out.println("Senha incorreta.");
            return false;
        }
    }
}