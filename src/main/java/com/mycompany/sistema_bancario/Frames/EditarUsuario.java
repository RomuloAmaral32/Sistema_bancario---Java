package com.mycompany.sistema_bancario.Frames;
import javax.swing.*;

import com.mycompany.sistema_bancario.Gerente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarUsuario extends JFrame {

    private JTextField nomeField, emailField, senhaField, cepField, numeroField;
    private JButton salvarButton, cancelarButton;

    public EditarUsuario(Gerente gerente) {
        // Configuração da janela
        setTitle("Editar Usuário");
        setSize(400, 350); // Ajustado o tamanho para acomodar os botões
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação dos componentes
        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField(20);
        nomeField.setEditable(true); // Agora pode ser editado
        nomeField.setBackground(Color.WHITE); // Definindo o fundo branco

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        emailField.setEditable(true); // Agora pode ser editado
        emailField.setBackground(Color.WHITE); // Definindo o fundo branco

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField(20);
        senhaField.setBackground(Color.WHITE); // Definindo o fundo branco

        JLabel cepLabel = new JLabel("CEP:");
        cepField = new JTextField(10);
        cepField.setBackground(Color.WHITE); // Definindo o fundo branco

        JLabel numeroLabel = new JLabel("Número:");
        numeroField = new JTextField(5);
        numeroField.setBackground(Color.WHITE); // Definindo o fundo branco

        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");

        // Painel para organização dos componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adicionando os campos de entrada e labels
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nomeLabel, gbc);
        gbc.gridx = 1;
        panel.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(senhaLabel, gbc);
        gbc.gridx = 1;
        panel.add(senhaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(cepLabel, gbc);
        gbc.gridx = 1;
        panel.add(cepField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(numeroLabel, gbc);
        gbc.gridx = 1;
        panel.add(numeroField, gbc);

        // Adicionando os botões e alinhando à direita
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(salvarButton, gbc);

        gbc.gridx = 1;
        panel.add(cancelarButton, gbc);

        // Alinhando os botões à direita no painel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);
        
        // Ajustando o painel de botões para que ocupe toda a largura
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; // Ambos os botões ocupam uma linha inteira
        panel.add(buttonPanel, gbc);

        // Ações dos botões
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAlteracoes(gerente);
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarEdicao(gerente);
            }
        });

        // Adicionando o painel à janela
        add(panel);
    }

    private void salvarAlteracoes(Gerente gerente) {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = new String(((JPasswordField) senhaField).getPassword());
        String cep = cepField.getText();
        String numero = numeroField.getText();

        // Aqui você pode adicionar validações ou salvar as alterações no sistema
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cep.isEmpty() || numero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            // Lógica para salvar as alterações
            JOptionPane.showMessageDialog(this, "Alterações salvas com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            GerenteInterface gerenteinterface = new GerenteInterface(gerente);
            gerenteinterface.setVisible(true);
            dispose(); // Fecha a janela após salvar
        }
    }

    private void cancelarEdicao(Gerente gerente) {
        int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja cancelar?", "Confirmar cancelamento", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            nomeField.setText("");
            emailField.setText("");
            senhaField.setText("");
            cepField.setText("");
            numeroField.setText("");
            GerenteInterface gerenteinterface = new GerenteInterface(gerente);
            gerenteinterface.setVisible(true);
            dispose(); 
        }
    }
}

