/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.parametro.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.investigacion.entities.InveGrupo;
import ec.edu.uasb.investigacion.session.InveGrupoFacadeLocal;
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
@Named(value = "gruposBean")
@ViewScoped
public class GruposJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;

    @EJB
    private InveGrupoFacadeLocal inveGrupoFacade;

    private List<InveGrupo> listaGrupos = new ArrayList<>();
    private InveGrupo inveGrupoEdit;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public List<InveGrupo> getListaGrupos() {
        return listaGrupos;
    }

    public InveGrupo getInveGrupoEdit() {
        return inveGrupoEdit;
    }

    public void setInveGrupoEdit(InveGrupo inveGrupoEdit) {
        this.inveGrupoEdit = inveGrupoEdit;
    }

//</editor-fold>
    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public GruposJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/parametros/editarGrupo.xhtml");
        super.setTituloModalEdicion("Grupo de enfoque para convocatoria");
        super.getListaWidgetPantallas().add("widgetDataTableGrupo");
        super.setWidthEditDisplay("800");
    }

    @Override
    public void init() {
        listaGrupos = inveGrupoFacade.findAll();
        super.init();
    }

    @Override
    public void reset() {
        inveGrupoEdit = new InveGrupo();
        super.reset();
    }

    @Override
    public String opcionItem(String op) {
        if (op.equals("actualizar")) {
            try {
                inveGrupoEdit = ((InveGrupo) this.getSelectedRow()).clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            inveGrupoEdit = new InveGrupo();

        }
        return super.opcionItem(op);
    }

    @Override
    public void insert() {
        inveGrupoFacade.create(inveGrupoEdit);
    }

    @Override
    public boolean preDelete() {
        boolean check = true;
        String entidad = null;
        List temp = inveProyectoFacade.getProyectoBy(inveGrupoEdit);
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
        inveGrupoFacade.remove((InveGrupo) this.getSelectedRow());
    }

    @Override
    public void update() {
        inveGrupoFacade.edit(inveGrupoEdit);
    }

}
