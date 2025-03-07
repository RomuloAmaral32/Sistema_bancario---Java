/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

import java.time.LocalDate;

/**
 *
 * @author Darlan
 */
public class InvestimetoRendaFixa {
    private String produto;
    private double valorInvestido;
    private LocalDate prazoMinimo;
    private LocalDate vencimento;

    public InvestimetoRendaFixa(String produto, double valorInvestido, LocalDate prazoMinimo, LocalDate vencimento) {
        this.produto = produto;
        this.valorInvestido = valorInvestido;
        this.prazoMinimo = prazoMinimo;
        this.vencimento = vencimento;
    }

    // Getters e Setters
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(double valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public LocalDate getPrazoMinimo() {
        return prazoMinimo;
    }

    public void setPrazoMinimo(LocalDate prazoMinimo) {
        this.prazoMinimo = prazoMinimo;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }
}
