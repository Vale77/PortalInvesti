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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_MENSAJE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveMensaje.findAll", query = "SELECT i FROM InveMensaje i")
    , @NamedQuery(name = "InveMensaje.findByMenCodigo", query = "SELECT i FROM InveMensaje i WHERE i.menCodigo = :menCodigo")
    , @NamedQuery(name = "InveMensaje.findByMenAsunto", query = "SELECT i FROM InveMensaje i WHERE i.menAsunto = :menAsunto")
    , @NamedQuery(name = "InveMensaje.findByMenMensaje", query = "SELECT i FROM InveMensaje i WHERE i.menMensaje = :menMensaje")
    , @NamedQuery(name = "InveMensaje.findByMenFechaCrea", query = "SELECT i FROM InveMensaje i WHERE i.menFechaCrea = :menFechaCrea")
    , @NamedQuery(name = "InveMensaje.findByMenUsuarioCrea", query = "SELECT i FROM InveMensaje i WHERE i.menUsuarioCrea = :menUsuarioCrea")
    , @NamedQuery(name = "InveMensaje.findByMenFechaAct", query = "SELECT i FROM InveMensaje i WHERE i.menFechaAct = :menFechaAct")
    , @NamedQuery(name = "InveMensaje.findByMenUsuarioAct", query = "SELECT i FROM InveMensaje i WHERE i.menUsuarioAct = :menUsuarioAct")})
public class InveMensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEN_CODIGO")
    private Short menCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "MEN_ASUNTO")
    private String menAsunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8000)
    @Column(name = "MEN_MENSAJE")
    private String menMensaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEN_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date menFechaCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "MEN_USUARIO_CREA")
    private String menUsuarioCrea;
    @Column(name = "MEN_FECHA_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date menFechaAct;
    @Size(max = 25)
    @Column(name = "MEN_USUARIO_ACT")
    private String menUsuarioAct;
    @JoinColumn(name = "TCV_CODIGO", referencedColumnName = "TCV_CODIGO")
    @ManyToOne(optional = false)
    private InveTipoConvocatoria inveTipoConvocatoria;

    public InveMensaje() {
    }

    public InveMensaje(Short menCodigo) {
        this.menCodigo = menCodigo;
    }

    public InveMensaje(Short menCodigo, String menAsunto, String menMensaje, Date menFechaCrea, String menUsuarioCrea) {
        this.menCodigo = menCodigo;
        this.menAsunto = menAsunto;
        this.menMensaje = menMensaje;
        this.menFechaCrea = menFechaCrea;
        this.menUsuarioCrea = menUsuarioCrea;
    }

    public Short getMenCodigo() {
        return menCodigo;
    }

    public void setMenCodigo(Short menCodigo) {
        this.menCodigo = menCodigo;
    }

    public String getMenAsunto() {
        return menAsunto;
    }

    public void setMenAsunto(String menAsunto) {
        this.menAsunto = menAsunto;
    }

    public String getMenMensaje() {
        return menMensaje;
    }

    public void setMenMensaje(String menMensaje) {
        this.menMensaje = menMensaje;
    }

    public Date getMenFechaCrea() {
        return menFechaCrea;
    }

    public void setMenFechaCrea(Date menFechaCrea) {
        this.menFechaCrea = menFechaCrea;
    }

    public String getMenUsuarioCrea() {
        return menUsuarioCrea;
    }

    public void setMenUsuarioCrea(String menUsuarioCrea) {
        this.menUsuarioCrea = menUsuarioCrea;
    }

    public Date getMenFechaAct() {
        return menFechaAct;
    }

    public void setMenFechaAct(Date menFechaAct) {
        this.menFechaAct = menFechaAct;
    }

    public String getMenUsuarioAct() {
        return menUsuarioAct;
    }

    public void setMenUsuarioAct(String menUsuarioAct) {
        this.menUsuarioAct = menUsuarioAct;
    }

    public InveTipoConvocatoria getInveTipoConvocatoria() {
        return inveTipoConvocatoria;
    }

    public void setInveTipoConvocatoria(InveTipoConvocatoria inveTipoConvocatoria) {
        this.inveTipoConvocatoria = inveTipoConvocatoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menCodigo != null ? menCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveMensaje)) {
            return false;
        }
        InveMensaje other = (InveMensaje) object;
        if ((this.menCodigo == null && other.menCodigo != null) || (this.menCodigo != null && !this.menCodigo.equals(other.menCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveMensaje[ menCodigo=" + menCodigo + " ]";
    }
    
}
