/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.investigacion.entities.InveRubro;
import ec.edu.uasb.investigacion.entities.InveRubroGrupo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveRubroGrupoFacadeLocal {

    void create(InveRubroGrupo inveRubroGrupo);

    void edit(InveRubroGrupo inveRubroGrupo);

    void remove(InveRubroGrupo inveRubroGrupo);

    InveRubroGrupo find(Object id);

    List<InveRubroGrupo> findAll();

    List<InveRubroGrupo> findRange(int[] range);

    int count();

    public List<InveRubro> getRubrosXGrupo(Short grpCodigo);

    public List<InveRubro> getRubrosXGrupoXRegistrables(Short grpCodigo);

    
}
