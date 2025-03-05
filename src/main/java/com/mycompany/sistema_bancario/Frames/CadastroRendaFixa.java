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

public class CadastroRendaFixa extends JFrame {

    private JLabel labelTitulo, labelNomeOpcao, labelTaxaRendimento, labelPrazoMinimo, labelPrazoMaximo;
    private JTextField campoNomeOpcao, campoTaxaRendimento, campoPrazoMinimo, campoPrazoMaximo;
    private JButton botaoRegistrar, botaoSair;

    public CadastroRendaFixa() {
        // Configurações da janela
        setTitle("Cadastro de Opções de Renda Fixa");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        labelTitulo = new JLabel("Cadastro de Renda Fixa", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelNomeOpcao = new JLabel("Nome da Opção:");
        campoNomeOpcao = new JTextField();

        labelTaxaRendimento = new JLabel("Taxa de Rendimento (%):");
        campoTaxaRendimento = new JTextField();

        labelPrazoMinimo = new JLabel("Prazo Mínimo (meses):");
        campoPrazoMinimo = new JTextField();

        labelPrazoMaximo = new JLabel("Prazo Máximo (meses):");
        campoPrazoMaximo = new JTextField();

        botaoRegistrar = new JButton("Registrar Opção");
        botaoSair = new JButton("Sair");

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 linhas, 2 colunas, espaço entre componentes

        // Adicionando componentes ao painel
        painel.add(labelNomeOpcao);
        painel.add(campoNomeOpcao);
        painel.add(labelTaxaRendimento);
        painel.add(campoTaxaRendimento);
        painel.add(labelPrazoMinimo);
        painel.add(campoPrazoMinimo);
        painel.add(labelPrazoMaximo);
        painel.add(campoPrazoMaximo);
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
                String taxaRendimento = campoTaxaRendimento.getText();
                String prazoMinimo = campoPrazoMinimo.getText();
                String prazoMaximo = campoPrazoMaximo.getText();

                // Verificação se os campos não estão vazios e os valores numéricos são válidos
                try {
                    double taxa = Double.parseDouble(taxaRendimento);
                    int minimo = Integer.parseInt(prazoMinimo);
                    int maximo = Integer.parseInt(prazoMaximo);

                    if (minimo > 0 && maximo > 0 && taxa > 0 && !nomeOpcao.isEmpty()) {
                        // Registro da opção no sistema (lógica a ser implementada)
                        JOptionPane.showMessageDialog(null, "Opção de Renda Fixa " + nomeOpcao + " registrada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Caso os valores inseridos não sejam numéricos
                    JOptionPane.showMessageDialog(null, "Taxa de rendimento, prazo mínimo e máximo devem ser valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Sair
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela de CadastroRendaFixa
                dispose();
                // Volta para a interface principal ou anterior (a ser definido)
                GerenteInterface gerenteInterface = new GerenteInterface();
                gerenteInterface.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        // Criando e exibindo a tela de CadastroRendaFixa
        CadastroRendaFixa cadastroRendaFixa = new CadastroRendaFixa();
        cadastroRendaFixa.setVisible(true);
    }
}
