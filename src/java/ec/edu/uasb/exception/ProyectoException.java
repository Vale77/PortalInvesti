/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.exception;

/**
 *
 * @author victor.barba
 */
public class ProyectoException extends Exception {

    /**
     * Creates a new instance of <code>VinculacionException</code> without
     * detail message.
     */
    public ProyectoException() {
    }

    /**
     * Constructs an instance of <code>VinculacionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ProyectoException(String msg) {
        super(msg);
    }
}
