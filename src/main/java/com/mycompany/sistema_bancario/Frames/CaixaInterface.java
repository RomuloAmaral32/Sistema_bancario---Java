package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 */
/**
 *
 * @author Rômulo Ferreira do Amaral
 * @matricula 202335015
 */
/**
 *
 * @author  Ian Nakamura Okano Preste
 * @matricula 202335038
 */

public class CaixaInterface extends JFrame {

    private JButton botaoSaque, botaoDeposito, botaoTransferencia, botaoSair;
    private JLabel titulo;

    public CaixaInterface() {
        // Configurações da janela
        setTitle("Área do Caixa");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando o título
        titulo = new JLabel("Operações do Caixa", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        // Criando os botões
        botaoSaque = new JButton("Atendimento de Saque");
        botaoDeposito = new JButton("Processamento de Depósitos");
        botaoTransferencia = new JButton("Transferência");
        botaoSair = new JButton("Sair");

        // Definindo o layout como GridBagLayout
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre componentes

        // Título ocupa toda a largura (2 colunas)
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(titulo, gbc);

        // Configurações para os botões (ocupam 80% da largura)
        gbc.gridwidth = 1; // Volta para 1 coluna
        gbc.fill = GridBagConstraints.HORIZONTAL; // O botão ocupa horizontalmente
        gbc.weightx = 0.8; // O botão ocupa 80% da largura disponível

        // Centralizando os botões
        gbc.gridx = 0; // Coluna do centro
        gbc.gridy = 1;
        painel.add(botaoSaque, gbc);

        // Preenchimento (vazio) para simular os 20% restantes
        gbc.gridx = 0;
        gbc.weightx = 0.2;
        painel.add(Box.createHorizontalStrut(0), gbc);

        gbc.weightx = 0.8;
        gbc.gridx = 0; // Mantém os botões centralizados
        gbc.gridy = 2;
        painel.add(botaoDeposito, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 0;
        painel.add(Box.createHorizontalStrut(0), gbc);

        gbc.weightx = 0.8;
        gbc.gridx = 0; // Centraliza o próximo botão
        gbc.gridy = 3;
        painel.add(botaoTransferencia, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 0;
        painel.add(Box.createHorizontalStrut(0), gbc);

        gbc.weightx = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 4;
        painel.add(botaoSair, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 0;
        painel.add(Box.createHorizontalStrut(0), gbc);

        // Adicionando painel à janela
        add(painel);

        // Ação para cada botão
        botaoSaque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Saque sacar = new Saque();
                sacar.setVisible(true);
            }
        });

        botaoDeposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Deposito depositar = new Deposito();
                depositar.setVisible(true);
            }
        });

        botaoTransferencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                TransferenciaCaixa telatransferencia = new TransferenciaCaixa(); 
                telatransferencia.setVisible(true);
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
        CaixaInterface caixaInterface = new CaixaInterface();
        caixaInterface.setVisible(true);
    }
}
