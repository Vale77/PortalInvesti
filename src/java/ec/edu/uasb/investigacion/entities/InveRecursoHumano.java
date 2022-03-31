/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.entities.PrinTipoDedicacion;
import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_RECURSO_HUMANO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveRecursoHumano.findAll", query = "SELECT i FROM InveRecursoHumano i")
    , @NamedQuery(name = "InveRecursoHumano.findByPryCodigo", query = "SELECT i FROM InveRecursoHumano i WHERE i.inveRecursoHumanoPK.pryCodigo = :pryCodigo")
    , @NamedQuery(name = "InveRecursoHumano.findByPerCodigo", query = "SELECT i FROM InveRecursoHumano i WHERE i.inveRecursoHumanoPK.perCodigo = :perCodigo")
    , @NamedQuery(name = "InveRecursoHumano.findByRhuNumeroMeses", query = "SELECT i FROM InveRecursoHumano i WHERE i.rhuNumeroMeses = :rhuNumeroMeses")})
public class InveRecursoHumano implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InveRecursoHumanoPK inveRecursoHumanoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RHU_NUMERO_MESES")
    private Short rhuNumeroMeses;
    @JoinColumn(name = "PRY_CODIGO", referencedColumnName = "PRY_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private InveProyecto inveProyecto;
    @JoinColumn(name = "ROL_CODIGO", referencedColumnName = "ROL_CODIGO")
    @ManyToOne(optional = false)
    private InveRol inveRol;
    @JoinColumn(name = "PER_CODIGO", referencedColumnName = "PER_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PrinPersona prinPersona;
    @JoinColumn(name = "TDE_CODIGO", referencedColumnName = "TDE_CODIGO")
    @ManyToOne
    private PrinTipoDedicacion prinTipoDedicacion;
    
    public InveRecursoHumano() {
    }

    public InveRecursoHumano(InveRecursoHumanoPK inveRecursoHumanoPK) {
        this.inveRecursoHumanoPK = inveRecursoHumanoPK;
    }

    public InveRecursoHumano(InveRecursoHumanoPK inveRecursoHumanoPK, short rhuNumeroMeses) {
        this.inveRecursoHumanoPK = inveRecursoHumanoPK;
        this.rhuNumeroMeses = rhuNumeroMeses;
    }

    public InveRecursoHumano(long pryCodigo, long perCodigo) {
        this.inveRecursoHumanoPK = new InveRecursoHumanoPK(pryCodigo, perCodigo);
    }

    public InveRecursoHumanoPK getInveRecursoHumanoPK() {
        return inveRecursoHumanoPK;
    }

    public void setInveRecursoHumanoPK(InveRecursoHumanoPK inveRecursoHumanoPK) {
        this.inveRecursoHumanoPK = inveRecursoHumanoPK;
    }

    public Short getRhuNumeroMeses() {
        return rhuNumeroMeses;
    }

    public void setRhuNumeroMeses(Short rhuNumeroMeses) {
        this.rhuNumeroMeses = rhuNumeroMeses;
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

    public PrinPersona getPrinPersona() {
        return prinPersona;
    }

    public void setPrinPersona(PrinPersona prinPersona) {
        this.prinPersona = prinPersona;
    }

    public PrinTipoDedicacion getPrinTipoDedicacion() {
        return prinTipoDedicacion;
    }

    public void setPrinTipoDedicacion(PrinTipoDedicacion prinTipoDedicacion) {
        this.prinTipoDedicacion = prinTipoDedicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inveRecursoHumanoPK != null ? inveRecursoHumanoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveRecursoHumano)) {
            return false;
        }
        InveRecursoHumano other = (InveRecursoHumano) object;
        if ((this.inveRecursoHumanoPK == null && other.inveRecursoHumanoPK != null) || (this.inveRecursoHumanoPK != null && !this.inveRecursoHumanoPK.equals(other.inveRecursoHumanoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveRecursoHumano[ inveRecursoHumanoPK=" + inveRecursoHumanoPK + " ]";
    }
    
}
