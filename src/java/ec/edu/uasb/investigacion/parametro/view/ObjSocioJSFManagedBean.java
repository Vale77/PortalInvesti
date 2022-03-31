/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.parametro.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.investigacion.entities.InveObjetivoSocioEconomico;
import ec.edu.uasb.investigacion.session.InveObjetivoSocioEconomicoFacadeLocal;
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
@Named(value = "objSocioBean")
@ViewScoped
public class ObjSocioJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveObjetivoSocioEconomicoFacadeLocal inveObjetivoSocioEconomicoFacade;

    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;

    private List<InveObjetivoSocioEconomico> listaOsoe = new ArrayList<>();
    private InveObjetivoSocioEconomico inveOsoeEdit;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public InveObjetivoSocioEconomico getInveOsoeEdit() {
        return inveOsoeEdit;
    }

    public void setInveOsoeEdit(InveObjetivoSocioEconomico inveOsoeEdit) {
        this.inveOsoeEdit = inveOsoeEdit;
    }

    public List<InveObjetivoSocioEconomico> getListaOsoe() {
        return listaOsoe;
    }

//</editor-fold>
    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public ObjSocioJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/parametros/editarObjetivoSocEco.xhtml");
        super.setTituloModalEdicion("Objetivo Socio-Económico");
        super.getListaWidgetPantallas().add("widgetDataTableOsoe");
        super.setWidthEditDisplay("825");
    }

    @Override
    public void init() {
        listaOsoe = inveObjetivoSocioEconomicoFacade.findAll();
        super.init();
    }

    @Override
    public void reset() {
        inveOsoeEdit = new InveObjetivoSocioEconomico();
        super.reset();
    }

    @Override
    public String opcionItem(String op) {
        if (op.equals("actualizar")) {
            try {
                inveOsoeEdit = ((InveObjetivoSocioEconomico) this.getSelectedRow()).clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.opcionItem(op);
    }

    @Override
    public void insert() {
        inveObjetivoSocioEconomicoFacade.create(inveOsoeEdit);
    }

    @Override
    public boolean preDelete() {
        boolean check = true;
        String entidad = null;
        System.out.println("preDelete " + inveOsoeEdit.getInveProyectoCollection());
        List temp = inveProyectoFacade.getProyectoBy(inveOsoeEdit);
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
        inveObjetivoSocioEconomicoFacade.remove((InveObjetivoSocioEconomico) this.getSelectedRow());
    }

    @Override
    public void update() {
        inveObjetivoSocioEconomicoFacade.edit(inveOsoeEdit);
    }

}
