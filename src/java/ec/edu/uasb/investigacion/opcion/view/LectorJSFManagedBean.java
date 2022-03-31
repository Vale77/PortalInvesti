/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.opcion.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.external.entities.AnioAcademico;
import ec.edu.uasb.external.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.investigacion.entities.InveCarta;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveProgramaProyecto;
import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveTipoSeguimiento;
import ec.edu.uasb.investigacion.parametro.view.NuevoLectorJSFManagedBean;
import ec.edu.uasb.investigacion.session.InveCartaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveLectorFacadeLocal;
import ec.edu.uasb.investigacion.session.InveProgramaProyectoFacadeLocal;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author victor.barba
 */
@Named(value = "lectorBean")
@ViewScoped
public class LectorJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @Inject
    private NuevoLectorJSFManagedBean lectorJSFManagedBean;
    @EJB
    private InveCartaFacadeLocal inveCartaFacade;
    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private InveLectorFacadeLocal inveLectorFacade;
    @EJB
    private InveProgramaProyectoFacadeLocal inveProgramaProyectoFacade;
    @EJB
    private InveConvocatoriaFacadeLocal inveConvocatoriaFacade;

    private final Usuario usr;
    private String filtrarAreas;
    private String perfil = new String();

    private Short anio;
    private List<InveCarta> listaCartas = new ArrayList<>();
    private List<InveCarta> filteredCartas;
    private List<AnioAcademico> listaAnios = new ArrayList<>();
    private List<InveProgramaProyecto> listaProgVincul = new ArrayList<>();
    private List<PrinPersona> listaPersonas = new ArrayList<>();
    private InveCarta cartaSelected;
    private InveCarta cartaEdit;
    private InveLector lectorEdit;
    private InveSeguimiento inveSeguimiento;
    private PrinPersona prinPersona;
    private String botonAdd;
    private List<InveConvocatoria> listaConvocatorias = new ArrayList<>();
    private InveConvocatoria convocatoria;

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

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public List<InveCarta> getListaCartas() {
        return listaCartas;
    }

    public List<InveCarta> getFilteredCartas() {
        return filteredCartas;
    }

    public void setFilteredCartas(List<InveCarta> filteredCartas) {
        this.filteredCartas = filteredCartas;
    }

    public List<InveProgramaProyecto> getListaProgVincul() {
        return listaProgVincul;
    }

    public List<AnioAcademico> getListaAnios() {
        return listaAnios;
    }

    public InveCarta getCartaSelected() {
        return cartaSelected;
    }

    public void setCartaSelected(InveCarta cartaSelected) {
        this.cartaSelected = cartaSelected;
    }

    public InveCarta getCartaEdit() {
        return cartaEdit;
    }

    public void setCartaEdit(InveCarta cartaEdit) {
        this.cartaEdit = cartaEdit;
    }

    public Short getAnio() {
        return anio;
    }

    public void setAnio(Short anio) {
        this.anio = anio;
    }

    public InveLector getLectorEdit() {
        return lectorEdit;
    }

    public void setLectorEdit(InveLector lectorEdit) {
        this.lectorEdit = lectorEdit;
    }

    public PrinPersona getPrinPersona() {
        return prinPersona;
    }

    public void setPrinPersona(PrinPersona prinPersona) {
        this.prinPersona = prinPersona;
    }

    public List<PrinPersona> getListaPersonas() {
        return listaPersonas;
    }

    public InveSeguimiento getInveSeguimiento() {
        return inveSeguimiento;
    }

    public void setInveSeguimiento(InveSeguimiento inveSeguimiento) {
        this.inveSeguimiento = inveSeguimiento;
    }
    //</editor-fold>

    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public LectorJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicionAdic("/pages/parametros/nuevoLector.xhtml");
        super.setTituloEdicAdic("Nuevo lector");
        super.setRenderEdicAdic(true);

        super.setPantallaEdicion("/pages/opciones/lectura/asignarLector.xhtml");
        super.setTituloModalEdicion("Asignación de lector");
        super.getListaWidgetPantallas().add("widgetLector");
        super.setWidthEditDisplay("1048");

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
    public void menuDefault() {
        this.setVisibleNew(false);
        this.setVisibleEdit(true);
        this.setVisibleDelete(false);
        refreshMenuBar(true);
    }

    public void retrieveBy() {
        cartaSelected = null;
        listaCartas.clear();
        if (anio != null) {
            listaCartas = inveCartaFacade.findBy(anio);
        } else if (convocatoria != null) {
            listaCartas = inveCartaFacade.findLectorBy(convocatoria);
        }
        RequestContext.getCurrentInstance().execute("PF('widgetLector').clearFilters();");
    }
    public void retrieve() {
        listaConvocatorias = inveConvocatoriaFacade.findAll();
        }
    private Date getFechaDespues(Date desde, int diasDespues) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(desde);
        calendar.add(Calendar.DAY_OF_YEAR, diasDespues);
        return calendar.getTime();
    }

    public String resetFilter() {
        cartaSelected = null;
        this.setDisabledEdit(true);
        RequestContext.getCurrentInstance().update("formMenuBar");
        return null;
    }

    public void calcFechaLimite() {
        lectorEdit.setLecFechaLimiteAcep(getFechaDespues(lectorEdit.getLecFechaDesignacion(), 5));
        inveSeguimiento.setSegFechaLimite(getFechaDespues(lectorEdit.getLecFechaDesignacion(), 15));
    }

    @Override
    public void onRowSelect(SelectEvent event) {
        try {
            cartaEdit = cartaSelected.clone();
            this.setDisabledEdit(cartaSelected.getCarEsPertinente() == null || cartaSelected.getCarEsPertinente().equals("N"));
            RequestContext.getCurrentInstance().update("formMenuBar");
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(CartaJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

    @Override
    public String opcionItem(String op) {
        listaProgVincul = inveProgramaProyectoFacade.findBy(cartaEdit);
        if (cartaEdit.getLector().getLecCodigo().compareTo(new Long("0")) > 0) {
            try {
                lectorEdit = cartaSelected.getLector().clone();
                prinPersona = lectorEdit.getPrinPersona();
                inveSeguimiento = (cartaEdit.getLector().getLecFechaDesignacion() == null ? new InveSeguimiento() : cartaEdit.getSeguimiento());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(LectorJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            prinPersona = null;
            lectorEdit = new InveLector();
            inveSeguimiento = new InveSeguimiento();
        }
        this.setVisibleBotonGrabar(lectorEdit.getLecFechaAceptacion() == null);
        return super.opcionItem(op);
    }

    @Override
    public void preUpdate() {
//        System.out.println(" " + cartaEdit.getLector());
//        System.out.println(" lectorEdit.getLecAceptacion() " + lectorEdit.getLecAceptacion());
        if (cartaEdit.getLector().getLecCodigo().compareTo(new Long("0")) < 0) {// es un nuevo registro cuando no se tiene asignado un nuevo lector
            lectorEdit.setInveProyecto(cartaEdit.getInveProyecto());
            lectorEdit.setLecUsuarioCrea(usr.getUsuCedPass());
            lectorEdit.setLecFechaCrea(new Date());
            lectorEdit.setPrinPersona(prinPersona);
            lectorEdit.setInveRol(new InveRol(new Short("1")));
        } else {
            lectorEdit.setLecFechaAct(new Date());
            lectorEdit.setLecUsuarioAct(usr.getUsuCedPass());
            lectorEdit.setLecFechaAceptacion(lectorEdit.getLecAceptacion().equals("S") ? new Date() : null);
        }
        if (cartaEdit.getLector().getLecCodigo().compareTo(new Long("0")) < 0
                || (cartaEdit.getLector().getLecCodigo().compareTo(new Long("0")) > 0 && lectorEdit.getLecAceptacion().equals("S") && cartaEdit.getLector().getLecNumero() > 1)) {
            inveSeguimiento.setInveProyecto(lectorEdit.getInveProyecto());
            inveSeguimiento.setInveTipoSeguimiento(new InveTipoSeguimiento(new Short("1")));// Lectura de proyecto de invesigación
            inveSeguimiento.setSegTipoComunic("OFICIO");
            inveSeguimiento.setSegNroComunic(inveSeguimiento.getSegNroComunic().toUpperCase());
            inveSeguimiento.setSegEstadoReg("A");
            inveSeguimiento.setSegFechaComunic(lectorEdit.getLecFechaDesignacion());
            inveSeguimiento.setSegFechaCrea(new Date());
            inveSeguimiento.setSegUsuarioCrea(usr.getUsuCedPass());
            inveSeguimiento.setSegFechaInicial(lectorEdit.getLecFechaDesignacion());
        }
    }

    @Override
    public void update() {
        inveLectorFacade.edit(lectorEdit, inveSeguimiento);
    }

    @Override
    public void postUpdate() {
        if (cartaEdit.getLector().getLecCodigo().compareTo(new Long("0")) < 0
                || (cartaEdit.getLector().getLecCodigo().compareTo(new Long("0")) > 0 && lectorEdit.getLecAceptacion().equals("S") && cartaEdit.getLector().getLecNumero() > 1)) {
            this.setMensaje("Lector asignado y notificado !");
        } else {
            this.setMensaje(lectorEdit.getLecAceptacion().equals("N") ? "Estado actualizado. Debe registrar otro Lector !" : "En espera de valoración !");
        }
        this.setIconMensaje("visto.jpg");
        RequestContext.getCurrentInstance().update("dialogMensaje");
        RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
        reset();
        retrieveBy();
        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
        RequestContext.getCurrentInstance().update("form");
    }

    @Override
    public void reset() {
        listaProgVincul.clear();
        prinPersona = null;
        cartaEdit = null;
        lectorEdit = null;
        inveSeguimiento = null;
        cartaSelected = null;
    }

    @Override
    public void cancelarButton() {
        super.cancelarButton();
        RequestContext.getCurrentInstance().reset("formEdit:pnlGrid");
        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
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
