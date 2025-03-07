/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

/**
 *
 * @author Darlan
 */
public class InvestimentoRendaVariavel {
    private String produto;
    private double risco;
    private double rentabilidade;

    public InvestimentoRendaVariavel(String produto, double risco, double rentabilidade) {
        this.produto = produto;
        this.risco = risco;
        this.rentabilidade = rentabilidade;
    }

    // Getters e Setters
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getRisco() {
        return risco;
    }

    public void setRisco(double risco) {
        this.risco = risco;
    }

    public double getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(double rentabilidade) {
        this.rentabilidade = rentabilidade;
    }
}
