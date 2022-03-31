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
import ec.edu.uasb.investigacion.entities.InveEstadoValoracion;
import ec.edu.uasb.investigacion.entities.InveInformeOper;
import ec.edu.uasb.investigacion.entities.InvePresupuesto;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveTipoSeguimiento;
import ec.edu.uasb.investigacion.entities.InveValoracion;
import ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveEstadoValoracionFacadeLocal;
import ec.edu.uasb.investigacion.session.InveInformeOperFacadeLocal;
import ec.edu.uasb.investigacion.session.InveSeguimientoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveValoracionFacadeLocal;
import ec.edu.uasb.principal.entities.PrinClaseDocumento;
import ec.edu.uasb.principal.entities.PrinDocumento;
import ec.edu.uasb.principal.entities.PrinTipoDedicacion;
import ec.edu.uasb.principal.session.PrinTipoDedicacionFacadeLocal;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author victor.barba
 */
@Named(value = "informeFinalBean")
@ViewScoped
public class InformeFinalJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveInformeOperFacadeLocal inveInformeOperFacade;

    @EJB
    private PrinTipoDedicacionFacadeLocal prinTipoDedicacionFacade;

    @EJB
    private InveSeguimientoFacadeLocal inveSeguimientoFacade;
    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private InveEstadoValoracionFacadeLocal estadoValoracionFacade;
    @EJB
    private InveValoracionFacadeLocal inveValoracionFacade;
    @EJB
    private InveConvocatoriaFacadeLocal inveConvocatoriaFacade;

    private final Usuario usr;
    private String filtrarAreas;
    private String perfil = new String();
    private final String modulo;

    private Short anio;
    private List<AnioAcademico> listaAnios = new ArrayList<>();
    private List<InveValoracion> listaProyValorados = new ArrayList<>();
    private List<InveValoracion> listaProyValoradosFilterd;
    private InveValoracion inveValoracionSelected;
    private InveSeguimiento inveSeguimiento;
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

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public void setConvocatoria(InveConvocatoria convocatoria) {
        this.convocatoria = convocatoria;
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

    public List<InveValoracion> getListaProyValorados() {
        return listaProyValorados;
    }

    public List<InveValoracion> getListaProyValoradosFilterd() {
        return listaProyValoradosFilterd;
    }

    public void setListaProyValoradosFilterd(List<InveValoracion> listaProyValoradosFilterd) {
        this.listaProyValoradosFilterd = listaProyValoradosFilterd;
    }

    public InveValoracion getInveValoracionSelected() {
        return inveValoracionSelected;
    }

    public void setInveValoracionSelected(InveValoracion inveValoracionSelected) {
        this.inveValoracionSelected = inveValoracionSelected;
    }

    public InveSeguimiento getInveSeguimiento() {
        return inveSeguimiento;
    }

    public void setInveSeguimiento(InveSeguimiento inveSeguimiento) {
        this.inveSeguimiento = inveSeguimiento;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="PRORROGAS y SUSPENSIONES">
    private String tipoTransaccion;
    private String nroProrroga;
    private List<InveEstadoValoracion> listaEstados = new ArrayList<>();

    public List<InveEstadoValoracion> getListaEstados() {
        return listaEstados;
    }

    public String getNroProrroga() {
        return nroProrroga;
    }

    public void setNroProrroga(String nroProrroga) {
        this.nroProrroga = nroProrroga;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Formulario entrega">
    private List<PrinTipoDedicacion> listaTipoDedicacion = new ArrayList<>();
    private InveInformeOper infoOperativoEdit;
    private String tituloFinal;
    private PrinDocumento prinDocumento = new PrinDocumento();

    public PrinDocumento getPrinDocumento() {
        return prinDocumento;
    }

    public void setPrinDocumento(PrinDocumento prinDocumento) {
        this.prinDocumento = prinDocumento;
    }

    public InveInformeOper getInfoOperativoEdit() {
        return infoOperativoEdit;
    }

    public void setInfoOperativoEdit(InveInformeOper infoOperativoEdit) {
        this.infoOperativoEdit = infoOperativoEdit;
    }

    public String getTituloFinal() {
        return tituloFinal;
    }

    public void setTituloFinal(String tituloFinal) {
        this.tituloFinal = tituloFinal;
    }

    public List<PrinTipoDedicacion> getListaTipoDedicacion() {
        return listaTipoDedicacion;
    }
//</editor-fold>

    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public InformeFinalJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setTituloModalEdicion("Informe final");
        super.getListaWidgetPantallas().add("widgetInforme");
        super.setPosicionEdicion("top");
        super.setWidthEditDisplay("1048");
//        super.setRenderPDF(true);
        // seguridad en perfiles y areas
        this.usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        modulo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subsist");
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
        tipoTransaccion = null;
        listaAnios = anioAcademicoFacade.getListaAnios();
        listaEstados = estadoValoracionFacade.findAll();
        listaTipoDedicacion = prinTipoDedicacionFacade.findAll();
        retrieveBy();
        retrieve();
        RequestContext.getCurrentInstance().execute("PF('widgetVarCtx').hide();");
        super.init();
    }

    @Override
    public void reset() {
        inveValoracionSelected = null;
        tituloFinal = null;
        inveSeguimiento = new InveSeguimiento();
        infoOperativoEdit = new InveInformeOper();
        listaPresup.clear();
        prinDocumento = new PrinDocumento();
    }

    @Override
    public void menuDefault() {
        this.setVisibleNew(false);
        this.setVisibleEdit(true);
        this.setVisibleDelete(false);
        refreshMenuBar(true);
    }

    public void retrieveBy() {
        listaProyValorados.clear();
        if (anio != null) {
            listaProyValorados = inveValoracionFacade.getValoracionesBy(anio);
        } else if (convocatoria != null) {
            listaProyValorados = inveValoracionFacade.getValorConvocatoriaBy(convocatoria);
        }
        RequestContext.getCurrentInstance().execute("PF('widgetInforme').clearFilters();");
    }

    public void retrieve() {
        listaConvocatorias = inveConvocatoriaFacade.findAll();
    }

    public String resetFilter() {
        inveValoracionSelected = null;
        this.setDisabledEdit(true);
        RequestContext.getCurrentInstance().update("formMenuBar");
        return null;
    }

    //<editor-fold defaultstate="collapsed" desc="calculo de fechas">
    private Date getFechaDespues(Date desde, int mesesDespues) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(desde);
        calendar.add(Calendar.MONTH, mesesDespues);
        return calendar.getTime();
    }

    public void calcFechaLimite() {
        inveSeguimiento.setSegFechaLimite(getFechaDespues(inveSeguimiento.getSegFechaInicial(), 3));
    }
//</editor-fold>    

    //<editor-fold defaultstate="collapsed" desc="Gastos ">
    private List<InvePresupuesto> listaPresup = new ArrayList<>();

    public List<InvePresupuesto> getListaPresup() {
        return listaPresup;
    }

    public BigDecimal getSumaTotal() {
        BigDecimal sumaTotal = BigDecimal.ZERO;
        for (InvePresupuesto ip : listaPresup) {
            sumaTotal = sumaTotal.add(ip.getPreMontoAprob() == null ? BigDecimal.ZERO : ip.getPreMontoAprob());
        }
        return sumaTotal;
    }

    public BigDecimal getSumaTotalGasto() {
        BigDecimal sumaTotalGasto = BigDecimal.ZERO;
        for (InvePresupuesto ip : listaPresup) {
            sumaTotalGasto = sumaTotalGasto.add(ip.getPreMontoGastado() == null ? BigDecimal.ZERO : ip.getPreMontoGastado());
        }
        return sumaTotalGasto;
    }

    public void rowEditInit(RowEditEvent event) {
        int fila = ((DataTable) event.getSource()).getRowIndex();
        RequestContext.getCurrentInstance().execute("$(function(){PrimeFaces.focus('formEdit:dataTablePresup:" + fila + ":inputDescrip')});");
    }
//</editor-fold>

    public void registrarTransaccion(String codOpcion) {
        switch (codOpcion) {
            case "1":
            case "2":
                nroProrroga = codOpcion;
                tipoTransaccion = "PRORROGA";
                break;
            case "3":
                nroProrroga = codOpcion;
                tipoTransaccion = "PRORROGA EXTRAORDINARIA";
                break;
            default:
                nroProrroga = null;
                tipoTransaccion = "SUSPENSION";
                break;
        }
        this.opcionItem("insertar");
    }

    class InvePresupuestoComp implements Comparator<InvePresupuesto> {

        @Override
        public int compare(InvePresupuesto e1, InvePresupuesto e2) {
            return (e1.getInveRubro().getRubNombre()).compareTo(e2.getInveRubro().getRubNombre());
        }
    }

    @Override
    public String opcionItem(String op) {
        super.opcionItem(op);
        if (op.equals("actualizar")) {
            // Entrega Informe Final
            super.setPantallaEdicion("/pages/opciones/infoFinal/editarInformeFinal.xhtml");
            RequestContext.getCurrentInstance().execute("PF('widgetVarWizardInfoFinal').loadStep(PF('widgetVarWizardInfoFinal').cfg.steps [0], true)");
            try {
                infoOperativoEdit = inveValoracionSelected.getInveInformeOper().clone();
                tituloFinal = (infoOperativoEdit.getInveProyecto().getPryTituloFinal() != null ? infoOperativoEdit.getInveProyecto().getPryTituloFinal() : null);
                prinDocumento = new PrinDocumento();
                listaPresup.clear();
                ArrayList<InvePresupuesto> presup = new ArrayList(inveValoracionSelected.getInveLector().getInveProyecto().getInvePresupuestoCollection());
                Collections.sort(presup, new InvePresupuestoComp());
                listaPresup.addAll(presup);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(InformeFinalJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {// PRORROGAS
            super.setPantallaEdicion("/pages/opciones/infoFinal/editarProrroga.xhtml");
        }
        return super.opcionItem(op);
    }

    //<editor-fold defaultstate="collapsed" desc="Manejo de archivos">
    public void subirAchivo(FileUploadEvent event) {
        prinDocumento = new PrinDocumento(new Long(1), modulo, "PROYECTOS", null, "INVE_PROYECTO", null, new Date(), usr.getUsuCedPass());
        prinDocumento.setPrinClaseDocumento(new PrinClaseDocumento(new Short("8")));// clase documento Informe Final
        prinDocumento.setDocExtension(event.getFile().getContentType());
        prinDocumento.setDocArchivo(event.getFile().getContents());
        prinDocumento.setDocNombre(event.getFile().getFileName());
        prinDocumento.setDocFechaCrea(new Date());
        prinDocumento.setDocUsuario(usr.getUsuCedPass());
        prinDocumento.setArchivo(event.getFile());
    }
//</editor-fold>

    public String onFlowProcess(FlowEvent event) {
        String tabDestino = event.getNewStep();
        if (infoOperativoEdit.getIopUsuarioCrea() == null) {
            boolean darPaso = true;
            switch (event.getNewStep()) {
                case "infoFinan":
                    if (prinDocumento.getDocArchivo() == null) {
                        this.setMensaje("ATENCIÓN. Ingrese el documento requerido: </br>- Informe Final de Investigación");
                        darPaso = false;
                        tabDestino = "documento";
                    }
                    break;
                default:
                    break;
            }
            if (darPaso == false) {
                this.setIconMensaje("error.png");
                RequestContext.getCurrentInstance().update("dialogMensaje");
                RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
            }
        }
        return tabDestino;
    }

    @Override
    public void preUpdate() {
        if (this.getOpcion().equals("actualizar")) {// RECIBIR EL INFORME FINAL
            inveValoracionSelected.getInveLector().getInveProyecto().setInvePresupuestoCollection(listaPresup);
            if (tituloFinal != null || !tituloFinal.trim().isEmpty()) {
                inveValoracionSelected.getInveLector().getInveProyecto().setPryTituloFinal(tituloFinal.trim());
                infoOperativoEdit.setInveProyecto(inveValoracionSelected.getInveLector().getInveProyecto());
            }
            infoOperativoEdit.setPryCodigo(inveValoracionSelected.getInveLector().getInveProyecto().getPryCodigo());
        } else {
            inveSeguimiento.setInveProyecto(inveValoracionSelected.getInveLector().getInveProyecto());
            if (this.getNroProrroga() != null) {
                inveSeguimiento.setInveTipoSeguimiento(new InveTipoSeguimiento(this.getNroProrroga().equals("3") ? new Short("6") : new Short("4")));// POR PRORROGA EXT y PRORROGA NORMAL
                inveSeguimiento.setSegTipoComunic("MEMO");
            } else {
                inveSeguimiento.setInveTipoSeguimiento(new InveTipoSeguimiento(new Short("10")));// Por SUSPENSION
                inveSeguimiento.setSegTipoComunic("OFICIO");
                inveSeguimiento.setSegFechaLimite(null);
            }

            inveSeguimiento.setSegNroComunic(inveSeguimiento.getSegNroComunic().toUpperCase());
            inveSeguimiento.setSegEstadoReg("A");
            inveSeguimiento.setSegFechaComunic(inveSeguimiento.getSegFechaInicial());
            inveSeguimiento.setSegFechaCrea(new Date());
            inveSeguimiento.setSegUsuarioCrea(usr.getUsuCedPass());
        }
    }

    @Override
    public void insert() {
        inveSeguimientoFacade.create(inveSeguimiento);
    }

    @Override
    public void update() {
        if (infoOperativoEdit.getIopFechaCrea() == null) {
            infoOperativoEdit.setIopUsuarioCrea(usr.getUsuCedPass());
            inveInformeOperFacade.create(infoOperativoEdit);
        } else {
            infoOperativoEdit.setIopFechaAct(new Date());
            infoOperativoEdit.setIopUsuarioAct(usr.getUsuCedPass());
            inveInformeOperFacade.edit(infoOperativoEdit);
        }
    }

    @Override
    public void postUpdate() {
        if (this.getOpcion().equals("insertar")) {
            this.setMensaje(tipoTransaccion + " registrada !");
        } else {
            this.setMensaje("Informe Final registrado !");
        }
        this.setIconMensaje("visto.jpg");
        RequestContext.getCurrentInstance().update("dialogMensaje");
        RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
        retrieveBy();
        reset();
        RequestContext.getCurrentInstance().update("formValoracion");
    }

    @Override
    public String print() {
        this.setTituloPDF("Ficha económica");
        String pry = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("proyecto");
        String urlReporte = "subsist=" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subsist")
                + "&reporte=fichaProyAprob&pProyecto=" + pry;
//        System.out.println(urlReporte);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte);
        return null;
    }

}
