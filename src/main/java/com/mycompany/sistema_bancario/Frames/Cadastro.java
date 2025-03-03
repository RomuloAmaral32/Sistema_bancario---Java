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

public class Cadastro extends JFrame {

    private JLabel labelTitulo, labelNome, labelCpf, labelSenha, labelPerfil;
    private JTextField campoNome, campoCpf;
    private JPasswordField campoSenha;
    private JComboBox<String> comboBoxPerfil;
    private JButton botaoCadastrar, botaoCancelar;

    public Cadastro() {
        // Configurações da janela
        setTitle("Tela de Cadastro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        labelTitulo = new JLabel("Cadastro de Usuário", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelNome = new JLabel("Nome:");
        campoNome = new JTextField();

        labelCpf = new JLabel("CPF:");
        campoCpf = new JTextField();

        labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();

        labelPerfil = new JLabel("Perfil:");
        comboBoxPerfil = new JComboBox<>(new String[] { "Cliente", "Caixa", "Gerente" });

        botaoCadastrar = new JButton("Cadastrar");
        botaoCancelar = new JButton("Cancelar");

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 linhas, 2 colunas, espaçamento de 10px

        // Adicionando componentes ao painel
        painel.add(labelNome);
        painel.add(campoNome);
        painel.add(labelCpf);
        painel.add(campoCpf);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(labelPerfil);
        painel.add(comboBoxPerfil);
        painel.add(new JLabel()); // Campo vazio para espaçamento
        painel.add(botaoCadastrar);
        painel.add(new JLabel()); // Campo vazio para espaçamento
        painel.add(botaoCancelar);

        // Adicionando o título e o painel à janela
        add(labelTitulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // Ação do botão Cadastrar
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String cpf = campoCpf.getText();
                char[] senhaInformada = campoSenha.getPassword();
                String senha = new String(senhaInformada); // Convertendo para String
                String perfil = (String) comboBoxPerfil.getSelectedItem();

                if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Aqui você pode adicionar a lógica para salvar o usuário
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!\n" +
                            "Nome: " + nome + "\n" +
                            "CPF: " + cpf + "\n" +
                            "Perfil: " + perfil);
                }
            }
        });

        // Ação do botão Cancelar
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoNome.setText("");
                campoCpf.setText("");
                campoSenha.setText("");
                comboBoxPerfil.setSelectedIndex(0);
            }
        });
    }

    public static void main(String[] args) {
        // Criando e exibindo a tela de Cadastro
        Cadastro cadastro = new Cadastro();
        cadastro.setVisible(true);
    }
}
