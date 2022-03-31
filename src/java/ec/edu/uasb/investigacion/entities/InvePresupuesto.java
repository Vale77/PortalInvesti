/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_PRESUPUESTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvePresupuesto.findAll", query = "SELECT i FROM InvePresupuesto i")
    , @NamedQuery(name = "InvePresupuesto.findByPryCodigo", query = "SELECT i FROM InvePresupuesto i WHERE i.invePresupuestoPK.pryCodigo = :pryCodigo")
    , @NamedQuery(name = "InvePresupuesto.findByRubCodigo", query = "SELECT i FROM InvePresupuesto i WHERE i.invePresupuestoPK.rubCodigo = :rubCodigo")
    , @NamedQuery(name = "InvePresupuesto.findByPreDescripcion", query = "SELECT i FROM InvePresupuesto i WHERE i.preDescripcion = :preDescripcion")
    , @NamedQuery(name = "InvePresupuesto.findByPreMontoAprob", query = "SELECT i FROM InvePresupuesto i WHERE i.preMontoAprob = :preMontoAprob")
    , @NamedQuery(name = "InvePresupuesto.findByPreMontoGastado", query = "SELECT i FROM InvePresupuesto i WHERE i.preMontoGastado = :preMontoGastado")})
public class InvePresupuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InvePresupuestoPK invePresupuestoPK;
    @Column(name = "PRE_DESCRIPCION")
    private String preDescripcion;
    @Column(name = "PRE_MONTO_APROB")
    private BigDecimal preMontoAprob;
    @Column(name = "PRE_MONTO_GASTADO")
    private BigDecimal preMontoGastado;
    @JoinColumn(name = "PRY_CODIGO", referencedColumnName = "PRY_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InveProyecto inveProyecto;
    @JoinColumn(name = "RUB_CODIGO", referencedColumnName = "RUB_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InveRubro inveRubro;

    public InvePresupuesto() {
    }

    public InvePresupuesto(InvePresupuestoPK invePresupuestoPK) {
        this.invePresupuestoPK = invePresupuestoPK;
    }

    public InvePresupuesto(InvePresupuestoPK invePresupuestoPK, String preDescripcion, BigDecimal preMontoAprob) {
        this.invePresupuestoPK = invePresupuestoPK;
        this.preDescripcion = preDescripcion;
        this.preMontoAprob = preMontoAprob;
    }

    public InvePresupuesto(long pryCodigo, short rubCodigo) {
        this.invePresupuestoPK = new InvePresupuestoPK(pryCodigo, rubCodigo);
    }

    public InvePresupuestoPK getInvePresupuestoPK() {
        return invePresupuestoPK;
    }

    public void setInvePresupuestoPK(InvePresupuestoPK invePresupuestoPK) {
        this.invePresupuestoPK = invePresupuestoPK;
    }

    public String getPreDescripcion() {
        return preDescripcion;
    }

    public void setPreDescripcion(String preDescripcion) {
        this.preDescripcion = preDescripcion;
    }

    public BigDecimal getPreMontoAprob() {
        return preMontoAprob;
    }

    public void setPreMontoAprob(BigDecimal preMontoAprob) {
        this.preMontoAprob = preMontoAprob;
    }

    public BigDecimal getPreMontoGastado() {
        return preMontoGastado;
    }

    public void setPreMontoGastado(BigDecimal preMontoGastado) {
        this.preMontoGastado = preMontoGastado;
    }

    public InveProyecto getInveProyecto() {
        return inveProyecto;
    }

    public void setInveProyecto(InveProyecto inveProyecto) {
        this.inveProyecto = inveProyecto;
    }

    public InveRubro getInveRubro() {
        return inveRubro;
    }

    public void setInveRubro(InveRubro inveRubro) {
        this.inveRubro = inveRubro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invePresupuestoPK != null ? invePresupuestoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvePresupuesto)) {
            return false;
        }
        InvePresupuesto other = (InvePresupuesto) object;
        if ((this.invePresupuestoPK == null && other.invePresupuestoPK != null) || (this.invePresupuestoPK != null && !this.invePresupuestoPK.equals(other.invePresupuestoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InvePresupuesto{" + "invePresupuestoPK=" + invePresupuestoPK + ", preDescripcion=" + preDescripcion + ", preMontoAprob=" + preMontoAprob + ", preMontoGastado=" + preMontoGastado + ", inveProyecto=" + inveProyecto + ", inveRubro=" + inveRubro + '}';
    }


    
}
