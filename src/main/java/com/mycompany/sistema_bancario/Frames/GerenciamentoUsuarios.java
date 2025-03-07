package com.mycompany.sistema_bancario.Frames;
import javax.swing.*;

import com.mycompany.sistema_bancario.Caixa;
import com.mycompany.sistema_bancario.Cliente;
import com.mycompany.sistema_bancario.Gerente;
import com.mycompany.sistema_bancario.JsonHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GerenciamentoUsuarios extends JFrame {

    private JTextField cpfField;
    private JButton editarButton;
    private JButton deletarButton;
    private JButton sairButton;
    private JComboBox<String> tipoUsuarioComboBox;
    @SuppressWarnings("rawtypes")
    private JsonHandler jsonHandler = new JsonHandler("src/file/java/com/mycompany/sistema_bancario/usuarios.json");
    public GerenciamentoUsuarios(Gerente gerente) {
        // Configuração da janela
        setTitle("Gerenciamento de Usuários");
        setSize(400, 300); // Aumentei o tamanho da janela para acomodar a combobox
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação dos componentes
        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField(20);
        editarButton = new JButton("Editar");
        deletarButton = new JButton("Deletar");
        sairButton = new JButton("Sair");

        // Criando a JComboBox para selecionar o tipo de usuário
        JLabel tipoUsuarioLabel = new JLabel("Tipo de Usuário:");
        String[] tiposDeUsuarios = { "Cliente", "Caixa", "Gerente" };
        tipoUsuarioComboBox = new JComboBox<>(tiposDeUsuarios);

        // Painel para organização dos componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adicionando o campo CPF
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(cpfLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(cpfField, gbc);

        // Adicionando o tipo de usuário (combobox)
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(tipoUsuarioLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(tipoUsuarioComboBox, gbc);

        // Adicionando os botões com o mesmo tamanho
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.33;  // Faz o botão ocupar um terço do espaço disponível
        panel.add(editarButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.33;  // Faz o botão ocupar um terço do espaço disponível
        panel.add(deletarButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 0.33;  // Faz o botão ocupar um terço do espaço disponível
        panel.add(sairButton, gbc);

        // Ações dos botões
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario(gerente);
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarUsuario();
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenteInterface gerenteInterface = new GerenteInterface(gerente);
                gerenteInterface.setVisible(true);
                dispose();
            }
        });

        // Adicionando o painel à janela
        add(panel);
    }

    private void editarUsuario(Gerente gerente) {
        String cpf = cpfField.getText();
        String tipoUsuario = (String) tipoUsuarioComboBox.getSelectedItem();

        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            EditarUsuario editusuario = new EditarUsuario(gerente);
            editusuario.setVisible(true);
            dispose();
        }
    }

    @SuppressWarnings("unchecked")
    private void deletarUsuario() {
        String cpf = cpfField.getText();
        String tipoUsuario = (String) tipoUsuarioComboBox.getSelectedItem();

        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                // Chamando o método do JsonHandler para remover o usuário
                System.out.println("aloooooooooooo4343434343");
                if (tipoUsuario.equals("Cliente")) {
                    jsonHandler.removeDataByCpf(cpf, Cliente.class);
                } else if (tipoUsuario.equals("Caixa")) {
                    jsonHandler.removeDataByCpf(cpf, Caixa.class);
                } else if (tipoUsuario.equals("Gerente")) {
                    jsonHandler.removeDataByCpf(cpf, Gerente.class);
                }
                JOptionPane.showMessageDialog(this, tipoUsuario + " com CPF " + cpf + " removido com sucesso.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover o usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}