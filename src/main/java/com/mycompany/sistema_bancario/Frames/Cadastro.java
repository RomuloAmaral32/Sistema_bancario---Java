package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.sistema_bancario.Usuario;
import com.mycompany.sistema_bancario.UsuarioService;

/**
 *
 * @author Rômulo Amaral
 */
public class Cadastro extends JFrame {

    private JLabel labelTitulo, labelNome, labelCpf, labelEmail, labelSenha, labelPerfil;
    private JTextField campoNome, campoCpf, campoEmail;
    private JPasswordField campoSenha;
    private JComboBox<String> comboBoxPerfil;
    private JButton botaoCadastrar, botaoCancelar;
    private UsuarioService usuarioService;

    public Cadastro() {
        usuarioService = new UsuarioService("usuarios.json");

        // Configurações da janela
        setTitle("Tela de Cadastro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        labelTitulo = new JLabel("Cadastro de Usuário", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelNome = new JLabel("Nome:");
        campoNome = new JTextField();

        labelCpf = new JLabel("CPF:");
        campoCpf = new JTextField();

        labelEmail = new JLabel("Email:");
        campoEmail = new JTextField();

        labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();

        labelPerfil = new JLabel("Perfil:");
        comboBoxPerfil = new JComboBox<>(new String[] { "Cliente", "Caixa", "Gerente" });

        botaoCadastrar = new JButton("Cadastrar");
        botaoCancelar = new JButton("Cancelar");

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(7, 2, 10, 10));

        painel.add(labelNome);
        painel.add(campoNome);
        painel.add(labelCpf);
        painel.add(campoCpf);
        painel.add(labelEmail);
        painel.add(campoEmail);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(labelPerfil);
        painel.add(comboBoxPerfil);
        painel.add(new JLabel());
        painel.add(botaoCadastrar);
        painel.add(new JLabel());
        painel.add(botaoCancelar);

        add(labelTitulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String cpf = campoCpf.getText();
                String email = campoEmail.getText();
                char[] senhaInformada = campoSenha.getPassword();
                String senha = new String(senhaInformada);
                String perfil = (String) comboBoxPerfil.getSelectedItem();

                if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || perfil == null) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        // Criar o novo usuário e salvar no backend
                        Usuario novoUsuario = new Usuario(nome, cpf, senha, email, perfil.toLowerCase(), "00000000", "00");
                        usuarioService.adicionarUsuario(novoUsuario);

                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Ação do botão Cancelar
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoNome.setText("");
                campoCpf.setText("");
                campoEmail.setText("");
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
