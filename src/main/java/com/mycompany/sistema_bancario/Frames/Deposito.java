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

public class Deposito extends JFrame {

    private JLabel labelTitulo, labelNomeContaOrigem, labelValorDeposito;
    private JTextField campoNomeContaOrigem, campoValorDeposito;
    private JButton botaoDepositar, botaoSair;

    public Deposito() {
        // Configurações da janela
        setTitle("Tela de Depósito");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        labelTitulo = new JLabel("Depósito", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelNomeContaOrigem = new JLabel("Nome da Conta de Origem:");
        campoNomeContaOrigem = new JTextField();

        labelValorDeposito = new JLabel("Valor do Depósito:");
        campoValorDeposito = new JTextField();

        botaoDepositar = new JButton("Depositar Dinheiro");
        botaoSair = new JButton("Sair");

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 linhas, 2 colunas, espaço de 10px

        // Adicionando componentes ao painel
        painel.add(labelNomeContaOrigem);
        painel.add(campoNomeContaOrigem);
        painel.add(labelValorDeposito);
        painel.add(campoValorDeposito);
        painel.add(new JLabel()); // Campo vazio
        painel.add(botaoDepositar);
        painel.add(new JLabel()); // Campo vazio
        painel.add(botaoSair);

        // Adicionando o título e o painel à janela
        add(labelTitulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // Ação do botão Depositar Dinheiro
        botaoDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeContaOrigem = campoNomeContaOrigem.getText();
                String valorDeposito = campoValorDeposito.getText();

                // Verificação se o valor é numérico e positivo
                try {
                    double valor = Double.parseDouble(valorDeposito);
                    if (valor > 0 && !nomeContaOrigem.isEmpty()) {
                        // Depósito realizado com sucesso
                        JOptionPane.showMessageDialog(null, "Depósito de R$ " + valorDeposito + " realizado com sucesso na conta " + nomeContaOrigem + "!");
                    } else if (nomeContaOrigem.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "O nome da conta de origem não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Valor inválido
                        JOptionPane.showMessageDialog(null, "Valor inválido! O depósito deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Caso o valor inserido não seja numérico
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Sair
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela de Depósito e volta para a interface anterior (por exemplo, a tela de Caixa)
                dispose();
                CaixaInterface caixaInterface = new CaixaInterface();
                caixaInterface.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        // Criando e exibindo a tela de Depósito
        Deposito deposito = new Deposito();
        deposito.setVisible(true);
    }
}
