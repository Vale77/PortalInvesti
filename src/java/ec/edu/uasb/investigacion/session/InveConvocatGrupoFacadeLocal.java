/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveConvocatGrupo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveConvocatGrupoFacadeLocal {

    void create(InveConvocatGrupo inveConvocatGrupo);

    void edit(InveConvocatGrupo inveConvocatGrupo);

    void remove(InveConvocatGrupo inveConvocatGrupo);

    InveConvocatGrupo find(Object id);

    List<InveConvocatGrupo> findAll();

    List<InveConvocatGrupo> findRange(int[] range);

    int count();
    
}
