/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.metamodel.SingularAttribute;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_CONVOCATORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveConvocatoria.findAll", query = "SELECT i FROM InveConvocatoria i")
    , @NamedQuery(name = "InveConvocatoria.findByCvoCodigo", query = "SELECT i FROM InveConvocatoria i WHERE i.cvoCodigo = :cvoCodigo")
    , @NamedQuery(name = "InveConvocatoria.findByCvoAnioAcad", query = "SELECT i FROM InveConvocatoria i WHERE i.cvoAnioAcad = :cvoAnioAcad")
    , @NamedQuery(name = "InveConvocatoria.findByCvoFechaInicio", query = "SELECT i FROM InveConvocatoria i WHERE i.cvoFechaInicio = :cvoFechaInicio")
    , @NamedQuery(name = "InveConvocatoria.findByCvoFechaFin", query = "SELECT i FROM InveConvocatoria i WHERE i.cvoFechaFin = :cvoFechaFin")
    , @NamedQuery(name = "InveConvocatoria.findByCvoEstado", query = "SELECT i FROM InveConvocatoria i WHERE i.cvoEstado = :cvoEstado")
    , @NamedQuery(name = "InveConvocatoria.findByCvoFechaEstado", query = "SELECT i FROM InveConvocatoria i WHERE i.cvoFechaEstado = :cvoFechaEstado")})
public class InveConvocatoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CVO_CODIGO")
    private Long cvoCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVO_ANIO_ACAD")
    private Short cvoAnioAcad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVO_FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cvoFechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVO_FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cvoFechaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CVO_ESTADO")
    private String cvoEstado;
    @Column(name = "CVO_FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cvoFechaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVO_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cvoFechaCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "CVO_USUARIO_CREA")
    private String cvoUsuarioCrea;
    @Column(name = "CVO_FECHA_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cvoFechaAct;
    @Size(max = 25)
    @Column(name = "CVO_USUARIO_ACT")
    private String cvoUsuarioAct;
    @JoinColumn(name = "TCV_CODIGO", referencedColumnName = "TCV_CODIGO")
    @ManyToOne(optional = false)
    private InveTipoConvocatoria inveTipoConvocatoria;
    @Transient
    private String grupos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveConvocatoria")
    private Collection<InveConvocatGrupo> inveConvocatGrupoCollection;

    public InveConvocatoria() {
    }

    public InveConvocatoria(Long cvoCodigo) {
        this.cvoCodigo = cvoCodigo;
    }

    public InveConvocatoria(Long cvoCodigo, Short cvoAnioAcad, Date cvoFechaInicio, Date cvoFechaFin, String cvoEstado, Date cvoFechaCrea, String cvoUsuarioCrea) {
        this.cvoCodigo = cvoCodigo;
        this.cvoAnioAcad = cvoAnioAcad;
        this.cvoFechaInicio = cvoFechaInicio;
        this.cvoFechaFin = cvoFechaFin;
        this.cvoEstado = cvoEstado;
        this.cvoFechaCrea = cvoFechaCrea;
        this.cvoUsuarioCrea = cvoUsuarioCrea;
    }

    public Long getCvoCodigo() {
        return cvoCodigo;
    }

    public void setCvoCodigo(Long cvoCodigo) {
        this.cvoCodigo = cvoCodigo;
    }

    public Short getCvoAnioAcad() {
        return cvoAnioAcad;
    }

    public void setCvoAnioAcad(Short cvoAnioAcad) {
        this.cvoAnioAcad = cvoAnioAcad;
    }

    public Date getCvoFechaInicio() {
        return cvoFechaInicio;
    }

    public void setCvoFechaInicio(Date cvoFechaInicio) {
        this.cvoFechaInicio = cvoFechaInicio;
    }

    public Date getCvoFechaFin() {
        return cvoFechaFin;
    }

    public void setCvoFechaFin(Date cvoFechaFin) {
        this.cvoFechaFin = cvoFechaFin;
    }

    public String getCvoEstado() {
        return cvoEstado;
    }

    public void setCvoEstado(String cvoEstado) {
        this.cvoEstado = cvoEstado;
    }

    public Date getCvoFechaEstado() {
        return cvoFechaEstado;
    }

    public void setCvoFechaEstado(Date cvoFechaEstado) {
        this.cvoFechaEstado = cvoFechaEstado;
    }

    public Date getCvoFechaCrea() {
        return cvoFechaCrea;
    }

    public void setCvoFechaCrea(Date cvoFechaCrea) {
        this.cvoFechaCrea = cvoFechaCrea;
    }

    public String getCvoUsuarioCrea() {
        return cvoUsuarioCrea;
    }

    public void setCvoUsuarioCrea(String cvoUsuarioCrea) {
        this.cvoUsuarioCrea = cvoUsuarioCrea;
    }

    public Date getCvoFechaAct() {
        return cvoFechaAct;
    }

    public void setCvoFechaAct(Date cvoFechaAct) {
        this.cvoFechaAct = cvoFechaAct;
    }

    public String getCvoUsuarioAct() {
        return cvoUsuarioAct;
    }

    public void setCvoUsuarioAct(String cvoUsuarioAct) {
        this.cvoUsuarioAct = cvoUsuarioAct;
    }

    public InveTipoConvocatoria getInveTipoConvocatoria() {
        return inveTipoConvocatoria;
    }

    public void setInveTipoConvocatoria(InveTipoConvocatoria inveTipoConvocatoria) {
        this.inveTipoConvocatoria = inveTipoConvocatoria;
    }

    @XmlTransient
    public Collection<InveConvocatGrupo> getInveConvocatGrupoCollection() {
        return inveConvocatGrupoCollection;
    }

    public void setInveConvocatGrupoCollection(Collection<InveConvocatGrupo> inveConvocatGrupoCollection) {
        this.inveConvocatGrupoCollection = inveConvocatGrupoCollection;
    }

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String grupos) {
        this.grupos = grupos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cvoCodigo != null ? cvoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveConvocatoria)) {
            return false;
        }
        InveConvocatoria other = (InveConvocatoria) object;
        return !((this.cvoCodigo == null && other.cvoCodigo != null) || (this.cvoCodigo != null && !this.cvoCodigo.equals(other.cvoCodigo)));
    }

    @Override
    public String toString() {
        return "InveConvocatoria{" + "cvoCodigo=" + cvoCodigo 
                + ", cvoAnioAcad=" + cvoAnioAcad 
                + ", cvoFechaInicio=" + cvoFechaInicio
                + ", cvoFechaFin=" + cvoFechaFin 
                + ", cvoEstado=" + cvoEstado 
                + ", cvoFechaEstado=" + cvoFechaEstado
                + ", cvoUsuarioAct=" + cvoUsuarioAct
                + ", inveTipoConvocatoria=" + inveTipoConvocatoria 
                + '}';
    }

    public void setCvoFechaInicio(SingularAttribute<InveConvocatoria, Date> cvoFechaInicio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
