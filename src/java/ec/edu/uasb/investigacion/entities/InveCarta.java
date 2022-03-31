/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.external.entities.AreaAcademica;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_CARTA")
@XmlRootElement
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "cartasResults",
            entities = {
                @EntityResult(entityClass = InveCarta.class)
                ,
                @EntityResult(entityClass = AreaAcademica.class)
                ,
                @EntityResult(entityClass = InveLector.class)
                ,
                @EntityResult(entityClass = InveSeguimiento.class)
            },
            columns = {
                @ColumnResult(name = "VALOR_LECTURA")
            }
    )}
)
public class InveCarta implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "PRY_CODIGO")
    private Long pryCodigo;
    @Basic(optional = false)
    @Column(name = "CAR_TIPO_CARTA")
    private String carTipoCarta;
    @Column(name = "CAR_CAPITULO")
    private Short carCapitulo;
    @Column(name = "CAR_TITULO_TESIS")
    private String carTituloTesis;
    @Column(name = "CAR_ES_PERTINENTE")
    private String carEsPertinente;
    @Column(name = "CAR_FECHA_PERTINENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date carFechaPertinencia;
    @Column(name = "CAR_RAZON_PERTINENCIA")
    private String carRazonPertinencia;
    @Column(name = "CAR_FECHA_RECEP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date carFechaRecep;
    @Column(name = "CAR_USUARIO_RECEP")
    private String carUsuarioRecep;
    @Basic(optional = false)
    @Column(name = "CAR_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date carFechaCrea;
    @Basic(optional = false)
    @Column(name = "CAR_USUARIO_CREA")
    private String carUsuarioCrea;
    @JoinColumn(name = "LIP_CODIGO", referencedColumnName = "LIP_CODIGO")
    @ManyToOne
    private InveLineaInvestProyec inveLineaInvestProyec;
    @JoinColumn(name = "PRY_CODIGO", referencedColumnName = "PRY_CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private InveProyecto inveProyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveCarta")
    private Collection<InveProgramaProyecto> inveProgramaProyectoCollection;
    @Transient
    private AreaAcademica area;
    @Transient
    private InveLector lector;
    @Transient
    private InveSeguimiento seguimiento;
    

    public InveCarta() {
    }

    public InveCarta(Long pryCodigo) {
        this.pryCodigo = pryCodigo;
    }

    public InveCarta(Long pryCodigo, Date carFechaCrea, String carUsuarioCrea) {
        this.pryCodigo = pryCodigo;
        this.carFechaCrea = carFechaCrea;
        this.carUsuarioCrea = carUsuarioCrea;
    }

    public Long getPryCodigo() {
        return pryCodigo;
    }

    public void setPryCodigo(Long pryCodigo) {
        this.pryCodigo = pryCodigo;
    }

    public String getCarTipoCarta() {
        return carTipoCarta;
    }

    public void setCarTipoCarta(String carTipoCarta) {
        this.carTipoCarta = carTipoCarta;
    }

    public Short getCarCapitulo() {
        return carCapitulo;
    }

    public void setCarCapitulo(Short carCapitulo) {
        this.carCapitulo = carCapitulo;
    }

    public String getCarTituloTesis() {
        return carTituloTesis;
    }

    public void setCarTituloTesis(String carTituloTesis) {
        this.carTituloTesis = carTituloTesis;
    }

    public String getCarEsPertinente() {
        return carEsPertinente;
    }

    public void setCarEsPertinente(String carEsPertinente) {
        this.carEsPertinente = carEsPertinente;
    }

    public Date getCarFechaPertinencia() {
        return carFechaPertinencia;
    }

    public void setCarFechaPertinencia(Date carFechaPertinencia) {
        this.carFechaPertinencia = carFechaPertinencia;
    }

    public String getCarRazonPertinencia() {
        return carRazonPertinencia;
    }

    public void setCarRazonPertinencia(String carRazonPertinencia) {
        this.carRazonPertinencia = carRazonPertinencia;
    }

    public Date getCarFechaRecep() {
        return carFechaRecep;
    }

    public void setCarFechaRecep(Date carFechaRecep) {
        this.carFechaRecep = carFechaRecep;
    }

    public String getCarUsuarioRecep() {
        return carUsuarioRecep;
    }

    public void setCarUsuarioRecep(String carUsuarioRecep) {
        this.carUsuarioRecep = carUsuarioRecep;
    }

    public Date getCarFechaCrea() {
        return carFechaCrea;
    }

    public void setCarFechaCrea(Date carFechaCrea) {
        this.carFechaCrea = carFechaCrea;
    }

    public String getCarUsuarioCrea() {
        return carUsuarioCrea;
    }

    public void setCarUsuarioCrea(String carUsuarioCrea) {
        this.carUsuarioCrea = carUsuarioCrea;
    }

    public InveLineaInvestProyec getInveLineaInvestProyec() {
        return inveLineaInvestProyec;
    }

    public void setInveLineaInvestProyec(InveLineaInvestProyec inveLineaInvestProyec) {
        this.inveLineaInvestProyec = inveLineaInvestProyec;
    }

    public InveProyecto getInveProyecto() {
        return inveProyecto;
    }

    public void setInveProyecto(InveProyecto inveProyecto) {
        this.inveProyecto = inveProyecto;
    }

    @XmlTransient
    public Collection<InveProgramaProyecto> getInveProgramaProyectoCollection() {
        return inveProgramaProyectoCollection;
    }

    public void setInveProgramaProyectoCollection(Collection<InveProgramaProyecto> inveProgramaProyectoCollection) {
        this.inveProgramaProyectoCollection = inveProgramaProyectoCollection;
    }

    public AreaAcademica getArea() {
        return area;
    }

    public void setArea(AreaAcademica area) {
        this.area = area;
    }

    public InveLector getLector() {
        return lector;
    }

    public void setLector(InveLector lector) {
        this.lector = lector;
    }

    public InveSeguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(InveSeguimiento seguimiento) {
        this.seguimiento = seguimiento;
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
        if (!(object instanceof InveCarta)) {
            return false;
        }
        InveCarta other = (InveCarta) object;
        return !((this.pryCodigo == null && other.pryCodigo != null) || (this.pryCodigo != null && !this.pryCodigo.equals(other.pryCodigo)));
    }

    @Override
    public InveCarta clone() throws CloneNotSupportedException {
        return (InveCarta) super.clone(); 
    }

    @Override
    public String toString() {
        return "InveCarta{" + "pryCodigo=" + pryCodigo
                + ", carEsPertinente=" + carEsPertinente
                + ", carFechaPertinencia=" + carFechaPertinencia
                + ", carRazonPertinencia=" + carRazonPertinencia
                + ", carFechaRecep=" + carFechaRecep
                + ", carUsuarioRecep=" + carUsuarioRecep
                + ", carFechaCrea=" + carFechaCrea
                + ", carUsuarioCrea=" + carUsuarioCrea
                + ", inveLineaInvestProyec=" + inveLineaInvestProyec
                //                + ", inveProyecto=" + inveProyecto 
                + '}';
    }

}
