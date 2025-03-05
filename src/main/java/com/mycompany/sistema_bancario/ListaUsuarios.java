package com.mycompany.sistema_bancario;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

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