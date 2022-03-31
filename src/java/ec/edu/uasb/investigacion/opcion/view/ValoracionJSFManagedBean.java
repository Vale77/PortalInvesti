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
import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveTipoSeguimiento;
import ec.edu.uasb.investigacion.entities.InveValoracion;
import ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveEstadoValoracionFacadeLocal;
import ec.edu.uasb.investigacion.session.InveLectorFacadeLocal;
import ec.edu.uasb.investigacion.session.InveValoracionFacadeLocal;
import ec.edu.uasb.principal.entities.PrinClaseDocumento;
import ec.edu.uasb.principal.entities.PrinDocumento;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author victor.barba
 */
@Named(value = "valoracionBean")
@ViewScoped
public class ValoracionJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private InveLectorFacadeLocal inveLectorFacade;
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
    private List<InveLector> listaLectores = new ArrayList<>();
    private List<InveLector> listaLectoresFiltrados;
    private List<InveEstadoValoracion> listaEstados = new ArrayList<>();
    private InveLector inveLectorSelected;
    private InveLector lectorEdit;
    private InveValoracion inveValoracionEdit;
    private List<SelectItem> esquema = new ArrayList<>();
    private String[] selectedItemsEsquema;
    private PrinDocumento doc;
    private InveSeguimiento inveSeguimiento;
    private List<InveConvocatoria> listaConvocatorias = new ArrayList<>();
    private InveConvocatoria convocatoria;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public List<InveLector> getListaLectores() {
        return listaLectores;
    }

    public List<InveLector> getListaLectoresFiltrados() {
        return listaLectoresFiltrados;
    }

    public void setListaLectoresFiltrados(List<InveLector> listaLectoresFiltrados) {
        this.listaLectoresFiltrados = listaLectoresFiltrados;
    }

    public List<AnioAcademico> getListaAnios() {
        return listaAnios;
    }

    public List<InveEstadoValoracion> getListaEstados() {
        return listaEstados;
    }

    public InveLector getInveLectorSelected() {
        return inveLectorSelected;
    }

    public void setInveLectorSelected(InveLector inveLectorSelected) {
        this.inveLectorSelected = inveLectorSelected;
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

    public InveValoracion getInveValoracionEdit() {
        return inveValoracionEdit;
    }

    public void setInveValoracionEdit(InveValoracion inveValoracionEdit) {
        this.inveValoracionEdit = inveValoracionEdit;
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

    public PrinDocumento getDoc() {
        return doc;
    }

    public void setDoc(PrinDocumento doc) {
        this.doc = doc;
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
    public ValoracionJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/opciones/lectura/editarValorac.xhtml");
        super.setTituloModalEdicion("Registrar valoración");
        super.getListaWidgetPantallas().add("widgetValora");
        super.setWidthEditDisplay("1048");
        super.setRenderEdicAdic(true);
        super.setRenderPDF(true);
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
        SelectItemGroup analisis = new SelectItemGroup("ANÁLISIS DEL PROYECTO");
        analisis.setSelectItems(new SelectItem[]{
            new SelectItem("3", "Importancia de la Investigación"),
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
        listaEstados = estadoValoracionFacade.getEstadoBy(new InveRol(new Short("1")));
        retrieveBy();
        retrieve();
        super.init();
    }

    @Override
    public void reset() {
        lectorEdit = null;
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
        inveLectorSelected = null;
        listaLectores.clear();
        if (anio != null) {
            listaLectores = inveLectorFacade.findBy(anio);
        } else if (convocatoria != null) {
            listaLectores = inveLectorFacade.findValoracionBy(convocatoria);
        }

        RequestContext.getCurrentInstance().execute("PF('widgetValora').clearFilters();");
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

    private Date getFechaDespues(Date desde, int mesesDespues) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(desde);
        calendar.add(Calendar.MONTH, mesesDespues);
        return calendar.getTime();
    }

    public void calcFechaLimite() {
        if (lectorEdit.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo().equals(new Short("1"))) {
            inveSeguimiento.setSegFechaLimite(getFechaDespues(inveSeguimiento.getSegFechaInicial(), 6));
        } else {
            inveSeguimiento.setSegFechaLimite(getFechaDespues(inveSeguimiento.getSegFechaInicial(), 3));
        }
    }

    public void resetFechas() {
        inveSeguimiento.setSegFechaInicial(null);
        inveSeguimiento.setSegFechaLimite(null);
    }

    @Override
    public String opcionItem(String op) {
        super.opcionItem(op);
        lectorEdit = inveLectorSelected;
        inveValoracionEdit = new InveValoracion();
        doc = new PrinDocumento();
        selectedItemsEsquema = null;
        inveSeguimiento = new InveSeguimiento();
        return null;
    }

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

    @Override
    public boolean checkRules() {
        boolean check = true;
        int total = ((SelectItemGroup) esquema.get(0)).getSelectItems().length + ((SelectItemGroup) esquema.get(1)).getSelectItems().length;
        if (Arrays.asList(selectedItemsEsquema).size() != total) {
            this.setMensaje("ATENCIÓN. Verificaciones de Datos y Análisis del Proyecto son obligatorias.</br>Por favor, verifiquelas con la estrucura del Informe de Valoración");
            check = false;
        }
        return check;
    }

    @Override
    public void preUpdate() {
        List<String> selec = Arrays.asList(selectedItemsEsquema);
        for (int i = 0; i < selec.size(); i++) {
            int val = Integer.parseInt(selec.get(i));
            switch (val) {
                case 3:
                    inveValoracionEdit.setValImportancia("S");
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

        inveSeguimiento.setInveProyecto(inveLectorSelected.getInveProyecto());
        inveSeguimiento.setInveTipoSeguimiento(new InveTipoSeguimiento(new Short("2")));// Valoración de proyecto
        inveSeguimiento.setSegTipoComunic("OFICIO");
        inveSeguimiento.setSegNroComunic(inveSeguimiento.getSegNroComunic().toUpperCase());
        inveSeguimiento.setSegEstadoReg("A");
        inveSeguimiento.setSegFechaComunic(inveValoracionEdit.getValFechaRecep());
        inveSeguimiento.setSegFechaCrea(new Date());
        inveSeguimiento.setSegUsuarioCrea(usr.getUsuCedPass());
//        inveSeguimiento.setSegFechaInicial(lectorEdit.getLecFechaDesignacion());

        doc.setDocDirDestino(doc.getDocDirGeneral() + "_" + inveLectorSelected.getInveProyecto().getPryCodigo());
        if (lectorEdit.getInveValoracionCollection().isEmpty()) {
            lectorEdit.getInveValoracionCollection().add(inveValoracionEdit);
        }
        lectorEdit.setLecFechaEntregaVal(inveValoracionEdit.getValFechaRecep());

        inveValoracionEdit.setInveLector(lectorEdit);
        inveValoracionEdit.setValUsuarioRecep(usr.getUsuCedPass());
        inveValoracionEdit.setValFechaCrea(new Date());
        inveValoracionEdit.setValUsuarioCrea(usr.getUsuCedPass());
    }

    @Override
    public void update() {
        inveValoracionFacade.edit(inveValoracionEdit, doc, inveSeguimiento);
    }

    @Override
    public void postUpdate() {
        this.setMensaje("Valoración registrada !");
        this.setIconMensaje("visto.jpg");
        RequestContext.getCurrentInstance().update("dialogMensaje");
        RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
        super.postUpdate();
        RequestContext.getCurrentInstance().update("formValoracion");
    }

    @Override
    public void cancelarButton() {
        super.cancelarButton();
        RequestContext.getCurrentInstance().reset("formEdit");
        RequestContext.getCurrentInstance().update("formEdit");
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
