/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.reportes.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.session.InveLectorFacadeLocal;
import ec.edu.uasb.investigacion.session.InveProyectoFacadeLocal;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author victor.barba
 */
@Named(value = "documentosBean")
@ViewScoped
public class DocumentoJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;

    @EJB
    private InveLectorFacadeLocal inveLectorFacade;

    private final Usuario usr;
    private String filtrarAreas;
    private String perfil = new String();

    private List<PrinPersona> listaPersonas = new ArrayList<>();
    private PrinPersona persona;

    private List<InveProyecto> listaProyectos = new ArrayList<>();
    private InveProyecto proyecto;
    private List<SelectItem> listaReportes = new ArrayList<>();
    private String reporte;

//<editor-fold defaultstate="collapsed" desc="propiedades">
    public List<PrinPersona> getListaPersonas() {
        return listaPersonas;
    }

    public List<InveProyecto> getListaProyectos() {
        return listaProyectos;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public List<SelectItem> getListaReportes() {
        return listaReportes;
    }
//</editor-fold>

    public DocumentoJSFManagedBean() {
        this.usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        super.setRenderPDF(true);
        List<Perfil> perfiles = (List<Perfil>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfiles");
        if (perfiles != null) {
            filtrarAreas = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("filtrarAreas");// áreas inicializadas             
            for (Perfil perf : perfiles) {
                if (perf.getIdPerfil().equals("USU_SIST") || perf.getIdPerfil().equals("ADM_INVE") || perf.getIdPerfil().equals("SEC_INVE")) {
                    perfil = perf.getIdPerfil();
                    filtrarAreas = "T";
                    break;
                }
            }
        }
        listaReportes.add(new SelectItem("0", "CERTIFICADOS", "", true));
        listaReportes.add(new SelectItem("repCertificadoLectura", "Lecturas", "", false));
        listaReportes.add(new SelectItem("repCertificadoInvestig", "Investigación de proyectos", "", false));
        listaReportes.add(new SelectItem("kardexProyectos", "Kardex proyectos", "", false));
    }

    class PrinPersonaComp implements Comparator<PrinPersona> {

        @Override
        public int compare(PrinPersona p1, PrinPersona p2) {
            return (p1.getPerPrimerApellido().toUpperCase()).compareTo(p2.getPerPrimerApellido().toUpperCase());
        }
    }

    @Override
    public void init() {
        List<PrinPersona> temp = inveLectorFacade.findAllLectores();
        /* Eliminar duplicados*/
        temp.addAll(inveLectorFacade.findAllInvestigadores());
        listaPersonas = new ArrayList<>(new HashSet<>(temp));
        Collections.sort(listaPersonas, new PrinPersonaComp());
        reset();
    }

    public void resetFiltro() {
        this.setRepEmbebVisible(false);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("urlRep");
        this.setSelectedRow(null);
        switch (reporte) {
            case "repCertificadoLectura":
            case "repCertificadoInvestig":
                break;
            case "kardexProyectos":
                if (listaProyectos.isEmpty()) {
                    listaProyectos = inveProyectoFacade.findAll();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRowSelect(SelectEvent event) {
        persona = null;
        proyecto = null;
        if (event.getComponent().getClientId().equals("formOpc:dataTableInvest")) {
            persona = (PrinPersona) event.getObject();
        } else {
            proyecto = (InveProyecto) event.getObject();
        }

    }

    @Override
    public String search() {
        String parametros = "";
        switch (reporte) {
            case "repCertificadoLectura":
                parametros = parametros + "&pLector=" + persona.getPerCodigo().toString();
                break;
            case "repCertificadoInvestig":
                parametros = parametros + "&pInvest=" + persona.getPerCodigo().toString();
                break;
            case "kardexProyectos":
                parametros = parametros + "&pCodigo=" + proyecto.getPryCodigo().toString();
                break;
            default:
                break;
        }
        String urlReporte = "subsist=" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subsist")
                + "&reporte=" + reporte + parametros;
//        System.out.println(urlReporte);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte + "&pfdrid_c=true");
        super.setRepEmbebVisible(true);
        RequestContext.getCurrentInstance().update("pdfPanel");
        //reporte = reporte.equals("inventarioLecturasInvisible") ? "inventarioLecturas" : reporte;
        return null;
    }

}
