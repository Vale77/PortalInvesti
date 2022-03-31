/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.parametro.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.investigacion.entities.InveEstadoValoracion;
import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.investigacion.session.InveEstadoValoracionFacadeLocal;
import ec.edu.uasb.investigacion.session.InveRolFacadeLocal;
import ec.edu.uasb.investigacion.session.InveValoracionFacadeLocal;
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
@Named(value = "estadoValBean")
@ViewScoped
public class EstadoValJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveRolFacadeLocal inveRolFacade;

    @EJB
    private InveValoracionFacadeLocal inveValoracionFacade;

    @EJB
    private InveEstadoValoracionFacadeLocal inveEstadoValoracionFacade;

    private List<InveEstadoValoracion> listaEstadosVal = new ArrayList<>();
    private List<InveRol> listaRoles = new ArrayList<>();
    private InveEstadoValoracion inveEstadoValEdit;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public InveEstadoValoracion getInveEstadoValEdit() {
        return inveEstadoValEdit;
    }

    public void setInveEstadoValEdit(InveEstadoValoracion inveEstadoValEdit) {
        this.inveEstadoValEdit = inveEstadoValEdit;
    }

    public List<InveEstadoValoracion> getListaEstadosVal() {
        return listaEstadosVal;
    }

    public List<InveRol> getListaRoles() {
        return listaRoles;
    }

//</editor-fold>
    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public EstadoValJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/parametros/editarEstadoValo.xhtml");
        super.setTituloModalEdicion("Estados de valoración");
        super.getListaWidgetPantallas().add("widgetDataTableEstados");
        super.setWidthEditDisplay("900");
    }

    @Override
    public void init() {
        listaEstadosVal = inveEstadoValoracionFacade.findAll();
        listaRoles = inveRolFacade.findAll();
        super.init();
    }

    @Override
    public void reset() {
        inveEstadoValEdit = new InveEstadoValoracion();
        super.reset();
    }

    @Override
    public String opcionItem(String op) {
        if (op.equals("actualizar")) {
            try {
                inveEstadoValEdit = ((InveEstadoValoracion) this.getSelectedRow()).clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.opcionItem(op);
    }

    @Override
    public void insert() {
        inveEstadoValoracionFacade.create(inveEstadoValEdit);
    }

    @Override
    public boolean preDelete() {
        boolean check = true;
        List temp = null;
        String entidad = null;
        temp = inveValoracionFacade.getValoracionBy(inveEstadoValEdit);
        if (!temp.isEmpty()) {
            entidad = "en Valoración";
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
        inveEstadoValoracionFacade.remove((InveEstadoValoracion) this.getSelectedRow());
    }

    @Override
    public void update() {
        inveEstadoValoracionFacade.edit(inveEstadoValEdit);
    }

}
