/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.parametro.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.investigacion.entities.InveGrupo;
import ec.edu.uasb.investigacion.entities.InveRubro;
import ec.edu.uasb.investigacion.entities.InveRubroGrupo;
import ec.edu.uasb.investigacion.session.InveGrupoFacadeLocal;
import ec.edu.uasb.investigacion.session.InvePresupuestoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveRubroFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
@Named(value = "rubrosBean")
@ViewScoped
public class RubrosJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InvePresupuestoFacadeLocal invePresupuestoFacade;
    @EJB
    private InveGrupoFacadeLocal inveGrupoFacade;
    @EJB
    private InveRubroFacadeLocal inveRubroFacade;

    private List<InveRubro> listaInveRubros = new ArrayList<>();
    private InveRubro inveRubroEdit;
    private Short[] selectedGrupos;
    private List<InveGrupo> listaGrupos = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public InveRubro getInveRubroEdit() {
        return inveRubroEdit;
    }

    public void setInveRubroEdit(InveRubro inveRubroEdit) {
        this.inveRubroEdit = inveRubroEdit;
    }

    public List<InveRubro> getListaInveRubros() {
        return listaInveRubros;
    }

    public Short[] getSelectedGrupos() {
        return selectedGrupos;
    }

    public void setSelectedGrupos(Short[] selectedGrupos) {
        this.selectedGrupos = selectedGrupos;
    }

    public List<InveGrupo> getListaGrupos() {
        return listaGrupos;
    }
//</editor-fold>

    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public RubrosJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/parametros/editarRubro.xhtml");
        super.setTituloModalEdicion("Rubros para investigación");
        super.getListaWidgetPantallas().add("widgetDataTableRubro");
        super.setWidthEditDisplay("800");

    }

    @Override
    public void init() {
        listaInveRubros = inveRubroFacade.findAll();
        listaGrupos = inveGrupoFacade.findAll();
        super.init();
    }

    @Override
    public void reset() {
        inveRubroEdit = new InveRubro();
        super.reset();
    }

    @Override
    public String opcionItem(String op) {
        selectedGrupos = null;
        if (op.equals("actualizar")) {
            try {
                inveRubroEdit = ((InveRubro) this.getSelectedRow()).clone();
                if (!inveRubroEdit.getInveRubroGrupoCollection().isEmpty()) {
                    selectedGrupos = new Short[inveRubroEdit.getInveRubroGrupoCollection().size()];
                    int i = 0;
                    for (InveRubroGrupo inveRubroGrupo : inveRubroEdit.getInveRubroGrupoCollection()) {
                        selectedGrupos[i++] = inveRubroGrupo.getInveGrupo().getGrpCodigo();
                    }
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.opcionItem(op);
    }

    @Override
    public boolean preDelete() {
        boolean check = true;
        String entidad = null;
        List temp = invePresupuestoFacade.getPresupuestoBy(inveRubroEdit);
        if (!temp.isEmpty()) {
            entidad = "en Presupuesto de proyecto";
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
        inveRubroFacade.remove((InveRubro) this.getSelectedRow());
    }

    @Override
    public void insert() {
        inveRubroFacade.create(inveRubroEdit, selectedGrupos);
    }

    @Override
    public void update() {
        inveRubroFacade.edit(inveRubroEdit, selectedGrupos);
    }

}
