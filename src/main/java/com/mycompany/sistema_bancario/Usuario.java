package com.mycompany.sistema_bancario;

import java.util.InputMismatchException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

/**
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 */
/**
 * @author Rômulo Amaral
 * @matricula 202335015
 */

@XmlRootElement
@XmlSeeAlso({Cliente.class, Caixa.class, Gerente.class})
public class Usuario {

    private String nome;
    private String cpf;
    private String senha;
    private String email;
    private String tipo; // "cliente", "caixa" ou "gerente"

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String senha, String email, String tipo) {
        if (validaNome(nome)) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome inválido. O nome deve conter apenas letras e ter pelo menos duas letras.");
        }

        if (validaCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }

        if (validaSenha(senha)) {
            this.senha = senha;
        } else {
            throw new IllegalArgumentException("Senha inválida. A senha não pode estar vazia ou conter apenas espaços.");
        }

        if (validaEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email inválido");
        }

        this.tipo = tipo;
    }

    @XmlElement
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (validaNome(nome)) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome inválido. O nome deve conter apenas letras e ter pelo menos duas letras.");
        }
    }

    @XmlElement
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (validaCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    @XmlElement
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (validaSenha(senha)) {
            this.senha = senha;
        } else {
            throw new IllegalArgumentException("Senha inválida. A senha não pode estar vazia ou conter apenas espaços.");
        }
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validaEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email inválido");
        }
    }

    @XmlElement
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo.equals("cliente") || tipo.equals("caixa") || tipo.equals("gerente")) {
            this.tipo = tipo;
        } else {
            throw new IllegalArgumentException("Tipo de usuário inválido.");
        }
    }

    private boolean validaCPF(String CPF) {
        CPF = CPF.replace(".", "").replace("-", "");

        if (CPF.length() != 11 || CPF.matches("(\\d)\\1{10}")) {
            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));
        } catch (InputMismatchException e) {
            return false;
        }
    }

    public boolean verificaSenha(String senhaInserida) {
        return this.senha.equals(senhaInserida);
    }

    private boolean validaNome(String nome) {
        return nome.matches("[a-zA-Z\\s]{2,}");
    }

    private boolean validaSenha(String senha) {
        return senha != null && !senha.trim().isEmpty();
    }

    private boolean validaEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }
}
