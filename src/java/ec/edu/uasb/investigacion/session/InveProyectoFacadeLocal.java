/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.exception.ProyectoException;
import ec.edu.uasb.investigacion.entities.InveAmbitoGeografico;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveDisciplinaCientifica;
import ec.edu.uasb.investigacion.entities.InveFuenteFinan;
import ec.edu.uasb.investigacion.entities.InveGrupo;
import ec.edu.uasb.investigacion.entities.InveGrupoInvestigacion;
import ec.edu.uasb.investigacion.entities.InveObjetivoSocioEconomico;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveTipoProyecto;
import ec.edu.uasb.principal.entities.PrinDocumento;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface InveProyectoFacadeLocal {

    void create(InveProyecto inveProyecto);

    void edit(InveProyecto inveProyecto);

    void remove(InveProyecto inveProyecto);

    InveProyecto find(Object id);

    List<InveProyecto> findAll();

    List<InveProyecto> findRange(int[] range);

    int count();

    public InveProyecto getProyectoBy(String codigoComite);

    public List<InveProyecto> getProyectoBy(InveGrupo inveGrupo);

    public List<InveProyecto> getProyectoBy(InveAmbitoGeografico ambitoGeografico);

    public List<InveProyecto> getProyectoBy(InveDisciplinaCientifica disciplinaCientifica);

    public List<InveProyecto> getProyectoBy(InveObjetivoSocioEconomico osoe);

    public List<InveProyecto> getProyectoBy(InveGrupoInvestigacion grupoInvestigacion);

    public List<InveProyecto> getProyectoBy(InveConvocatoria inveConvocatoria);

    public List<InveProyecto> getProyectoBy(InveFuenteFinan fuenteFinan);

    public List<InveProyecto> findBy(Short anio, InveConvocatoria convoc, InveTipoProyecto inveTipoProyecto);

    public void create(InveProyecto entity, List<PrinDocumento> listaDocs) throws ProyectoException;

    public void edit(InveProyecto entity, List<PrinDocumento> listaDocs) throws ProyectoException;

    public void edit(InveProyecto entity, InveSeguimiento seguimiento);

    public void remove(InveProyecto entity, List<PrinDocumento> listaDocs);

    public Integer findEstudianteDoctoradoBy(String id);

    public String findDePlantaBy(String id, String contratos, BigDecimal anios);

    public Integer findGraduadoBy(String id, Short nivel);

    public List<InveProyecto> getInvesAprobados(Short anio);

    public InveProyecto findProyectoBy(Long codPersona);
    
    public List<InveProyecto> findConvocatoriaBy(InveConvocatoria convoc, InveTipoProyecto inveTipoProyecto);

    public List<InveProyecto> getConvocatoriaAprobados(InveConvocatoria convoc);
    
    public List<InveProyecto> findAll(InveConvocatoria convocatoria);


}
