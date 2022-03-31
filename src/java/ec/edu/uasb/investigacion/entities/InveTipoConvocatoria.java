/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_TIPO_CONVOCATORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveTipoConvocatoria.findAll", query = "SELECT i FROM InveTipoConvocatoria i")
    , @NamedQuery(name = "InveTipoConvocatoria.findByTcvCodigo", query = "SELECT i FROM InveTipoConvocatoria i WHERE i.tcvCodigo = :tcvCodigo")
    , @NamedQuery(name = "InveTipoConvocatoria.findByTcvNombre", query = "SELECT i FROM InveTipoConvocatoria i WHERE i.tcvNombre = :tcvNombre")})
public class InveTipoConvocatoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TCV_CODIGO")
    private Short tcvCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TCV_NOMBRE")
    private String tcvNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveTipoConvocatoria")
    private Collection<InveConvocatoria> inveConvocatoriaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inveTipoConvocatoria")
    private Collection<InveMensaje> inveMensajeCollection;

    public InveTipoConvocatoria() {
    }

    public InveTipoConvocatoria(Short tcvCodigo) {
        this.tcvCodigo = tcvCodigo;
    }

    public InveTipoConvocatoria(Short tcvCodigo, String tcvNombre) {
        this.tcvCodigo = tcvCodigo;
        this.tcvNombre = tcvNombre;
    }

    public Short getTcvCodigo() {
        return tcvCodigo;
    }

    public void setTcvCodigo(Short tcvCodigo) {
        this.tcvCodigo = tcvCodigo;
    }

    public String getTcvNombre() {
        return tcvNombre;
    }

    public void setTcvNombre(String tcvNombre) {
        this.tcvNombre = tcvNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tcvCodigo != null ? tcvCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveTipoConvocatoria)) {
            return false;
        }
        InveTipoConvocatoria other = (InveTipoConvocatoria) object;
        if ((this.tcvCodigo == null && other.tcvCodigo != null) || (this.tcvCodigo != null && !this.tcvCodigo.equals(other.tcvCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveTipoConvocatoria[ tcvCodigo=" + tcvCodigo + " ]";
    }

    @XmlTransient
    public Collection<InveConvocatoria> getInveConvocatoriaCollection() {
        return inveConvocatoriaCollection;
    }

    public void setInveConvocatoriaCollection(Collection<InveConvocatoria> inveConvocatoriaCollection) {
        this.inveConvocatoriaCollection = inveConvocatoriaCollection;
    }

    @XmlTransient
    public Collection<InveMensaje> getInveMensajeCollection() {
        return inveMensajeCollection;
    }

    public void setInveMensajeCollection(Collection<InveMensaje> inveMensajeCollection) {
        this.inveMensajeCollection = inveMensajeCollection;
    }

    public String getTcvNombre(String tcvNombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
