/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.entities;

import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveLectorContratado;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveRecursoHumano;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "PRIN_PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrinPersona.findAll", query = "SELECT p FROM PrinPersona p")
    , @NamedQuery(name = "PrinPersona.findByPerCodigo", query = "SELECT p FROM PrinPersona p WHERE p.perCodigo = :perCodigo")
    , @NamedQuery(name = "PrinPersona.findByPerIdDoc", query = "SELECT p FROM PrinPersona p WHERE p.perIdDoc = :perIdDoc")})
public class PrinPersona implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_CODIGO")
    private Long perCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PER_ID_DOC")
    private String perIdDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PER_TIPO_DOC")
    private String perTipoDoc;
    @Size(max = 100)
    @Column(name = "PER_PRIMER_APELLIDO")
    private String perPrimerApellido;
    @Size(max = 100)
    @Column(name = "PER_SEGUNDO_APELLIDO")
    private String perSegundoApellido;
    @Size(max = 200)
    @Column(name = "PER_NOMBRES")
    private String perNombres;
    @Column(name = "PER_FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date perFechaNacimiento;
    @Size(max = 1)
    @Column(name = "PER_SEXO")
    private String perSexo;
    @Size(max = 1)
    @Column(name = "PER_GENERO")
    private String perGenero;
    @Size(max = 30)
    @Column(name = "PER_ESTADO_CIVIL")
    private String perEstadoCivil;
    @Size(max = 1)
    @Column(name = "PER_TERCERA_EDAD")
    private String perTerceraEdad;
    @Size(max = 60)
    @Column(name = "PER_EMAIL")
    private String perEmail;
    @Size(max = 15)
    @Column(name = "PER_CELULAR")
    private String perCelular;
    @Size(max = 250)
    @Column(name = "PER_DIREC_DOM")
    private String perDirecDom;
    @Size(max = 15)
    @Column(name = "PER_TELEFONO_DOM")
    private String perTelefonoDom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PER_PRC_DISCAP")
    private BigDecimal perPrcDiscap;
    @Size(max = 15)
    @Column(name = "PER_NRO_CARNET_DISCAP")
    private String perNroCarnetDiscap;
    @Size(max = 60)
    @Column(name = "PER_OCUPACION_ACTUAL")
    private String perOcupacionActual;
    @Size(max = 60)
    @Column(name = "PER_EMAIL_TRABAJO")
    private String perEmailTrabajo;
    @Size(max = 250)
    @Column(name = "PER_DIREC_TRABAJO")
    private String perDirecTrabajo;
    @Size(max = 250)
    @Column(name = "PER_LUGAR_TRABAJO")
    private String perLugarTrabajo;
    @Size(max = 15)
    @Column(name = "PER_TELEFONO_TRAB")
    private String perTelefonoTrab;
    @Size(max = 15)
    @Column(name = "PER_CUENTA_BANCO")
    private String perCuentaBanco;
    @Size(max = 10)
    @Column(name = "PER_TIPO_CUENTA")
    private String perTipoCuenta;
    @Size(max = 15)
    @Column(name = "PER_NUMERO_RUC")
    private String perNumeroRuc;
    @Size(max = 60)
    @Column(name = "PER_AUTOIDENTIFICACION")
    private String perAutoidentificacion;
    @Size(max = 5)
    @Column(name = "PER_GRUPO_SANGUINEO")
    private String perGrupoSanguineo;
    @Size(max = 1)
    @Column(name = "PER_SUSCRIPCION")
    private String perSuscripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "PER_USUARIO")
    private String perUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PER_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date perFechaCrea;
    @Column(name = "PER_FECHA_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date perFechaAct;
    @Size(max = 25)
    @Column(name = "PER_USUARIO_ACT")
    private String perUsuarioAct;
    @Column(name = "PER_ENROL_CODIGO")
    private Integer perEnrolCodigo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "prinPersona")
    private InveLectorContratado inveLectorContratado;

    @OneToMany(mappedBy = "prinPersona")
    private Collection<InveProyecto> inveProyPrimerResponCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prinPersona")
    private Collection<InveRecursoHumano> inveRecursoHumanoCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prinPersona")
    private Collection<PrinTitulo> prinTituloCollection;
    @JoinColumn(name = "BAN_CODIGO", referencedColumnName = "BAN_CODIGO")
    @ManyToOne
    private PrinBanco prinBanco;
    @JoinColumns({
        @JoinColumn(name = "CIU_CODIGO", referencedColumnName = "CIU_CODIGO")
        , @JoinColumn(name = "PAI_CODIGO", referencedColumnName = "PAI_CODIGO")})
    @ManyToOne
    private PrinCiudad prinCiudad;
    @JoinColumn(name = "NAC_PAI_CODIGO", referencedColumnName = "PAI_CODIGO")
    @ManyToOne
    private PrinPais prinNacionalidad;
    @JoinColumn(name = "DIS_CODIGO", referencedColumnName = "DIS_CODIGO")
    @ManyToOne
    private PrinDiscapacidad prinDiscapacidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prinPersona")
    private Collection<InveLector> inveLectorCollection;
    @Transient
    private PrinTitulo prinTitulo;

    public PrinPersona() {
    }

    public PrinPersona(Long perCodigo) {
        this.perCodigo = perCodigo;
    }

    public PrinPersona(Long perCodigo, String perIdDoc, String perTipoDoc, String perUsuario, Date perFechaCrea) {
        this.perCodigo = perCodigo;
        this.perIdDoc = perIdDoc;
        this.perTipoDoc = perTipoDoc;
        this.perUsuario = perUsuario;
        this.perFechaCrea = perFechaCrea;
    }

    public Long getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Long perCodigo) {
        this.perCodigo = perCodigo;
    }

    public String getPerIdDoc() {
        return perIdDoc;
    }

    public void setPerIdDoc(String perIdDoc) {
        this.perIdDoc = perIdDoc;
    }

    public String getPerTipoDoc() {
        return perTipoDoc;
    }

    public void setPerTipoDoc(String perTipoDoc) {
        this.perTipoDoc = perTipoDoc;
    }

    public String getPerPrimerApellido() {
        return perPrimerApellido;
    }

    public void setPerPrimerApellido(String perPrimerApellido) {
        this.perPrimerApellido = perPrimerApellido;
    }

    public String getPerSegundoApellido() {
        return perSegundoApellido;
    }

    public void setPerSegundoApellido(String perSegundoApellido) {
        this.perSegundoApellido = perSegundoApellido;
    }

    public String getPerNombres() {
        return perNombres;
    }

    public void setPerNombres(String perNombres) {
        this.perNombres = perNombres;
    }

    public Date getPerFechaNacimiento() {
        return perFechaNacimiento;
    }

    public void setPerFechaNacimiento(Date perFechaNacimiento) {
        this.perFechaNacimiento = perFechaNacimiento;
    }

    public String getPerSexo() {
        return perSexo;
    }

    public void setPerSexo(String perSexo) {
        this.perSexo = perSexo;
    }

    public String getPerGenero() {
        return perGenero;
    }

    public void setPerGenero(String perGenero) {
        this.perGenero = perGenero;
    }

    public String getPerEstadoCivil() {
        return perEstadoCivil;
    }

    public void setPerEstadoCivil(String perEstadoCivil) {
        this.perEstadoCivil = perEstadoCivil;
    }

    public String getPerTerceraEdad() {
        return perTerceraEdad;
    }

    public void setPerTerceraEdad(String perTerceraEdad) {
        this.perTerceraEdad = perTerceraEdad;
    }

    public String getPerEmail() {
        return perEmail;
    }

    public void setPerEmail(String perEmail) {
        this.perEmail = perEmail;
    }

    public String getPerCelular() {
        return perCelular;
    }

    public void setPerCelular(String perCelular) {
        this.perCelular = perCelular;
    }

    public String getPerDirecDom() {
        return perDirecDom;
    }

    public void setPerDirecDom(String perDirecDom) {
        this.perDirecDom = perDirecDom;
    }

    public String getPerTelefonoDom() {
        return perTelefonoDom;
    }

    public void setPerTelefonoDom(String perTelefonoDom) {
        this.perTelefonoDom = perTelefonoDom;
    }

    public BigDecimal getPerPrcDiscap() {
        return perPrcDiscap;
    }

    public void setPerPrcDiscap(BigDecimal perPrcDiscap) {
        this.perPrcDiscap = perPrcDiscap;
    }

    public String getPerNroCarnetDiscap() {
        return perNroCarnetDiscap;
    }

    public void setPerNroCarnetDiscap(String perNroCarnetDiscap) {
        this.perNroCarnetDiscap = perNroCarnetDiscap;
    }

    public String getPerOcupacionActual() {
        return perOcupacionActual;
    }

    public void setPerOcupacionActual(String perOcupacionActual) {
        this.perOcupacionActual = perOcupacionActual;
    }

    public String getPerEmailTrabajo() {
        return perEmailTrabajo;
    }

    public void setPerEmailTrabajo(String perEmailTrabajo) {
        this.perEmailTrabajo = perEmailTrabajo;
    }

    public String getPerDirecTrabajo() {
        return perDirecTrabajo;
    }

    public void setPerDirecTrabajo(String perDirecTrabajo) {
        this.perDirecTrabajo = perDirecTrabajo;
    }

    public String getPerLugarTrabajo() {
        return perLugarTrabajo;
    }

    public void setPerLugarTrabajo(String perLugarTrabajo) {
        this.perLugarTrabajo = perLugarTrabajo;
    }

    public String getPerTelefonoTrab() {
        return perTelefonoTrab;
    }

    public void setPerTelefonoTrab(String perTelefonoTrab) {
        this.perTelefonoTrab = perTelefonoTrab;
    }

    public String getPerCuentaBanco() {
        return perCuentaBanco;
    }

    public void setPerCuentaBanco(String perCuentaBanco) {
        this.perCuentaBanco = perCuentaBanco;
    }

    public String getPerTipoCuenta() {
        return perTipoCuenta;
    }

    public void setPerTipoCuenta(String perTipoCuenta) {
        this.perTipoCuenta = perTipoCuenta;
    }

    public String getPerNumeroRuc() {
        return perNumeroRuc;
    }

    public void setPerNumeroRuc(String perNumeroRuc) {
        this.perNumeroRuc = perNumeroRuc;
    }

    public String getPerAutoidentificacion() {
        return perAutoidentificacion;
    }

    public void setPerAutoidentificacion(String perAutoidentificacion) {
        this.perAutoidentificacion = perAutoidentificacion;
    }

    public String getPerGrupoSanguineo() {
        return perGrupoSanguineo;
    }

    public void setPerGrupoSanguineo(String perGrupoSanguineo) {
        this.perGrupoSanguineo = perGrupoSanguineo;
    }

    public String getPerSuscripcion() {
        return perSuscripcion;
    }

    public void setPerSuscripcion(String perSuscripcion) {
        this.perSuscripcion = perSuscripcion;
    }

    public String getPerUsuario() {
        return perUsuario;
    }

    public void setPerUsuario(String perUsuario) {
        this.perUsuario = perUsuario;
    }

    public Date getPerFechaCrea() {
        return perFechaCrea;
    }

    public void setPerFechaCrea(Date perFechaCrea) {
        this.perFechaCrea = perFechaCrea;
    }

    public Date getPerFechaAct() {
        return perFechaAct;
    }

    public void setPerFechaAct(Date perFechaAct) {
        this.perFechaAct = perFechaAct;
    }

    public String getPerUsuarioAct() {
        return perUsuarioAct;
    }

    public void setPerUsuarioAct(String perUsuarioAct) {
        this.perUsuarioAct = perUsuarioAct;
    }

    @XmlTransient
    public Collection<PrinTitulo> getPrinTituloCollection() {
        return prinTituloCollection;
    }

    public void setPrinTituloCollection(Collection<PrinTitulo> prinTituloCollection) {
        this.prinTituloCollection = prinTituloCollection;
    }

    @XmlTransient
    public Collection<InveRecursoHumano> getInveRecursoHumanoCollection() {
        return inveRecursoHumanoCollection;
    }

    public void setInveRecursoHumanoCollection(Collection<InveRecursoHumano> inveRecursoHumanoCollection) {
        this.inveRecursoHumanoCollection = inveRecursoHumanoCollection;
    }

    public PrinBanco getPrinBanco() {
        return prinBanco;
    }

    public void setPrinBanco(PrinBanco prinBanco) {
        this.prinBanco = prinBanco;
    }

    public PrinCiudad getPrinCiudad() {
        return prinCiudad;
    }

    public void setPrinCiudad(PrinCiudad prinCiudad) {
        this.prinCiudad = prinCiudad;
    }

    public PrinDiscapacidad getPrinDiscapacidad() {
        return prinDiscapacidad;
    }

    public void setPrinDiscapacidad(PrinDiscapacidad prinDiscapacidad) {
        this.prinDiscapacidad = prinDiscapacidad;
    }

    public PrinPais getPrinNacionalidad() {
        return prinNacionalidad;
    }

    public void setPrinNacionalidad(PrinPais prinNacionalidad) {
        this.prinNacionalidad = prinNacionalidad;
    }

    public PrinTitulo getPrinTitulo() {
        return prinTitulo;
    }

    public void setPrinTitulo(PrinTitulo prinTitulo) {
        this.prinTitulo = prinTitulo;
    }

    public Integer getPerEnrolCodigo() {
        return perEnrolCodigo;
    }

    public void setPerEnrolCodigo(Integer perEnrolCodigo) {
        this.perEnrolCodigo = perEnrolCodigo;
    }

    @XmlTransient
    public Collection<InveProyecto> getInveProyPrimerResponCollection() {
        return inveProyPrimerResponCollection;
    }

    public void setInveProyPrimerResponCollection(Collection<InveProyecto> inveProyPrimerResponCollection) {
        this.inveProyPrimerResponCollection = inveProyPrimerResponCollection;
    }

    public InveLectorContratado getInveLectorContratado() {
        return inveLectorContratado;
    }

    public void setInveLectorContratado(InveLectorContratado inveLectorContratado) {
        this.inveLectorContratado = inveLectorContratado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perCodigo != null ? perCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinPersona)) {
            return false;
        }
        PrinPersona other = (PrinPersona) object;
        return !((this.perCodigo == null && other.perCodigo != null) || (this.perCodigo != null && !this.perCodigo.equals(other.perCodigo)));
    }

    @Override
    public PrinPersona clone() throws CloneNotSupportedException {
        return (PrinPersona) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "PrinPersona{" + "perCodigo=" + perCodigo
                + ", perIdDoc=" + perIdDoc
//                + ", perTipoDoc=" + perTipoDoc
//                + ", perSexo=" + perSexo
//                + ", perGenero=" + perGenero
//                + ", perTerceraEdad=" + perTerceraEdad
//                + ", perEmail=" + perEmail
//                + ", perCelular=" + perCelular
//                + ", perDirecDom=" + perDirecDom
//                + ", perTelefonoDom=" + perTelefonoDom
//                + ", perPrcDiscap=" + perPrcDiscap
//                + ", perNroCarnetDiscap=" + perNroCarnetDiscap
//                + ", perOcupacionActual=" + perOcupacionActual
//                + ", perEmailTrabajo=" + perEmailTrabajo
//                + ", perDirecTrabajo=" + perDirecTrabajo
//                + ", perTelefonoTrab=" + perTelefonoTrab
//                + ", perLugarTrabajo=" + perLugarTrabajo
//                + ", perTipoCuenta=" + perTipoCuenta
//                + ", perAutoidentificacion=" + perAutoidentificacion
//                + ", perSuscripcion=" + perSuscripcion
//                + ", perFechaCrea=" + perFechaCrea
//                + ", perFechaAct=" + perFechaAct
//                + ", prinCiudad=" + prinCiudad
                + ", prinNacionalidad=" + prinNacionalidad
//                + ", prinDiscapacidad=" + prinDiscapacidad
                + '}';
    }

}
