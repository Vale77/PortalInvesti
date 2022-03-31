/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.opcion.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.external.entities.AnioAcademico;
import ec.edu.uasb.external.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.investigacion.entities.InveConvocatGrupo;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveGrupo;
import ec.edu.uasb.investigacion.entities.InveTipoConvocatoria;
import ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveGrupoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveProyectoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveTipoConvocatoriaFacadeLocal;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@Named(value = "convoBean")
@ViewScoped
public class ConvocatoriaJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;
    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private InveTipoConvocatoriaFacadeLocal inveTipoConvocatoriaFacade;
    @EJB
    private InveConvocatoriaFacadeLocal inveConvocatoriaFacade;
    @EJB
    private InveGrupoFacadeLocal inveGrupoFacade;

    private final Usuario usr;
    private String filtrarAreas;
    private String perfil = new String();

    private Short anio;

    private InveTipoConvocatoria tipoConvoEdit;
    private List<InveConvocatoria> listaConvocatorias = new ArrayList<>();
    private List<InveConvocatoria> listaConvofiltered;
    private List<AnioAcademico> listaAnios = new ArrayList<>();
    private List<InveTipoConvocatoria> listaTipoConvo = new ArrayList<>();
    private InveConvocatoria convocatoriaSelected;
    private InveConvocatoria convocatoriaEdit;
    private List<InveGrupo> listaGrupos = new ArrayList<>();
    private Short[] selectedGrupos;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public List<InveConvocatoria> getListaConvocatorias() {
        return listaConvocatorias;
    }

    public List<InveConvocatoria> getListaConvofiltered() {
        return listaConvofiltered;
    }

    public void setListaConvofiltered(List<InveConvocatoria> listaConvofiltered) {
        this.listaConvofiltered = listaConvofiltered;
    }

    public List<InveTipoConvocatoria> getListaTipoConvo() {
        return listaTipoConvo;
    }

    public List<AnioAcademico> getListaAnios() {
        return listaAnios;
    }

    public List<InveGrupo> getListaGrupos() {
        return listaGrupos;
    }

    public Short getAnio() {
        return anio;
    }

    public void setAnio(Short anio) {
        this.anio = anio;
    }

    public InveTipoConvocatoria getTipoConvoEdit() {
        return tipoConvoEdit;
    }

    public void setTipoConvoEdit(InveTipoConvocatoria tipoConvoEdit) {
        this.tipoConvoEdit = tipoConvoEdit;
    }

    public InveConvocatoria getConvocatoriaSelected() {
        return convocatoriaSelected;
    }

    public void setConvocatoriaSelected(InveConvocatoria convocatoriaSelected) {
        this.convocatoriaSelected = convocatoriaSelected;
    }

    public InveConvocatoria getConvocatoriaEdit() {
        return convocatoriaEdit;
    }

    public void setConvocatoriaEdit(InveConvocatoria convocatoriaEdit) {
        this.convocatoriaEdit = convocatoriaEdit;
    }

    public Short[] getSelectedGrupos() {
        return selectedGrupos;
    }

    public void setSelectedGrupos(Short[] selectedGrupos) {
        this.selectedGrupos = selectedGrupos;
    }

    //</editor-fold>
    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public ConvocatoriaJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/opciones/convoca/editarConvo.xhtml");
        super.setTituloModalEdicion("Convocatoria");
        super.getListaWidgetPantallas().add("widgetConvo");
        super.setWidthEditDisplay("800");
        // seguridad en perfiles y areas
        this.usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        List<Perfil> perfiles = (List<Perfil>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfiles");
        if (perfiles != null) {
            filtrarAreas = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("filtrarInfo");// áreas inicializadas             
            for (Perfil perf : perfiles) {
                if (perf.getIdPerfil().equals("USU_SIST") || perf.getIdPerfil().equals("ADM_INVE") || perf.getIdPerfil().equals("SEC_INVE")) {
                    perfil = perf.getIdPerfil();
                    filtrarAreas = "T";
                    break;
//                } else if (perf.getIdPerfil().equals("SEC_INVE") || perf.getIdPerfil().equals("SEC_INS")) {
//                    perfil = perf.getIdPerfil();
//                    filtrarAreas = "A";
//                    break;
                }
            }
        }
    }

    @Override
    public void init() {
        listaTipoConvo = inveTipoConvocatoriaFacade.findAll();
        listaGrupos = inveGrupoFacade.findAll();
        listaAnios = anioAcademicoFacade.getListaAnios();
        retrieveBy();
        super.init();
    }

    @Override
    public boolean isDisabledNew() {
        this.setDisabledNew((anio == null));
        return super.isDisabledNew();
    }

    public void retrieveBy() {
        convocatoriaSelected = null;
        listaConvocatorias.clear();
//        listaConvofiltered = null;
        if (anio != null) {
            listaConvocatorias = inveConvocatoriaFacade.findBy(anio);
        }
    }

    @Override
    public void onFilter() {
        convocatoriaSelected = null;
        super.onFilter();
    }

    //<editor-fold defaultstate="collapsed" desc="validaciones ">
    public void validateFechas(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        //Leave the null handling of startDate to required="true"
        Object startDateValue = component.getAttributes().get("fechaInicio");
        if (startDateValue == null) {
            return;
        }

        Date startDate = (Date) startDateValue;
        Date endDate = (Date) value;
        if (endDate.before(startDate)) {
            FacesMessage msg = new FacesMessage(" La fecha Final debe ser mayor a la fecha Inicial", " La fecha Final debe ser mayor a la fecha Inicial");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
//</editor-fold>

    public void resetCampo(String campo) {
        switch (campo) {
            case ("FechaFin"):
                convocatoriaEdit.setCvoFechaFin(null);
                break;
        }
    }

    @Override
    public String opcionItem(String op) {
        super.opcionItem(op);
        selectedGrupos = null;
//        tipoConvoEdit = tipoConvo;
        if (super.getOpcion().equals("actualizar")) {
            convocatoriaEdit = convocatoriaSelected;
            tipoConvoEdit = convocatoriaEdit.getInveTipoConvocatoria();
            if (!convocatoriaEdit.getInveConvocatGrupoCollection().isEmpty()) {
                selectedGrupos = new Short[convocatoriaEdit.getInveConvocatGrupoCollection().size()];
                int i = 0;
                for (InveConvocatGrupo convocatGrupo : convocatoriaEdit.getInveConvocatGrupoCollection()) {
                    selectedGrupos[i++] = convocatGrupo.getInveGrupo().getGrpCodigo();
                }
            }
        } else {
            convocatoriaEdit = new InveConvocatoria();
            convocatoriaEdit.setCvoAnioAcad(anio);
            convocatoriaEdit.setCvoEstado("I");
        }
        return null;
    }

    @Override
    public void preUpdate() {
        if (this.getOpcion().equals("actualizar")) {
            convocatoriaEdit.setInveTipoConvocatoria(tipoConvoEdit);
            convocatoriaEdit.setCvoFechaAct(new Date());
            convocatoriaEdit.setCvoUsuarioAct(usr.getUsuCedPass());
        } else {
            convocatoriaEdit.setInveTipoConvocatoria(tipoConvoEdit);
            convocatoriaEdit.setCvoFechaCrea(new Date());
            convocatoriaEdit.setCvoUsuarioCrea(usr.getUsuCedPass());
        }
        if (!convocatoriaEdit.getCvoEstado().equals("I")) {
            convocatoriaEdit.setCvoFechaEstado(new Date());
        }
    }

    @Override
    public void insert() {
        inveConvocatoriaFacade.create(convocatoriaEdit, selectedGrupos);
    }

    @Override
    public void update() {
        inveConvocatoriaFacade.edit(convocatoriaEdit, selectedGrupos);
    }

    @Override
    public void delete() {
        inveConvocatoriaFacade.remove(convocatoriaSelected);
    }

    @Override
    public void postUpdate() {
        this.setMensaje("Datos registrados correctamente !");
        this.setIconMensaje("visto.jpg");
        RequestContext.getCurrentInstance().update("dialogMensaje");
        RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
        super.postUpdate();
        RequestContext.getCurrentInstance().update("formConvo");
    }

    @Override
    public boolean preDelete() {
        boolean check = true;
        String entidad = null;
        List temp = inveProyectoFacade.getProyectoBy(convocatoriaSelected);
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
    public void postDelete() {
        super.postDelete();
        RequestContext.getCurrentInstance().update("formConvo");
    }

}
