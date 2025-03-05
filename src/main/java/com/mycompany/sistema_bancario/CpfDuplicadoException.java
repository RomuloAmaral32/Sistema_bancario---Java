/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.sistema_bancario;

/**
 *
 * @author Darlan
 */
public class CpfDuplicadoException extends Exception {

    /**
     * Creates a new instance of <code>CpfDuplicadoException</code> without
     * detail message.
     */
    public CpfDuplicadoException() {
    }

    /**
     * Constructs an instance of <code>CpfDuplicadoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CpfDuplicadoException(String msg) {
        super(msg);
    }
}
