package com.mycompany.sistema_bancario.Frames;

import com.mycompany.sistema_bancario.Usuario;
import com.mycompany.sistema_bancario.Cliente;
import com.mycompany.sistema_bancario.Caixa;
import com.mycompany.sistema_bancario.Gerente;
import com.mycompany.sistema_bancario.UsuarioService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterface extends JFrame {

    private JLabel labelCPF, labelSenha;
    private JTextField campoCPF;
    private JPasswordField campoSenha;
    private JButton botaoLogin, botaoCancelar;
    private UsuarioService usuarioService;

    public LoginInterface() {
        // Inicializa o UsuarioService com o caminho do arquivo JSON
        usuarioService = new UsuarioService("usuarios.json");

        setTitle("Tela de Login");
        setSize(550, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        labelCPF = new JLabel("CPF:");
        labelSenha = new JLabel("Senha:");
        campoCPF = new JTextField(15);
        campoSenha = new JPasswordField(15);

        botaoLogin = new JButton("Login");
        botaoCancelar = new JButton("Cancelar");

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 2));
        painel.add(labelCPF);
        painel.add(campoCPF);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(botaoLogin);
        painel.add(botaoCancelar);

        add(painel);

        // Ação ao clicar no botão de login
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = campoCPF.getText();
                String senha = new String(campoSenha.getPassword());

                try {
                    // Valida o login (CPF e senha)
                    Usuario usuario = usuarioService.validarLogin(cpf, senha);

                    JOptionPane.showMessageDialog(null, "Login bem-sucedido");

                    // Redireciona com base no perfil do usuário
                    if (usuario instanceof Cliente) {
                        abrirTelaCliente();
                    } else if (usuario instanceof Caixa) {
                        abrirTelaCaixa();
                    } else if (usuario instanceof Gerente) {
                        abrirTelaGerente();
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação ao clicar no botão de cancelar
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoCPF.setText("");
                campoSenha.setText("");
                dispose(); // Fecha a janela atual
                // Volta para a tela de cadastro
                Cadastro cadastrar = new Cadastro();
                cadastrar.setVisible(true);
            }
        });
    }

    // Métodos para abrir as telas baseadas no perfil
    private void abrirTelaCliente() {
        JOptionPane.showMessageDialog(this, "Redirecionando para a tela do Cliente...");
        // Aqui você pode abrir a interface específica do Cliente
    }

    private void abrirTelaCaixa() {
        JOptionPane.showMessageDialog(this, "Redirecionando para a tela do Caixa...");
        // Aqui você pode abrir a interface específica do Caixa
    }

    private void abrirTelaGerente() {
        JOptionPane.showMessageDialog(this, "Redirecionando para a tela do Gerente...");
        // Aqui você pode abrir a interface específica do Gerente
    }

    public static void main(String[] args) {
        LoginInterface telaLogin = new LoginInterface();
        telaLogin.setVisible(true);
    }
}
