/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.opcion.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.exception.InscripcionException;
import ec.edu.uasb.exception.ProyectoException;
import ec.edu.uasb.external.entities.AnioAcademico;
import ec.edu.uasb.external.entities.AreaAcademica;
import ec.edu.uasb.external.entities.Titulo;
import ec.edu.uasb.external.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.external.session.AreaAcademicaFacadeLocal;
import ec.edu.uasb.external.session.TituloFacadeLocal;
import ec.edu.uasb.investigacion.entities.InveAmbitoGeografico;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveDisciplinaCientifica;
import ec.edu.uasb.investigacion.entities.InveGrupo;
import ec.edu.uasb.investigacion.entities.InveGrupoInvestigacion;
import ec.edu.uasb.investigacion.entities.InveObjetivoSocioEconomico;
import ec.edu.uasb.investigacion.entities.InvePresupuesto;
import ec.edu.uasb.investigacion.entities.InvePresupuestoPK;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveRubro;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveTipoProyecto;
import ec.edu.uasb.investigacion.entities.InveTipoSeguimiento;
import ec.edu.uasb.investigacion.session.InveAmbitoGeograficoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveDisciplinaCientificaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveGrupoInvestigacionFacadeLocal;
import ec.edu.uasb.investigacion.session.InveObjetivoSocioEconomicoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveProyectoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveRubroGrupoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveSeguimientoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveTipoProyectoFacadeLocal;
import ec.edu.uasb.principal.entities.PrinCategoriaInvest;
import ec.edu.uasb.principal.entities.PrinCiudad;
import ec.edu.uasb.principal.entities.PrinCiudadPK;
import ec.edu.uasb.principal.entities.PrinClaseDocumento;
import ec.edu.uasb.principal.entities.PrinDocumento;
import ec.edu.uasb.principal.entities.PrinEntidad;
import ec.edu.uasb.principal.entities.PrinPais;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.session.PrinCategoriaInvestFacadeLocal;
import ec.edu.uasb.principal.session.PrinCiudadFacadeLocal;
import ec.edu.uasb.principal.session.PrinClaseDocumentoFacadeLocal;
import ec.edu.uasb.principal.session.PrinDocumentoFacadeLocal;
import ec.edu.uasb.principal.session.PrinEntidadFacadeLocal;
import ec.edu.uasb.principal.session.PrinPaisFacadeLocal;
import ec.edu.uasb.principal.session.PrinPersonaFacadeLocal;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.vinculacion.entities.VincCine;
import ec.edu.uasb.vinculacion.session.VincCineFacadeLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.Table;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author victor.barba
 */
@Named(value = "proyectoBean")
@ViewScoped
public class ProyectoJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveRubroGrupoFacadeLocal inveRubroGrupoFacade;
    @EJB
    private InveSeguimientoFacadeLocal inveSeguimientoFacade;
    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;
    @EJB
    private PrinDocumentoFacadeLocal prinDocumentoFacade;
    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private InveGrupoInvestigacionFacadeLocal inveGrupoInvestigacionFacade;
    @EJB
    private InveDisciplinaCientificaFacadeLocal inveDisciplinaCientificaFacade;
    @EJB
    private AreaAcademicaFacadeLocal areaAcademicaFacade;
    @EJB
    private PrinCiudadFacadeLocal prinCiudadFacade;
    @EJB
    private PrinPaisFacadeLocal prinPaisFacade;
    @EJB
    private PrinEntidadFacadeLocal prinEntidadFacade;
    @EJB
    private PrinPersonaFacadeLocal prinPersonaFacade;
    @EJB
    private VincCineFacadeLocal vincCineFacade;
    @EJB
    private InveObjetivoSocioEconomicoFacadeLocal inveObjetivoSocioEconomicoFacade;
    @EJB
    private InveAmbitoGeograficoFacadeLocal inveAmbitoGeograficoFacade;
    @EJB
    private PrinClaseDocumentoFacadeLocal prinClaseDocumentoFacade;
    @EJB
    private InveConvocatoriaFacadeLocal inveConvocatoriaFacade;
    @EJB
    private TituloFacadeLocal tituloFacade;
    @EJB
    private PrinCategoriaInvestFacadeLocal prinCategoriaInvestFacade;
    @EJB
    private InveTipoProyectoFacadeLocal inveTipoProyectoFacade;

    private final String modulo;
    private final Usuario usr;
    private String filtrarAreas;
    private String perfil = new String();

    private Short anio;
    private List<AnioAcademico> listaAnios = new ArrayList<>();

    private List<InveProyecto> listaProyectos = new ArrayList<>();
    private List<InveProyecto> filteredProyectos;
    private InveProyecto inveProyectoEdit;
    private boolean valorEstado;
    private InveProyecto inveProyectoSelected;
    private PrinCategoriaInvest categoria;
    private boolean habilitarAddCatalogos = true;
    private String botonAdd;
    private Short grupoRubro;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public Short getGrupoRubro() {
        return grupoRubro;
    }

    public void setGrupoRubro(Short grupoRubro) {
        this.grupoRubro = grupoRubro;
    }

    public PrinCategoriaInvest getCategoria() {
        return categoria;
    }

    public void setCategoria(PrinCategoriaInvest categoria) {
        this.categoria = categoria;
    }

    public boolean isValorEstado() {
        return valorEstado;
    }

    public void setValorEstado(boolean valorEstado) {
        this.valorEstado = valorEstado;
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

    public List<InveProyecto> getListaProyectos() {
        return listaProyectos;
    }

    public InveProyecto getInveProyectoEdit() {
        return inveProyectoEdit;
    }

    public void setInveProyectoEdit(InveProyecto inveProyectoEdit) {
        this.inveProyectoEdit = inveProyectoEdit;
    }

    public InveProyecto getInveProyectoSelected() {
        return inveProyectoSelected;
    }

    public void setInveProyectoSelected(InveProyecto inveProyectoSelected) {
        this.inveProyectoSelected = inveProyectoSelected;
    }

    public List<InveProyecto> getFilteredProyectos() {
        return filteredProyectos;
    }

    public void setFilteredProyectos(List<InveProyecto> filteredProyectos) {
        this.filteredProyectos = filteredProyectos;
    }

    public boolean isHabilitarAddCatalogos() {
        return habilitarAddCatalogos;
    }

    public void setHabilitarAddCatalogos(boolean habilitarAddCatalogos) {
        this.habilitarAddCatalogos = habilitarAddCatalogos;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables Investigador">
    private List<PrinPais> listaPais = new ArrayList<>();
    private List<PrinCiudad> listaCiudades = new ArrayList<>();
    private List<PrinEntidad> listaUniversidades = new ArrayList<>();
    private Short paiCodigo;
    private Integer ciuCodigo;
    private String tipoDoc = null;
    private String nroDocumento;
    private PrinPersona persona;
    private PrinPersona personaOrig;
    private Titulo titulo;
    private String codigoCI;

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public String getCodigoCI() {
        return codigoCI;
    }

    public void setCodigoCI(String codigoCI) {
        this.codigoCI = codigoCI;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public PrinPersona getPersona() {
        return persona;
    }

    public PrinPersona getPersonaOrig() {
        return personaOrig;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public List<PrinCiudad> getListaCiudades() {
        return listaCiudades;
    }

    public List<PrinPais> getListaPais() {
        return listaPais;
    }

    public Integer getCiuCodigo() {
        return ciuCodigo;
    }

    public void setCiuCodigo(Integer ciuCodigo) {
        this.ciuCodigo = ciuCodigo;
    }

    public Short getPaiCodigo() {
        return paiCodigo;
    }

    public void setPaiCodigo(Short paiCodigo) {
        this.paiCodigo = paiCodigo;
    }

    public List<PrinEntidad> getListaUniversidades() {
        return listaUniversidades;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables Info Adicional">
    private String tipoInvestig;
    private List<InveGrupoInvestigacion> listaGrupoInvestigacion = new ArrayList<>();
    private List<InveDisciplinaCientifica> listaCientificas = new ArrayList<>();
    private List<InveObjetivoSocioEconomico> listaEconomicos = new ArrayList<>();
    private List<InveAmbitoGeografico> listaGeograficos = new ArrayList<>();
    private List<VincCine> listaAreaCine = new ArrayList<>();
    private List<VincCine> listaSubAreaCine = new ArrayList<>();
    private List<VincCine> listaCine = new ArrayList<>();
    private VincCine cine;
    private VincCine areaCine;
    private VincCine subAreaCine;
    private Short areaCodigo;
    private List<AreaAcademica> listaAreas = new ArrayList<>();
    private List<InveConvocatoria> listaConvocatorias = new ArrayList<>();
    private InveConvocatoria convocatoria;
    private InveGrupoInvestigacion grupoInvestigacionEdit;
    private InveDisciplinaCientifica inveDiscipEdit;
    private InveObjetivoSocioEconomico inveOsoeEdit;
    private InveAmbitoGeografico inveAmbitoGeograficoEdit;

    public InveAmbitoGeografico getInveAmbitoGeograficoEdit() {
        return inveAmbitoGeograficoEdit;
    }

    public void setInveAmbitoGeograficoEdit(InveAmbitoGeografico inveAmbitoGeograficoEdit) {
        this.inveAmbitoGeograficoEdit = inveAmbitoGeograficoEdit;
    }

    public InveDisciplinaCientifica getInveDiscipEdit() {
        return inveDiscipEdit;
    }

    public void setInveDiscipEdit(InveDisciplinaCientifica inveDiscipEdit) {
        this.inveDiscipEdit = inveDiscipEdit;
    }

    public InveObjetivoSocioEconomico getInveOsoeEdit() {
        return inveOsoeEdit;
    }

    public void setInveOsoeEdit(InveObjetivoSocioEconomico inveOsoeEdit) {
        this.inveOsoeEdit = inveOsoeEdit;
    }

    public InveGrupoInvestigacion getGrupoInvestigacionEdit() {
        return grupoInvestigacionEdit;
    }

    public void setGrupoInvestigacionEdit(InveGrupoInvestigacion grupoInvestigacionEdit) {
        this.grupoInvestigacionEdit = grupoInvestigacionEdit;
    }

    public Short getAreaCodigo() {
        return areaCodigo;
    }

    public void setAreaCodigo(Short areaCodigo) {
        this.areaCodigo = areaCodigo;
    }

    public List<AreaAcademica> getListaAreas() {
        return listaAreas;
    }

    public String getTipoInvestig() {
        return tipoInvestig;
    }

    public void setTipoInvestig(String tipoInvestig) {
        this.tipoInvestig = tipoInvestig;
    }

    public InveConvocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(InveConvocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    public List<InveConvocatoria> getListaConvocatorias() {
        return listaConvocatorias;
    }

    public List<VincCine> getListaAreaCine() {
        return listaAreaCine;
    }

    public List<VincCine> getListaSubAreaCine() {
        return listaSubAreaCine;
    }

    public List<VincCine> getListaCine() {
        return listaCine;
    }

    public VincCine getCine() {
        return cine;
    }

    public void setCine(VincCine cine) {
        this.cine = cine;
    }

    public VincCine getAreaCine() {
        return areaCine;
    }

    public void setAreaCine(VincCine areaCine) {
        this.areaCine = areaCine;
    }

    public VincCine getSubAreaCine() {
        return subAreaCine;
    }

    public void setSubAreaCine(VincCine subAreaCine) {
        this.subAreaCine = subAreaCine;
    }

    public List<InveDisciplinaCientifica> getListaCientificas() {
        return listaCientificas;
    }

    public List<InveGrupoInvestigacion> getListaGrupoInvestigacion() {
        return listaGrupoInvestigacion;
    }

    public List<InveObjetivoSocioEconomico> getListaEconomicos() {
        return listaEconomicos;
    }

    public List<InveAmbitoGeografico> getListaGeograficos() {
        return listaGeograficos;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables Documento">
    private List<PrinClaseDocumento> listaClaseDocs = new ArrayList<>();
    private List<PrinDocumento> listaDocumentos = new ArrayList<>();
    private PrinDocumento prinDocumento = new PrinDocumento();
    private List<Short> clasesDocs = new ArrayList<>();

    public PrinDocumento getPrinDocumento() {
        return prinDocumento;
    }

    public void setPrinDocumento(PrinDocumento prinDocumento) {
        this.prinDocumento = prinDocumento;
    }

    public List<PrinClaseDocumento> getListaClaseDocs() {
        return listaClaseDocs;
    }

    public List<PrinDocumento> getListaDocumentos() {
        return listaDocumentos;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables Presupuesto">
    private List<InvePresupuesto> listaPresup = new ArrayList<>();
    private List<InveRubro> listaRubros = new ArrayList<>();

    public List<InvePresupuesto> getListaPresup() {
        return listaPresup;
    }

    public List<InveRubro> getListaRubros() {
        return listaRubros;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables REACTIVACIÓN">
    private InveSeguimiento inveSeguimiento;

    public InveSeguimiento getInveSeguimiento() {
        return inveSeguimiento;
    }

    public void setInveSeguimiento(InveSeguimiento inveSeguimiento) {
        this.inveSeguimiento = inveSeguimiento;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Check">
    private List<SelectItem> esquema = new ArrayList<>();
    private List<InveTipoProyecto> listaTipoProy = new ArrayList<>();
    private List<InveProyecto> listaProyAdjunto = new ArrayList<>();
    private String[] selectedItemsEsquema;

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

    public List<InveTipoProyecto> getListaTipoProy() {
        return listaTipoProy;
    }

    public List<InveProyecto> getListaProyAdjunto() {
        return listaProyAdjunto;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Opciones Menu de pantalla">
    @Override
    public boolean isDisabledNew() {
        this.setDisabledNew(anio == null || convocatoria == null || convocatoria.getCvoEstado().equals("C"));
        // this.setDisabledNew(convocatoria == null || convocatoria.getCvoEstado().equals("C")); MV
        return super.isDisabledNew();
    }

    @Override
    public boolean isDisabledDelete() {
        if (convocatoria != null && convocatoria.getCvoEstado().equals("A")) {
            if (inveProyectoEdit.getPryEstadoReg() != null) {
                this.setDisabledDelete(!inveProyectoEdit.getPryEstadoReg().equals("I"));
            }
        } else {
            setDisabledDelete(true);
        }
        return super.isDisabledDelete();
    }

    @Override
    public boolean isDisabledEdit() {
        if (inveProyectoSelected != null && inveProyectoSelected.getPryEstadoReg().equals("X")) {
            return true;
        } else {
            return super.isDisabledEdit();
        }
    }
//</editor-fold>
    
    public ProyectoJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/opciones/proyecto/nuevoProy.xhtml");
        super.setTituloModalEdicion("Registro de Proyectos");
        super.getListaWidgetPantallas().add("widgetProy");
        super.setWidthEditDisplay("1048");
        super.setVisibleBotonGrabar(false);
        super.setVisibleBotonCancelar(false);
        super.setPosicionEdicion("top");
        super.setRenderEdicAdic(true);
        // seguridad en perfiles y areas
        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        modulo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subsist");
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
        // Clase de documentos para registro de proyecto
        clasesDocs.add(new Short("4"));
        clasesDocs.add(new Short("5"));
        clasesDocs.add(new Short("6"));
        // CheckList
        SelectItemGroup elementos = new SelectItemGroup("2. ELEMENTOS DEL PROYECTO");
        elementos.setSelectItems(new SelectItem[]{
            new SelectItem("10", "Justificación"),
            new SelectItem("11", "Relevancia"),
            new SelectItem("12", "Planteamiento"),
            new SelectItem("13", "Metodología"),
            new SelectItem("14", "Plan inicial")}
        );
        SelectItemGroup crono = new SelectItemGroup("3. CRONOGRAMA");
        crono.setSelectItems(new SelectItem[]{
            new SelectItem("15", "Mensual (y/o Valorado)")}
        );
        SelectItemGroup recursos = new SelectItemGroup("4. RECURSOS HUMANOS");
        recursos.setSelectItems(new SelectItem[]{
            new SelectItem("16", "Ayudantes de investigación")}
        );
        SelectItemGroup presup = new SelectItemGroup("5. PRESUPUESTO");
        presup.setSelectItems(new SelectItem[]{
            new SelectItem("17", "Rubros y montos")}
        );
        esquema.add(elementos);
        esquema.add(crono);
//        esquema.add(recursos);
//        esquema.add(presup);
    }

    @Override
    public void init() {
        listaAnios = anioAcademicoFacade.getListaAnios();
        listaGrupoInvestigacion = inveGrupoInvestigacionFacade.findAll();
        listaTipoProy = inveTipoProyectoFacade.findAll();
        retrieveBy();
        retrieve();
        RequestContext.getCurrentInstance().execute("PF('widgetVarCtx').hide();");
        super.init();
    }

    @Override
    public void reset() {
        tipoDoc = null;
        nroDocumento = null;
        persona = new PrinPersona();
        persona.setPrinNacionalidad(new PrinPais());
        paiCodigo = null;
        ciuCodigo = null;
        titulo = null;
        tipoInvestig = null;
        areaCodigo = null;
        inveProyectoEdit = new InveProyecto();
        inveProyectoEdit.setPryEstadoReg("R");
        inveProyectoEdit.setPryOrigenIngreso("S");
        areaCine = null;
        subAreaCine = null;
        cine = null;
        inveDiscipEdit = null;
        inveOsoeEdit = null;
        inveAmbitoGeograficoEdit = null;
        grupoInvestigacionEdit = null;
        selectedItemsEsquema = null;
        codigoCI = null;
        categoria = null;
        listaPresup.clear();
    }

    public String resetFilter() {
        inveProyectoSelected = null;
        RequestContext.getCurrentInstance().update("formMenuBar");
        return null;
    }

    //<editor-fold defaultstate="collapsed" desc="selección de fila">
    @Override
    public void onRowSelect(SelectEvent event) {
        try {
            inveProyectoEdit = inveProyectoSelected.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ProyectoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.onRowSelect(event);
    }

    @Override
    public void onRowUnselect(UnselectEvent event) {
        inveProyectoSelected = null;
        super.onRowUnselect(event);
    }
//</editor-fold>

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

    //<editor-fold defaultstate="collapsed" desc="recuperar listados">
    public void retrieveBy() {
        inveProyectoSelected = null;
        listaProyectos.clear();
        if (anio != null && convocatoria != null) {
            listaProyectos = inveProyectoFacade.findBy(anio, convocatoria, null);
        }else if (convocatoria != null){
        listaProyectos =  inveProyectoFacade.findConvocatoriaBy(convocatoria, null);
        }
    }

    public void retrieveByTipo() {
        listaProyAdjunto.clear();
        if (inveProyectoEdit.getInveTipoProyecto() != null && inveProyectoEdit.getInveTipoProyecto().getTipCodigo().equals(new Short("2"))) {
            listaProyAdjunto = inveProyectoFacade.findBy(anio, convocatoria, inveProyectoEdit.getInveTipoProyecto());// proyectos para asociar 
        }

    }

    public void retrieve() {
        listaConvocatorias = inveConvocatoriaFacade.findAll();
        listaAreaCine = vincCineFacade.findBy(null);
        listaAreas = areaAcademicaFacade.getListaAreasDepartamentos();
        listaPais = prinPaisFacade.findAllorde();
        listaUniversidades = prinEntidadFacade.findAllUniversidades();
        listaClaseDocs = prinClaseDocumentoFacade.findClaseDocs(clasesDocs);
        listaEconomicos = inveObjetivoSocioEconomicoFacade.findAll();
        listaCientificas = inveDisciplinaCientificaFacade.findAll();
        listaGeograficos = inveAmbitoGeograficoFacade.findAll();
    }

    public void retrieveCines(String op) {
        cine = null;
        if (op.equals("Subarea")) {
            listaSubAreaCine = vincCineFacade.findBy(areaCine.getCinCodigo());
            listaCine.clear();
            subAreaCine = null;
        } else {
            listaCine = vincCineFacade.findBy(subAreaCine.getCinCodigo());
        }
    }

    public void cargarCiudades() {
        listaCiudades.clear();
        if (paiCodigo != null) {
            listaCiudades = prinCiudadFacade.findAllorde(paiCodigo);
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="recuperar datos investigador y proyecto">
    private void recupInvestigador() {
        if (inveProyectoEdit.getPryCorreo() == null && persona.getPerEmail() != null) {
            inveProyectoEdit.setPryCorreo(persona.getPerEmail());
        }
        paiCodigo = (persona.getPrinCiudad() == null ? null : persona.getPrinCiudad().getPrinPais() == null ? null : persona.getPrinCiudad().getPrinPais().getPaiCodigo());
        cargarCiudades();
        ciuCodigo = (persona.getPrinCiudad() != null ? persona.getPrinCiudad().getPrinCiudadPK().getCiuCodigo() : null);
        tipoDoc = persona.getPerTipoDoc();
        nroDocumento = persona.getPerIdDoc();
        if (inveProyectoEdit.getPrinCategoriaInvest() == null) {
            categoria = prinCategoriaInvestFacade.find(categoria.getCaiCodigo());
        } else {
            categoria = prinCategoriaInvestFacade.find(inveProyectoEdit.getPrinCategoriaInvest().getCaiCodigo());
        }
        grupoRubro = null;
        if (categoria != null) {
            switch (categoria.getCaiCodigo()) {
                case 1:
                    titulo = tituloFacade.find(persona.getPerCodigo(), "Docentes");
                    grupoRubro = new Short("1"); // Rubros para docente de planta
                    break;
                case 2:
                    titulo = tituloFacade.find(persona.getPerCodigo(), "Docentes");
                    grupoRubro = new Short("5"); // Rubros para docente contratado
                    break;
                case 4:
                    titulo = tituloFacade.find(persona.getPerCodigo(), "Docentes");
                    grupoRubro = new Short("2"); // Rubros para Asistente académico
                    break;
                case 3:
                    titulo = tituloFacade.find(persona.getPerCodigo(), "Graduados Mae");
                    if (titulo != null) {
                        grupoRubro = new Short("6");// Rubros para graduado de maestría
                    } else {
                        titulo = tituloFacade.find(persona.getPerCodigo(), "Graduados Doc");
                        grupoRubro = new Short("4");// Rubros para graduado de doctorado
                    }
                    break;
                case 5:
                    titulo = tituloFacade.find(persona.getPerCodigo(), "Estudiantes");
                    grupoRubro = new Short("3"); // Rubros para estudiantes de doctorado
                    break;
            }

        }
    }

    private void recupProyecto() {
        codigoCI = inveProyectoEdit.getPryCodigoCi();
        areaCodigo = inveProyectoEdit.getPryAreaAcad();
        tipoInvestig = inveProyectoEdit.getPryTipoInvestig();
        cine = inveProyectoEdit.getVincCine();
        if (cine != null) {
            subAreaCine = cine.getVincCine();
            areaCine = subAreaCine.getVincCine();
            listaSubAreaCine = vincCineFacade.findBy(areaCine.getCinCodigo());
            listaCine = vincCineFacade.findBy(subAreaCine.getCinCodigo());
        }
        if (inveProyectoEdit.getPryCodigo() == null) {// registro nuevo
            listaRubros = inveRubroGrupoFacade.getRubrosXGrupo(grupoRubro);
            for (InveRubro inveRubro : listaRubros) {
                InvePresupuesto presup = new InvePresupuesto();
                presup.setInveRubro(inveRubro);
                presup.setInveProyecto(inveProyectoEdit);
                presup.setInvePresupuestoPK(new InvePresupuestoPK(null, inveRubro.getRubCodigo()));
                listaPresup.add(presup);
            }
        } else {
            listaRubros = inveRubroGrupoFacade.getRubrosXGrupo(inveProyectoEdit.getInveGrupo().getGrpCodigo());
            listaPresup.addAll(inveProyectoEdit.getInvePresupuestoCollection());
        }
        retrieveByTipo();
        inveDiscipEdit = inveProyectoEdit.getInveDisciplinaCientifica();
        inveOsoeEdit = inveProyectoEdit.getInveObjetivoSocioEconomico();
        inveAmbitoGeograficoEdit = inveProyectoEdit.getInveAmbitoGeografico();
        grupoInvestigacionEdit = inveProyectoEdit.getInveGrupoInvestigacion();
        if (inveProyectoEdit.getPryCodigo() == null) {// registro nuevo
            initListaArchivos();
        } else {
            listaDocumentos = prinDocumentoFacade.getDocumentos(modulo, InveProyecto.class.getAnnotation(Table.class).name(), inveProyectoEdit.getPryCodigo());
        }
        List<String> temp = new ArrayList<>();
        if (inveProyectoEdit.getPryJustificacion() != null && inveProyectoEdit.getPryJustificacion().equals("S")) {
            temp.add("10");
        }
        if (inveProyectoEdit.getPryRelevancia() != null && inveProyectoEdit.getPryRelevancia().equals("S")) {
            temp.add("11");
        }
        if (inveProyectoEdit.getPryPlanteamiento() != null && inveProyectoEdit.getPryPlanteamiento().equals("S")) {
            temp.add("12");
        }
        if (inveProyectoEdit.getPryMetodologia() != null && inveProyectoEdit.getPryMetodologia().equals("S")) {
            temp.add("13");
        }
        if (inveProyectoEdit.getPryPlanInicial() != null && inveProyectoEdit.getPryPlanInicial().equals("S")) {
            temp.add("14");
        }
        if (inveProyectoEdit.getPryCronoMensual() != null && inveProyectoEdit.getPryCronoMensual().equals("S")) {
            temp.add("15");
        }
        selectedItemsEsquema = new String[temp.size()];
        selectedItemsEsquema = temp.toArray(selectedItemsEsquema);
    }

    private boolean estaHabilitado() { // CUMPLIMIENTO DE LA NORMA
        grupoRubro = null;
        switch (convocatoria.getInveTipoConvocatoria().getTcvCodigo()) {
            case 2: // Convocatoria ESPECIFICA
                // DOCENTES CONTRATADOS CON 2 años consecutivos como profesores - tipo contratos: 3,4,5
                String cumpleContratado = inveProyectoFacade.findDePlantaBy(persona.getPerIdDoc(), "3,4,5", new BigDecimal("2.0"));
                if (cumpleContratado.equals("S")) {
                    categoria = new PrinCategoriaInvest(new Short("2"));
                    grupoRubro = new Short("5");
                    return true;
                }
                // GRADUADOS DE MAESTRIA 
                Integer gradMaestria = inveProyectoFacade.findGraduadoBy(persona.getPerIdDoc(), new Short("2"));
                if (gradMaestria.compareTo(new Integer("0")) > 0) {
                    categoria = new PrinCategoriaInvest(new Short("3"));
                    grupoRubro = new Short("6");
                    return true;
                }
                // GRADUADOS DE  DOCTORADO
                Integer gradDoc = inveProyectoFacade.findGraduadoBy(persona.getPerIdDoc(), new Short("3"));
                if (gradDoc.compareTo(new Integer("0")) > 0) {
                    categoria = new PrinCategoriaInvest(new Short("3"));
                    grupoRubro = new Short("4");
                    return true;
                }
                break;
            case 1: // Convocatoria PERMANANTE
//                 DOCENTES planta CON 1 año como profesores - tipo contratos: 1,2, 7
                String cumpleDocente = inveProyectoFacade.findDePlantaBy(persona.getPerIdDoc(), "1,2,7", new BigDecimal("0.0"));
                if (cumpleDocente.equals("S")) {
                    categoria = new PrinCategoriaInvest(new Short("1"));
                    grupoRubro = new Short("1");
                    return true;
                }
                // DOCENTES Asistente contratado CON 1 años consecutivos como profesores - tipo contratos: 6
                String cumpleAsistente = inveProyectoFacade.findDePlantaBy(persona.getPerIdDoc(), "6", new BigDecimal("1.0"));
                if (cumpleAsistente.equals("S")) {
                    categoria = new PrinCategoriaInvest(new Short("4"));
                    grupoRubro = new Short("2");
                    return true;
                }
                // ESTUDIANTE DE DOCTORADO
                Integer estudDocto = inveProyectoFacade.findEstudianteDoctoradoBy(persona.getPerIdDoc());
                if (estudDocto.compareTo(new Integer("0")) > 0) {
                    categoria = new PrinCategoriaInvest(new Short("5"));
                    grupoRubro = new Short("3");
                    return true;
                }
                break;
        }
        return false;
    }

    private void buscarRegistro(String cedPass) throws InscripcionException {
        boolean estaHabilitada = false;
        persona = prinPersonaFacade.findByCedula(cedPass);
        if (persona != null) {
            try {
                personaOrig = persona.clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ProyectoJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            estaHabilitada = estaHabilitado();
            if (estaHabilitada) {
                recupInvestigador();
                recupProyecto();
            }
        } else {
            estaHabilitada = false;
        }
        if (estaHabilitada == false) {
            if (categoria != null) {
                switch (convocatoria.getInveTipoConvocatoria().getTcvCodigo()) {
                    case 2: // Convocatoria ESPECIFICA
                        this.setMensaje("Convocatoria " + convocatoria.getInveTipoConvocatoria().getTcvNombre()
                                + ".- Sólo de permiten:<br/><br/>- Docentes contratados<br/>- Graduados de Maestría/Doctorado"
                                + "<br/><br/>que cumplan con la norma para aplicar a un proyecto de investigación");
                        break;
                    case 1: // Convocatoria PERMANANTE
                        this.setMensaje("Convocatoria " + convocatoria.getInveTipoConvocatoria().getTcvNombre()
                                + ".- Sólo de permiten:<br/><br/>- Asistentes académicos<br/>- Docentes de planta<br/>- Estudiantes de Doctorado"
                                + "<br/><br/>que cumplan con la norma para aplicar a un proyecto de investigación");
                        break;
                }
            } else {
                if (persona != null) {
                    this.setMensaje(persona.getPerIdDoc() + " " + persona.getPerNombres() + " " + persona.getPerPrimerApellido() + " no cumple con las normas para aplicar a un proyecto de investigación");
                } else {
                    this.setMensaje("La identificación " + nroDocumento + " no registra datos");
                }
            }
            this.setIconMensaje("error.png");
            RequestContext.getCurrentInstance().update("dialogMensaje");
            RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
            reset();
        }
    }

    public String txt_NroDocValueChange() {
        try {
            buscarRegistro(nroDocumento);
            RequestContext.getCurrentInstance().execute(persona.getPerCodigo() == null ? "setfocoNombre();" : "setfocoEmail();");

        } catch (InscripcionException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            Collection paneles = new ArrayList();
            paneles.add("formEdit:pnlPerso");
            paneles.add("formEdit:panelDomicilio");
            paneles.add("formEdit:panelTitulo");
            RequestContext.getCurrentInstance().update(paneles);
        }
        return null;
    }
//</editor-fold>

    public void rowEditInit(RowEditEvent event) {
        int fila = ((DataTable) event.getSource()).getRowIndex();
        RequestContext.getCurrentInstance().execute("$(function(){PrimeFaces.focus('formEdit:dataTablePresup:" + fila + ":inputDescrip')});");
    }

    //<editor-fold defaultstate="collapsed" desc="Manejo de archivos">
    private void initListaArchivos() {
        listaDocumentos.clear();
        for (PrinClaseDocumento pcd : listaClaseDocs) {
            PrinDocumento doc = new PrinDocumento(new Long(listaDocumentos.size() + 1), modulo, "PROYECTOS", null, "INVE_PROYECTO", null, new Date(), usr.getUsuCedPass());
            doc.setPrinClaseDocumento(pcd);
            doc.setDocFechaCrea(new Date());
            doc.setDocUsuario(usr.getUsuCedPass());
            listaDocumentos.add(doc);
        }
    }

    public void subirAchivo(FileUploadEvent event) {
        for (PrinDocumento doc : listaDocumentos) {
            if (doc.getDocCodigo().equals(prinDocumento.getDocCodigo())) {
                doc.setDocExtension(event.getFile().getContentType());
                doc.setDocArchivo(event.getFile().getContents());
                doc.setDocNombre(event.getFile().getFileName());
                doc.setArchivo(event.getFile());
                doc.setArchivoOriginal(event.getFile());
                doc.setModificado(true);
                break;
            }
        }
        RequestContext.getCurrentInstance().update("formEdit:dataTableDocu");
        RequestContext.getCurrentInstance().execute("PF('dlgEditAdic').hide();");
    }

    //<editor-fold defaultstate="collapsed" desc="Retiro de proyectos">
    public void habilitarRetiro() {
        super.setVisibleBotonAdicGrabar(valorEstado);
    }

    public void retirarProyecto() {
        this.setPantallaEdicionAdic("/pages/opciones/proyecto/retirarProy.xhtml");
        this.setTituloEdicAdic("Retirar Proyecto");
        this.setPosicionEditAdic("center");
        botonAdd = "opcionRetiro";
        try {
            inveProyectoEdit = inveProyectoSelected.clone();
            valorEstado = false;
            super.setVisibleBotonAdicGrabar(false);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void activarProyecto() {
        this.setPantallaEdicionAdic("/pages/opciones/proyecto/activarProyecto.xhtml");
        this.setTituloEdicAdic("Activar Proyecto");
        this.setPosicionEditAdic("center");
        botonAdd = "opcionActivar";
        try {
            inveSeguimiento = new InveSeguimiento();
            inveProyectoEdit = inveProyectoSelected.clone();
            super.setVisibleBotonAdicGrabar(true);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

//</editor-fold>
    public void processAddButton(ActionEvent ae) {
        botonAdd = ae.getComponent().getId();
        super.setVisibleBotonAdicGrabar(true);
        switch (botonAdd) {
            case ("commandButtonDocs"):
                this.setPantallaEdicionAdic("/pages/opciones/proyecto/tabs/nuevoDocumento.xhtml");
                this.setTituloEdicAdic("Cargar documento");
                this.setPosicionEditAdic("center");
                super.setVisibleBotonAdicGrabar(false);
                Object doc = ae.getComponent().getAttributes().get("documento");
                if (doc != null) {
                    prinDocumento = (PrinDocumento) doc;
                }
                break;
            case ("commandButtonAddGin"):
                this.setPantallaEdicionAdic("/pages/parametros/editarGrupoInvest.xhtml");
                this.setTituloEdicAdic("Nuevo grupo de investigación");
                this.setPosicionEditAdic("center");
                grupoInvestigacionEdit = new InveGrupoInvestigacion();
                break;
            case ("commandButtonAddDisc"):
                this.setPantallaEdicionAdic("/pages/parametros/editarDisciplina.xhtml");
                this.setTituloEdicAdic("Nueva disciplina");
                this.setPosicionEditAdic("center");
                inveDiscipEdit = new InveDisciplinaCientifica();
                break;
            case ("commandButtonAddSocio"):
                this.setPantallaEdicionAdic("/pages/parametros/editarObjetivoSocEco.xhtml");
                this.setTituloEdicAdic("Nueva objetivo socio-económico");
                this.setPosicionEditAdic("center");
                inveOsoeEdit = new InveObjetivoSocioEconomico();
                break;
            case ("commandButtonAddAmbito"):
                this.setPantallaEdicionAdic("/pages/parametros/editarAmbitoGeo.xhtml");
                this.setTituloEdicAdic("Nueva ambito geográfico");
                this.setPosicionEditAdic("center");
                inveAmbitoGeograficoEdit = new InveAmbitoGeografico();
                break;
        }
    }

    @Override
    public void updateAdicButton_processAction(ActionEvent ae) {
        switch (botonAdd) {
            case ("commandButtonAddGin"):
                inveGrupoInvestigacionFacade.create(grupoInvestigacionEdit);
                listaGrupoInvestigacion = inveGrupoInvestigacionFacade.findAll();
                RequestContext.getCurrentInstance().update("formEdit:selectOneMenuGrupoInvest");
                break;
            case ("commandButtonAddDisc"):
                inveDisciplinaCientificaFacade.create(inveDiscipEdit);
                listaCientificas = inveDisciplinaCientificaFacade.findAll();
                RequestContext.getCurrentInstance().update("formEdit:disciplina");
                break;
            case ("commandButtonAddSocio"):
                inveObjetivoSocioEconomicoFacade.create(inveOsoeEdit);
                listaEconomicos = inveObjetivoSocioEconomicoFacade.findAll();
                RequestContext.getCurrentInstance().update("formEdit:objSocio");
                break;
            case ("commandButtonAddAmbito"):
                inveAmbitoGeograficoFacade.create(inveAmbitoGeograficoEdit);
                listaGeograficos = inveAmbitoGeograficoFacade.findAll();
                RequestContext.getCurrentInstance().update("formEdit:ambito");
                break;
            case ("opcionRetiro"):
                if (valorEstado) {// retirar proyecto con estado "X"
                    inveProyectoEdit.setPryEstadoReg("X");
                    inveProyectoFacade.edit(inveProyectoEdit);
                    this.init();
                    RequestContext.getCurrentInstance().update("form");
                }
                break;
            case ("opcionActivar"):
                inveSeguimiento.setInveTipoSeguimiento(new InveTipoSeguimiento(new Short("11")));// Por REACTIVACION
                inveSeguimiento.setSegTipoComunic("OFICIO");
                inveSeguimiento.setSegNroComunic(inveSeguimiento.getSegNroComunic().toUpperCase());
                inveSeguimiento.setSegEstadoReg("A");
                inveSeguimiento.setSegFechaComunic(inveSeguimiento.getSegFechaInicial());
                inveSeguimiento.setSegFechaCrea(new Date());
                inveSeguimiento.setSegUsuarioCrea(usr.getUsuCedPass());
                inveSeguimiento.setInveProyecto(inveProyectoEdit);
                inveSeguimientoFacade.create(inveSeguimiento);
                this.init();
                RequestContext.getCurrentInstance().update("form");
                break;
        }
        super.updateAdicButton_processAction(ae);
    }
//</editor-fold>

    public BigDecimal getSumaTotal() {
        BigDecimal sumaTotal = BigDecimal.ZERO;
        for (InvePresupuesto ip : listaPresup) {
            sumaTotal = sumaTotal.add(ip.getPreMontoAprob() == null ? BigDecimal.ZERO : ip.getPreMontoAprob());
        }
        return sumaTotal;
    }

    public String onFlowProcess(FlowEvent event) {
        boolean darPaso = true;
        String tabDestino = event.getNewStep();
        switch (event.getNewStep()) {
            case "envio":
                for (PrinDocumento doc : listaDocumentos) {
                    if (doc.getDocArchivo() == null && doc.getPrinClaseDocumento().getClsCodigo() == 4) {// clase de documento proyecto
                        this.setMensaje("ATENCIÓN. Ingrese el documento requerido: </br>- Proyecto con esquema requerido");
                        darPaso = false;
                        tabDestino = "documento";
                    }
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
        return tabDestino;
    }

    @Override
    public String opcionItem(String op) {
        listaPresup.clear();
        if (op.equals("actualizar")) {
            persona = inveProyectoEdit.getPrinPersona();
            recupInvestigador();
            recupProyecto();
            if (inveProyectoEdit.getPryEstadoReg().equals("I")) {
                inveProyectoEdit.setPryEstadoReg("R");
            }
        } else {
            reset();
            initListaArchivos();
            inveProyectoEdit = new InveProyecto();
            inveProyectoEdit.setPryEstadoReg("R");
            inveProyectoEdit.setPryOrigenIngreso("S");
        }
        RequestContext.getCurrentInstance().execute("PF('widgetVarWizardPry').loadStep(PF('widgetVarWizardPry').cfg.steps [0], true)");

        return super.opcionItem(op);
    }

    @Override
    public boolean checkRules() {
        if (inveProyectoEdit.getPryEstadoReg().equals("A")) {
            int total = ((SelectItemGroup) esquema.get(0)).getSelectItems().length
                    + ((SelectItemGroup) esquema.get(1)).getSelectItems().length;
            if (Arrays.asList(selectedItemsEsquema).size() != total) {
                this.setMensaje("ATENCIÓN. Verificaciones son obligatorias.</br>Por favor, verifiquelas con el documento del Esquema del Proyecto Ingresado");
                return false;
            }
        }
        return true;
    }

    @Override
    public void preUpdate() {
        List<String> selec = Arrays.asList(selectedItemsEsquema);
        persona.setPrinCiudad(new PrinCiudad(new PrinCiudadPK(paiCodigo, ciuCodigo)));
//        persona.setPrinNacionalidad(new PrinPais(paiCodigoNac));
        if (persona.getPerEmail() == null) {
            persona.setPerEmail(inveProyectoEdit.getPryCorreo().toLowerCase().trim());
        }
        if (inveProyectoEdit.getPryCelular() != null) {
            if (inveProyectoEdit.getPryCelular().trim().isEmpty()) {
                inveProyectoEdit.setPryCelular(null);
            }
            if ((persona.getPerCelular() == null || persona.getPerCelular().trim().isEmpty()) && inveProyectoEdit.getPryCelular() != null) {
                persona.setPerCelular(inveProyectoEdit.getPryCelular().trim());
            }
        }
        inveProyectoEdit.setPryJustificacion(null);
        inveProyectoEdit.setPryRelevancia(null);
        inveProyectoEdit.setPryPlanteamiento(null);
        inveProyectoEdit.setPryMetodologia(null);
        inveProyectoEdit.setPryPlanInicial(null);
        inveProyectoEdit.setPryCronoMensual(null);
        for (int i = 0; i < selec.size(); i++) {
            int val = Integer.parseInt(selec.get(i));
            switch (val) {
                case 10:
                    inveProyectoEdit.setPryJustificacion("S");
                    break;
                case 11:
                    inveProyectoEdit.setPryRelevancia("S");
                    break;
                case 12:
                    inveProyectoEdit.setPryPlanteamiento("S");
                    break;
                case 13:
                    inveProyectoEdit.setPryMetodologia("S");
                    break;
                case 14:
                    inveProyectoEdit.setPryPlanInicial("S");
                    break;
                case 15:
                    inveProyectoEdit.setPryCronoMensual("S");
                    break;
                default:
                    break;
            }
        }
        inveProyectoEdit.setPryAreaAcad(areaCodigo);
        inveProyectoEdit.setPrinPersona(persona);
        inveProyectoEdit.setInveConvocatoria(convocatoria);
        inveProyectoEdit.setInveDisciplinaCientifica(inveDiscipEdit);
        inveProyectoEdit.setInveObjetivoSocioEconomico(inveOsoeEdit);
        inveProyectoEdit.setInveAmbitoGeografico(inveAmbitoGeograficoEdit);
        inveProyectoEdit.setInveGrupoInvestigacion(grupoInvestigacionEdit);
        inveProyectoEdit.setVincCine(cine);
        inveProyectoEdit.setPryTipoInvestig(tipoInvestig);
        inveProyectoEdit.setPrinCategoriaInvest(categoria);
        inveProyectoEdit.setPryCorreo(inveProyectoEdit.getPryCorreo().toLowerCase());
        inveProyectoEdit.setPryAnioAcad(anio);
        inveProyectoEdit.setInveGrupo(new InveGrupo(grupoRubro));
        if (inveProyectoEdit.getPryEstadoReg().equals("A")) {
            inveProyectoEdit.setPryCodigoCi(codigoCI.toUpperCase().trim());
        }
//        if (inveProyectoEdit.getPryEstadoReg().equals("A") && inveProyectoEdit.getInveCarta() == null) {
//            inveProyectoEdit.setPryFechaRecepcion(new Date());
//        }
        if (this.getOpcion().equals("actualizar")) {
            inveProyectoEdit.setPryFechaAct(new Date());
            inveProyectoEdit.setPryUsuarioAct(usr.getUsuCedPass());
        } else {
            inveProyectoEdit.setPryFechaCrea(new Date());
            inveProyectoEdit.setPryUsuarioCrea(usr.getUsuCedPass());
        }
        inveProyectoEdit.setInvePresupuestoCollection(listaPresup);

    }

    @Override
    public void insert() {
        try {
            inveProyectoFacade.create(inveProyectoEdit, listaDocumentos);

        } catch (ProyectoException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        try {
            inveProyectoFacade.edit(inveProyectoEdit, listaDocumentos);
        } catch (ProyectoException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Eliminar proyecto">
    @Override
    public boolean preDelete() {
        listaDocumentos = prinDocumentoFacade.getDocumentos(modulo, InveProyecto.class
                .getAnnotation(Table.class
                ).name(), inveProyectoSelected.getPryCodigo());
        return true;
    }

    @Override
    public void delete() {
        inveProyectoFacade.remove(inveProyectoSelected, listaDocumentos);
    }

    @Override
    public void postDelete() {
        retrieveBy();
        menuDefault();
        deSeleccionarListas();
        RequestContext.getCurrentInstance().update("formProyectos");
    }
//</editor-fold>

    @Override
    public void postUpdate() {
        this.setMensaje("Proyecto recibido !");
        this.setIconMensaje("visto.jpg");
        RequestContext.getCurrentInstance().update("dialogMensaje");
        RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
        this.reset();
        retrieveBy();
        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
    }

    @Override
    public void cancelarButton() {
        super.cancelarButton();
        RequestContext.getCurrentInstance().reset("formEdit:pnlGrid");
        RequestContext.getCurrentInstance().update("formProyectos");
        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
//        RequestContext.getCurrentInstance().execute("PF('widgetVardataTableDocu').unselectAllRows();");

    }

    @Override
    public void resetAdic() {
        inveProyectoSelected = null;
    }

}
