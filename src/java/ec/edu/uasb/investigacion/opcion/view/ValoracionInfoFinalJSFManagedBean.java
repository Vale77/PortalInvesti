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
import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveTipoSeguimiento;
import ec.edu.uasb.investigacion.entities.InveValoracion;
import ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveEstadoValoracionFacadeLocal;
import ec.edu.uasb.investigacion.session.InveInformeOperFacadeLocal;
import ec.edu.uasb.investigacion.session.InveLectorFacadeLocal;
import ec.edu.uasb.investigacion.session.InveSeguimientoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveValoracionFacadeLocal;
import ec.edu.uasb.principal.entities.PrinClaseDocumento;
import ec.edu.uasb.principal.entities.PrinDocumento;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

/**
 *
 * @author victor.barba
 */
@Named(value = "valorInfoFinalBean")
@ViewScoped
public class ValoracionInfoFinalJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveSeguimientoFacadeLocal inveSeguimientoFacade;

    @EJB
    private InveLectorFacadeLocal inveLectorFacade;
    @EJB
    private InveInformeOperFacadeLocal inveInformeOperFacade;
    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private InveValoracionFacadeLocal inveValoracionFacade;
    @EJB
    private InveEstadoValoracionFacadeLocal estadoValoracionFacade;
    @EJB
    private InveConvocatoriaFacadeLocal inveConvocatoriaFacade;

    private final Usuario usr;
    private String filtrarAreas;
    private String perfil = new String();
    private final String modulo;
    private Short anio;

    private List<InveInformeOper> listaInfoOper = new ArrayList<>();
    private List<InveInformeOper> filteredInfoOper;
    private List<InveLector> listaLectorFinal = new ArrayList<>();
    private InveLector inveLectorSelected;
    private InveLector inveLectorEdit;
    private PrinDocumento doc;
    private InveValoracion inveValoracionEdit;
    private List<AnioAcademico> listaAnios = new ArrayList<>();
    private List<InveEstadoValoracion> listaEstados = new ArrayList<>();
    private List<InveConvocatoria> listaConvocatorias = new ArrayList<>();
    private InveConvocatoria convocatoria;
    private InveInformeOper inveInformeOperSelected;
    private InveSeguimiento inveSeguimiento;
    private List<SelectItem> esquema = new ArrayList<>();
    private String[] selectedItemsEsquema;
    private int filaProyecto;
    private String botonAdd;
    private boolean disabledNotif = true;
    private boolean aprobado = false;
    private boolean hacerModif = false;
    private Boolean solicContrato;
    private Date fechaSolicContrato;
    private Long nroSolic;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public Long getNroSolic() {
        return nroSolic;
    }

    public void setNroSolic(Long nroSolic) {
        this.nroSolic = nroSolic;
    }

    public Date getFechaSolicContrato() {
        return fechaSolicContrato;
    }

    public void setFechaSolicContrato(Date fechaSolicContrato) {
        this.fechaSolicContrato = fechaSolicContrato;
    }

    public Boolean getSolicContrato() {
        return solicContrato;
    }

    public void setSolicContrato(Boolean solicContrato) {
        this.solicContrato = solicContrato;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public boolean isHacerModif() {
        return hacerModif;
    }

    public void setHacerModif(boolean hacerModif) {
        this.hacerModif = hacerModif;
    }

    public boolean isDisabledNotif() {
        return disabledNotif;
    }

    public List<InveInformeOper> getListaInfoOper() {
        return listaInfoOper;
    }

    public List<InveEstadoValoracion> getListaEstados() {
        return listaEstados;
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

    public List<SelectItem> getEsquema() {
        return esquema;
    }

    public void setEsquema(List<SelectItem> esquema) {
        this.esquema = esquema;
    }

    public String[] getSelectedItemsEsquema() {
        return selectedItemsEsquema;
    }

    public void setSelectedItemsEsquema(String[] selectedItemsEsquema) {
        this.selectedItemsEsquema = selectedItemsEsquema;
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

    public InveValoracion getInveValoracionEdit() {
        return inveValoracionEdit;
    }

    public void setInveValoracionEdit(InveValoracion inveValoracionEdit) {
        this.inveValoracionEdit = inveValoracionEdit;
    }

    public InveLector getInveLectorSelected() {
        return inveLectorSelected;
    }

    public void setInveLectorSelected(InveLector inveLectorSelected) {
        this.inveLectorSelected = inveLectorSelected;
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
    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public ValoracionInfoFinalJSFManagedBean() {
        super.setRenderPDF(true);
        super.setTituloPDF("Solicitud de contrato");
        super.getListaWidgetPantallas().add("widgetInformeFinal");
        super.getListaWidgetPantallas().add("widgetLector");
        super.setWidthEditDisplay("1048");
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
        SelectItemGroup datos = new SelectItemGroup("DATOS DEL PROYECTO");
        datos.setSelectItems(new SelectItem[]{
            new SelectItem("1", "Título del Proyecto"),
            new SelectItem("2", "Lector(a) del proyecto")
        }
        );
        SelectItemGroup analisis = new SelectItemGroup("ANÁLISIS DE LA INVESTIGACIÓN");
        analisis.setSelectItems(new SelectItem[]{
            new SelectItem("3", "Corresponde al proyecto"),
            new SelectItem("4", "Aportes relevantes"),
            new SelectItem("5", "Comentarios"),
            new SelectItem("6", "Recomendaciones")}
        );
        esquema.add(datos);
        esquema.add(analisis);
    }

    @Override
    public void init() {
        listaAnios = anioAcademicoFacade.getListaAnios();
        listaEstados = estadoValoracionFacade.getEstadoBy(new InveRol(new Short("2")));
        retrieveBy();
        retrieve();
        super.init();
    }

    @Override
    public void reset() {
        inveLectorSelected = null;
        inveLectorEdit = null;
        inveSeguimiento = null;
        solicContrato = null;
        fechaSolicContrato = null;
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

    public void regFechaSolic() {
        fechaSolicContrato = solicContrato ? new Date() : null;
    }

    @Override
    public String opcionItem(String op) {
        super.opcionItem(op);
        this.setVisibleBotonGrabar(true);
        if (super.getOpcion().equals("actualizar")) {
            this.setPantallaEdicion("/pages/opciones/infoFinal/valorac/regValorInfoFinal.xhtml");
            this.setTituloModalEdicion("Registrar valoración");
            try {
                inveLectorEdit = inveLectorSelected.clone();
                inveValoracionEdit = new InveValoracion();
                doc = new PrinDocumento();
                selectedItemsEsquema = null;
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ValoracionInfoFinalJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
//            this.setVisibleBotonGrabar(true);
        } else {
            this.setPantallaEdicion("/pages/opciones/infoFinal/valorac/regNotif.xhtml");
            this.setTituloModalEdicion("Registrar notificación");
            fechaSolicContrato = inveInformeOperSelected.getInveProyecto().getPryFechaSolicContrato();
            solicContrato = fechaSolicContrato != null ? true : null;
            nroSolic = inveInformeOperSelected.getInveProyecto().getPryNumeroSolicContrato();
            if (inveInformeOperSelected.getInveSeguimiento().getSegNotifAprobado() != null) {
                inveSeguimiento = inveInformeOperSelected.getInveSeguimiento();
            } else {
                inveSeguimiento = new InveSeguimiento();
            }
//            this.setVisibleBotonGrabar(true);
            if (inveInformeOperSelected.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo() == 3 || inveInformeOperSelected.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo() == 2) {
                this.setVisibleBotonGrabar(nroSolic == null);
            } else {
                this.setVisibleBotonGrabar(inveSeguimiento.getSegNroComunic() == null);
            }
        }
        RequestContext.getCurrentInstance().update("dlgEdit");
        RequestContext.getCurrentInstance().update("formEdit:pnlGrid");
        return null;
    }

    //<editor-fold defaultstate="collapsed" desc="calculo de fechas">
    public void calcFechaLimite() {
//        inveSeguimiento.setSegFechaLimite(getFechaDespues(inveLectorEdit.getLecFechaDesignacion(), 30));
    }

    private Date getFechaDespues(Date desde, int diasDespues) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(desde);
        calendar.add(Calendar.DAY_OF_YEAR, diasDespues);
        return calendar.getTime();
    }

    public void resetFechas() {
        inveValoracionEdit.setValRecomendaPublic(null);
    }
//</editor-fold>   

    //<editor-fold defaultstate="collapsed" desc="documentos">
    public void subirAchivo(FileUploadEvent event) {
        doc = new PrinDocumento(new Long(1), modulo, "PROYECTOS", null, "INVE_VALORACION", null, new Date(), usr.getUsuCedPass());
        doc.setPrinClaseDocumento(new PrinClaseDocumento(new Short("7")));// clase documento Valoración
        doc.setDocExtension(event.getFile().getContentType());
        doc.setDocArchivo(event.getFile().getContents());
        doc.setDocNombre(event.getFile().getFileName());
        doc.setDocFechaCrea(new Date());
        doc.setDocUsuario(usr.getUsuCedPass());
        doc.setArchivo(event.getFile());
    }
//</editor-fold>

//    class LecturaComp implements Comparator<InveLector> {
//
//        @Override
//        public int compare(InveLector e1, InveLector e2) {
//            return e1.getLecNumero().compareTo(e2.getLecNumero());
//        }
//    }
    private void controlNotif() {
        int suma = 0;
        int cont = 0;
        aprobado = false;
        hacerModif = false;
//        InveLector maxLec = Collections.max(listaLectorFinal, new LecturaComp());
        for (InveLector val : listaLectorFinal) {
            if (val.getEstadoVal() != null) {
                cont++;
                suma = suma + new Integer(val.getEstadoVal());
                if (val.getEstadoVal().equals("1")) {
                    aprobado = true;
                }
                if (val.getEstadoVal().equals("2")) {
                    hacerModif = true;
                }
            }
        }
        if (suma == 6) {
            aprobado = false; // estado de valoración
        }
        disabledNotif = (cont < 2); // habilita boton Notificar
    }

    public void onRowToggle(ToggleEvent event) {
        inveLectorSelected = null;
        disabledNotif = true;
        if (event.getVisibility() == Visibility.VISIBLE) {
            try {
                filaProyecto = ((DataTable) event.getSource()).getRowIndex();
                inveInformeOperSelected = ((InveInformeOper) event.getData()).clone();
                listaLectorFinal = inveLectorFacade.findValFinalByProyecto(inveInformeOperSelected.getInveProyecto());
                controlNotif();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ValoracionInfoFinalJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            inveInformeOperSelected = null;
            listaLectorFinal.clear();
            deSeleccionarListas();
        }

    }

    @Override
    public void preUpdate() {
        if (super.getOpcion().equals("actualizar")) {
            List<String> selec = Arrays.asList(selectedItemsEsquema);
            inveValoracionEdit.setValImportancia("S");
            for (int i = 0; i < selec.size(); i++) {
                int val = Integer.parseInt(selec.get(i));
                switch (val) {
                    case 3:
                        inveValoracionEdit.setValCorrespondencia("S");
                        break;
                    case 4:
                        inveValoracionEdit.setValAporteRelevante("S");
                        break;
                    case 5:
                        inveValoracionEdit.setValComentario("S");
                        break;
                    case 6:
                        inveValoracionEdit.setValRecomendacion("S");
                        break;
                    default:
                        break;
                }
            }

            doc.setDocDirDestino(doc.getDocDirGeneral() + "_" + inveLectorSelected.getInveProyecto().getPryCodigo());
            if (inveLectorEdit.getInveValoracionCollection().isEmpty()) {
                inveLectorEdit.getInveValoracionCollection().add(inveValoracionEdit);
            }
            inveLectorEdit.setLecFechaEntregaVal(inveValoracionEdit.getValFechaRecep());
            inveValoracionEdit.setInveLector(inveLectorEdit);
            inveValoracionEdit.setValUsuarioRecep(usr.getUsuCedPass());
            inveValoracionEdit.setValFechaCrea(new Date());
            inveValoracionEdit.setValUsuarioCrea(usr.getUsuCedPass());
        } else {
            //  POR APROBACIÓN DE INVESTIGACION SE GENERAN SOLICITUDES DE CONTRATO 'SOLO' A LOS GRADUADOS DE MAESTRÍA Y DOCTORADO y DOCENTES CONTRATADOS
            if (inveInformeOperSelected.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo() == 3 || inveInformeOperSelected.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo() == 2) {
                if (inveInformeOperSelected.getInveProyecto().getPryNumeroSolicContrato() == null) {
                    inveInformeOperSelected.getInveProyecto().setPryFechaSolicContrato(fechaSolicContrato);
                    inveInformeOperSelected.getInveProyecto().setPryNumeroSolicContrato(nroSolic);
                }
            }
            inveSeguimiento.setInveProyecto(inveInformeOperSelected.getInveProyecto());
            if (inveInformeOperSelected.getInveSeguimiento().getSegCodigo() < 0
                    || inveInformeOperSelected.getInveSeguimiento().getSegNotifAprobado() == null) {// registro nuevo
                inveSeguimiento.setSegNotifAprobado(aprobado ? "S" : hacerModif ? null : "N");// marcar la notificacion como notificación de aprobación si el analisis de los registros así lo determinan
                inveSeguimiento.setInveTipoSeguimiento(new InveTipoSeguimiento(new Short("3")));// Valoración de proyecto
                inveSeguimiento.setSegTipoComunic("OFICIO");
                inveSeguimiento.setSegNroComunic(inveSeguimiento.getSegNroComunic().toUpperCase());
                inveSeguimiento.setSegEstadoReg("A");
                inveSeguimiento.setSegFechaCrea(new Date());
                inveSeguimiento.setSegUsuarioCrea(usr.getUsuCedPass());
            }
        }
    }

    @Override
    public void update() {
        // REGISTO DE VALORACIONES
        inveValoracionFacade.edit(inveValoracionEdit, doc, inveSeguimiento);
    }

    @Override
    public void insert() {
        // NOTIFICACIONES POR APROBACIÓN O NO APROBACION
        inveSeguimientoFacade.create(inveSeguimiento, listaLectorFinal, aprobado);
    }

    @Override
    public boolean checkRules() {
        boolean ok = true;
        if (super.getOpcion().equals("insertar")) {
            if (inveInformeOperSelected.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo() == 3 || inveInformeOperSelected.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo() == 2) {
                BigDecimal monto = inveSeguimientoFacade.obtenerMontoxBeca(inveInformeOperSelected.getInveProyecto());
                if ((monto == null || monto.equals(BigDecimal.ZERO))) {
                    this.setMensaje("No ha registrado el monto por Beca de Investigación en el proyecto");
                    ok = false;
                }
            }
        }
        return ok;

    }

    @Override
    public void postUpdate() {
        if (super.getOpcion().equals("actualizar")) {
            listaLectorFinal = inveLectorFacade.findValFinalByProyecto(inveInformeOperSelected.getInveProyecto());
            controlNotif();
            this.setMensaje("Valoración registrada !");
            RequestContext.getCurrentInstance().update("form:dataTableFinal:" + filaProyecto + ":dataTableLector");
            reset();
            RequestContext.getCurrentInstance().execute("PF('widgetLector').unselectAllRows();");
        } else {
            this.setMensaje("Notificación " + inveSeguimiento.getSegNroComunic().toUpperCase() + " registrada !");
            listaInfoOper = inveInformeOperFacade.findBy(anio);
            inveSeguimiento = null;
            RequestContext.getCurrentInstance().update("form:dataTableFinal");
        }
        this.setIconMensaje("visto.jpg");
        RequestContext.getCurrentInstance().update("dialogMensaje");
        RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
    }

    @Override
    public void cancelarButton() {
        reset();
        RequestContext.getCurrentInstance().reset("formEdit:pnlGrid");
        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
    }

    public void verSolicitud(ActionEvent ae) {
        inveInformeOperSelected = (InveInformeOper) ae.getComponent().getAttributes().get("informe");

        String urlReporte = "subsist=" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subsist")
                + "&reporte=repSolicContratoBeca&pNroSolicitud=" + inveInformeOperSelected.getInveProyecto().getPryNumeroSolicContrato();
//        System.out.println(urlReporte);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte + "&pfdrid_c=true");
        super.setRepEmbebVisible(true);
        RequestContext.getCurrentInstance().update("pdfDialog");
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
