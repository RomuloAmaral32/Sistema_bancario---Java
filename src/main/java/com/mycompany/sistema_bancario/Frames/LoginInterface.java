package com.mycompany.sistema_bancario.Frames;

import com.mycompany.sistema_bancario.Caixa;
import com.mycompany.sistema_bancario.Cliente;
import com.mycompany.sistema_bancario.Gerente;
import com.mycompany.sistema_bancario.LoginService;
import com.mycompany.sistema_bancario.Usuario;
import com.mycompany.sistema_bancario.UsuarioService;

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

public class LoginInterface extends JFrame {

    private JLabel labelCPF, labelSenha;
    private JTextField campoCPF;
    private JPasswordField campoSenha;
    private JButton botaoLogin, botaoCancelar;
    private LoginService loginService; // Instância de LoginService para verificar login
    private Gerente gerente;
    private Caixa caixa;
    private Cliente cliente;

    private void converte(Usuario usuario){
        UsuarioService usuarioService = new UsuarioService("src/file/java/com/mycompany/sistema_bancario/usuarios.json");
        if(usuario.getTipo().equals("gerente")){   
            gerente = new Gerente(
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getTipo(),
                usuario.getCep(),
                usuario.getNumero(),
                "1",
                1000000,
                usuarioService);
        } else if(usuario.getTipo().equals("caixa")){       // String numero,
            //UsuarioService usuarioService = new UsuarioService("src/file/java/com/mycompany/sistema_bancario/usuarios.json");
            Caixa caixa = new Caixa(
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getTipo(),
                usuario.getCep(),
                usuario.getNumero(),
                "1",
                usuarioService);
        } else if(usuario.getTipo().equals("cliente")){   // String cep, String numero,
            cliente = new Cliente(
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getTipo(),
                usuario.getCep(),
                usuario.getNumero(),
                0.0
                );
        }
    }
    public LoginInterface() {
        // Inicializa o LoginService com o caminho do arquivo JSON
        loginService = new LoginService("src/file/java/com/mycompany/sistema_bancario/usuarios.json");

        setTitle("Tela de Login");
        setSize(550, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  

        labelCPF = new JLabel("CPF:");
        labelSenha = new JLabel("Senha:");
        campoCPF = new JTextField(15);
        campoSenha = new JPasswordField(15);

        botaoLogin = new JButton("Login");
        botaoCancelar = new JButton("Cadastrar");

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
                    // Verifica o login através do LoginService
                    Usuario usuario = loginService.verificarLogin(cpf, senha);
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido! Bem-vindo(a), " + usuario.getNome());
                    UsuarioService usuarioService = new UsuarioService("src/file/java/com/mycompany/sistema_bancario/usuarios.json");
                
                    if (usuario.getTipo().equalsIgnoreCase("cliente")) {
                        // Fazer o cast de Usuario para Cliente
                        Cliente cliente = (Cliente) usuario;
                        
                        // Passa o cliente correto para a interface de cliente
                        ClienteInterface telacliente = new ClienteInterface(cliente);
                        telacliente.setVisible(true);
                        dispose(); 
                    
                    } else if( usuario.getTipo().equals("caixa") ){
                        Caixa caixa = new Caixa(
                        "Caixa",
                        "11357820674",
                        "senhaCaixa",
                        "caixa@email.com",
                        "caixa",
                        "36000000",
                        "123",
                        "001",
                        usuarioService);
                        dispose(); 
                        CaixaInterface telacaixa = new CaixaInterface(caixa);
                        telacaixa.setVisible(true);
                    } else if( usuario.getTipo().equals("gerente") ){
                        dispose(); 
                        GerenteInterface telagerente = new GerenteInterface(gerente);
                        telagerente.setVisible(true);
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
                dispose();
                Cadastro cadastrar = new Cadastro();
                cadastrar.setVisible(true);
            }
        });
    }
}
