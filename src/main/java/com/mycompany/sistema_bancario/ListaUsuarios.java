package com.mycompany.sistema_bancario;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ListaUsuarios {
    private List<Usuario> usuarios;

    public ListaUsuarios() {
        // Construtor vazio necessário para JAXB
    }

    public ListaUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @XmlElement
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}