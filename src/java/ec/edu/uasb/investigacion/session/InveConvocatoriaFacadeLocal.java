/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveTipoConvocatoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveConvocatoriaFacadeLocal {

    void create(InveConvocatoria inveConvocatoria);

    void edit(InveConvocatoria inveConvocatoria);

    void remove(InveConvocatoria inveConvocatoria);

    InveConvocatoria find(Object id);

    List<InveConvocatoria> findAll();

    List<InveConvocatoria> findRange(int[] range);

    int count();

    public void create(InveConvocatoria entity, Short[] selectedGrupos);

    public void edit(InveConvocatoria entity, Short[] selectedGrupos);

    public List<InveConvocatoria> findBy(Short anio);
     
   public InveConvocatoria findByGrupo(Short grupo);
}
