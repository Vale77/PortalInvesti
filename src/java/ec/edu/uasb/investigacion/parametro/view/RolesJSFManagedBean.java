/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.parametro.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.investigacion.session.InveEstadoValoracionFacadeLocal;
import ec.edu.uasb.investigacion.session.InveRecursoHumanoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveRolFacadeLocal;
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
@Named(value = "rolesBean")
@ViewScoped
public class RolesJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveRecursoHumanoFacadeLocal inveRecursoHumanoFacade;

    @EJB
    private InveEstadoValoracionFacadeLocal inveEstadoValoracionFacade;

    @EJB
    private InveRolFacadeLocal inveRolFacade;

    private List<InveRol> listaInveRoles = new ArrayList<>();
    private InveRol inveRolEdit;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public InveRol getInveRolEdit() {
        return inveRolEdit;
    }

    public void setInveRolEdit(InveRol inveRolEdit) {
        this.inveRolEdit = inveRolEdit;
    }

    public List<InveRol> getListaInveRoles() {
        return listaInveRoles;
    }
//</editor-fold>

    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public RolesJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/parametros/editarRol.xhtml");
        super.setTituloModalEdicion("Rol de investigación");
        super.getListaWidgetPantallas().add("widgetDataTableRol");
        super.setWidthEditDisplay("800");
    }

    @Override
    public void init() {
        listaInveRoles = inveRolFacade.findAll();
        super.init();
    }

    @Override
    public void reset() {
        inveRolEdit = new InveRol();
        super.reset();
    }

    @Override
    public String opcionItem(String op) {
        if (op.equals("actualizar")) {
            try {
                inveRolEdit = ((InveRol) this.getSelectedRow()).clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.opcionItem(op);
    }

    @Override
    public void insert() {
        inveRolFacade.create(inveRolEdit);
    }

    @Override
    public boolean preDelete() {
        boolean check = true;
        List temp = null;
        String entidad = null;
        temp = inveEstadoValoracionFacade.getEstadoBy(inveRolEdit);
        if (!temp.isEmpty()) {
            entidad = "en Estados de valoración";
            check = false;
        } else {
            temp = inveRecursoHumanoFacade.getEstadoBy(inveRolEdit);
            entidad = "en Rol de Asistentes";
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
        inveRolFacade.remove((InveRol) this.getSelectedRow());
    }

    @Override
    public void update() {
        inveRolFacade.edit(inveRolEdit);
    }

}
