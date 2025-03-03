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

public class LoginInterface extends JFrame {

    private JLabel labelCPF, labelSenha, labelPerfil;
    private JTextField campoCPF;
    private JPasswordField campoSenha;
    private JButton botaoLogin, botaoCancelar;

    public LoginInterface() {
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

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = campoCPF.getText();
                String senha = new String(campoSenha.getPassword());

                if (cpf.equals("123.456.789-00") && senha.equals("1234")) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido");
                }else {
                    JOptionPane.showMessageDialog(null, "CPF, senha ou perfil incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoCPF.setText("");
                campoSenha.setText("");
                dispose(); // Fecha a janela atual
                Cadastro cadastrar = new Cadastro(); // Volta para a tela de login
                cadastrar.setVisible(true);
            }
        });
    }


    public static void main(String[] args) {
        LoginInterface telaLogin = new LoginInterface();
        telaLogin.setVisible(true);
    }
}
