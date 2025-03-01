package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteInterface extends JFrame {

    private JButton botaoTransferencia, botaoSaldo, botaoExtrato, botaoInvestimentoRF, botaoInvestimentoRV, botaoCredito, botaoSair;
    private JLabel titulo;

    public ClienteInterface() {
        setTitle("Área do Cliente");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        botaoTransferencia = new JButton("Transferência");
        botaoSaldo = new JButton("Consulta de Saldo");
        botaoExtrato = new JButton("Consulta de Extrato");
        botaoInvestimentoRF = new JButton("Investimento em Renda Fixa");
        botaoInvestimentoRV = new JButton("Investimento em Renda Variável");
        botaoCredito = new JButton("Solicitação de Crédito");
        botaoSair = new JButton("Sair");

        titulo = new JLabel("Controle do Cliente", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(8, 1, 10, 10)); 

        painel.add(titulo);
        painel.add(botaoTransferencia);
        painel.add(botaoSaldo);
        painel.add(botaoExtrato);
        painel.add(botaoInvestimentoRF);
        painel.add(botaoInvestimentoRV);
        painel.add(botaoCredito);
        painel.add(botaoSair);

        add(painel);

        // Ação para cada botão
        botaoTransferencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica da operação de transferência
                JOptionPane.showMessageDialog(null, "Operação de Transferência selecionada.");
            }
        });

        botaoSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica da consulta de saldo
                JOptionPane.showMessageDialog(null, "Operação de Consulta de Saldo selecionada.");
            }
        });

        botaoExtrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica da consulta de extrato
                JOptionPane.showMessageDialog(null, "Operação de Consulta de Extrato selecionada.");
            }
        });

        botaoInvestimentoRF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de investimento em renda fixa
                JOptionPane.showMessageDialog(null, "Operação de Investimento em Renda Fixa selecionada.");
            }
        });

        botaoInvestimentoRV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de investimento em renda variável
                JOptionPane.showMessageDialog(null, "Operação de Investimento em Renda Variável selecionada.");
            }
        });

        botaoCredito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de solicitação de crédito
                JOptionPane.showMessageDialog(null, "Operação de Solicitação de Crédito selecionada.");
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retorna à tela de login
                dispose(); // Fecha a janela atual
                LoginInterface telaLogin = new LoginInterface(); // Volta para a tela de login
                telaLogin.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        ClienteInterface clienteInterface = new ClienteInterface();
        clienteInterface.setVisible(true);
    }
}
