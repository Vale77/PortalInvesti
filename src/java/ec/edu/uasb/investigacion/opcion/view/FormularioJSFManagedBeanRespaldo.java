/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.opcion.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.exception.InscripcionException;
import ec.edu.uasb.external.entities.AreaAcademica;
import ec.edu.uasb.external.session.AreaAcademicaFacadeLocal;
import ec.edu.uasb.investigacion.entities.InveAmbitoGeografico;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveCronogramaMensual;
import ec.edu.uasb.investigacion.entities.InveDisciplinaCientifica;
import ec.edu.uasb.investigacion.entities.InveLineaInvestProyec;
import ec.edu.uasb.investigacion.entities.InveObjetivoSocioEconomico;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveTipoProyecto;
import ec.edu.uasb.investigacion.session.InveAmbitoGeograficoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveCronogramaMensualFacadeLocal;
import ec.edu.uasb.investigacion.session.InveDisciplinaCientificaFacadeLocal;
import ec.edu.uasb.vinculacion.entities.VincCine;
import ec.edu.uasb.investigacion.session.InveLineaInvestProyecFacadeLocal;
import ec.edu.uasb.investigacion.session.InveObjetivoSocioEconomicoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveProyectoFacadeLocal;
import ec.edu.uasb.principal.entities.PrinCiudad;
import ec.edu.uasb.principal.entities.PrinEntidad;
import ec.edu.uasb.principal.entities.PrinPais;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.entities.PrinTitulo;
import ec.edu.uasb.principal.session.PrinCiudadFacadeLocal;
import ec.edu.uasb.principal.session.PrinEntidadFacadeLocal;
import ec.edu.uasb.principal.session.PrinPaisFacadeLocal;
import ec.edu.uasb.principal.session.PrinPersonaFacadeLocal;
import ec.edu.uasb.principal.session.PrinTituloFacadeLocal;
import ec.edu.uasb.vinculacion.session.VincCineFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author victor.barba
 */
@Named(value = "formularioRespaldoBean")
@ViewScoped
public class FormularioJSFManagedBeanRespaldo extends BaseJSFManagedBean implements Serializable {

    @EJB
    private AreaAcademicaFacadeLocal areaAcademicaFacade;
    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;
    @EJB
    private InveLineaInvestProyecFacadeLocal inveLineaInvestProyecFacade;
    @EJB
    private PrinTituloFacadeLocal prinTituloFacade;
    @EJB
    private PrinPaisFacadeLocal prinPaisFacade;
    @EJB
    private PrinEntidadFacadeLocal prinEntidadFacade;
    @EJB
    private PrinCiudadFacadeLocal prinCiudadFacade;
    @EJB
    private VincCineFacadeLocal vincCineFacade;
    @EJB
    private InveObjetivoSocioEconomicoFacadeLocal inveObjetivoSocioEconomicoFacade;
    @EJB
    private InveDisciplinaCientificaFacadeLocal inveDisciplinaCientificaFacade;
    @EJB
    private InveAmbitoGeograficoFacadeLocal inveAmbitoGeograficoFacade;
    @EJB
    private PrinPersonaFacadeLocal prinPersonaFacade;
    @EJB
    private InveCronogramaMensualFacadeLocal inveCronogramaMensualFacade;

    private InveConvocatoria convocatoria;
    private String tipoDoc = null;
    private String nroDocumento;
    private String captcha;
    private InputText inputTextCaptcha = new InputText();
    private PrinPersona persona;
    private PrinPersona personaOrig;
    private InveProyecto inveProyecto;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public InveProyecto getInveProyecto() {
        return inveProyecto;
    }

    public void setInveProyecto(InveProyecto inveProyecto) {
        this.inveProyecto = inveProyecto;
    }

    public InveConvocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public InputText getInputTextCaptcha() {
        return inputTextCaptcha;
    }

    public void setInputTextCaptcha(InputText inputTextCaptcha) {
        this.inputTextCaptcha = inputTextCaptcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables Investigador">
    private List<PrinPais> listaPais = new ArrayList<>();
    private List<PrinCiudad> listaCiudades = new ArrayList<>();
    private List<PrinEntidad> listaUniversidades = new ArrayList<>();
    private List<PrinTitulo> listaTitulos = new ArrayList<>();
    private Short paiCodigo;
    private Short paiCodigoNac;
    private Short paiCodigoNacOrig;
    private Short paiCodigoTit;
    private Integer ciuCodigo;
    private Date fechaNac;
    private PrinEntidad prinEntidad;
    private PrinTitulo prinTitulo;

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

    public Short getPaiCodigoNac() {
        return paiCodigoNac;
    }

    public void setPaiCodigoNac(Short paiCodigoNac) {
        this.paiCodigoNac = paiCodigoNac;
    }

    public Short getPaiCodigoNacOrig() {
        return paiCodigoNacOrig;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public PrinEntidad getPrinEntidad() {
        return prinEntidad;
    }

    public void setPrinEntidad(PrinEntidad prinEntidad) {
        this.prinEntidad = prinEntidad;
    }

    public List<PrinEntidad> getListaUniversidades() {
        return listaUniversidades;
    }

    public Short getPaiCodigoTit() {
        return paiCodigoTit;
    }

    public void setPaiCodigoTit(Short paiCodigoTit) {
        this.paiCodigoTit = paiCodigoTit;
    }

    public PrinTitulo getPrinTitulo() {
        return prinTitulo;
    }

    public void setPrinTitulo(PrinTitulo prinTitulo) {
        this.prinTitulo = prinTitulo;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables Elementos">
    private Short areaCodigo;
    private List<AreaAcademica> listaAreas = new ArrayList<>();

    public Short getAreaCodigo() {
        return areaCodigo;
    }

    public void setAreaCodigo(Short areaCodigo) {
        this.areaCodigo = areaCodigo;
    }

    public List<AreaAcademica> getListaAreas() {
        return listaAreas;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables Info Adicional">
    private String tipoInvestig;
    private Short linCodigo;
    private List<InveLineaInvestProyec> listaLineaInvestProyec = new ArrayList<>();
    private List<InveDisciplinaCientifica> listaCientificas = new ArrayList<>();
    private List<InveObjetivoSocioEconomico> listaEconomicos = new ArrayList<>();
    private List<InveAmbitoGeografico> listaGeograficos = new ArrayList<>();
    private List<VincCine> listaAreaCine = new ArrayList<>();
    private List<VincCine> listaSubAreaCine = new ArrayList<>();
    private List<VincCine> listaCine = new ArrayList<>();
    private VincCine cine;
    private VincCine areaCine;
    private VincCine subAreaCine;
    private Short dscCodigo;
    private Short oseCodigo;
    private Short ambCodigo;

    public String getTipoInvestig() {
        return tipoInvestig;
    }

    public void setTipoInvestig(String tipoInvestig) {
        this.tipoInvestig = tipoInvestig;
    }

    public Short getLinCodigo() {
        return linCodigo;
    }

    public void setLinCodigo(Short linCodigo) {
        this.linCodigo = linCodigo;
    }

    public List<InveLineaInvestProyec> getListaLineaInvestProyec() {
        return listaLineaInvestProyec;
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

    public Short getDscCodigo() {
        return dscCodigo;
    }

    public void setDscCodigo(Short dscCodigo) {
        this.dscCodigo = dscCodigo;
    }

    public List<InveDisciplinaCientifica> getListaCientificas() {
        return listaCientificas;
    }

    public Short getOseCodigo() {
        return oseCodigo;
    }

    public void setOseCodigo(Short oseCodigo) {
        this.oseCodigo = oseCodigo;
    }

    public List<InveObjetivoSocioEconomico> getListaEconomicos() {
        return listaEconomicos;
    }

    public Short getAmbCodigo() {
        return ambCodigo;
    }

    public void setAmbCodigo(Short ambCodigo) {
        this.ambCodigo = ambCodigo;
    }

    public List<InveAmbitoGeografico> getListaGeograficos() {
        return listaGeograficos;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables Cronograma">
    private InveCronogramaMensual cronogramaMensual;
    private List<InveCronogramaMensual> listaCronoMensual = new ArrayList<>();
    private boolean disabledAdd = false;

    public List<InveCronogramaMensual> getListaCronoMensual() {
        return listaCronoMensual;
    }

    public InveCronogramaMensual getCronogramaMensual() {
        return cronogramaMensual;
    }

    public void setCronogramaMensual(InveCronogramaMensual cronogramaMensual) {
        this.cronogramaMensual = cronogramaMensual;
    }

    public boolean isDisabledAdd() {
        return disabledAdd;
    }

    public void setDisabledAdd(boolean disabledAdd) {
        this.disabledAdd = disabledAdd;
    }

//</editor-fold>
    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public FormularioJSFManagedBeanRespaldo() {
    }

    @Override
    public void init() {
        tipoDoc = null;
        nroDocumento = null;
        reset();
        retrieve();
    }

    // <editor-fold defaultstate="collapsed" desc="Captcha">
    public void generaCaptcha_processAction() {
        Random rdm = new Random();
        int rl = rdm.nextInt(99999);
        captcha = Integer.toHexString(rl).toUpperCase();
        inputTextCaptcha.resetValue();
    }

    public boolean validarCaptcha() {
        if (!inputTextCaptcha.getValue().toString().trim().equalsIgnoreCase(captcha.trim())) {
            String msg2 = " Los caracteres ingresados no corresponden a los de la imagen.";
            FacesMessage msg = new FacesMessage(msg2, msg2);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("formRegistro:inputTextCaptcha", msg);
            return false;
        }
        return true;
    }
//</editor-fold>

    @Override
    public void reset() {
        //Datos personales
        persona = new PrinPersona();
        paiCodigoNac = null;
        paiCodigoNacOrig = null;
        paiCodigo = null;
        ciuCodigo = null;
        paiCodigoTit = null;
        prinTitulo = new PrinTitulo();
        prinEntidad = new PrinEntidad();
        // Proyecto
        inveProyecto = new InveProyecto();
        inveProyecto.setInveTipoProyecto(new InveTipoProyecto(new Short("1")));
        tipoInvestig = null;
    }

    //<editor-fold defaultstate="collapsed" desc="Recuperar listados">
    public void retrieve() {
        listaAreaCine = vincCineFacade.findBy(null);
        listaAreas = areaAcademicaFacade.getListaAreasDepartamentos();
        listaPais = prinPaisFacade.findAllorde();
        listaUniversidades = prinEntidadFacade.findAllUniversidades();

        listaEconomicos = inveObjetivoSocioEconomicoFacade.findAll();
        listaCientificas = inveDisciplinaCientificaFacade.findAll();
        listaGeograficos = inveAmbitoGeograficoFacade.findAll();
    }

    public void cargarCiudades() {
        listaCiudades.clear();
        if (paiCodigo != null) {
            listaCiudades = prinCiudadFacade.findAllorde(paiCodigo);
        }
    }

    public void retrieveCines(String opcion) {
        cine = null;
        if (opcion.equals("Subarea")) {
            listaSubAreaCine = vincCineFacade.findBy(areaCine.getCinCodigo());
            listaCine.clear();
            subAreaCine = null;
        } else {
            listaCine = vincCineFacade.findBy(subAreaCine.getCinCodigo());
        }
    }

    public void retrievelineas() {
        listaLineaInvestProyec = inveLineaInvestProyecFacade.findBy(areaCodigo);

    }
//</editor-fold>

    private void clonarVariables() {
        try {
            personaOrig = persona.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FormularioJSFManagedBeanRespaldo.class.getName()).log(Level.SEVERE, null, ex);
        }
        paiCodigoNacOrig = paiCodigoNac;
    }

    private void buscarRegistro(String cedPass) throws InscripcionException {
        persona = prinPersonaFacade.findByCedula(cedPass);
        if (persona != null) {
            paiCodigo = (persona.getPrinCiudad() == null ? null : persona.getPrinCiudad().getPrinPais().getPaiCodigo());
            paiCodigoNac = (persona.getPrinNacionalidad() == null ? null : persona.getPrinNacionalidad().getPaiCodigo());
            fechaNac = persona.getPerFechaNacimiento();
            cargarCiudades();
            ciuCodigo = (persona.getPrinCiudad() != null ? persona.getPrinCiudad().getPrinCiudadPK().getCiuCodigo() : null);
            listaTitulos = prinTituloFacade.findTitulosBy(persona);
            if (!listaTitulos.isEmpty()) {
                for (PrinTitulo titulo : listaTitulos) {
                    if (titulo.getEsPrincipal().equals("S")) {
                        persona.setPrinTitulo(titulo);
                        prinTitulo = titulo;
                        break;
                    }
                }
            }
            if (prinTitulo != null) {
                prinEntidad = (prinTitulo.getPrinEntidad() != null ? prinTitulo.getPrinEntidad() : new PrinEntidad());
                paiCodigoTit = (prinTitulo.getPrinPais() != null ? prinTitulo.getPrinPais().getPaiCodigo() : null);
            }
            listaCronoMensual = inveCronogramaMensualFacade.findAll();
            if (listaCronoMensual.isEmpty()) {
                Boolean[] meses = {false, false, false, false, false, false};
                listaCronoMensual.add(new InveCronogramaMensual(meses));
            }
            tipoDoc = persona.getPerTipoDoc();
            nroDocumento = persona.getPerIdDoc();
            clonarVariables();
        } else {
            reset();
        }
    }

    public String txt_NroDocValueChange() {
        try {
            buscarRegistro(nroDocumento);
            RequestContext.getCurrentInstance().execute(persona.getPerCodigo() == null ? "setfocoNombre();" : "setfocoEmail();");
        } catch (InscripcionException ex) {
            Logger.getLogger(FormularioJSFManagedBeanRespaldo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Collection paneles = new ArrayList();
            paneles.add("form:pnlPerso");
            paneles.add("form:panelDomicilio");
            paneles.add("form:panelTitulo");
            RequestContext.getCurrentInstance().update(paneles);
        }
        return null;
    }

    public String addCrono() {
        Boolean[] meses = {false, false, false, false, false, false};
        listaCronoMensual.add(new InveCronogramaMensual(meses));
        return null;
    }

    public void rowEditInit(RowEditEvent event) {
        int fila = ((DataTable) event.getSource()).getRowIndex();
        disabledAdd = true;
        RequestContext.getCurrentInstance().execute("$(function(){PrimeFaces.focus('form:dataTableCrono:" + fila + ":inputActiv')});");
    }

    public void onRowEdit(RowEditEvent event) {
//        System.out.println("onRowEdit ");
        disabledAdd = true;
        InveCronogramaMensual icm = (InveCronogramaMensual) event.getObject();
        for (int i = 0; i < icm.getMeses().length; i++) {
            switch (i) {
                case 0:
                    icm.setCroMes1(icm.getMeses()[i] ? "S" : "N");
                    break;
                case 1:
                    icm.setCroMes2(icm.getMeses()[i] ? "S" : "N");
                    break;
                case 2:
                    icm.setCroMes3(icm.getMeses()[i] ? "S" : "N");
                    break;
                case 3:
                    icm.setCroMes4(icm.getMeses()[i] ? "S" : "N");
                    break;
                case 4:
                    icm.setCroMes5(icm.getMeses()[i] ? "S" : "N");
                    break;
                case 5:
                    icm.setCroMes6(icm.getMeses()[i] ? "S" : "N");
                    break;
            }
        }
//        System.out.println("icm.getMeses()" + icm);
        int fila = ((DataTable) event.getSource()).getRowIndex();
        listaCronoMensual.set(fila, icm);
        RequestContext.getCurrentInstance().execute("refreshAddBoton();");
        RequestContext.getCurrentInstance().update(":form:dataTableCrono:headerActivCrono");
    }

    public void onRowCancel(RowEditEvent event) {
        disabledAdd = false;
    }

    public String onFlowProcess(FlowEvent event) {
//        System.out.println("onFlowProcess " + event.getNewStep());
//        if (event.getNewStep().equals("cronograma")) {
//
//        }

        return event.getNewStep();
    }

    @Override
    public void insert() {
        inveProyectoFacade.create(inveProyecto);
    }

    @Override
    public void preUpdate() {

    }

    @Override
    public void update() {
        inveProyectoFacade.edit(inveProyecto);
    }

    @Override
    public void postUpdate() {
        this.setMensaje("Datos registrados correctamente !");
        this.setIconMensaje("visto.jpg");
        RequestContext.getCurrentInstance().update("dialogMensaje");
        RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
    }

}
