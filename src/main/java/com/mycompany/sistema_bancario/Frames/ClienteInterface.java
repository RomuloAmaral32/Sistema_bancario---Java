package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Rômulo Amaral
 * @matricula 202335015
 */


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
                TransferenciaInterface telatransferencia = new TransferenciaInterface(); 
                telatransferencia.setVisible(true);
            }
        });

        botaoSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaSenhaSaldoInterface saldo = new TelaSenhaSaldoInterface(); // Volta para a tela de login
                saldo.setVisible(true);
            }
        });

        botaoExtrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               TelaSenhaExtratoInterface extrato = new TelaSenhaExtratoInterface(); // Volta para a tela de login
                extrato.setVisible(true);
            }
        });

        botaoInvestimentoRF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                RendaFixa rendafixa = new RendaFixa(); // Volta para a tela de login
                rendafixa.setVisible(true);
            }
        });

        botaoInvestimentoRV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                RendaVariavel rendavariavel = new RendaVariavel(); // Volta para a tela de login
                rendavariavel.setVisible(true);
            }
        });

        botaoCredito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                SolicitarCredito credito = new SolicitarCredito(); // Volta para a tela de login
                credito.setVisible(true);
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retorna à tela de login
                dispose(); // Fecha a janela atual
                LoginInterface login = new LoginInterface(); // Volta para a tela de login
                login.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        ClienteInterface clienteInterface = new ClienteInterface();
        clienteInterface.setVisible(true);
    }
}
