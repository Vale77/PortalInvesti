/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.parametro.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.investigacion.entities.InveGrupoInvestigacion;
import ec.edu.uasb.investigacion.session.InveGrupoInvestigacionFacadeLocal;
import ec.edu.uasb.investigacion.session.InveProyectoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@Named(value = "grupoInvestBean")
@ViewScoped
public class GrupoInvestJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveGrupoInvestigacionFacadeLocal inveGrupoInvestigacionFacade;

    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;

    private List<InveGrupoInvestigacion> listaGrupoInvestigacion = new ArrayList<>();
    private InveGrupoInvestigacion grupoInvestigacionEdit;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public void setGrupoInvestigacionEdit(InveGrupoInvestigacion grupoInvestigacionEdit) {
        this.grupoInvestigacionEdit = grupoInvestigacionEdit;
    }

    public InveGrupoInvestigacion getGrupoInvestigacionEdit() {
        return grupoInvestigacionEdit;
    }

    public List<InveGrupoInvestigacion> getListaGrupoInvestigacion() {
        return listaGrupoInvestigacion;
    }
//</editor-fold>

    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public GrupoInvestJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/parametros/editarGrupoInvest.xhtml");
        super.setTituloModalEdicion("Grupo de investigación");
        super.getListaWidgetPantallas().add("widgetDataTableGin");
        super.setWidthEditDisplay("850");
    }

    @Override
    public void init() {
        listaGrupoInvestigacion = inveGrupoInvestigacionFacade.findAll();
        super.init();
    }

    @Override
    public void reset() {
        grupoInvestigacionEdit = new InveGrupoInvestigacion();
        super.reset();
    }

    @Override
    public String opcionItem(String op) {
        if (op.equals("actualizar")) {
            try {
                grupoInvestigacionEdit = ((InveGrupoInvestigacion) this.getSelectedRow()).clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.opcionItem(op);
    }

    @Override
    public void insert() {
        inveGrupoInvestigacionFacade.create(grupoInvestigacionEdit);
    }

    @Override
    public boolean preDelete() {
        boolean check = true;
        String entidad = null;
        List temp = inveProyectoFacade.getProyectoBy(grupoInvestigacionEdit);
        if (!temp.isEmpty()) {
            entidad = "en proyectos de investigación";
            check = false;
        }
        if (check == false) {
            this.setIconMensaje("error.png");
            this.setMensaje("Error. No puede eliminar este registro, esta siendo utilizado " + entidad);
            RequestContext.getCurrentInstance().update("dialogMensaje");
            RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
        }
        return check;
    }

    @Override
    public void delete() {
        inveGrupoInvestigacionFacade.remove((InveGrupoInvestigacion) this.getSelectedRow());
    }

    @Override
    public void update() {
        inveGrupoInvestigacionFacade.edit(grupoInvestigacionEdit);
    }

}
