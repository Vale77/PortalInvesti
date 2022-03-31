/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_EQUIPO_MULTI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveEquipoMulti.findAll", query = "SELECT i FROM InveEquipoMulti i")
    , @NamedQuery(name = "InveEquipoMulti.findByPryCodigo", query = "SELECT i FROM InveEquipoMulti i WHERE i.inveEquipoMultiPK.pryCodigo = :pryCodigo")
    , @NamedQuery(name = "InveEquipoMulti.findByRolCodigo", query = "SELECT i FROM InveEquipoMulti i WHERE i.inveEquipoMultiPK.rolCodigo = :rolCodigo")
    , @NamedQuery(name = "InveEquipoMulti.findByPryAreaAcad", query = "SELECT i FROM InveEquipoMulti i WHERE i.pryAreaAcad = :pryAreaAcad")})
public class InveEquipoMulti implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InveEquipoMultiPK inveEquipoMultiPK;
    @Basic(optional = false)
    @Column(name = "PRY_AREA_ACAD")
    private Short pryAreaAcad;
    @Basic(optional = false)
    @Column(name = "EQU_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date equFechaCrea;
    @Basic(optional = false)
    @Column(name = "EQU_USUARIO_CREA")
    private String equUsuarioCrea;
    @JoinColumn(name = "PRY_CODIGO", referencedColumnName = "PRY_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InveProyecto inveProyecto;
    @JoinColumn(name = "ROL_CODIGO", referencedColumnName = "ROL_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InveRol inveRol;

    public InveEquipoMulti() {
    }

    public InveEquipoMulti(InveEquipoMultiPK inveEquipoMultiPK) {
        this.inveEquipoMultiPK = inveEquipoMultiPK;
    }

    public InveEquipoMulti(InveEquipoMultiPK inveEquipoMultiPK, Short pryAreaAcad, Date equFechaCrea, String equUsuarioCrea) {
        this.inveEquipoMultiPK = inveEquipoMultiPK;
        this.pryAreaAcad = pryAreaAcad;
        this.equFechaCrea = equFechaCrea;
        this.equUsuarioCrea = equUsuarioCrea;
    }

    public InveEquipoMulti(Long pryCodigo, Short rolCodigo) {
        this.inveEquipoMultiPK = new InveEquipoMultiPK(pryCodigo, rolCodigo);
    }

    public InveEquipoMultiPK getInveEquipoMultiPK() {
        return inveEquipoMultiPK;
    }

    public void setInveEquipoMultiPK(InveEquipoMultiPK inveEquipoMultiPK) {
        this.inveEquipoMultiPK = inveEquipoMultiPK;
    }

    public Short getPryAreaAcad() {
        return pryAreaAcad;
    }

    public void setPryAreaAcad(Short pryAreaAcad) {
        this.pryAreaAcad = pryAreaAcad;
    }

    public Date getEquFechaCrea() {
        return equFechaCrea;
    }

    public void setEquFechaCrea(Date equFechaCrea) {
        this.equFechaCrea = equFechaCrea;
    }

    public String getEquUsuarioCrea() {
        return equUsuarioCrea;
    }

    public void setEquUsuarioCrea(String equUsuarioCrea) {
        this.equUsuarioCrea = equUsuarioCrea;
    }

    public InveProyecto getInveProyecto() {
        return inveProyecto;
    }

    public void setInveProyecto(InveProyecto inveProyecto) {
        this.inveProyecto = inveProyecto;
    }

    public InveRol getInveRol() {
        return inveRol;
    }

    public void setInveRol(InveRol inveRol) {
        this.inveRol = inveRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inveEquipoMultiPK != null ? inveEquipoMultiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveEquipoMulti)) {
            return false;
        }
        InveEquipoMulti other = (InveEquipoMulti) object;
        if ((this.inveEquipoMultiPK == null && other.inveEquipoMultiPK != null) || (this.inveEquipoMultiPK != null && !this.inveEquipoMultiPK.equals(other.inveEquipoMultiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication10.InveEquipoMulti[ inveEquipoMultiPK=" + inveEquipoMultiPK + " ]";
    }
    
}
