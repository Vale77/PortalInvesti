/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.opcion.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.external.entities.AnioAcademico;
import ec.edu.uasb.external.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveInformeOper;
import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveTipoSeguimiento;
import ec.edu.uasb.investigacion.parametro.view.NuevoLectorJSFManagedBean;
import ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveInformeOperFacadeLocal;
import ec.edu.uasb.investigacion.session.InveLectorFacadeLocal;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

/**
 *
 * @author victor.barba
 */
@Named(value = "lectorInfoFinalBean")
@ViewScoped
public class LectorInfoFinalJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveLectorFacadeLocal inveLectorFacade;
    @EJB
    private InveInformeOperFacadeLocal inveInformeOperFacade;
    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private InveConvocatoriaFacadeLocal inveConvocatoriaFacade;

    @Inject
    private NuevoLectorJSFManagedBean lectorJSFManagedBean;

    private final Usuario usr;
    private String filtrarAreas;
    private String perfil = new String();

    private Short anio;
    private List<PrinPersona> listaPersonas = new ArrayList<>();
    private List<InveInformeOper> listaInfoOper = new ArrayList<>();
    private List<InveInformeOper> filteredInfoOper;
    private List<InveLector> listaLectorFinal = new ArrayList<>();
    private InveLector inveLectorSelected;
    private InveLector inveLectorEdit;
    private List<AnioAcademico> listaAnios = new ArrayList<>();
    private InveInformeOper inveInformeOperSelected;
    private InveSeguimiento inveSeguimiento;
    private PrinPersona prinPersona;
    private int filaProyecto;
    private String botonAdd;
    private List<InveConvocatoria> listaConvocatorias = new ArrayList<>();
    private InveConvocatoria convocatoria;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public List<PrinPersona> getListaPersonas() {
        return listaPersonas;
    }

    public List<InveInformeOper> getListaInfoOper() {
        return listaInfoOper;
    }

    public List<InveLector> getListaLectorFinal() {
        return listaLectorFinal;
    }

    public List<InveInformeOper> getFilteredInfoOper() {
        return filteredInfoOper;
    }

    public void setFilteredInfoOper(List<InveInformeOper> filteredInfoOper) {
        this.filteredInfoOper = filteredInfoOper;
    }

    public InveInformeOper getInveInformeOperSelected() {
        return inveInformeOperSelected;
    }

    public void setInveInformeOperSelected(InveInformeOper inveInformeOperSelected) {
        this.inveInformeOperSelected = inveInformeOperSelected;
    }

    public List<AnioAcademico> getListaAnios() {
        return listaAnios;
    }

    public Short getAnio() {
        return anio;
    }

    public void setAnio(Short anio) {
        this.anio = anio;
    }

    public InveLector getInveLectorEdit() {
        return inveLectorEdit;
    }

    public void setInveLectorEdit(InveLector inveLectorEdit) {
        this.inveLectorEdit = inveLectorEdit;
    }

    public InveLector getInveLectorSelected() {
        return inveLectorSelected;
    }

    public void setInveLectorSelected(InveLector inveLectorSelected) {
        this.inveLectorSelected = inveLectorSelected;
    }

    public PrinPersona getPrinPersona() {
        return prinPersona;
    }

    public void setPrinPersona(PrinPersona prinPersona) {
        this.prinPersona = prinPersona;
    }

    public InveSeguimiento getInveSeguimiento() {
        return inveSeguimiento;
    }

    public void setInveSeguimiento(InveSeguimiento inveSeguimiento) {
        this.inveSeguimiento = inveSeguimiento;
    }

    public List<InveConvocatoria> getListaConvocatorias() {
        return listaConvocatorias;
    }

    public void setListaConvocatorias(List<InveConvocatoria> listaConvocatorias) {
        this.listaConvocatorias = listaConvocatorias;
    }

    public InveConvocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(InveConvocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Nuevo lector">
    class PrinPersonaComp implements Comparator<PrinPersona> {

        @Override
        public int compare(PrinPersona p1, PrinPersona p2) {

            String p1Apellido1 = p1.getPerPrimerApellido().trim().toUpperCase();
            String p1Apellido2 = (p1.getPerSegundoApellido() != null && !p1.getPerSegundoApellido().trim().isEmpty() ? p1.getPerSegundoApellido().trim() : "");
            String p2Apellido1 = p2.getPerPrimerApellido().trim().toUpperCase();
            String p2Apellido2 = (p2.getPerSegundoApellido() != null && !p2.getPerSegundoApellido().trim().isEmpty() ? p2.getPerSegundoApellido().trim() : "");

            return (p1Apellido1.concat(p1Apellido2).concat(p1.getPerNombres().trim()).compareTo(p2Apellido1.concat(p2Apellido2).concat(p1.getPerNombres().trim())));
        }
    }

    @Override
    public void addNuevoItemButton_processAction(ActionEvent ae) {
        lectorJSFManagedBean.addNuevoItemButton_processAction();
    }

    @Override
    public void updateAdicButton_processAction(ActionEvent ae) {
        lectorJSFManagedBean.updateAdicButton_processAction();
        try {
            listaPersonas = inveLectorFacade.findAllDocentes();
            Collections.sort(listaPersonas, new PrinPersonaComp());
            prinPersona = lectorJSFManagedBean.getNuevoLector().clone(); // asignar nuevo lector creado al listado
            super.updateAdicButton_processAction(ae);
            RequestContext.getCurrentInstance().update("formEdit:selectOneMenuLector");
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(LectorInfoFinalJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public LectorInfoFinalJSFManagedBean() {
        //super.setTieneMenuBar(true);
        super.setPantallaEdicionAdic("/pages/parametros/nuevoLector.xhtml");
        super.setTituloEdicAdic("Nuevo lector");
        super.setPantallaEdicion("/pages/opciones/infoFinal/lector/asigLectorInfoFinal.xhtml");
        super.getListaWidgetPantallas().add("widgetInformeFinal");
        super.getListaWidgetPantallas().add("widgetLector");
        super.setWidthEditDisplay("1048");
        super.setRenderEdicAdic(true);
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
                } else if (perf.getIdPerfil().equals("SEC_INVE") || perf.getIdPerfil().equals("SEC_INS")) {
                    perfil = perf.getIdPerfil();
                    filtrarAreas = "A";
                    break;
                }
            }
        }
    }

    @Override
    public void init() {
        listaAnios = anioAcademicoFacade.getListaAnios();
        listaPersonas = inveLectorFacade.findAllDocentes();
        Collections.sort(listaPersonas, new PrinPersonaComp());
        retrieve();
        retrieveBy();
        super.init();
    }

    @Override
    public void reset() {
        inveLectorSelected = null;
        prinPersona = null;
        inveLectorEdit = null;
        inveSeguimiento = null;
    }

    @Override
    public void menuDefault() {
        this.setVisibleNew(false);
        this.setVisibleEdit(true);
        this.setVisibleDelete(false);
        refreshMenuBar(true);
    }

    public void retrieveBy() {
       inveInformeOperSelected = null;
        listaInfoOper.clear();
        if (anio != null) {
            listaInfoOper = inveInformeOperFacade.findBy(anio);
        } else if (convocatoria != null) {
            listaInfoOper = inveInformeOperFacade.findInformeBy(convocatoria);
        }
        RequestContext.getCurrentInstance().execute("PF('widgetLector').clearFilters();");
        deSeleccionarListas();
    }

    public void retrieve() {
        listaConvocatorias = inveConvocatoriaFacade.findAll();
    }

    public String resetFilter() {
        inveLectorSelected = null;
        this.setDisabledEdit(true);
        RequestContext.getCurrentInstance().update("formMenuBar");
        return null;
    }

    public void calcFechaLimite() {
        inveLectorEdit.setLecFechaLimiteAcep(getFechaDespues(inveLectorEdit.getLecFechaDesignacion(), 5));
        inveSeguimiento.setSegFechaLimite(getFechaDespues(inveLectorEdit.getLecFechaDesignacion(), 30));
    }

    private Date getFechaDespues(Date desde, int diasDespues) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(desde);
        calendar.add(Calendar.DAY_OF_YEAR, diasDespues);
        return calendar.getTime();
    }

    @Override
    public String opcionItem(String op) {
        super.opcionItem(op);
        if (super.getOpcion().equals("insertar")) {
            this.setTituloModalEdicion("Añadir lector");
            prinPersona = null;
            inveLectorEdit = new InveLector();
            inveSeguimiento = new InveSeguimiento();
        } else {
            this.setTituloModalEdicion("Editar lector");
            try {
                inveLectorEdit = inveLectorSelected.clone();
                prinPersona = inveLectorEdit.getPrinPersona();
                inveSeguimiento = (inveLectorEdit.getSeguimiento() == null ? new InveSeguimiento() : inveLectorEdit.getSeguimiento());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(LectorInfoFinalJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestContext.getCurrentInstance().update("dlgEdit");
        return null;
    }

    public void onRowToggle(ToggleEvent event) {
        inveLectorSelected = null;
        if (event.getVisibility() == Visibility.VISIBLE) {
            try {
                filaProyecto = ((DataTable) event.getSource()).getRowIndex();
                inveInformeOperSelected = ((InveInformeOper) event.getData()).clone();
                listaLectorFinal = inveLectorFacade.findByRol(inveInformeOperSelected.getInveProyecto(), new Short("2"));// lecturas con rol de lector final
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(LectorInfoFinalJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            inveInformeOperSelected = null;
            listaLectorFinal.clear();
            deSeleccionarListas();
        }
    }

    @Override
    public void preUpdate() {
        if (super.getOpcion().equals("insertar")) {
            inveLectorEdit.setInveProyecto(inveInformeOperSelected.getInveProyecto());
            inveLectorEdit.setLecUsuarioCrea(usr.getUsuCedPass());
            inveLectorEdit.setLecFechaCrea(new Date());
            inveLectorEdit.setPrinPersona(prinPersona);
            inveLectorEdit.setInveRol(new InveRol(new Short("2")));
        } else {
            inveLectorEdit.setLecFechaAct(new Date());
            inveLectorEdit.setLecUsuarioAct(usr.getUsuCedPass());
            inveLectorEdit.setLecFechaAceptacion(inveLectorEdit.getLecAceptacion() != null && inveLectorEdit.getLecAceptacion().equals("S") ? new Date() : null);
        }
        // seguimiento
        inveSeguimiento.setInveProyecto(inveInformeOperSelected.getInveProyecto());
        inveSeguimiento.setInveTipoSeguimiento(new InveTipoSeguimiento(new Short("0"))); // Lectura de informe final
        inveSeguimiento.setSegTipoComunic("OFICIO");
        inveSeguimiento.setSegNroComunic(inveSeguimiento.getSegNroComunic().toUpperCase());
        inveSeguimiento.setSegEstadoReg("A");
        inveSeguimiento.setSegFechaComunic(inveLectorEdit.getLecFechaDesignacion());
        inveSeguimiento.setSegFechaInicial(inveLectorEdit.getLecFechaDesignacion());
        inveSeguimiento.setSegFechaCrea(new Date());
        inveSeguimiento.setSegUsuarioCrea(usr.getUsuCedPass());
    }

    @Override
    public void update() {
        inveLectorFacade.edit(inveLectorEdit, inveSeguimiento);
    }

    @Override
    public void insert() {
        inveLectorFacade.edit(inveLectorEdit, inveSeguimiento);
    }

    @Override
    public void postUpdate() {
        listaLectorFinal = inveLectorFacade.findByRol(inveInformeOperSelected.getInveProyecto(), new Short("2"));
        reset();
        RequestContext.getCurrentInstance().execute("PF('widgetLector').unselectAllRows();");
        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
        RequestContext.getCurrentInstance().update("form:dataTableFinal:" + filaProyecto + ":dataTableLector");
    }

    @Override
    public void cancelarButton() {
        reset();
        RequestContext.getCurrentInstance().execute("PF('widgetLector').unselectAllRows();");
        RequestContext.getCurrentInstance().reset("formEdit:pnlGrid");
        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
        RequestContext.getCurrentInstance().update("form:dataTableFinal:" + filaProyecto + ":dataTableLector");
    }

//     private void enviarNotificacion(InveLector entity) {
////        Map m = new HashMap();
//        StringBuilder mensaje;
//        SimpleDateFormat formatterCarta = new SimpleDateFormat("'Quito,' EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "EC"));
////        // parametros de carta
////        m.put("@FECHA", formatterCarta.format(new Date()));
////        m.put("@LECTOR", entity.getPrinPersona().getPerNombres().toUpperCase()+" "+entity.getPrinPersona().getPerPrimerApellido().toUpperCase());
//        try {
//            mensaje = JsfUtil.ReadTextFile("/reportes/LectorProyecto1.docx");
//            System.out.println(mensaje.toString());
//            //mensaje = JsfUtil.ReplaceKeyText(m, mensaje);
////            this.sendEmail("Soporte Servicios",
////                    "selenia.larenas@uasb.edu.ec;fernando.andrade@uasb.edu.ec;",
////                    "", "soporteservicios@uasb.edu.ec;juancarlos.lozada@uasb.edu.ec;victor.barba@uasb.edu.ec;",
////                    "Asunto", mensaje.toString(), "HTML");
////            this.sendEmail("Soporte Servicios", "victor.barba@uasb.edu.ec", "", "", "Asignación de lector de proyecto de investigación - UASB", mensaje.toString(), "HTML");
//            mensaje = null;
//        } catch (IOException ex) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
