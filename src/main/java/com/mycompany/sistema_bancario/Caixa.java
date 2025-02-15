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
        super(nome, cpf, senha, email, tipo); // Chama o construtor da classe base
        this.numeroFuncionario = numeroFuncionario;	
    }

    public String getNumeroFuncionario() {
        return numeroFuncionario;
    }

    public void setNumeroFuncionario(String numeroFuncionario) {
        this.numeroFuncionario = numeroFuncionario;
    }
}