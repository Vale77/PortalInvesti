/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.session;

import ec.edu.uasb.external.entities.AnioAcademico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface AnioAcademicoFacadeLocal {

    AnioAcademico find(Object id);

    List<AnioAcademico> findAll();

    List<AnioAcademico> findRange(int[] range);

    int count();

    public List<AnioAcademico> getListaAnios();

}
