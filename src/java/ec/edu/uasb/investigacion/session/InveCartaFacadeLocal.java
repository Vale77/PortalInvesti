/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveCarta;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveCartaFacadeLocal {

    void create(InveCarta inveCarta);

    void edit(InveCarta inveCarta);

    void remove(InveCarta inveCarta);

    InveCarta find(Object id);

    List<InveCarta> findAll();

    List<InveCarta> findRange(int[] range);

    int count();

    public List<InveCarta> findBy(Short anio, Short area);

    public List<InveCarta> findBy(Short anio);
    
    public List<InveCarta> findLectorBy(InveConvocatoria convoc);
}
