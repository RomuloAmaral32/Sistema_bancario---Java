package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RendaVariavel extends JFrame {

    private JLabel labelTitulo, labelValor, labelSenha, labelInvestimento;
    private JTextField campoValor;
    private JPasswordField campoSenha; // Usando JPasswordField para senha
    private JButton botaoConfirmar, botaoSair;
    private JComboBox<String> comboBoxInvestimentos;
    private final String senhaCorreta = "1234";  // Senha fictícia para validação

    public RendaVariavel() {
        // Configurações da janela
        setTitle("Investimentos em Renda Variável");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        labelTitulo = new JLabel("Investimentos em Renda Variável", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelInvestimento = new JLabel("Escolha o investimento:");
        comboBoxInvestimentos = new JComboBox<>(new String[] {
            "Ações", "Fundos Imobiliários", "ETFs", "Derivativos", "BDRs"
        });

        labelValor = new JLabel("Valor a investir:");
        campoValor = new JTextField();

        labelSenha = new JLabel("Senha de confirmação:");
        campoSenha = new JPasswordField(); // Usando JPasswordField

        botaoConfirmar = new JButton("Confirmar Investimento");
        botaoSair = new JButton("Sair");

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 linhas, 2 colunas, espaço entre componentes

        // Adicionando componentes ao painel
        painel.add(labelInvestimento);
        painel.add(comboBoxInvestimentos);
        painel.add(labelValor);
        painel.add(campoValor);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(new JLabel()); // Campo vazio
        painel.add(botaoConfirmar);
        painel.add(new JLabel()); // Campo vazio
        painel.add(botaoSair);

        // Adicionando título e painel à janela
        add(labelTitulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // Ação do botão Confirmar Investimento
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de validação de senha
                char[] senhaInformada = campoSenha.getPassword(); // Usando getPassword() para pegar a senha
                String senhaStr = new String(senhaInformada); // Convertendo para String
                if (senhaStr.equals(senhaCorreta)) {
                    String investimentoSelecionado = (String) comboBoxInvestimentos.getSelectedItem();
                    String valorInvestido = campoValor.getText();
                    // Aqui você pode adicionar lógica para registrar o investimento
                    JOptionPane.showMessageDialog(null, "Investimento de R$ " + valorInvestido + " em " + investimentoSelecionado + " realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta! O investimento não foi realizado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Sair
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela de Renda Variável
                dispose();
                ClienteInterface clienteInterface = new ClienteInterface();
                clienteInterface.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        // Criando e exibindo a tela de Renda Variável
        RendaVariavel rendaVariavel = new RendaVariavel();
        rendaVariavel.setVisible(true);
    }
}
