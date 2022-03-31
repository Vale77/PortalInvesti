/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_VERSION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveVersion.findAll", query = "SELECT i FROM InveVersion i")
    , @NamedQuery(name = "InveVersion.findByVerCodigo", query = "SELECT i FROM InveVersion i WHERE i.verCodigo = :verCodigo")
    , @NamedQuery(name = "InveVersion.findByVerRecepProyecto", query = "SELECT i FROM InveVersion i WHERE i.verRecepProyecto = :verRecepProyecto")
    , @NamedQuery(name = "InveVersion.findByVerObservacionesRecep", query = "SELECT i FROM InveVersion i WHERE i.verObservacionesRecep = :verObservacionesRecep")
    , @NamedQuery(name = "InveVersion.findByVerNroOficioMaxEntrega", query = "SELECT i FROM InveVersion i WHERE i.verNroOficioMaxEntrega = :verNroOficioMaxEntrega")
    , @NamedQuery(name = "InveVersion.findByVerFechaMaxEntrega", query = "SELECT i FROM InveVersion i WHERE i.verFechaMaxEntrega = :verFechaMaxEntrega")
    , @NamedQuery(name = "InveVersion.findByVerFechaRecep", query = "SELECT i FROM InveVersion i WHERE i.verFechaRecep = :verFechaRecep")
    , @NamedQuery(name = "InveVersion.findByVerUsuarioRecep", query = "SELECT i FROM InveVersion i WHERE i.verUsuarioRecep = :verUsuarioRecep")})
public class InveVersion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VER_CODIGO")
    private Integer verCodigo;
    @Size(max = 1)
    @Column(name = "VER_RECEP_PROYECTO")
    private String verRecepProyecto;
    @Size(max = 8000)
    @Column(name = "VER_OBSERVACIONES_RECEP")
    private String verObservacionesRecep;
    @Size(max = 15)
    @Column(name = "VER_NRO_OFICIO_MAX_ENTREGA")
    private String verNroOficioMaxEntrega;
    @Size(max = 10)
    @Column(name = "VER_FECHA_MAX_ENTREGA")
    private String verFechaMaxEntrega;
    @Size(max = 10)
    @Column(name = "VER_FECHA_RECEP")
    private String verFechaRecep;
    @Size(max = 25)
    @Column(name = "VER_USUARIO_RECEP")
    private String verUsuarioRecep;
    @JoinColumn(name = "PRY_CODIGO", referencedColumnName = "PRY_CODIGO")
    @ManyToOne
    private InveProyecto inveProyecto;

    public InveVersion() {
    }

    public InveVersion(Integer verCodigo) {
        this.verCodigo = verCodigo;
    }

    public Integer getVerCodigo() {
        return verCodigo;
    }

    public void setVerCodigo(Integer verCodigo) {
        this.verCodigo = verCodigo;
    }

    public String getVerRecepProyecto() {
        return verRecepProyecto;
    }

    public void setVerRecepProyecto(String verRecepProyecto) {
        this.verRecepProyecto = verRecepProyecto;
    }

    public String getVerObservacionesRecep() {
        return verObservacionesRecep;
    }

    public void setVerObservacionesRecep(String verObservacionesRecep) {
        this.verObservacionesRecep = verObservacionesRecep;
    }

    public String getVerNroOficioMaxEntrega() {
        return verNroOficioMaxEntrega;
    }

    public void setVerNroOficioMaxEntrega(String verNroOficioMaxEntrega) {
        this.verNroOficioMaxEntrega = verNroOficioMaxEntrega;
    }

    public String getVerFechaMaxEntrega() {
        return verFechaMaxEntrega;
    }

    public void setVerFechaMaxEntrega(String verFechaMaxEntrega) {
        this.verFechaMaxEntrega = verFechaMaxEntrega;
    }

    public String getVerFechaRecep() {
        return verFechaRecep;
    }

    public void setVerFechaRecep(String verFechaRecep) {
        this.verFechaRecep = verFechaRecep;
    }

    public String getVerUsuarioRecep() {
        return verUsuarioRecep;
    }

    public void setVerUsuarioRecep(String verUsuarioRecep) {
        this.verUsuarioRecep = verUsuarioRecep;
    }

    public InveProyecto getInveProyecto() {
        return inveProyecto;
    }

    public void setInveProyecto(InveProyecto inveProyecto) {
        this.inveProyecto = inveProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (verCodigo != null ? verCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveVersion)) {
            return false;
        }
        InveVersion other = (InveVersion) object;
        if ((this.verCodigo == null && other.verCodigo != null) || (this.verCodigo != null && !this.verCodigo.equals(other.verCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.investigacion.entities.InveVersion[ verCodigo=" + verCodigo + " ]";
    }
    
}
