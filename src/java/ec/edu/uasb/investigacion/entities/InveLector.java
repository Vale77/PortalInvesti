/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "INVE_LECTOR")
@XmlRootElement
@NamedQuery(name = "InveLector.findByInveProyecto", query = "SELECT i FROM InveLector i WHERE i.inveProyecto = :inveProyecto and i.inveRol = :inveRol ")
@SqlResultSetMappings({
     @SqlResultSetMapping(name = "UltimasLecturasValInfoFinal",
            entities = {
                @EntityResult(entityClass = InveLector.class)
            },
            columns = {
                @ColumnResult(name = "ESTADO")
                ,@ColumnResult(name = "TEXTO_ESTADO")
            }
    ),
    @SqlResultSetMapping(name = "SegLecturasResults",
            entities = {
                @EntityResult(entityClass = InveLector.class)
                ,
                @EntityResult(entityClass = InveSeguimiento.class)
            },
            columns = {
                @ColumnResult(name = "ESTADO")
                ,@ColumnResult(name = "TEXTO_ESTADO")
            }
    )
//    ,@SqlResultSetMapping(name = "SegLecturasFinales",
//            entities = {
//                @EntityResult(entityClass = InveLector.class)
//                ,
//                @EntityResult(entityClass = InveSeguimiento.class)
//            }
//    )
}
)
public class InveLector implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEC_CODIGO")
    private Long lecCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LEC_NUMERO")
    private Short lecNumero;
    @Column(name = "LEC_FECHA_DESIGNACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lecFechaDesignacion;
    @Column(name = "LEC_FECHA_LIMITE_ACEP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lecFechaLimiteAcep;
    @Size(max = 1)
    @Column(name = "LEC_ACEPTACION")
    private String lecAceptacion;
    @Column(name = "LEC_FECHA_ACEPTACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lecFechaAceptacion;
    @Column(name = "LEC_FECHA_ENTREGA_VAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lecFechaEntregaVal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LEC_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lecFechaCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "LEC_USUARIO_CREA")
    private String lecUsuarioCrea;
    @Column(name = "LEC_FECHA_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lecFechaAct;
    @Size(max = 25)
    @Column(name = "LEC_USUARIO_ACT")
    private String lecUsuarioAct;
    @JoinColumn(name = "PRY_CODIGO", referencedColumnName = "PRY_CODIGO")
    @ManyToOne(optional = false)
    private InveProyecto inveProyecto;
    @JoinColumn(name = "ROL_CODIGO", referencedColumnName = "ROL_CODIGO")
    @ManyToOne
    private InveRol inveRol;
    @JoinColumn(name = "PER_CODIGO", referencedColumnName = "PER_CODIGO")
    @ManyToOne(optional = false)
    private PrinPersona prinPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveLector")
    private Collection<InveValoracion> inveValoracionCollection;
    @Transient
    private InveSeguimiento seguimiento;
    @Transient
    private String estadoVal;
    @Transient
    private String textoEstado;

    public InveLector() {
    }

    public InveLector(Long lecCodigo) {
        this.lecCodigo = lecCodigo;
    }

    public InveLector(Long lecCodigo, Short lecNumero, Date lecFechaDesignacion, Date lecFechaLimiteAcep, Date lecFechaEntregaVal, Date lecFechaCrea, String lecUsuarioCrea) {
        this.lecCodigo = lecCodigo;
        this.lecNumero = lecNumero;
        this.lecFechaDesignacion = lecFechaDesignacion;
        this.lecFechaLimiteAcep = lecFechaLimiteAcep;
        this.lecFechaEntregaVal = lecFechaEntregaVal;
        this.lecFechaCrea = lecFechaCrea;
        this.lecUsuarioCrea = lecUsuarioCrea;
    }

    public Long getLecCodigo() {
        return lecCodigo;
    }

    public void setLecCodigo(Long lecCodigo) {
        this.lecCodigo = lecCodigo;
    }

    public Short getLecNumero() {
        return lecNumero;
    }

    public void setLecNumero(Short lecNumero) {
        this.lecNumero = lecNumero;
    }

    public Date getLecFechaDesignacion() {
        return lecFechaDesignacion;
    }

    public void setLecFechaDesignacion(Date lecFechaDesignacion) {
        this.lecFechaDesignacion = lecFechaDesignacion;
    }

    public Date getLecFechaLimiteAcep() {
        return lecFechaLimiteAcep;
    }

    public void setLecFechaLimiteAcep(Date lecFechaLimiteAcep) {
        this.lecFechaLimiteAcep = lecFechaLimiteAcep;
    }

    public String getLecAceptacion() {
        return lecAceptacion;
    }

    public void setLecAceptacion(String lecAceptacion) {
        this.lecAceptacion = lecAceptacion;
    }

    public Date getLecFechaAceptacion() {
        return lecFechaAceptacion;
    }

    public void setLecFechaAceptacion(Date lecFechaAceptacion) {
        this.lecFechaAceptacion = lecFechaAceptacion;
    }

    public Date getLecFechaEntregaVal() {
        return lecFechaEntregaVal;
    }

    public void setLecFechaEntregaVal(Date lecFechaEntregaVal) {
        this.lecFechaEntregaVal = lecFechaEntregaVal;
    }

    public Date getLecFechaCrea() {
        return lecFechaCrea;
    }

    public void setLecFechaCrea(Date lecFechaCrea) {
        this.lecFechaCrea = lecFechaCrea;
    }

    public String getLecUsuarioCrea() {
        return lecUsuarioCrea;
    }

    public void setLecUsuarioCrea(String lecUsuarioCrea) {
        this.lecUsuarioCrea = lecUsuarioCrea;
    }

    public Date getLecFechaAct() {
        return lecFechaAct;
    }

    public void setLecFechaAct(Date lecFechaAct) {
        this.lecFechaAct = lecFechaAct;
    }

    public String getLecUsuarioAct() {
        return lecUsuarioAct;
    }

    public void setLecUsuarioAct(String lecUsuarioAct) {
        this.lecUsuarioAct = lecUsuarioAct;
    }

    public InveProyecto getInveProyecto() {
        return inveProyecto;
    }

    public void setInveProyecto(InveProyecto inveProyecto) {
        this.inveProyecto = inveProyecto;
    }

    public PrinPersona getPrinPersona() {
        return prinPersona;
    }

    public void setPrinPersona(PrinPersona prinPersona) {
        this.prinPersona = prinPersona;
    }

    public InveRol getInveRol() {
        return inveRol;
    }

    public void setInveRol(InveRol inveRol) {
        this.inveRol = inveRol;
    }

    @XmlTransient
    public Collection<InveValoracion> getInveValoracionCollection() {
        return inveValoracionCollection;
    }

    public void setInveValoracionCollection(Collection<InveValoracion> inveValoracionCollection) {
        this.inveValoracionCollection = inveValoracionCollection;
    }

    public InveSeguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(InveSeguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    public String getEstadoVal() {
        return estadoVal;
    }

    public void setEstadoVal(String estadoVal) {
        this.estadoVal = estadoVal;
    }

    public String getTextoEstado() {
        return textoEstado;
    }

    public void setTextoEstado(String textoEstado) {
        this.textoEstado = textoEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lecCodigo != null ? lecCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveLector)) {
            return false;
        }
        InveLector other = (InveLector) object;
        return !((this.lecCodigo == null && other.lecCodigo != null) || (this.lecCodigo != null && !this.lecCodigo.equals(other.lecCodigo)));
    }

    @Override
    public InveLector clone() throws CloneNotSupportedException {
        return (InveLector) super.clone();
    }

    @Override
    public String toString() {
        return "InveLector{" + "lecCodigo=" + lecCodigo
                + ", lecNumero=" + lecNumero
                + ", lecFechaDesignacion=" + lecFechaDesignacion
                + ", lecFechaLimiteAcep=" + lecFechaLimiteAcep
                + ", lecAceptacion=" + lecAceptacion
                + ", lecFechaAceptacion=" + lecFechaAceptacion
                + ", lecFechaEntregaVal=" + lecFechaEntregaVal
                + ", lecFechaCrea=" + lecFechaCrea
                + ", lecUsuarioCrea=" + lecUsuarioCrea
                + ", prinPersona=" + prinPersona
                + '}';
    }

}
