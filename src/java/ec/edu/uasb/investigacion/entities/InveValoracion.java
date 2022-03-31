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
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "INVE_VALORACION")
@XmlRootElement
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "ValoracionesResults",
            entities = {
                @EntityResult(entityClass = InveValoracion.class)
                ,
                @EntityResult(entityClass = InveLector.class)
                ,
                @EntityResult(entityClass = InveSeguimiento.class)
                ,
                @EntityResult(entityClass = InveInformeOper.class)
            },
            columns = {
                @ColumnResult(name = "DIAS")
                ,@ColumnResult(name = "PRO1")
                ,@ColumnResult(name = "PRO2")
                ,@ColumnResult(name = "PROEXT")
                ,@ColumnResult(name = "COMUNIC1")
                ,@ColumnResult(name = "COMUNIC1")
                ,@ColumnResult(name = "COMUNIC3")
            }
    )}
)
public class InveValoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VAL_CODIGO")
    private Long valCodigo;
    
    @Basic(optional = false)
    @Column(name = "VAL_IMPORTANCIA")
    private String valImportancia;
    @Basic(optional = false)
    @Column(name = "VAL_APORTE_RELEVANTE")
    private String valAporteRelevante;
    @Basic(optional = false)
    @Column(name = "VAL_COMENTARIO")
    private String valComentario;
    @Basic(optional = false)
    @Column(name = "VAL_RECOMENDACION")
    private String valRecomendacion;
    @Column(name = "VAL_CORRESPONDENCIA")
    private String valCorrespondencia;

    @Column(name = "VAL_RECOMIENDA_PUBLIC")
    private String valRecomendaPublic;    
    @Column(name = "VAL_USUARIO_RECEP")
    private String valUsuarioRecep;
    @Column(name = "VAL_FECHA_RECEP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valFechaRecep;
    @Basic(optional = false)
    @Column(name = "VAL_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valFechaCrea;
    @Basic(optional = false)
    @Column(name = "VAL_USUARIO_CREA")
    private String valUsuarioCrea;
    @Column(name = "VAL_FECHA_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valFechaAct;
    @Column(name = "VAL_USUARIO_ACT")
    private String valUsuarioAct;
    @JoinColumn(name = "ESV_CODIGO", referencedColumnName = "ESV_CODIGO")
    @ManyToOne
    private InveEstadoValoracion inveEstadoValoracion;
    @JoinColumn(name = "LEC_CODIGO", referencedColumnName = "LEC_CODIGO")
    @ManyToOne(optional = false)
    private InveLector inveLector;
    @Transient
    private InveSeguimiento inveSeguimiento;
    @Transient
    private InveInformeOper inveInformeOper;    
    @Transient
    private Integer dias;
    @Transient
    private Date pro1;
    @Transient
    private Date pro2;
    @Transient
    private Date proExt;
    @Transient
    private String comunic1;
    @Transient
    private String comunic2;
    @Transient
    private String comunic3;

    public InveValoracion() {
    }

    public InveValoracion(Long valCodigo) {
        this.valCodigo = valCodigo;
    }

    public InveValoracion(Long valCodigo, String valImportancia, String valAporteRelevante, String valComentario, String valRecomendacion, Date valFechaCrea, String valUsuarioCrea) {
        this.valCodigo = valCodigo;
        this.valImportancia = valImportancia;
        this.valAporteRelevante = valAporteRelevante;
        this.valComentario = valComentario;
        this.valRecomendacion = valRecomendacion;
        this.valFechaCrea = valFechaCrea;
        this.valUsuarioCrea = valUsuarioCrea;
    }

    public void setInveSeguimiento(InveSeguimiento inveSeguimiento) {
        this.inveSeguimiento = inveSeguimiento;
    }

    public InveSeguimiento getInveSeguimiento() {
        return inveSeguimiento;
    }

    public InveInformeOper getInveInformeOper() {
        return inveInformeOper;
    }

    public void setInveInformeOper(InveInformeOper inveInformeOper) {
        this.inveInformeOper = inveInformeOper;
    }

    public Long getValCodigo() {
        return valCodigo;
    }

    public void setValCodigo(Long valCodigo) {
        this.valCodigo = valCodigo;
    }

    public String getValImportancia() {
        return valImportancia;
    }

    public void setValImportancia(String valImportancia) {
        this.valImportancia = valImportancia;
    }

    public String getValAporteRelevante() {
        return valAporteRelevante;
    }

    public void setValAporteRelevante(String valAporteRelevante) {
        this.valAporteRelevante = valAporteRelevante;
    }

    public String getValComentario() {
        return valComentario;
    }

    public void setValComentario(String valComentario) {
        this.valComentario = valComentario;
    }

    public String getValRecomendacion() {
        return valRecomendacion;
    }

    public void setValRecomendacion(String valRecomendacion) {
        this.valRecomendacion = valRecomendacion;
    }

    public String getValRecomendaPublic() {
        return valRecomendaPublic;
    }

    public void setValRecomendaPublic(String valRecomendaPublic) {
        this.valRecomendaPublic = valRecomendaPublic;
    }

    public String getValCorrespondencia() {
        return valCorrespondencia;
    }

    public void setValCorrespondencia(String valCorrespondencia) {
        this.valCorrespondencia = valCorrespondencia;
    }

    public String getValUsuarioRecep() {
        return valUsuarioRecep;
    }

    public void setValUsuarioRecep(String valUsuarioRecep) {
        this.valUsuarioRecep = valUsuarioRecep;
    }

    public Date getValFechaRecep() {
        return valFechaRecep;
    }

    public void setValFechaRecep(Date valFechaRecep) {
        this.valFechaRecep = valFechaRecep;
    }

    public Date getValFechaCrea() {
        return valFechaCrea;
    }

    public void setValFechaCrea(Date valFechaCrea) {
        this.valFechaCrea = valFechaCrea;
    }

    public String getValUsuarioCrea() {
        return valUsuarioCrea;
    }

    public void setValUsuarioCrea(String valUsuarioCrea) {
        this.valUsuarioCrea = valUsuarioCrea;
    }

    public Date getValFechaAct() {
        return valFechaAct;
    }

    public void setValFechaAct(Date valFechaAct) {
        this.valFechaAct = valFechaAct;
    }

    public String getValUsuarioAct() {
        return valUsuarioAct;
    }

    public void setValUsuarioAct(String valUsuarioAct) {
        this.valUsuarioAct = valUsuarioAct;
    }

    public InveEstadoValoracion getInveEstadoValoracion() {
        return inveEstadoValoracion;
    }

    public void setInveEstadoValoracion(InveEstadoValoracion inveEstadoValoracion) {
        this.inveEstadoValoracion = inveEstadoValoracion;
    }

    public InveLector getInveLector() {
        return inveLector;
    }

    public void setInveLector(InveLector inveLector) {
        this.inveLector = inveLector;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Date getPro1() {
        return pro1;
    }

    public void setPro1(Date pro1) {
        this.pro1 = pro1;
    }

    public Date getPro2() {
        return pro2;
    }

    public void setPro2(Date pro2) {
        this.pro2 = pro2;
    }

    public Date getProExt() {
        return proExt;
    }

    public void setProExt(Date proExt) {
        this.proExt = proExt;
    }

    public String getComunic1() {
        return comunic1;
    }

    public void setComunic1(String comunic1) {
        this.comunic1 = comunic1;
    }

    public String getComunic2() {
        return comunic2;
    }

    public void setComunic2(String comunic2) {
        this.comunic2 = comunic2;
    }

    public String getComunic3() {
        return comunic3;
    }

    public void setComunic3(String comunic3) {
        this.comunic3 = comunic3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valCodigo != null ? valCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InveValoracion)) {
            return false;
        }
        InveValoracion other = (InveValoracion) object;
        return !((this.valCodigo == null && other.valCodigo != null) || (this.valCodigo != null && !this.valCodigo.equals(other.valCodigo)));
    }

    @Override
    public String toString() {
        return "InveValoracion{" + "valCodigo=" + valCodigo
                + ", valImportancia=" + valImportancia
                + ", valAporteRelevante=" + valAporteRelevante
                + ", valComentario=" + valComentario
                + ", valRecomendacion=" + valRecomendacion
                + ", valCorrespondencia=" + valCorrespondencia
                + ", valUsuarioRecep=" + valUsuarioRecep
                + ", valFechaRecep=" + valFechaRecep
                + '}';
    }

}
