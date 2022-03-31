/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.parametro.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.investigacion.entities.InveDisciplinaCientifica;
import ec.edu.uasb.investigacion.session.InveDisciplinaCientificaFacadeLocal;
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
@Named(value = "disciplinaBean")
@ViewScoped
public class DsiciplinaJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveDisciplinaCientificaFacadeLocal inveDisciplinaCientificaFacade;

    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;

    private List<InveDisciplinaCientifica> listaDisciplinaCientifica = new ArrayList<>();
    private InveDisciplinaCientifica inveDiscipEdit;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public List<InveDisciplinaCientifica> getListaDisciplinaCientifica() {
        return listaDisciplinaCientifica;
    }

    public InveDisciplinaCientifica getInveDiscipEdit() {
        return inveDiscipEdit;
    }

    public void setInveDiscipEdit(InveDisciplinaCientifica inveDiscipEdit) {
        this.inveDiscipEdit = inveDiscipEdit;
    }

//</editor-fold>
    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public DsiciplinaJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/parametros/editarDisciplina.xhtml");
        super.setTituloModalEdicion("Disciplina científica");
        super.getListaWidgetPantallas().add("widgetDataTableDisci");
        super.setWidthEditDisplay("800");
    }

    @Override
    public void init() {
        listaDisciplinaCientifica = inveDisciplinaCientificaFacade.findAll();
        super.init();
    }

    @Override
    public void reset() {
        inveDiscipEdit = new InveDisciplinaCientifica();
        super.reset();
    }

    @Override
    public String opcionItem(String op) {
        if (op.equals("actualizar")) {
            try {
                inveDiscipEdit = ((InveDisciplinaCientifica) this.getSelectedRow()).clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.opcionItem(op);
    }

    @Override
    public void insert() {
        inveDisciplinaCientificaFacade.create(inveDiscipEdit);
    }

    @Override
    public boolean preDelete() {
        boolean check = true;
        String entidad = null;
        List temp = inveProyectoFacade.getProyectoBy(inveDiscipEdit);
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
        inveDisciplinaCientificaFacade.remove((InveDisciplinaCientifica) this.getSelectedRow());
    }

    @Override
    public void update() {
        inveDisciplinaCientificaFacade.edit(inveDiscipEdit);
    }

}
