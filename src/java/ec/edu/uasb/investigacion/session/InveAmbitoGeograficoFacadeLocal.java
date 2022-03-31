/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveAmbitoGeografico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveAmbitoGeograficoFacadeLocal {

    void create(InveAmbitoGeografico inveAmbitoGeografico);

    void edit(InveAmbitoGeografico inveAmbitoGeografico);

    void remove(InveAmbitoGeografico inveAmbitoGeografico);

    InveAmbitoGeografico find(Object id);

    List<InveAmbitoGeografico> findAll();

    List<InveAmbitoGeografico> findRange(int[] range);

    int count();
    
}
