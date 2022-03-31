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
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveTipoSeguimiento;
import ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveProyectoFacadeLocal;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@Named(value = "cierreBean")
@ViewScoped
public class CierreJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;

    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private InveConvocatoriaFacadeLocal inveConvocatoriaFacade;

    private final Usuario usr;
    private String filtrarAreas;
    private String perfil = new String();
    private final String modulo;

    private Short anio;
    private List<AnioAcademico> listaAnios = new ArrayList<>();
    private List<InveProyecto> listaInvestigAprob = new ArrayList<>();
    private List<InveProyecto> listaInvestigAprobFilterd;
    private InveProyecto inveInveProyectoSelected;
    private InveProyecto inveInveProyectoEdit;
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

    public void setConvocatoria(InveConvocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public List<AnioAcademico> getListaAnios() {
        return listaAnios;
    }

    public Short getAnio() {
        return anio;
    }

    public void setAnio(Short anio) {
        this.anio = anio;
    }

    public List<InveProyecto> getListaInvestigAprob() {
        return listaInvestigAprob;
    }

    public List<InveProyecto> getListaInvestigAprobFilterd() {
        return listaInvestigAprobFilterd;
    }

    public void setListaInvestigAprobFilterd(List<InveProyecto> listaInvestigAprobFilterd) {
        this.listaInvestigAprobFilterd = listaInvestigAprobFilterd;
    }

    public InveProyecto getInveInveProyectoSelected() {
        return inveInveProyectoSelected;
    }

    public void setInveInveProyectoSelected(InveProyecto inveInveProyectoSelected) {
        this.inveInveProyectoSelected = inveInveProyectoSelected;
    }

    public InveProyecto getInveInveProyectoEdit() {
        return inveInveProyectoEdit;
    }

    public void setInveInveProyectoEdit(InveProyecto inveInveProyectoEdit) {
        this.inveInveProyectoEdit = inveInveProyectoEdit;
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
    public CierreJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setTituloModalEdicion("Registrar cierre");
        super.setPantallaEdicion("/pages/opciones/cierre/regCierreInvest.xhtml");
        super.getListaWidgetPantallas().add("widgetCierre");
        super.setWidthEditDisplay("960");
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
        listaAnios = anioAcademicoFacade.getListaAnios();
        retrieveBy();
        retrieve();
        super.init();
    }

    @Override
    public void reset() {
        inveInveProyectoSelected = null;
        inveSeguimiento = new InveSeguimiento();
        inveInveProyectoEdit = new InveProyecto();
    }

    @Override
    public void menuDefault() {
        this.setVisibleNew(false);
        this.setVisibleEdit(true);
        this.setVisibleDelete(false);
        refreshMenuBar(true);
    }

    public void retrieveBy() {
        listaInvestigAprob.clear();
        if (anio != null) {
            listaInvestigAprob = inveProyectoFacade.getInvesAprobados(anio);
        }else if (convocatoria != null){
        listaInvestigAprob = inveProyectoFacade.getConvocatoriaAprobados(convocatoria);
        }
        
        RequestContext.getCurrentInstance().execute("PF('widgetCierre').clearFilters();");
    }

    public String resetFilter() {
        inveInveProyectoSelected = null;
        this.setDisabledEdit(true);
        return null;
    }

    @Override
    public String opcionItem(String op) {
        super.opcionItem(op);
        if (op.equals("actualizar")) {
            try {
                inveInveProyectoEdit = inveInveProyectoSelected.clone();
                if (inveInveProyectoEdit.getInveSeguimiento().getSegCodigo() > 0) {
                    inveSeguimiento = inveInveProyectoEdit.getInveSeguimiento();
                    if (inveInveProyectoSelected.getPryAutorizaEnvioRepos() != null) {
                        this.setVisibleBotonGrabar(false);
                    } else {
                        this.setVisibleBotonGrabar(true);
                    }
                } else {
                    inveSeguimiento = new InveSeguimiento();
                    this.setVisibleBotonGrabar(true);
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(CierreJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.opcionItem(op);
    }

    @Override
    public void preUpdate() {
        if (this.getOpcion().equals("actualizar")) {
            if (inveInveProyectoEdit.getInveSeguimiento().getSegCodigo() < 0) {
                inveSeguimiento.setInveProyecto(inveInveProyectoSelected);
                inveSeguimiento.setInveTipoSeguimiento(new InveTipoSeguimiento(new Short("9")));// CIERRE DE INVESTIGACION
                inveSeguimiento.setSegTipoComunic("MEMO");
                inveSeguimiento.setSegNroComunic(inveSeguimiento.getSegNroComunic().toUpperCase());
                inveSeguimiento.setSegEstadoReg("A");
                inveSeguimiento.setSegFechaCrea(new Date());
                inveSeguimiento.setSegUsuarioCrea(usr.getUsuCedPass());
            }
            if (inveInveProyectoEdit.getPryAutorizaEnvioRepos() != null && inveInveProyectoEdit.getPryAutorizaEnvioRepos().equals("S")) {
                inveInveProyectoEdit.setPryFechaEnvioRepos(new Date());
            }
        }
    }

    @Override
    public void update() {
        inveProyectoFacade.edit(inveInveProyectoEdit, inveSeguimiento);
    }

    @Override
    public void postUpdate() {
        if (inveInveProyectoEdit.getInveSeguimiento().getSegCodigo() < 0) {
            this.setMensaje("Cierre de investigaión registrado !");
            this.setIconMensaje("visto.jpg");
            RequestContext.getCurrentInstance().update("dialogMensaje");
            RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
        }
        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
        retrieveBy();
        reset();
        RequestContext.getCurrentInstance().update("formCierre");

    }
     public void retrieve() {
        listaConvocatorias = inveConvocatoriaFacade.findAll();
        }

    @Override
    public String print() {
//        this.setTituloPDF("Ficha económica");
//        String pry = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("proyecto");
//        String urlReporte = "subsist=" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subsist")
//                + "&reporte=fichaProyAprob&pProyecto=" + pry;
////        System.out.println(urlReporte);
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte);
        return null;
    }

}
