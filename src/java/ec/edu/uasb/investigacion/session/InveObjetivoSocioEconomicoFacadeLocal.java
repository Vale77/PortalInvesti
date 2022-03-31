/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveObjetivoSocioEconomico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveObjetivoSocioEconomicoFacadeLocal {

    void create(InveObjetivoSocioEconomico inveObjetivoSocioEconomico);

    void edit(InveObjetivoSocioEconomico inveObjetivoSocioEconomico);

    void remove(InveObjetivoSocioEconomico inveObjetivoSocioEconomico);

    InveObjetivoSocioEconomico find(Object id);

    List<InveObjetivoSocioEconomico> findAll();

    List<InveObjetivoSocioEconomico> findRange(int[] range);

    int count();
    
}
