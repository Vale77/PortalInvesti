/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalgesdocente.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "CONTRATO_DOCENTE", schema = "GESTIONDOCENTE")
@XmlRootElement

public class ContratoDocente implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CDO_CODIGO")
    private Long cdoCodigo;
    @Basic(optional = false)
    @Column(name = "CDO_ANIO_ACAD")
    private Long cdoAnioAcad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRF_CODIGO")
    private Long prfCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRG_CODIGO")
    private Long prgCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARE_CODIGO")
    private Long areCodigo;
    @Column(name = "ASG_CODIGO")
    private Long asgCodigo;
    @Column(name = "PRL_CODIGO")
    private Long prlCodigo;
    @Column(name = "ASP_CODIGO")
    private Long aspCodigo;
    @Column(name = "STA_CODIGO")
    private String staCodigo;
    @Size(max = 20)
    @Column(name = "CDO_NUMERO")
    private String cdoNumero;
    @Column(name = "CDO_FECHA_GEN_CONTR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdoFechaGenContr;
    @Column(name = "CDO_FECINI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdoFecini;
    @Column(name = "CDO_FECFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdoFecfin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CDO_MONTO")
    private BigDecimal cdoMonto;
    @Column(name = "CDO_CANT_UNIDAD")
    private Short cdoCantUnidad;
    @Size(max = 1)
    @Column(name = "CDO_DSCTO_USO_RESID")
    private String cdoDsctoUsoResid;
    @Size(max = 1024)
    @Column(name = "CDO_OBSERVACIONES")
    private String cdoObservaciones;
    @Size(max = 50)
    @Column(name = "CDO_REVISADO_POR")
    private String cdoRevisadoPor;
    @Lob
    @Column(name = "CDO_TEXTO")
    private String cdoTexto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CDO_USU_CREA")
    private Long cdoUsuCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CDO_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdoFechaCrea;
    @Column(name = "CDO_USU_MODIF")
    private Long cdoUsuModif;
    @Column(name = "CDO_FECHA_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdoFechaModif;
    @JoinColumn(name = "RDO_CODIGO", referencedColumnName = "RDO_CODIGO")
    @ManyToOne(optional = false)
    private RolDocente rolDocente;
    @JoinColumn(name = "TFP_CODIGO", referencedColumnName = "TFP_CODIGO")
    @ManyToOne
    private TiposFormaPago tiposFormaPago;
    
    public ContratoDocente() {
    }

    public ContratoDocente(Long cdoCodigo) {
        this.cdoCodigo = cdoCodigo;
    }

    public ContratoDocente(Long cdoCodigo, Long cdoAnioAcad, Long prfCodigo, Long prgCodigo, Long areCodigo, String staCodigo,
            Date cdoFecini, BigDecimal cdoMonto, Long cdoUsuCrea, Date cdoFechaCrea) {
        this.cdoCodigo = cdoCodigo;
        this.cdoAnioAcad = cdoAnioAcad;
        this.prfCodigo = prfCodigo;
        this.prgCodigo = prgCodigo;
        this.areCodigo = areCodigo;
        this.staCodigo = staCodigo;
        this.cdoFecini = cdoFecini;
        this.cdoMonto = cdoMonto;
        this.cdoUsuCrea = cdoUsuCrea;
        this.cdoFechaCrea = cdoFechaCrea;
    }

    public Long getCdoCodigo() {
        return cdoCodigo;
    }

    public void setCdoCodigo(Long cdoCodigo) {
        this.cdoCodigo = cdoCodigo;
    }

    public Long getCdoAnioAcad() {
        return cdoAnioAcad;
    }

    public void setCdoAnioAcad(Long cdoAnioAcad) {
        this.cdoAnioAcad = cdoAnioAcad;
    }

    public Long getPrfCodigo() {
        return prfCodigo;
    }

    public void setPrfCodigo(Long prfCodigo) {
        this.prfCodigo = prfCodigo;
    }

    public Long getPrgCodigo() {
        return prgCodigo;
    }

    public void setPrgCodigo(Long prgCodigo) {
        this.prgCodigo = prgCodigo;
    }

    public Long getAreCodigo() {
        return areCodigo;
    }

    public void setAreCodigo(Long areCodigo) {
        this.areCodigo = areCodigo;
    }

    public String getStaCodigo() {
        return staCodigo;
    }

    public void setStaCodigo(String staCodigo) {
        this.staCodigo = staCodigo;
    }

    public Long getAsgCodigo() {
        return asgCodigo;
    }

    public void setAsgCodigo(Long asgCodigo) {
        this.asgCodigo = asgCodigo;
    }

    public Long getPrlCodigo() {
        return prlCodigo;
    }

    public void setPrlCodigo(Long prlCodigo) {
        this.prlCodigo = prlCodigo;
    }

    public Long getAspCodigo() {
        return aspCodigo;
    }

    public void setAspCodigo(Long aspCodigo) {
        this.aspCodigo = aspCodigo;
    }

    public String getCdoNumero() {
        return cdoNumero;
    }

    public void setCdoNumero(String cdoNumero) {
        this.cdoNumero = cdoNumero;
    }

    public Date getCdoFecini() {
        return cdoFecini;
    }

    public void setCdoFecini(Date cdoFecini) {
        this.cdoFecini = cdoFecini;
    }

    public Date getCdoFecfin() {
        return cdoFecfin;
    }

    public void setCdoFecfin(Date cdoFecfin) {
        this.cdoFecfin = cdoFecfin;
    }

    public BigDecimal getCdoMonto() {
        return cdoMonto;
    }

    public void setCdoMonto(BigDecimal cdoMonto) {
        this.cdoMonto = cdoMonto;
    }

    public Short getCdoCantUnidad() {
        return cdoCantUnidad;
    }

    public void setCdoCantUnidad(Short cdoCantUnidad) {
        this.cdoCantUnidad = cdoCantUnidad;
    }

    public String getCdoDsctoUsoResid() {
        return cdoDsctoUsoResid;
    }

    public void setCdoDsctoUsoResid(String cdoDsctoUsoResid) {
        this.cdoDsctoUsoResid = cdoDsctoUsoResid;
    }

    public String getCdoObservaciones() {
        return cdoObservaciones;
    }

    public void setCdoObservaciones(String cdoObservaciones) {
        this.cdoObservaciones = cdoObservaciones;
    }

    public String getCdoTexto() {
        return cdoTexto;
    }

    public void setCdoTexto(String cdoTexto) {
        this.cdoTexto = cdoTexto;
    }

    public Long getCdoUsuCrea() {
        return cdoUsuCrea;
    }

    public void setCdoUsuCrea(Long cdoUsuCrea) {
        this.cdoUsuCrea = cdoUsuCrea;
    }

    public Date getCdoFechaCrea() {
        return cdoFechaCrea;
    }

    public void setCdoFechaCrea(Date cdoFechaCrea) {
        this.cdoFechaCrea = cdoFechaCrea;
    }

    public Long getCdoUsuModif() {
        return cdoUsuModif;
    }

    public void setCdoUsuModif(Long cdoUsuModif) {
        this.cdoUsuModif = cdoUsuModif;
    }

    public Date getCdoFechaModif() {
        return cdoFechaModif;
    }

    public void setCdoFechaModif(Date cdoFechaModif) {
        this.cdoFechaModif = cdoFechaModif;
    }

    public Date getCdoFechaGenContr() {
        return cdoFechaGenContr;
    }

    public void setCdoFechaGenContr(Date cdoFechaGenContr) {
        this.cdoFechaGenContr = cdoFechaGenContr;
    }

    public String getCdoRevisadoPor() {
        return cdoRevisadoPor;
    }

    public void setCdoRevisadoPor(String cdoRevisadoPor) {
        this.cdoRevisadoPor = cdoRevisadoPor;
    }

    public TiposFormaPago getTiposFormaPago() {
        return tiposFormaPago;
    }

    public void setTiposFormaPago(TiposFormaPago tiposFormaPago) {
        this.tiposFormaPago = tiposFormaPago;
    }

    public RolDocente getRolDocente() {
        return rolDocente;
    }

    public void setRolDocente(RolDocente rolDocente) {
        this.rolDocente = rolDocente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.cdoCodigo != null ? this.cdoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoDocente)) {
            return false;
        }
        ContratoDocente other = (ContratoDocente) object;
        if ((this.cdoCodigo == null && other.cdoCodigo != null) || (this.cdoCodigo != null && !this.cdoCodigo.equals(other.cdoCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public ContratoDocente clone() throws CloneNotSupportedException {
        return (ContratoDocente) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ContratoDocente{" + "cdoCodigo=" + cdoCodigo
                + ", cdoAnioAcad=" + cdoAnioAcad
                + ", prfCodigo=" + prfCodigo
                + ", prgCodigo=" + prgCodigo
                + ", areCodigo=" + areCodigo
                + ", asgCodigo=" + asgCodigo
                + ", prlCodigo=" + prlCodigo
                + ", aspCodigo=" + aspCodigo
                + ", staCodigo=" + staCodigo
                + ", cdoNumero=" + cdoNumero
                + ", cdoFechaGenContr=" + cdoFechaGenContr
                + ", cdoFecini=" + cdoFecini
                + ", cdoFecfin=" + cdoFecfin
                + ", cdoMonto=" + cdoMonto
                + ", cdoCantUnidad=" + cdoCantUnidad
                + ", cdoDsctoUsoResid=" + cdoDsctoUsoResid
                + ", cdoObservaciones=" + cdoObservaciones
                + ", cdoRevisadoPor=" + cdoRevisadoPor
                + ", cdoUsuCrea=" + cdoUsuCrea
                + ", cdoFechaCrea=" + cdoFechaCrea
                + '}';
    }

}
