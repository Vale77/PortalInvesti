/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.parametro.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.investigacion.entities.InveAmbitoGeografico;
import ec.edu.uasb.investigacion.session.InveAmbitoGeograficoFacadeLocal;
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
@Named(value = "ambitoGeoBean")
@ViewScoped
public class AmbitoGeoJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveAmbitoGeograficoFacadeLocal inveAmbitoGeograficoFacade;

    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;

    private List<InveAmbitoGeografico> listaAmbitos = new ArrayList<>();
    private InveAmbitoGeografico inveAmbitoGeograficoEdit;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public List<InveAmbitoGeografico> getListaAmbitos() {
        return listaAmbitos;
    }

    public InveAmbitoGeografico getInveAmbitoGeograficoEdit() {
        return inveAmbitoGeograficoEdit;
    }

    public void setInveAmbitoGeograficoEdit(InveAmbitoGeografico inveAmbitoGeograficoEdit) {
        this.inveAmbitoGeograficoEdit = inveAmbitoGeograficoEdit;
    }
//</editor-fold>

    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public AmbitoGeoJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/parametros/editarAmbitoGeo.xhtml");
        super.setTituloModalEdicion("Ambito geográfico");
        super.getListaWidgetPantallas().add("widgetDataTableAmbito");
        super.setWidthEditDisplay("800");
    }

    @Override
    public void init() {
        listaAmbitos = inveAmbitoGeograficoFacade.findAll();
        super.init();
    }

    @Override
    public void reset() {
        inveAmbitoGeograficoEdit = new InveAmbitoGeografico();
        super.reset();
    }

    @Override
    public String opcionItem(String op) {
        if (op.equals("actualizar")) {
            try {
                inveAmbitoGeograficoEdit = ((InveAmbitoGeografico) this.getSelectedRow()).clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.opcionItem(op);
    }

    @Override
    public void insert() {
        inveAmbitoGeograficoFacade.create(inveAmbitoGeograficoEdit);
    }

    @Override
    public boolean preDelete() {
        boolean check = true;
        String entidad = null;
        List temp = inveProyectoFacade.getProyectoBy(inveAmbitoGeograficoEdit);
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
        inveAmbitoGeograficoFacade.remove((InveAmbitoGeografico) this.getSelectedRow());
    }

    @Override
    public void update() {
        inveAmbitoGeograficoFacade.edit(inveAmbitoGeograficoEdit);
    }

}
