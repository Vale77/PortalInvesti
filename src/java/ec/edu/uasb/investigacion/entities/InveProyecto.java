/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.external.entities.AreaAcademica;
import ec.edu.uasb.principal.entities.PrinCategoriaInvest;
import ec.edu.uasb.vinculacion.entities.VincCine;
import ec.edu.uasb.principal.entities.PrinPais;
import ec.edu.uasb.principal.entities.PrinPersona;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_PROYECTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveProyecto.findAll", query = "SELECT i FROM InveProyecto i order by i.pryTitulo")
    , @NamedQuery(name = "InveProyecto.findByPryCodigo", query = "SELECT i FROM InveProyecto i WHERE i.pryCodigo = :pryCodigo")
    , @NamedQuery(name = "InveProyecto.findByPryCodigoCi", query = "SELECT i FROM InveProyecto i WHERE i.pryCodigoCi = :pryCodigoCi")})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ProyAprobResults",
            entities = {
                @EntityResult(entityClass = InveProyecto.class)
                ,
                 @EntityResult(entityClass = InveSeguimiento.class)
            },
            columns = {
                @ColumnResult(name = "OFICIO_APROB")
                ,@ColumnResult(name = "FECHA_APROB")
            }
    )}
)
public class InveProyecto implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRY_CODIGO")
    private Long pryCodigo;
    @Column(name = "PRY_CODIGO_CI")
    private String pryCodigoCi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRY_ANIO_ACAD")
    private short pryAnioAcad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PRY_ESTADO_REG")
    private String pryEstadoReg;
    @Column(name = "PRY_FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pryFechaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRY_TITULO")
    private String pryTitulo;
    @Column(name = "PRY_TITULO_FINAL")
    private String pryTituloFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRY_CORREO")
    private String pryCorreo;
    @Column(name = "PRY_CELULAR")
    private String pryCelular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRY_AREA_ACAD")
    private Short pryAreaAcad;
    @Column(name = "PRY_FECHA_CIERRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pryFechaCierre;
    @Column(name = "PRY_FECHA_ENTREGA_FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pryFechaEntregaFinal;
    @Column(name = "PRY_FECHA_RECEPCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pryFechaRecepcion;
    @Column(name = "PRY_OBJETIVO_GEN")
    private String pryObjetivoGen;
    @Column(name = "PRY_OBJETIVO_ESP")
    private String pryObjetivoEsp;
    @Column(name = "PRY_JUSTIFICACION")
    private String pryJustificacion;
    @Column(name = "PRY_JUSTIFICACION_TEXT")
    private String pryJustificacionText;
    @Column(name = "PRY_RELEVANCIA")
    private String pryRelevancia;
    @Column(name = "PRY_RELEVANCIA_TEXT")
    private String pryRelevanciaText;
    @Column(name = "PRY_PLANTEAMIENTO")
    private String pryPlanteamiento;
    @Column(name = "PRY_PLANTEAMIENTO_TEXT")
    private String pryPlanteamientoText;
    @Column(name = "PRY_METODOLOGIA")
    private String pryMetodologia;
    @Column(name = "PRY_METODOLOGIA_TEXT")
    private String pryMetodologiaText;
    @Column(name = "PRY_PLAN_INICIAL")
    private String pryPlanInicial;
    @Column(name = "PRY_PLAN_INICIAL_TEXT")
    private String pryPlanInicialText;
    @Column(name = "PRY_CRONO_MENSUAL")
    private String pryCronoMensual;
    @Column(name = "PRY_OBSERVACION")
    private String pryObservacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRY_TIPO_INVESTIG")
    private String pryTipoInvestig;
    @Column(name = "PRY_ORIGEN_INGRESO")
    private String pryOrigenIngreso;
    @Column(name = "PRY_NUMERO_SOLIC_CONTRATO")
    private Long pryNumeroSolicContrato;
    @Column(name = "PRY_FECHA_SOLIC_CONTRATO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pryFechaSolicContrato;
    @Column(name = "PRY_CONTRATO")
    private String pryContrato;
    @Column(name = "PRY_FECHA_CONTRATO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pryFechaContrato;
    @Column(name = "PRY_FECHA_RECEP_CONTRATO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pryFechaRecepContrato;
    @Column(name = "PRY_AUTORIZA_ENVIO_REPOS")
    private String pryAutorizaEnvioRepos;
    @Column(name = "PRY_FECHA_AUTORIZA_ENVIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pryFechaAutorizaEnvio;
    @Column(name = "PRY_CODIGO_REPOS")
    private String pryCodigoRepos;
    @Column(name = "PRY_FECHA_ENVIO_REPOS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pryFechaEnvioRepos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRY_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pryFechaCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRY_USUARIO_CREA")
    private String pryUsuarioCrea;
    @Column(name = "PRY_FECHA_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pryFechaAct;
    @Column(name = "PRY_USUARIO_ACT")
    private String pryUsuarioAct;
    @OneToMany(mappedBy = "inveProyecto")
    private Collection<InveVersion> inveVersionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveProyecto")
    private Collection<InveLector> inveLectorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveProyecto")
    private Collection<InveSeguimiento> inveSeguimientoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveProyecto")
    private Collection<InvePresupuesto> invePresupuestoCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "inveProyecto")
    private InveCarta inveCarta;
    @JoinColumn(name = "CVO_CODIGO", referencedColumnName = "CVO_CODIGO")
    @ManyToOne(optional = false)
    private InveConvocatoria inveConvocatoria;
    @JoinColumn(name = "AMB_CODIGO", referencedColumnName = "AMB_CODIGO")
    @ManyToOne
    private InveAmbitoGeografico inveAmbitoGeografico;
    @JoinColumn(name = "DSC_CODIGO", referencedColumnName = "DSC_CODIGO")
    @ManyToOne
    private InveDisciplinaCientifica inveDisciplinaCientifica;
    @JoinColumn(name = "FIN_CODIGO", referencedColumnName = "FIN_CODIGO")
    @ManyToOne
    private InveFuenteFinan inveFuenteFinan;
    @JoinColumn(name = "GRP_CODIGO", referencedColumnName = "GRP_CODIGO")
    @ManyToOne
    private InveGrupo inveGrupo;
    @JoinColumn(name = "OSE_CODIGO", referencedColumnName = "OSE_CODIGO")
    @ManyToOne
    private InveObjetivoSocioEconomico inveObjetivoSocioEconomico;
    @OneToMany(mappedBy = "inveProyecto")
    private Collection<InveProyecto> inveProyectoCollection;
    @JoinColumn(name = "INV_PRY_CODIGO", referencedColumnName = "PRY_CODIGO")
    @ManyToOne
    private InveProyecto inveProyecto;
    @JoinColumn(name = "TIP_CODIGO", referencedColumnName = "TIP_CODIGO")
    @ManyToOne
    private InveTipoProyecto inveTipoProyecto;
    @JoinColumn(name = "CAI_CODIGO", referencedColumnName = "CAI_CODIGO")
    @ManyToOne
    private PrinCategoriaInvest prinCategoriaInvest;
    @JoinColumn(name = "CIN_CODIGO", referencedColumnName = "CIN_CODIGO")
    @ManyToOne
    private VincCine vincCine;
    @JoinColumn(name = "GIN_CODIGO", referencedColumnName = "GIN_CODIGO")
    @ManyToOne
    private InveGrupoInvestigacion inveGrupoInvestigacion;
    @JoinColumn(name = "PAI_CODIGO", referencedColumnName = "PAI_CODIGO")
    @ManyToOne
    private PrinPais prinPais;
    @JoinColumn(name = "PER_CODIGO", referencedColumnName = "PER_CODIGO")
    @ManyToOne(optional = false)
    private PrinPersona prinPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveProyecto")
    private Collection<InveEquipoMulti> inveEquipoMultiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveProyecto")
    private Collection<InveRecursoHumano> inveRecursoHumanoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveProyecto")
    private Collection<InveCronogramaMensual> inveCronogramaMensualCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "inveProyecto")
    private InveInformeOper inveInformeOper;
    @Transient
    private AreaAcademica area;
    @Transient
    private String notificacionAprob;
    @Transient
    private Date fechaNotifAprob;
    @Transient
    private InveSeguimiento inveSeguimiento;

    public InveProyecto() {
    }

    public InveProyecto(Long pryCodigo) {
        this.pryCodigo = pryCodigo;
    }

    public InveProyecto(Long pryCodigo, short pryAnioAcad, String pryEstadoReg, Date pryFechaEstado, String pryTitulo,
            String pryTituloFinal, String pryCelular, String pryCorreo, Short pryAreaAcad,
            String pryJustificacion, String pryRelevancia, String pryObjetivoGen, String pryObjetivoEsp, String pryPlanteamiento,
            String pryMetodologia, String pryPlanInicial, String pryTipoInvestig, Date pryFechaCrea, String pryUsuarioCrea, String notificacionAprob) {
        this.pryCodigo = pryCodigo;
        this.pryAnioAcad = pryAnioAcad;
        this.pryEstadoReg = pryEstadoReg;
        this.pryFechaEstado = pryFechaEstado;
        this.pryTitulo = pryTitulo;
        this.pryTituloFinal = pryTituloFinal;
        this.pryCorreo = pryCorreo;
        this.pryCelular = pryCelular;
        this.pryAreaAcad = pryAreaAcad;
        this.pryJustificacion = pryJustificacion;
        this.pryRelevancia = pryRelevancia;
        this.pryObjetivoGen = pryObjetivoGen;
        this.pryObjetivoEsp = pryObjetivoEsp;
        this.pryPlanteamiento = pryPlanteamiento;
        this.pryMetodologia = pryMetodologia;
        this.pryPlanInicial = pryPlanInicial;
        this.pryTipoInvestig = pryTipoInvestig;
        this.pryFechaCrea = pryFechaCrea;
        this.pryUsuarioCrea = pryUsuarioCrea;
        this.notificacionAprob= notificacionAprob;
        }

    public Long getPryCodigo() {
        return pryCodigo;
    }

    public void setPryCodigo(Long pryCodigo) {
        this.pryCodigo = pryCodigo;
    }

    public String getPryCodigoCi() {
        return pryCodigoCi;
    }

    public void setPryCodigoCi(String pryCodigoCi) {
        this.pryCodigoCi = pryCodigoCi;
    }

    public short getPryAnioAcad() {
        return pryAnioAcad;
    }

    public void setPryAnioAcad(short pryAnioAcad) {
        this.pryAnioAcad = pryAnioAcad;
    }

    public String getPryEstadoReg() {
        return pryEstadoReg;
    }

    public void setPryEstadoReg(String pryEstadoReg) {
        this.pryEstadoReg = pryEstadoReg;
    }

    public Date getPryFechaEstado() {
        return pryFechaEstado;
    }

    public void setPryFechaEstado(Date pryFechaEstado) {
        this.pryFechaEstado = pryFechaEstado;
    }

    public String getPryTitulo() {
        return pryTitulo;
    }

    public void setPryTitulo(String pryTitulo) {
        this.pryTitulo = pryTitulo;
    }

    public String getPryTituloFinal() {
        return pryTituloFinal;
    }

    public void setPryTituloFinal(String pryTituloFinal) {
        this.pryTituloFinal = pryTituloFinal;
    }

    public String getPryCorreo() {
        return pryCorreo;
    }

    public void setPryCorreo(String pryCorreo) {
        this.pryCorreo = pryCorreo;
    }

    public String getPryCelular() {
        return pryCelular;
    }

    public void setPryCelular(String pryCelular) {
        this.pryCelular = pryCelular;
    }

    public Short getPryAreaAcad() {
        return pryAreaAcad;
    }

    public void setPryAreaAcad(Short pryAreaAcad) {
        this.pryAreaAcad = pryAreaAcad;
    }

    public Date getPryFechaCierre() {
        return pryFechaCierre;
    }

    public void setPryFechaCierre(Date pryFechaCierre) {
        this.pryFechaCierre = pryFechaCierre;
    }

    public Date getPryFechaEntregaFinal() {
        return pryFechaEntregaFinal;
    }

    public void setPryFechaEntregaFinal(Date pryFechaEntregaFinal) {
        this.pryFechaEntregaFinal = pryFechaEntregaFinal;
    }

    public Date getPryFechaRecepcion() {
        return pryFechaRecepcion;
    }

    public void setPryFechaRecepcion(Date pryFechaRecepcion) {
        this.pryFechaRecepcion = pryFechaRecepcion;
    }

    public String getPryJustificacion() {
        return pryJustificacion;
    }

    public void setPryJustificacion(String pryJustificacion) {
        this.pryJustificacion = pryJustificacion;
    }

    public String getPryRelevancia() {
        return pryRelevancia;
    }

    public void setPryRelevancia(String pryRelevancia) {
        this.pryRelevancia = pryRelevancia;
    }

    public String getPryJustificacionText() {
        return pryJustificacionText;
    }

    public void setPryJustificacionText(String pryJustificacionText) {
        this.pryJustificacionText = pryJustificacionText;
    }

    public String getPryRelevanciaText() {
        return pryRelevanciaText;
    }

    public void setPryRelevanciaText(String pryRelevanciaText) {
        this.pryRelevanciaText = pryRelevanciaText;
    }

    public String getPryPlanteamientoText() {
        return pryPlanteamientoText;
    }

    public void setPryPlanteamientoText(String pryPlanteamientoText) {
        this.pryPlanteamientoText = pryPlanteamientoText;
    }

    public String getPryMetodologiaText() {
        return pryMetodologiaText;
    }

    public void setPryMetodologiaText(String pryMetodologiaText) {
        this.pryMetodologiaText = pryMetodologiaText;
    }

    public String getPryPlanInicialText() {
        return pryPlanInicialText;
    }

    public void setPryPlanInicialText(String pryPlanInicialText) {
        this.pryPlanInicialText = pryPlanInicialText;
    }

    public String getPryObjetivoGen() {
        return pryObjetivoGen;
    }

    public void setPryObjetivoGen(String pryObjetivoGen) {
        this.pryObjetivoGen = pryObjetivoGen;
    }

    public String getPryObjetivoEsp() {
        return pryObjetivoEsp;
    }

    public void setPryObjetivoEsp(String pryObjetivoEsp) {
        this.pryObjetivoEsp = pryObjetivoEsp;
    }

    public String getPryCronoMensual() {
        return pryCronoMensual;
    }

    public void setPryCronoMensual(String pryCronoMensual) {
        this.pryCronoMensual = pryCronoMensual;
    }

    public String getPryObservacion() {
        return pryObservacion;
    }

    public void setPryObservacion(String pryObservacion) {
        this.pryObservacion = pryObservacion;
    }

    public String getPryPlanteamiento() {
        return pryPlanteamiento;
    }

    public void setPryPlanteamiento(String pryPlanteamiento) {
        this.pryPlanteamiento = pryPlanteamiento;
    }

    public String getPryMetodologia() {
        return pryMetodologia;
    }

    public void setPryMetodologia(String pryMetodologia) {
        this.pryMetodologia = pryMetodologia;
    }

    public String getPryPlanInicial() {
        return pryPlanInicial;
    }

    public void setPryPlanInicial(String pryPlanInicial) {
        this.pryPlanInicial = pryPlanInicial;
    }

    public String getPryTipoInvestig() {
        return pryTipoInvestig;
    }

    public void setPryTipoInvestig(String pryTipoInvestig) {
        this.pryTipoInvestig = pryTipoInvestig;
    }

    public Date getPryFechaEnvioRepos() {
        return pryFechaEnvioRepos;
    }

    public void setPryFechaEnvioRepos(Date pryFechaEnvioRepos) {
        this.pryFechaEnvioRepos = pryFechaEnvioRepos;
    }

    public Date getPryFechaCrea() {
        return pryFechaCrea;
    }

    public void setPryFechaCrea(Date pryFechaCrea) {
        this.pryFechaCrea = pryFechaCrea;
    }

    public String getPryUsuarioCrea() {
        return pryUsuarioCrea;
    }

    public void setPryUsuarioCrea(String pryUsuarioCrea) {
        this.pryUsuarioCrea = pryUsuarioCrea;
    }

    public Date getPryFechaAct() {
        return pryFechaAct;
    }

    public void setPryFechaAct(Date pryFechaAct) {
        this.pryFechaAct = pryFechaAct;
    }

    public String getPryUsuarioAct() {
        return pryUsuarioAct;
    }

    public void setPryUsuarioAct(String pryUsuarioAct) {
        this.pryUsuarioAct = pryUsuarioAct;
    }

    @XmlTransient
    public Collection<InveVersion> getInveVersionCollection() {
        return inveVersionCollection;
    }

    public void setInveVersionCollection(Collection<InveVersion> inveVersionCollection) {
        this.inveVersionCollection = inveVersionCollection;
    }

    @XmlTransient
    public Collection<InveLector> getInveLectorCollection() {
        return inveLectorCollection;
    }

    public void setInveLectorCollection(Collection<InveLector> inveLectorCollection) {
        this.inveLectorCollection = inveLectorCollection;
    }

    @XmlTransient
    public Collection<InvePresupuesto> getInvePresupuestoCollection() {
        return invePresupuestoCollection;
    }

    public void setInvePresupuestoCollection(Collection<InvePresupuesto> invePresupuestoCollection) {
        this.invePresupuestoCollection = invePresupuestoCollection;
    }

    public InveCarta getInveCarta() {
        return inveCarta;
    }

    public void setInveCarta(InveCarta inveCarta) {
        this.inveCarta = inveCarta;
    }

    public InveAmbitoGeografico getInveAmbitoGeografico() {
        return inveAmbitoGeografico;
    }

    public void setInveAmbitoGeografico(InveAmbitoGeografico inveAmbitoGeografico) {
        this.inveAmbitoGeografico = inveAmbitoGeografico;
    }

    public InveConvocatoria getInveConvocatoria() {
        return inveConvocatoria;
    }

    public void setInveConvocatoria(InveConvocatoria inveConvocatoria) {
        this.inveConvocatoria = inveConvocatoria;
    }

    public InveDisciplinaCientifica getInveDisciplinaCientifica() {
        return inveDisciplinaCientifica;
    }

    public void setInveDisciplinaCientifica(InveDisciplinaCientifica inveDisciplinaCientifica) {
        this.inveDisciplinaCientifica = inveDisciplinaCientifica;
    }

    public PrinCategoriaInvest getPrinCategoriaInvest() {
        return prinCategoriaInvest;
    }

    public void setPrinCategoriaInvest(PrinCategoriaInvest prinCategoriaInvest) {
        this.prinCategoriaInvest = prinCategoriaInvest;
    }

    public InveFuenteFinan getInveFuenteFinan() {
        return inveFuenteFinan;
    }

    public void setInveFuenteFinan(InveFuenteFinan inveFuenteFinan) {
        this.inveFuenteFinan = inveFuenteFinan;
    }

    public InveGrupo getInveGrupo() {
        return inveGrupo;
    }

    public void setInveGrupo(InveGrupo inveGrupo) {
        this.inveGrupo = inveGrupo;
    }

    public InveObjetivoSocioEconomico getInveObjetivoSocioEconomico() {
        return inveObjetivoSocioEconomico;
    }

    public void setInveObjetivoSocioEconomico(InveObjetivoSocioEconomico inveObjetivoSocioEconomico) {
        this.inveObjetivoSocioEconomico = inveObjetivoSocioEconomico;
    }

    public InveGrupoInvestigacion getInveGrupoInvestigacion() {
        return inveGrupoInvestigacion;
    }

    public void setInveGrupoInvestigacion(InveGrupoInvestigacion inveGrupoInvestigacion) {
        this.inveGrupoInvestigacion = inveGrupoInvestigacion;
    }

    @XmlTransient
    public Collection<InveProyecto> getInveProyectoCollection() {
        return inveProyectoCollection;
    }

    public void setInveProyectoCollection(Collection<InveProyecto> inveProyectoCollection) {
        this.inveProyectoCollection = inveProyectoCollection;
    }

    public InveTipoProyecto getInveTipoProyecto() {
        return inveTipoProyecto;
    }

    public void setInveTipoProyecto(InveTipoProyecto inveTipoProyecto) {
        this.inveTipoProyecto = inveTipoProyecto;
    }

    public String getPryOrigenIngreso() {
        return pryOrigenIngreso;
    }

    public void setPryOrigenIngreso(String pryOrigenIngreso) {
        this.pryOrigenIngreso = pryOrigenIngreso;
    }

    public Long getPryNumeroSolicContrato() {
        return pryNumeroSolicContrato;
    }

    public void setPryNumeroSolicContrato(Long pryNumeroSolicContrato) {
        this.pryNumeroSolicContrato = pryNumeroSolicContrato;
    }

    public Date getPryFechaSolicContrato() {
        return pryFechaSolicContrato;
    }

    public void setPryFechaSolicContrato(Date pryFechaSolicContrato) {
        this.pryFechaSolicContrato = pryFechaSolicContrato;
    }

    public String getPryContrato() {
        return pryContrato;
    }

    public void setPryContrato(String pryContrato) {
        this.pryContrato = pryContrato;
    }

    public Date getPryFechaContrato() {
        return pryFechaContrato;
    }

    public void setPryFechaContrato(Date pryFechaContrato) {
        this.pryFechaContrato = pryFechaContrato;
    }

    public Date getPryFechaRecepContrato() {
        return pryFechaRecepContrato;
    }

    public void setPryFechaRecepContrato(Date pryFechaRecepContrato) {
        this.pryFechaRecepContrato = pryFechaRecepContrato;
    }

    public String getPryAutorizaEnvioRepos() {
        return pryAutorizaEnvioRepos;
    }

    public void setPryAutorizaEnvioRepos(String pryAutorizaEnvioRepos) {
        this.pryAutorizaEnvioRepos = pryAutorizaEnvioRepos;
    }

    public Date getPryFechaAutorizaEnvio() {
        return pryFechaAutorizaEnvio;
    }

    public void setPryFechaAutorizaEnvio(Date pryFechaAutorizaEnvio) {
        this.pryFechaAutorizaEnvio = pryFechaAutorizaEnvio;
    }

    public String getPryCodigoRepos() {
        return pryCodigoRepos;
    }

    public void setPryCodigoRepos(String pryCodigoRepos) {
        this.pryCodigoRepos = pryCodigoRepos;
    }

    public VincCine getVincCine() {
        return vincCine;
    }

    public void setVincCine(VincCine vincCine) {
        this.vincCine = vincCine;
    }

    public InveProyecto getInveProyecto() {
        return inveProyecto;
    }

    public void setInveProyecto(InveProyecto inveProyecto) {
        this.inveProyecto = inveProyecto;
    }

    @XmlTransient
    public Collection<InveEquipoMulti> getInveEquipoMultiCollection() {
        return inveEquipoMultiCollection;
    }

    public void setInveEquipoMultiCollection(Collection<InveEquipoMulti> inveEquipoMultiCollection) {
        this.inveEquipoMultiCollection = inveEquipoMultiCollection;
    }

    @XmlTransient
    public Collection<InveRecursoHumano> getInveRecursoHumanoCollection() {
        return inveRecursoHumanoCollection;
    }

    public void setInveRecursoHumanoCollection(Collection<InveRecursoHumano> inveRecursoHumanoCollection) {
        this.inveRecursoHumanoCollection = inveRecursoHumanoCollection;
    }

    @XmlTransient
    public Collection<InveCronogramaMensual> getInveCronogramaMensualCollection() {
        return inveCronogramaMensualCollection;
    }

    public void setInveCronogramaMensualCollection(Collection<InveCronogramaMensual> inveCronogramaMensualCollection) {
        this.inveCronogramaMensualCollection = inveCronogramaMensualCollection;
    }

    public InveInformeOper getInveInformeOper() {
        return inveInformeOper;
    }

    public void setInveInformeOper(InveInformeOper inveInformeOper) {
        this.inveInformeOper = inveInformeOper;
    }

    @XmlTransient
    public Collection<InveSeguimiento> getInveSeguimientoCollection() {
        return inveSeguimientoCollection;
    }

    public void setInveSeguimientoCollection(Collection<InveSeguimiento> inveSeguimientoCollection) {
        this.inveSeguimientoCollection = inveSeguimientoCollection;
    }

    public PrinPais getPrinPais() {
        return prinPais;
    }

    public void setPrinPais(PrinPais prinPais) {
        this.prinPais = prinPais;
    }

    public PrinPersona getPrinPersona() {
        return prinPersona;
    }

    public void setPrinPersona(PrinPersona prinPersona) {
        this.prinPersona = prinPersona;
    }

    public AreaAcademica getArea() {
        return area;
    }

    public void setArea(AreaAcademica area) {
        this.area = area;
    }

    public String getNotificacionAprob() {
        return notificacionAprob;
    }

    public void setNotificacionAprob(String notificacionAprob) {
        this.notificacionAprob = notificacionAprob;
    }

    public Date getFechaNotifAprob() {
        return fechaNotifAprob;
    }

    public void setFechaNotifAprob(Date fechaNotifAprob) {
        this.fechaNotifAprob = fechaNotifAprob;
    }

    public InveSeguimiento getInveSeguimiento() {
        return inveSeguimiento;
    }

    public void setInveSeguimiento(InveSeguimiento inveSeguimiento) {
        this.inveSeguimiento = inveSeguimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pryCodigo != null ? pryCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveProyecto)) {
            return false;
        }
        InveProyecto other = (InveProyecto) object;
        return !((this.pryCodigo == null && other.pryCodigo != null) || (this.pryCodigo != null && !this.pryCodigo.equals(other.pryCodigo)));
    }

    @Override
    public InveProyecto clone() throws CloneNotSupportedException {
        return (InveProyecto) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "InveProyecto{" + "pryCodigo=" + pryCodigo
                + ", pryCodigoCi=" + pryCodigoCi
                + ", pryAnioAcad=" + pryAnioAcad
                + ", pryEstadoReg=" + pryEstadoReg
                + ", pryTitulo=" + pryTitulo
                + ", pryCorreo=" + pryCorreo
                + ", pryCelular=" + pryCelular
                + ", pryAreaAcad=" + pryAreaAcad
                + ", pryFechaCierre=" + pryFechaCierre
                + ", pryFechaEntregaFinal=" + pryFechaEntregaFinal
                + ", pryFechaRecepcion=" + pryFechaRecepcion
                + ", pryFechaRecepcion=" + pryFechaRecepcion
                + ", notificacionAprob=" + notificacionAprob
                //                + ", pryJustificacion=" + pryJustificacion
                //                + ", pryRelevancia=" + pryRelevancia
                + ", pryObjetivoGen=" + pryObjetivoGen
                + ", pryObjetivoEsp=" + pryObjetivoEsp
                + ", pryJustificacionText=" + pryJustificacionText
                + ", pryMetodologiaText=" + pryMetodologiaText
                + ", pryPlanInicialText=" + pryPlanInicialText
                + ", pryPlanteamientoText=" + pryPlanteamientoText
                + ", pryRelevanciaText=" + pryRelevanciaText
                //                + ", pryPlanteamiento=" + pryPlanteamiento
                //                + ", pryMetodologia=" + pryMetodologia
                //                + ", pryPlanInicial=" + pryPlanInicial
                //                + ", pryCronoMensual=" + pryCronoMensual
                + ", pryObservacion=" + pryObservacion
                + ", pryTipoInvestig=" + pryTipoInvestig
                + ", pryOrigenIngreso=" + pryOrigenIngreso
//                + ", pryFechaCrea=" + pryFechaCrea
//                + ", pryUsuarioCrea=" + pryUsuarioCrea
//                + ", pryFechaAct=" + pryFechaAct
//                + ", pryUsuarioAct=" + pryUsuarioAct
//                + ", inveCarta=" + inveCarta
//                + ", inveAmbitoGeografico=" + inveAmbitoGeografico
//                + ", inveConvocatoria=" + inveConvocatoria
//                + ", inveDisciplinaCientifica=" + inveDisciplinaCientifica
//                + ", prinCategoriaInvest=" + prinCategoriaInvest
//                + ", inveFuenteFinan=" + inveFuenteFinan
//                + ", inveObjetivoSocioEconomico=" + inveObjetivoSocioEconomico
//                + ", inveGrupoInvestigacion=" + inveGrupoInvestigacion
//                + ", inveTipoProyecto=" + inveTipoProyecto
//                + ", vincCine=" + vincCine
//                + ", prinPersona=" + prinPersona
//                + ", inveInformeOper=" + inveInformeOper
                + '}';
    }

}
