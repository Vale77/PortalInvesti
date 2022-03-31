/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.reportes.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.external.entities.AnioAcademico;
import ec.edu.uasb.external.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import static ec.edu.uasb.investigacion.entities.InveConvocatoria_.cvoCodigo;
import static ec.edu.uasb.investigacion.entities.InveConvocatoria_.cvoFechaInicio;
import static ec.edu.uasb.investigacion.entities.InveConvocatoria_.inveTipoConvocatoria;
import ec.edu.uasb.investigacion.entities.InveTipoConvocatoria;
import ec.edu.uasb.investigacion.opcion.view.ConvocatoriaJSFManagedBean;
import ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveLectorFacadeLocal;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.Entity;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@Named(value = "listadoBean")
@ViewScoped
public class ListadoJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveLectorFacadeLocal inveLectorFacade;
    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private InveConvocatoriaFacadeLocal inveConvocatoriaFacade;

    private final Usuario usr;
    private String filtrarAreas;
    private String perfil = new String();

    private List<AnioAcademico> listaAnios = new ArrayList<>();
    private List<PrinPersona> listaLectores = new ArrayList<>();
    private PrinPersona lector;
    private Short anio;

    private List<SelectItem> listaReportes = new ArrayList<>();
    private String reporte;
    private Date fechaInicial;
    private Date fechaFinal;
    private String opRadio = "T";
    private boolean opOcultarInvest = false;
    private String campoFecha = "registro";

    private List<InveConvocatoria> listaConvocatorias = new ArrayList<>();

    public List<InveConvocatoria> getListaConvocatorias() {
        return listaConvocatorias;
    }

    public void setListaConvocatorias(List<InveConvocatoria> listaConvocatorias) {
        this.listaConvocatorias = listaConvocatorias;
    }
    private InveConvocatoria convocatoria;

//<editor-fold defaultstate="collapsed" desc="propiedades">
    public void setOpOcultarInvest(boolean opOcultarInvest) {
        this.opOcultarInvest = opOcultarInvest;
    }

    public boolean isOpOcultarInvest() {
        return opOcultarInvest;
    }

    public String getCampoFecha() {
        return campoFecha;
    }

    public void setCampoFecha(String campoFecha) {
        this.campoFecha = campoFecha;
    }

    public String getOpRadio() {
        return opRadio;
    }

    public void setOpRadio(String opRadio) {
        this.opRadio = opRadio;
    }

    public PrinPersona getLector() {
        return lector;
    }

    public void setLector(PrinPersona lector) {
        this.lector = lector;
    }

    public List<PrinPersona> getListaLectores() {
        return listaLectores;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
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

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public List<SelectItem> getListaReportes() {
        return listaReportes;
    }

    public void setConvocatoria(InveConvocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    public InveConvocatoria getConvocatoria() {
        return convocatoria;
    }

//</editor-fold>
    public ListadoJSFManagedBean() {
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
//                } else if (perf.getIdPerfil().equals("SEC_AREA") || perf.getIdPerfil().equals("SEC_INS")) {
//                    perfil = perf.getIdPerfil();
//                    filtrarAreas = "A";
//                    break;
                }
            }
        }
        listaReportes.add(new SelectItem("0", "GENERALES", "", true));
        listaReportes.add(new SelectItem("repProyectosPresentados", "Listado de proyectos", "", false));
        listaReportes.add(new SelectItem("repProyectosCuadrosGenerales", "Cuadros generales de proyectos", "", false));
        listaReportes.add(new SelectItem("repCuadroPorAreaAplicada", "Cuadro: Ambito Geo.,Disciplina, Objetivo S-E ", "", false));
        listaReportes.add(new SelectItem("repEstadoGeneralProyectos", "Estado de general de investigaciones", "", false));
        listaReportes.add(new SelectItem("repProyectosCerrados", "Cierre de investigaciones", "", false));
        listaReportes.add(new SelectItem("1", "LECTURA DE PROYECTOS", "", true));
        listaReportes.add(new SelectItem("repEstadoGeneralLecturas", "Estado general de Lecturas", "", false));
        listaReportes.add(new SelectItem("repValoracionLectura", "Valoración de lecturas", "", false));
        listaReportes.add(new SelectItem("repCuadrosLectoresProy", "Lectores proyectos investigación", "", false));
        listaReportes.add(new SelectItem("2", "INFORMES FINALES", "", true));
        listaReportes.add(new SelectItem("repEntregaInfoFinal", "Entregas de informes finales", "", false));
        listaReportes.add(new SelectItem("repEstadoGeneralInfoFinal", "Estado general Lecturas  de Info. Final", "", false));
        listaReportes.add(new SelectItem("repValoracionInfoFinal", "Valoración de informe final", "", false));
        listaReportes.add(new SelectItem("repCuadrosLectoresInforFinal", "Lectores informe final", "", false));

    }

    @Override
    public void init() {
        listaAnios = anioAcademicoFacade.findAll();
        listaLectores = inveLectorFacade.findAllLectores();
        listaConvocatorias = inveConvocatoriaFacade.findAll();
        reset();
    }

//    public void selectReport(ValueChangeEvent vce) {
//        reporte = null;
//        super.setRepEmbebVisible(false);
//    }
    @Override
    public void reset() {
        anio = null;
        super.reset();
        opOcultarInvest = true;
    }

    public void retrieveBy() {
        this.setRepEmbebVisible(false);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("urlRep");
    }

    public static String formatDate(Date currentDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(currentDate);
    }

    public void resetFiltro() {
        opRadio = "T";
        campoFecha = "registro";
        lector = null;

    }

    public void resetFecha() {

    }

    public void resetItems() {
        this.setFechaInicial(null);
        this.setFechaFinal(null);
        this.setConvocatoria(null);
        }

    @Override
    public String search() {
      //        List temp = new ArrayList();
        Calendar fecha = Calendar.getInstance();
        String parametros = "";
        parametros = parametros + (opOcultarInvest ? "" : "&pTrue=N");
        if (opRadio.equals("F")) {
            fecha.setTime(fechaFinal);
            fecha.add(Calendar.DATE, 1);
            this.setAnio(null);
        }
        if (opRadio.equals("V")) {
            parametros = parametros + "&pInicio=" + formatDate(convocatoria.getCvoFechaInicio()) + "&pFin=" + formatDate(convocatoria.getCvoFechaFin());
        } else {
            parametros = parametros + (anio == null ? "" : "&pAnio=" + anio);
        }
        if (lector != null) {
            parametros = parametros + "&pLector=" + lector.getPerCodigo();
        }
        switch (reporte) {
            case "repProyectosPresentados":
            case "repProyectosCuadrosGenerales":
            case "repCuadroPorAreaAplicada":
                if (opRadio.equals("F")) {
                    parametros = parametros + "&pDesde=" + formatDate(fechaInicial) + "&pHasta=" + formatDate(fecha.getTime());
                    parametros = parametros + "&pFiltro=AND $X{[BETWEEN],PRY_FECHA_CREA, pDesde, pHasta}";
                } else if (opRadio.equals("V")) {
                    parametros = parametros + "&pInicio=" + formatDate(convocatoria.getCvoFechaInicio()) + "&pFin=" + formatDate(convocatoria.getCvoFechaFin());
                    parametros = parametros + "&pFiltro1=AND $X{[BETWEEN],INVE_CONVOCATORIA.CVO_FECHA_INICIO, pInicio, pFin}";
                    resetItems();
                }
                break;
            case ("repEstadoGeneralProyectos"):
                if (opRadio.equals("F")) {
                    parametros = parametros + "&pDesde=" + formatDate(fechaInicial) + "&pHasta=" + formatDate(fecha.getTime());
                    parametros = parametros + "&pFiltro=AND $X{[BETWEEN],TABLA.FECHA, pDesde, pHasta}";
                } else if (opRadio.equals("V")) {
                    parametros = parametros + "&pInicio=" + formatDate(convocatoria.getCvoFechaInicio()) + "&pFin=" + formatDate(convocatoria.getCvoFechaFin());
                    parametros = parametros + "&pFiltro1=AND $X{[BETWEEN],TABLA.CVO_FECHA_INICIO, pInicio, pFin}";
                    resetItems();
                }
                break;
            case ("repEstadoGeneralLecturas"):
            case ("repValoracionInfoFinal"):
                if (opRadio.equals("F")) {
                    parametros = parametros + "&pDesde=" + formatDate(fechaInicial) + "&pHasta=" + formatDate(fecha.getTime());
                    if (campoFecha.equals("prorroga")) {
                        parametros = parametros + "&pFiltro=AND $X{[BETWEEN],ULTIMO_SEG_LECTURA.SEG_FECHA_COMUNIC, pDesde, pHasta}"; // Por Designación
                    } else {
                        parametros = parametros + "&pFiltro=AND $X{[BETWEEN],ULTIMA_LECTURA.LEC_FECHA_ENTREGA_VAL, pDesde, pHasta}"; // Por entrega valoracion
                    }
                } else if (opRadio.equals("V")) {
                    parametros = parametros + "&pInicio=" + formatDate(convocatoria.getCvoFechaInicio()) + "&pFin=" + formatDate(convocatoria.getCvoFechaFin());
                    parametros = parametros + "&pFiltro1=AND $X{[BETWEEN],INVE_CONVOCATORIA.CVO_FECHA_INICIO, pInicio, pFin}";
                    resetItems();
                }
                break;
            case ("repValoracionLectura"):
                if (opRadio.equals("F")) {
                    parametros = parametros + "&pDesde=" + formatDate(fechaInicial) + "&pHasta=" + formatDate(fecha.getTime());
                    parametros = parametros + "&pFiltro=AND $X{[BETWEEN],INVE_VALORACION.VAL_FECHA_RECEP, pDesde, pHasta}";
                } else if (opRadio.equals("V")) {
                    parametros = parametros + "&pInicio=" + formatDate(convocatoria.getCvoFechaInicio()) + "&pFin=" + formatDate(convocatoria.getCvoFechaFin());
                    parametros = parametros + "&pFiltro1=AND $X{[BETWEEN],INVE_CONVOCATORIA.CVO_FECHA_INICIO, pInicio, pFin}";
                    resetItems();
                }
                break;
            case ("repCuadrosLectoresProy"):
                if (opRadio.equals("F")) {
                    parametros = parametros + "&pDesde=" + formatDate(fechaInicial) + "&pHasta=" + formatDate(fecha.getTime());
                    parametros = parametros + "&pFiltro=AND $X{[BETWEEN],ULTIMO_SEG_LECTURA.SEG_FECHA_COMUNIC, pDesde, pHasta}";
                }
                break;
            case ("repProyectosCerrados"):
                if (opRadio.equals("F")) {
                    parametros = parametros + "&pDesde=" + formatDate(fechaInicial) + "&pHasta=" + formatDate(fecha.getTime());
                    parametros = parametros + "&pFiltro=AND $X{[BETWEEN],INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC, pDesde, pHasta}";
                } else if (opRadio.equals("V")) {
                    parametros = parametros + "&pInicio=" + formatDate(convocatoria.getCvoFechaInicio()) + "&pFin=" + formatDate(convocatoria.getCvoFechaFin());
                    parametros = parametros + "&pFiltro1=AND $X{[BETWEEN],INVE_CONVOCATORIA.CVO_FECHA_INICIO, pInicio, pFin}";
                    resetItems();
                }
                break;
            case ("repEntregaInfoFinal"):
                if (opRadio.equals("F")) {
                    parametros = parametros + "&pDesde=" + formatDate(fechaInicial) + "&pHasta=" + formatDate(fechaFinal);
                    if (campoFecha.equals("prorroga")) {
                        parametros = parametros
                                + "&pFiltro=AND isnull(PRORROGAEXT.SEG_FECHA_COMUNIC,isnull(PRORROGA2.SEG_FECHA_COMUNIC,isnull(PRORROGA1.SEG_FECHA_COMUNIC,INVE_SEGUIMIENTO.SEG_FECHA_COMUNIC))) between $P{pDesde} and $P{pHasta} ";
                    } else {
                        parametros = parametros + "&pFiltro=AND $X{[BETWEEN],IOP_FECHA_CREA, pDesde, pHasta}";
                    }
                } else if (opRadio.equals("V")) {
                    parametros = parametros + "&pInicio=" + formatDate(convocatoria.getCvoFechaInicio()) + "&pFin=" + formatDate(convocatoria.getCvoFechaFin());
                    parametros = parametros + "&pFiltro1=AND $X{[BETWEEN],INVE_CONVOCATORIA.CVO_FECHA_INICIO, pInicio, pFin}";
                    resetItems();
                }

                break;
            case ("repEstadoGeneralInfoFinal"):
                if (opRadio.equals("F")) {
                    parametros = parametros + "&pDesde=" + formatDate(fechaInicial) + "&pHasta=" + formatDate(fecha.getTime());
                    if (campoFecha.equals("prorroga")) {
                        parametros = parametros + "&pFiltro=AND $X{[BETWEEN],ULTIMO_SEG_LECTURA.SEG_FECHA_COMUNIC, pDesde, pHasta}"; // Por Designación
                    } else {
                        parametros = parametros + "&pFiltro=AND $X{[BETWEEN],ULTIMA_LECTURA.LEC_FECHA_ENTREGA_VAL, pDesde, pHasta}"; // Por entrega valoracion
                    }
                } else if (opRadio.equals("V")) {
                    parametros = parametros + "&pInicio=" + formatDate(convocatoria.getCvoFechaInicio()) + "&pFin=" + formatDate(convocatoria.getCvoFechaFin());
                    parametros = parametros + "&pFiltro1=AND $X{[BETWEEN],INVE_CONVOCATORIA.CVO_FECHA_INICIO, pInicio, pFin}";
                    resetItems();
                }
                break;
            case ("repCuadrosLectoresInforFinal"):
                if (opRadio.equals("F")) {
                    parametros = parametros + "&pDesde=" + formatDate(fechaInicial) + "&pHasta=" + formatDate(fecha.getTime());
                    parametros = parametros + "&pFiltro=AND $X{[BETWEEN],ULTIMO_SEG_LECTURA.SEG_FECHA_COMUNIC, pDesde, pHasta}";
                } else if (opRadio.equals("V")) {
                    parametros = parametros + "&pInicio=" + formatDate(convocatoria.getCvoFechaInicio()) + "&pFin=" + formatDate(convocatoria.getCvoFechaFin());
                    parametros = parametros + "&pFiltro1=AND $X{[BETWEEN],TABLA.CVO_FECHA_INICIO, pInicio, pFin}";
                    resetItems();
                }
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
