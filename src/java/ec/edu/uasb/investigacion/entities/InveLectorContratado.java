/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.principal.entities.PrinPersona;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_LECTOR_CONTRATADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveLectorContratado.findAll", query = "SELECT i FROM InveLectorContratado i")
    , @NamedQuery(name = "InveLectorContratado.findByPerCodigo", query = "SELECT i FROM InveLectorContratado i WHERE i.perCodigo = :perCodigo")})
public class InveLectorContratado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PER_CODIGO")
    private Long perCodigo;
    @Basic(optional = false)
    @Column(name = "LCO_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lcoFechaCrea;
    @Basic(optional = false)
    @Column(name = "LCO_USUARIO_CREA")
    private String lcoUsuarioCrea;
    @JoinColumn(name = "PER_CODIGO", referencedColumnName = "PER_CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PrinPersona prinPersona;

    public InveLectorContratado() {
    }

    public InveLectorContratado(Long perCodigo) {
        this.perCodigo = perCodigo;
    }

    public InveLectorContratado(Long perCodigo, Date lcoFechaCrea, String lcoUsuarioCrea) {
        this.perCodigo = perCodigo;
        this.lcoFechaCrea = lcoFechaCrea;
        this.lcoUsuarioCrea = lcoUsuarioCrea;
    }

    public Long getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Long perCodigo) {
        this.perCodigo = perCodigo;
    }

    public Date getLcoFechaCrea() {
        return lcoFechaCrea;
    }

    public void setLcoFechaCrea(Date lcoFechaCrea) {
        this.lcoFechaCrea = lcoFechaCrea;
    }

    public String getLcoUsuarioCrea() {
        return lcoUsuarioCrea;
    }

    public void setLcoUsuarioCrea(String lcoUsuarioCrea) {
        this.lcoUsuarioCrea = lcoUsuarioCrea;
    }

    public PrinPersona getPrinPersona() {
        return prinPersona;
    }

    public void setPrinPersona(PrinPersona prinPersona) {
        this.prinPersona = prinPersona;
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
        if (!(object instanceof InveLectorContratado)) {
            return false;
        }
        InveLectorContratado other = (InveLectorContratado) object;
        return !((this.perCodigo == null && other.perCodigo != null) || (this.perCodigo != null && !this.perCodigo.equals(other.perCodigo)));
    }

    @Override
    public String toString() {
        return "javaapplication8.InveLectorContratado[ perCodigo=" + perCodigo + " ]";
    }
    
}
