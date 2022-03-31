/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_SEGUIMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InveSeguimiento.findAll", query = "SELECT i FROM InveSeguimiento i")
    , @NamedQuery(name = "InveSeguimiento.findBySegCodigo", query = "SELECT i FROM InveSeguimiento i WHERE i.segCodigo = :segCodigo")
    , @NamedQuery(name = "InveSeguimiento.findBySegTipoComunic", query = "SELECT i FROM InveSeguimiento i WHERE i.segTipoComunic = :segTipoComunic")
    , @NamedQuery(name = "InveSeguimiento.findBySegNroComunic", query = "SELECT i FROM InveSeguimiento i WHERE i.segNroComunic = :segNroComunic")
    , @NamedQuery(name = "InveSeguimiento.findBySegFechaComunic", query = "SELECT i FROM InveSeguimiento i WHERE i.segFechaComunic = :segFechaComunic")
    , @NamedQuery(name = "InveSeguimiento.findBySegFechaInicial", query = "SELECT i FROM InveSeguimiento i WHERE i.segFechaInicial = :segFechaInicial")
    , @NamedQuery(name = "InveSeguimiento.findBySegFechaLimite", query = "SELECT i FROM InveSeguimiento i WHERE i.segFechaLimite = :segFechaLimite")
    , @NamedQuery(name = "InveSeguimiento.findBySegMonto", query = "SELECT i FROM InveSeguimiento i WHERE i.segMonto = :segMonto")
    , @NamedQuery(name = "InveSeguimiento.findBySegUsuarioCrea", query = "SELECT i FROM InveSeguimiento i WHERE i.segUsuarioCrea = :segUsuarioCrea")
    , @NamedQuery(name = "InveSeguimiento.findBySegFechaCrea", query = "SELECT i FROM InveSeguimiento i WHERE i.segFechaCrea = :segFechaCrea")})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "fechasContrato",
            columns = {
                @ColumnResult(name = "SEG_FECHA_INICIAL")
                ,@ColumnResult(name = "SEG_FECHA_LIMITE")
            }
    )
})
public class InveSeguimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEG_CODIGO")
    private Long segCodigo;
    @Basic(optional = false)
    @Column(name = "SEG_TIPO_COMUNIC")
    private String segTipoComunic;
    @Basic(optional = false)
    @Column(name = "SEG_NRO_COMUNIC")
    private String segNroComunic;
    @Basic(optional = false)
    @Column(name = "SEG_ESTADO_REG")
    private String segEstadoReg;
    @Basic(optional = false)
    @Column(name = "SEG_FECHA_COMUNIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date segFechaComunic;
    @Column(name = "SEG_FECHA_INICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date segFechaInicial;
    @Column(name = "SEG_FECHA_LIMITE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date segFechaLimite;
    @Column(name = "LEC_CODIGO")
    private Long lecCodigo;
    @Column(name = "SEG_NOTIF_APROBADO")
    private String segNotifAprobado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SEG_MONTO")
    private BigDecimal segMonto;
    @Basic(optional = false)
    @Column(name = "SEG_USUARIO_CREA")
    private String segUsuarioCrea;
    @Basic(optional = false)
    @Column(name = "SEG_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date segFechaCrea;
    @JoinColumn(name = "PRY_CODIGO", referencedColumnName = "PRY_CODIGO")
    @ManyToOne(optional = false)
    private InveProyecto inveProyecto;
    @JoinColumn(name = "TSE_CODIGO", referencedColumnName = "TSE_CODIGO")
    @ManyToOne(optional = false)
    private InveTipoSeguimiento inveTipoSeguimiento;

    public InveSeguimiento() {
    }

    public InveSeguimiento(Long segCodigo) {
        this.segCodigo = segCodigo;
    }

    public InveSeguimiento(Long segCodigo, String segTipoComunic, String segNroComunic, String segEstadoReg, Date segFechaComunic, String segUsuarioCrea, Date segFechaCrea) {
        this.segCodigo = segCodigo;
        this.segTipoComunic = segTipoComunic;
        this.segNroComunic = segNroComunic;
        this.segEstadoReg = segEstadoReg;
        this.segFechaComunic = segFechaComunic;
        this.segUsuarioCrea = segUsuarioCrea;
        this.segFechaCrea = segFechaCrea;
    }

    public Long getSegCodigo() {
        return segCodigo;
    }

    public void setSegCodigo(Long segCodigo) {
        this.segCodigo = segCodigo;
    }

    public String getSegTipoComunic() {
        return segTipoComunic;
    }

    public void setSegTipoComunic(String segTipoComunic) {
        this.segTipoComunic = segTipoComunic;
    }

    public String getSegNroComunic() {
        return segNroComunic;
    }

    public void setSegNroComunic(String segNroComunic) {
        this.segNroComunic = segNroComunic;
    }

    public String getSegEstadoReg() {
        return segEstadoReg;
    }

    public void setSegEstadoReg(String segEstadoReg) {
        this.segEstadoReg = segEstadoReg;
    }

    public Date getSegFechaComunic() {
        return segFechaComunic;
    }

    public void setSegFechaComunic(Date segFechaComunic) {
        this.segFechaComunic = segFechaComunic;
    }

    public Date getSegFechaInicial() {
        return segFechaInicial;
    }

    public void setSegFechaInicial(Date segFechaInicial) {
        this.segFechaInicial = segFechaInicial;
    }

    public Date getSegFechaLimite() {
        return segFechaLimite;
    }

    public void setSegFechaLimite(Date segFechaLimite) {
        this.segFechaLimite = segFechaLimite;
    }

    public Long getLecCodigo() {
        return lecCodigo;
    }

    public void setLecCodigo(Long lecCodigo) {
        this.lecCodigo = lecCodigo;
    }

    public String getSegNotifAprobado() {
        return segNotifAprobado;
    }

    public void setSegNotifAprobado(String segNotifAprobado) {
        this.segNotifAprobado = segNotifAprobado;
    }

    public BigDecimal getSegMonto() {
        return segMonto;
    }

    public void setSegMonto(BigDecimal segMonto) {
        this.segMonto = segMonto;
    }

    public String getSegUsuarioCrea() {
        return segUsuarioCrea;
    }

    public void setSegUsuarioCrea(String segUsuarioCrea) {
        this.segUsuarioCrea = segUsuarioCrea;
    }

    public Date getSegFechaCrea() {
        return segFechaCrea;
    }

    public void setSegFechaCrea(Date segFechaCrea) {
        this.segFechaCrea = segFechaCrea;
    }

    public InveProyecto getInveProyecto() {
        return inveProyecto;
    }

    public void setInveProyecto(InveProyecto inveProyecto) {
        this.inveProyecto = inveProyecto;
    }

    public InveTipoSeguimiento getInveTipoSeguimiento() {
        return inveTipoSeguimiento;
    }

    public void setInveTipoSeguimiento(InveTipoSeguimiento inveTipoSeguimiento) {
        this.inveTipoSeguimiento = inveTipoSeguimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (segCodigo != null ? segCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveSeguimiento)) {
            return false;
        }
        InveSeguimiento other = (InveSeguimiento) object;
        if ((this.segCodigo == null && other.segCodigo != null) || (this.segCodigo != null && !this.segCodigo.equals(other.segCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InveSeguimiento{" + "segCodigo=" + segCodigo
                + ", segTipoComunic=" + segTipoComunic
                + ", segNroComunic=" + segNroComunic
                + ", segFechaComunic=" + segFechaComunic
                + ", segFechaInicial=" + segFechaInicial
                + ", segFechaLimite=" + segFechaLimite
                + ", segNotifAprobado=" + segNotifAprobado
                + ", segMonto=" + segMonto
                + ", inveProyecto=" + inveProyecto.getPryCodigo()
                + '}';
    }

}
