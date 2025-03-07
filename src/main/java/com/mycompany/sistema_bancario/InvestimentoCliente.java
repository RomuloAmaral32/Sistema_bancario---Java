/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darlan
 */
public class InvestimentoCliente {
    private String contaBancaria;
    private List<InvestimetoRendaFixa> rendaFixa;
    private List<InvestimentoRendaVariavel> rendaVariavel;

    public InvestimentoCliente(String contaBancaria) {
        this.contaBancaria = contaBancaria;
        this.rendaFixa = new ArrayList<>();
        this.rendaVariavel = new ArrayList<>();
    }

    // Getters e Setters
    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public List<InvestimetoRendaFixa> getRendaFixa() {
        return rendaFixa;
    }

    public void setRendaFixa(List<InvestimetoRendaFixa> rendaFixa) {
        this.rendaFixa = rendaFixa;
    }

    public List<InvestimentoRendaVariavel> getRendaVariavel() {
        return rendaVariavel;
    }

    public void setRendaVariavel(List<InvestimentoRendaVariavel> rendaVariavel) {
        this.rendaVariavel = rendaVariavel;
    }

    // Método para adicionar um investimento de renda fixa
    public void adicionarInvestimetoRendaFixa(InvestimetoRendaFixa investimento) {
        this.rendaFixa.add(investimento);
    }

    // Método para adicionar um investimento de renda variável
    public void adicionarInvestimentoRendaVariavel(InvestimentoRendaVariavel investimento) {
        this.rendaVariavel.add(investimento);
    }
}
