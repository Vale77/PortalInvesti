/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.registro.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.exception.InscripcionException;
import ec.edu.uasb.exception.ProyectoException;
import ec.edu.uasb.external.entities.AreaAcademica;
import ec.edu.uasb.external.entities.Titulo;
import ec.edu.uasb.external.session.AreaAcademicaFacadeLocal;
import ec.edu.uasb.external.session.TituloFacadeLocal;
import ec.edu.uasb.investigacion.entities.InveAmbitoGeografico;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveDisciplinaCientifica;
import ec.edu.uasb.investigacion.entities.InveGrupo;
import ec.edu.uasb.investigacion.entities.InveGrupoInvestigacion;
import ec.edu.uasb.investigacion.entities.InveObjetivoSocioEconomico;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveTipoProyecto;
import ec.edu.uasb.investigacion.session.InveAmbitoGeograficoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveDisciplinaCientificaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveGrupoInvestigacionFacadeLocal;
import ec.edu.uasb.investigacion.session.InveObjetivoSocioEconomicoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveProyectoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveTipoProyectoFacadeLocal;
import ec.edu.uasb.principal.entities.PrinCategoriaInvest;
import ec.edu.uasb.principal.entities.PrinCiudad;
import ec.edu.uasb.principal.entities.PrinCiudadPK;
import ec.edu.uasb.principal.entities.PrinEntidad;
import ec.edu.uasb.principal.entities.PrinPais;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.session.PrinCategoriaInvestFacadeLocal;
import ec.edu.uasb.principal.session.PrinCiudadFacadeLocal;
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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author victor.barba
 */
@Named(value = "regPryBean")
@ViewScoped
public class RegistroPryJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveConvocatoriaFacadeLocal inveConvocatoriaFacade;
    @EJB
    private PrinPersonaFacadeLocal prinPersonaFacade;
    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;
    @EJB
    private PrinCiudadFacadeLocal prinCiudadFacade;
    @EJB
    private PrinPaisFacadeLocal prinPaisFacade;
    @EJB
    private PrinCategoriaInvestFacadeLocal prinCategoriaInvestFacade;
    @EJB
    private TituloFacadeLocal tituloFacade;
    @EJB
    private PrinEntidadFacadeLocal prinEntidadFacade;
    @EJB
    private VincCineFacadeLocal vincCineFacade;
    @EJB
    private InveObjetivoSocioEconomicoFacadeLocal inveObjetivoSocioEconomicoFacade;
    @EJB
    private InveAmbitoGeograficoFacadeLocal inveAmbitoGeograficoFacade;
    @EJB
    private InveDisciplinaCientificaFacadeLocal inveDisciplinaCientificaFacade;
    @EJB
    private AreaAcademicaFacadeLocal areaAcademicaFacade;
    @EJB
    private InveGrupoInvestigacionFacadeLocal inveGrupoInvestigacionFacade;
    @EJB
    private InveTipoProyectoFacadeLocal inveTipoProyectoFacade;

    private final String modulo;
    private final Usuario usr;
    private String perfil = null;

    //<editor-fold defaultstate="collapsed" desc="propiedades Proyecto">
    private InveProyecto inveProyectoEdit;
    private PrinCategoriaInvest categoria;
    private Short anio;
    private boolean cambioRealizado = false;

    public String setCambio() {
        cambioRealizado = true;
        return null;
    }

    public InveProyecto getInveProyectoEdit() {
        return inveProyectoEdit;
    }

    public void setInveProyectoEdit(InveProyecto inveProyectoEdit) {
        this.inveProyectoEdit = inveProyectoEdit;
    }

    public PrinCategoriaInvest getCategoria() {
        return categoria;
    }

    public void setCategoria(PrinCategoriaInvest categoria) {
        this.categoria = categoria;
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

    private Short grupoRubro;

    public Short getGrupoRubro() {
        return grupoRubro;
    }

    public void setGrupoRubro(Short grupoRubro) {
        this.grupoRubro = grupoRubro;
    }

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
    private InveConvocatoria convocatoria;
    private InveGrupoInvestigacion grupoInvestigacionEdit;
    private InveDisciplinaCientifica inveDiscipEdit;
    private InveObjetivoSocioEconomico inveOsoeEdit;
    private InveAmbitoGeografico inveAmbitoGeograficoEdit;
    private boolean habilitarAddCatalogos = false;

    public boolean isHabilitarAddCatalogos() {
        return habilitarAddCatalogos;
    }

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

    //<editor-fold defaultstate="collapsed" desc="PreCheck">
    private List<InveTipoProyecto> listaTipoProy = new ArrayList<>();

    public List<InveTipoProyecto> getListaTipoProy() {
        return listaTipoProy;
    }
//</editor-fold>

    public RegistroPryJSFManagedBean() {
        super.setRenderEdicAdic(true);
        super.setRenderPDF(true);
        // seguridad en perfiles y areas
        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        modulo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subsist");
        List<Perfil> perfiles = (List<Perfil>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfiles");
        if (perfiles != null) {
            for (Perfil perf : perfiles) {
                if (perf.getIdPerfil().equals("USU_INVE") || perf.getIdPerfil().equals("USU_SIST")) {
                    perfil = perf.getIdPerfil();
                    break;
                }
            }
        }

    }

    public void verificarAcceso(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (perfil == null) {
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("/error/access");
            fc.getExternalContext().invalidateSession();
        } else {
            // cargar datos de investigador y proyecto si lo tiene
            if (persona.getPerCodigo() == null) {
                try {
                    buscarRegistro(usr.getUsuCedPass());
                } catch (InscripcionException ex) {
                    Logger.getLogger(RegistroPryJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void init() {
        listaTipoProy = inveTipoProyectoFacade.findAll();
        listaGrupoInvestigacion = inveGrupoInvestigacionFacade.findAll();
        retrieve();
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
        areaCine = null;
        subAreaCine = null;
        cine = null;
        inveDiscipEdit = null;
        inveOsoeEdit = null;
        inveAmbitoGeograficoEdit = null;
        grupoInvestigacionEdit = null;
        codigoCI = null;
        categoria = null;
    }

    //<editor-fold defaultstate="collapsed" desc="recuperar listados">
    public void retrieve() {
        listaAreaCine = vincCineFacade.findBy(null);
        listaAreas = areaAcademicaFacade.getListaAreasDepartamentos();
        listaPais = prinPaisFacade.findAllorde();
        listaUniversidades = prinEntidadFacade.findAllUniversidades();
//        listaClaseDocs = prinClaseDocumentoFacade.findClaseDocs(clasesDocs);
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="recuperar datos investigador y proyecto">
    private boolean estaHabilitado() { // CUMPLIMIENTO DE LA NORMA
        grupoRubro = null;
        /**
         * ******** Convocatoria PERMANANTE *************
         */
        //  DOCENTES planta CON 1 año como profesores - tipo contratos: 1,2, 7
        String cumpleDocente = inveProyectoFacade.findDePlantaBy(persona.getPerIdDoc(), "1,2,7", new BigDecimal("1.0"));
        if (cumpleDocente.equals("S")) {
            categoria = new PrinCategoriaInvest(new Short("1"));
            grupoRubro = new Short("1");
            convocatoria = inveConvocatoriaFacade.findByGrupo(grupoRubro);
            return true;
        }
        // DOCENTES Asistente contratado CON 1 años consecutivos como profesores - tipo contratos: 6
        String cumpleAsistente = inveProyectoFacade.findDePlantaBy(persona.getPerIdDoc(), "6", new BigDecimal("1.0"));
        if (cumpleAsistente.equals("S")) {
            categoria = new PrinCategoriaInvest(new Short("4"));
            grupoRubro = new Short("2");
            convocatoria = inveConvocatoriaFacade.findByGrupo(grupoRubro);
            return true;
        }
        // ESTUDIANTE DE DOCTORADO
        Integer estudDocto = inveProyectoFacade.findEstudianteDoctoradoBy(persona.getPerIdDoc());
        if (estudDocto.compareTo(new Integer("0")) > 0) {
            categoria = new PrinCategoriaInvest(new Short("5"));
            grupoRubro = new Short("3");
            convocatoria = inveConvocatoriaFacade.findByGrupo(grupoRubro);
            return true;
        }
        /**
         * ******** fin Convocatoria PERMANANTE *************
         */

        /**
         * ******** Convocatoria ESPECIFICA *************
         */
        // DOCENTES CONTRATADOS CON 2 años consecutivos como profesores - tipo contratos: 3,4,5
        String cumpleContratado = inveProyectoFacade.findDePlantaBy(persona.getPerIdDoc(), "3,4,5", new BigDecimal("2.0"));
        if (cumpleContratado.equals("S")) {
            categoria = new PrinCategoriaInvest(new Short("2"));
            grupoRubro = new Short("5");
            convocatoria = inveConvocatoriaFacade.findByGrupo(grupoRubro);
            return true;
        }
        // GRADUADOS DE MAESTRIA 
        Integer gradMaestria = inveProyectoFacade.findGraduadoBy(persona.getPerIdDoc(), new Short("2"));
        if (gradMaestria.compareTo(new Integer("0")) > 0) {
            categoria = new PrinCategoriaInvest(new Short("3"));
            grupoRubro = new Short("6");
            convocatoria = inveConvocatoriaFacade.findByGrupo(grupoRubro);
            return true;
        }
        // GRADUADOS DE  DOCTORADO
        Integer gradDoc = inveProyectoFacade.findGraduadoBy(persona.getPerIdDoc(), new Short("3"));
        if (gradDoc.compareTo(new Integer("0")) > 0) {
            categoria = new PrinCategoriaInvest(new Short("3"));
            grupoRubro = new Short("4");
            convocatoria = inveConvocatoriaFacade.findByGrupo(grupoRubro);
            return true;
        }
        /**
         * ******** fin Convocatoria ESPECIFICA *************
         */
        return false;
    }

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
        inveProyectoEdit = inveProyectoFacade.findProyectoBy(usr.getUsuCodigo());
        if (inveProyectoEdit == null) {
            inveProyectoEdit = new InveProyecto();
            inveProyectoEdit.setPryEstadoReg("I");
            inveProyectoEdit.setPryOrigenIngreso("I");
            inveProyectoEdit.setInveTipoProyecto(new InveTipoProyecto(new Short("1")));
            this.setOpcion("insertar");
        } else {
            this.setOpcion("actualizar");
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
//        if (inveProyectoEdit.getPryCodigo() == null) {// registro nuevo
//            listaRubros = inveRubroGrupoFacade.getRubrosXGrupo(grupoRubro);
//            for (InveRubro inveRubro : listaRubros) {
//                InvePresupuesto presup = new InvePresupuesto();
//                presup.setInveRubro(inveRubro);
//                presup.setInveProyecto(inveProyectoEdit);
//                presup.setInvePresupuestoPK(new InvePresupuestoPK(null, inveRubro.getRubCodigo()));
//                listaPresup.add(presup);
//            }
//        } else {
//            listaRubros = inveRubroGrupoFacade.getRubrosXGrupo(inveProyectoEdit.getInveGrupo().getGrpCodigo());
//            listaPresup.addAll(inveProyectoEdit.getInvePresupuestoCollection());
//        }
            inveDiscipEdit = inveProyectoEdit.getInveDisciplinaCientifica();
            inveOsoeEdit = inveProyectoEdit.getInveObjetivoSocioEconomico();
            inveAmbitoGeograficoEdit = inveProyectoEdit.getInveAmbitoGeografico();
            grupoInvestigacionEdit = inveProyectoEdit.getInveGrupoInvestigacion();
        }

    }

    private void buscarRegistro(String cedPass) throws InscripcionException {
        boolean estaHabilitada;
        persona = prinPersonaFacade.findByCedula(cedPass);
        if (persona != null) {
            try {
                personaOrig = persona.clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
            estaHabilitada = estaHabilitado();
            if (estaHabilitada) {
                anio = convocatoria.getCvoAnioAcad();
                recupProyecto();
                recupInvestigador();
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
            System.out.println("buscarRegistro " + this.getMensaje());
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
            paneles.add("form:pnlPerso");
            paneles.add("form:panelDomicilio");
            paneles.add("form:panelTitulo");
            RequestContext.getCurrentInstance().update(paneles);
        }
        return null;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Crear registro">
    @Override
    public void preUpdate() {
        persona.setPrinCiudad(new PrinCiudad(new PrinCiudadPK(paiCodigo, ciuCodigo)));
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
        if (this.getOpcion().equals("actualizar")) {
            inveProyectoEdit.setPryFechaAct(new Date());
            inveProyectoEdit.setPryUsuarioAct(usr.getUsuCedPass());
        } else {
            inveProyectoEdit.setPryFechaCrea(new Date());
            inveProyectoEdit.setPryUsuarioCrea(usr.getUsuCedPass());
            inveProyectoEdit.setPryFechaEstado(new Date());
        }
//        inveProyectoEdit.setInvePresupuestoCollection(listaPresup);
    }

    @Override
    public void insert() {
        try {
            inveProyectoFacade.create(inveProyectoEdit, null);
        } catch (ProyectoException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
//        System.out.println("update() " + inveProyectoEdit);
        inveProyectoFacade.edit(inveProyectoEdit);
    }

    @Override
    public void postUpdate() {
        if (this.getOpcion().equals("actualizar")) {
            this.setMensaje("Información ACTUALIZADA. Si ya esta lista toda la información del proyecto,</br> debe proceder a 'Enviar' el mismo al comité !");
        } else {
            this.setMensaje("Proyecto INGRESADO. Si ya esta lista toda la información del proyecto,</br> debe proceder a Enviar el mismo al comité !");
        }
        this.setIconMensaje("visto.jpg");
        RequestContext.getCurrentInstance().update("dialogMensaje");
        RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");

    }
//</editor-fold>

    public String onFlowProcess(FlowEvent event) {
        String tabDestino = event.getNewStep();
//        switch (tabDestino) {
//            case "infoProy":
////                RequestContext.getCurrentInstance().execute("PF('widgetVarWizardPry').loadStep (widgetVarWizardPry.cfg.steps [1],true);");
//                break;
//            case "envio":
//                break;
//            default:
//                break;
//        }
//        if (cambioRealizado == true) {
            this.preUpdate();
            if (this.getOpcion().equals("actualizar")) {
                this.update();
            } else {
                this.insert();
            }
//            cambioRealizado = false;
//        }
        return tabDestino;
    }

    @Override
    public String print() {
        String parametros = "&pCodProyecto=" + inveProyectoEdit.getPryCodigo();
        String urlReporte = "subsist=" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subsist")
                + "&reporte=repProyecto" + parametros;
//        System.out.println(urlReporte);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte + "&pfdrid_c=true");
        RequestContext.getCurrentInstance().update("pdfDialog");
        return null;
    }

    public void envioComite() {
        inveProyectoEdit.setPryEstadoReg("E");
        inveProyectoEdit.setPryFechaEstado(new Date());
        if (this.getOpcion().equals("actualizar")) {
            this.update();
        }
    }
}
