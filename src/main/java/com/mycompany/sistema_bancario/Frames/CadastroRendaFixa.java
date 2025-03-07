package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.sistema_bancario.Gerente;

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
 * @author Ian Nakamura Okano Preste
 * @matricula 202335038
 */

public class CadastroRendaFixa extends JFrame {

    private Gerente gerente; // Instância do Gerente
    private JLabel labelTitulo; // Declaração do labelTitulo
    private JLabel labelNomeOpcao;
    private JTextField campoNomeOpcao;
    private JLabel labelTaxaRendimento;
    private JTextField campoTaxaRendimento;
    private JLabel labelPrazoMinimo;
    private JTextField campoPrazoMinimo;
    private JLabel labelPrazoMaximo;
    private JTextField campoPrazoMaximo;
    private JButton botaoRegistrar;
    private JButton botaoSair;

    public CadastroRendaFixa(Gerente gerente) {
        this.gerente = gerente; // Recebe o gerente como parâmetro

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
                        // Chama o método do Gerente para cadastrar a opção de Renda Fixa
                        gerente.cadastrarRendaFixa(nomeOpcao, taxa, minimo, maximo);

                        JOptionPane.showMessageDialog(null,
                                "Opção de Renda Fixa " + nomeOpcao + " registrada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Caso os valores inseridos não sejam numéricos
                    JOptionPane.showMessageDialog(null,
                            "Taxa de rendimento, prazo mínimo e máximo devem ser valores numéricos válidos.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
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
                GerenteInterface gerenteInterface = new GerenteInterface(gerente);
                gerenteInterface.setVisible(true);
            }
        });
    }

}