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

public class CadastroRendaVariavel extends JFrame {

    private JLabel labelTitulo, labelNomeOpcao, labelPercentualRisco, labelRentabilidadeEsperada;
    private JTextField campoNomeOpcao, campoPercentualRisco, campoRentabilidadeEsperada;
    private JButton botaoRegistrar, botaoSair;

    public CadastroRendaVariavel() {
        // Configurações da janela
        setTitle("Cadastro de Opções de Renda Variável");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        labelTitulo = new JLabel("Cadastro de Renda Variável", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelNomeOpcao = new JLabel("Nome da Opção:");
        campoNomeOpcao = new JTextField();

        labelPercentualRisco = new JLabel("Percentual de Risco (%):");
        campoPercentualRisco = new JTextField();

        labelRentabilidadeEsperada = new JLabel("Rentabilidade Esperada (%):");
        campoRentabilidadeEsperada = new JTextField();

        botaoRegistrar = new JButton("Registrar Opção");
        botaoSair = new JButton("Sair");

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 linhas, 2 colunas, espaço entre componentes

        // Adicionando componentes ao painel
        painel.add(labelNomeOpcao);
        painel.add(campoNomeOpcao);
        painel.add(labelPercentualRisco);
        painel.add(campoPercentualRisco);
        painel.add(labelRentabilidadeEsperada);
        painel.add(campoRentabilidadeEsperada);
        painel.add(new JLabel()); // Campo vazio
        painel.add(botaoRegistrar);
        painel.add(new JLabel()); // Campo vazio
        painel.add(botaoSair);

        // Adicionando o título e o painel à janela
        add(labelTitulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // Ação do botão Registrar Opção
        botaoRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeOpcao = campoNomeOpcao.getText();
                String percentualRisco = campoPercentualRisco.getText();
                String rentabilidadeEsperada = campoRentabilidadeEsperada.getText();

                // Verificação se os campos não estão vazios e os valores numéricos são válidos
                try {
                    double risco = Double.parseDouble(percentualRisco);
                    double rentabilidade = Double.parseDouble(rentabilidadeEsperada);

                    if (risco > 0 && rentabilidade > 0 && !nomeOpcao.isEmpty()) {
                        // Registro da opção no sistema (lógica a ser implementada)
                        JOptionPane.showMessageDialog(null, "Opção de Renda Variável " + nomeOpcao + " registrada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Caso os valores inseridos não sejam numéricos
                    JOptionPane.showMessageDialog(null, "Percentual de risco e rentabilidade esperada devem ser valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Sair
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela de CadastroRendaVariavel
                dispose();
                // Volta para a interface principal ou anterior (a ser definido)
                GerenteInterface gerenteInterface = new GerenteInterface();
                gerenteInterface.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        // Criando e exibindo a tela de CadastroRendaVariavel
        CadastroRendaVariavel cadastroRendaVariavel = new CadastroRendaVariavel();
        cadastroRendaVariavel.setVisible(true);
    }
}
