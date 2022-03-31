/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinClaseDocumento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface PrinClaseDocumentoFacadeLocal {

    void create(PrinClaseDocumento prinClaseDocumento);

    void edit(PrinClaseDocumento prinClaseDocumento);

    void remove(PrinClaseDocumento prinClaseDocumento);

    PrinClaseDocumento find(Object id);

    List<PrinClaseDocumento> findAll();

    List<PrinClaseDocumento> findRange(int[] range);

    int count();

    public List<PrinClaseDocumento> findBy(String modulo);

    public List<PrinClaseDocumento> findClaseDocs(List<Short> clases);
}
