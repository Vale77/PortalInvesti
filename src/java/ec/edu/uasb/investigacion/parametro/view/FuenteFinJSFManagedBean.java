/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.parametro.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.investigacion.entities.InveFuenteFinan;
import ec.edu.uasb.investigacion.session.InveFuenteFinanFacadeLocal;
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
@Named(value = "fuenteFinBean")
@ViewScoped
public class FuenteFinJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveFuenteFinanFacadeLocal inveFuenteFinanFacade;

    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;

    private List<InveFuenteFinan> listaFuenteFinan = new ArrayList<>();
    private InveFuenteFinan inveFuenteFinanEdit;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public void setInveFuenteFinanEdit(InveFuenteFinan inveFuenteFinanEdit) {
        this.inveFuenteFinanEdit = inveFuenteFinanEdit;
    }

    public InveFuenteFinan getInveFuenteFinanEdit() {
        return inveFuenteFinanEdit;
    }

    public List<InveFuenteFinan> getListaFuenteFinan() {
        return listaFuenteFinan;
    }

//</editor-fold>
    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public FuenteFinJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/parametros/editarFuenteFin.xhtml");
        super.setTituloModalEdicion("Fuente de financiamiento");
        super.getListaWidgetPantallas().add("widgetDataTableFuente");
        super.setWidthEditDisplay("800");
    }

    @Override
    public void init() {
        listaFuenteFinan = inveFuenteFinanFacade.findAll();
        super.init();
    }

    @Override
    public void reset() {
        inveFuenteFinanEdit = new InveFuenteFinan();
        super.reset();
    }

    @Override
    public String opcionItem(String op) {
        if (op.equals("actualizar")) {
            try {
                inveFuenteFinanEdit = ((InveFuenteFinan) this.getSelectedRow()).clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.opcionItem(op);
    }

    @Override
    public void insert() {
        inveFuenteFinanFacade.create(inveFuenteFinanEdit);
    }

    @Override
    public boolean preDelete() {
        boolean check = true;
        String entidad = null;
        List temp = inveProyectoFacade.getProyectoBy(inveFuenteFinanEdit);
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
        inveFuenteFinanFacade.remove((InveFuenteFinan) this.getSelectedRow());
    }

    @Override
    public void update() {
        inveFuenteFinanFacade.edit(inveFuenteFinanEdit);
    }

}
