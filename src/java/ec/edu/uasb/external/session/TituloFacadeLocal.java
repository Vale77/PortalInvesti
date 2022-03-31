/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.session;

import ec.edu.uasb.external.entities.Titulo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface TituloFacadeLocal {

    void create(Titulo titulo);

    void edit(Titulo titulo);

    void remove(Titulo titulo);

    Titulo find(Object id);

    List<Titulo> findAll();

    List<Titulo> findRange(int[] range);

    int count();

    public Titulo find(Object id, String categoria);
}
