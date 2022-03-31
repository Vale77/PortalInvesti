/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.entities;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "PRIN_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrinDocumento.findAll", query = "SELECT p FROM PrinDocumento p")
    , @NamedQuery(name = "PrinDocumento.findByDocModOrigen", query = "SELECT p FROM PrinDocumento p WHERE p.docModOrigen = :docModOrigen")
    , @NamedQuery(name = "PrinDocumento.findByDocEntidad", query = "SELECT p FROM PrinDocumento p WHERE p.docEntidad = :docEntidad")
    , @NamedQuery(name = "PrinDocumento.findByDocEntidadCodigo", query = "SELECT p FROM PrinDocumento p WHERE p.docEntidadCodigo = :docEntidadCodigo")
    , @NamedQuery(name = "PrinDocumento.findByDocExtension", query = "SELECT p FROM PrinDocumento p WHERE p.docExtension = :docExtension")
    , @NamedQuery(name = "PrinDocumento.findByDocNombre", query = "SELECT p FROM PrinDocumento p WHERE p.docNombre = :docNombre")})
public class PrinDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOC_CODIGO")
    private Long docCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "DOC_MOD_ORIGEN")
    private String docModOrigen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DOC_DIR_GENERAL")
    private String docDirGeneral;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DOC_DIR_DESTINO")
    private String docDirDestino;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DOC_ENTIDAD")
    private String docEntidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOC_ENTIDAD_CODIGO")
    private Long docEntidadCodigo;
    @Column(name = "DOC_EXTENSION")
    private String docExtension;
    @Column(name = "DOC_NOMBRE")
    private String docNombre;
    @Lob
    @Column(name = "DOC_ARCHIVO")
    private byte[] docArchivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOC_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docFechaCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "DOC_USUARIO")
    private String docUsuario;
    @Column(name = "DOC_FECHA_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docFechaAct;
    @Size(max = 25)
    @Column(name = "DOC_USUARIO_ACT")
    private String docUsuarioAct;
    @JoinColumn(name = "CLS_CODIGO", referencedColumnName = "CLS_CODIGO")
    @ManyToOne(optional = false)
    private PrinClaseDocumento prinClaseDocumento;
    @Transient
    private UploadedFile archivo;
    @Transient
    private UploadedFile archivoOriginal;
    @Transient
    private boolean modificado;

    public PrinDocumento() {
    }

    public PrinDocumento(Long docCodigo) {
        this.docCodigo = docCodigo;
    }

    public PrinDocumento(Long docCodigo, String docModOrigen, String docDirGeneral, String docDirDestino, 
            String docEntidad, Long docEntidadCodigo, Date docFechaCrea, String docUsuario) {
        this.docCodigo = docCodigo;
        this.docModOrigen = docModOrigen;
        this.docDirGeneral = docDirGeneral;
        this.docEntidad = docEntidad;
        this.docEntidadCodigo = docEntidadCodigo;
        this.docFechaCrea = docFechaCrea;
        this.docUsuario = docUsuario;
    }

    public Long getDocCodigo() {
        return docCodigo;
    }

    public void setDocCodigo(Long docCodigo) {
        this.docCodigo = docCodigo;
    }

    public String getDocModOrigen() {
        return docModOrigen;
    }

    public void setDocModOrigen(String docModOrigen) {
        this.docModOrigen = docModOrigen;
    }

    public String getDocDirGeneral() {
        return docDirGeneral;
    }

    public void setDocDirGeneral(String docDirGeneral) {
        this.docDirGeneral = docDirGeneral;
    }

    public String getDocDirDestino() {
        return docDirDestino;
    }

    public void setDocDirDestino(String docDirDestino) {
        this.docDirDestino = docDirDestino;
    }



    public String getDocEntidad() {
        return docEntidad;
    }

    public void setDocEntidad(String docEntidad) {
        this.docEntidad = docEntidad;
    }

    public Long getDocEntidadCodigo() {
        return docEntidadCodigo;
    }

    public void setDocEntidadCodigo(Long docEntidadCodigo) {
        this.docEntidadCodigo = docEntidadCodigo;
    }

    public String getDocExtension() {
        return docExtension;
    }

    public void setDocExtension(String docExtension) {
        this.docExtension = docExtension;
    }

    public String getDocNombre() {
        return docNombre;
    }

    public void setDocNombre(String docNombre) {
        this.docNombre = docNombre;
    }

    public byte[] getDocArchivo() {
        return docArchivo;
    }

    public void setDocArchivo(byte[] docArchivo) {
        this.docArchivo = docArchivo;
    }

    public Date getDocFechaCrea() {
        return docFechaCrea;
    }

    public void setDocFechaCrea(Date docFechaCrea) {
        this.docFechaCrea = docFechaCrea;
    }

    public String getDocUsuario() {
        return docUsuario;
    }

    public void setDocUsuario(String docUsuario) {
        this.docUsuario = docUsuario;
    }

    public Date getDocFechaAct() {
        return docFechaAct;
    }

    public void setDocFechaAct(Date docFechaAct) {
        this.docFechaAct = docFechaAct;
    }

    public String getDocUsuarioAct() {
        return docUsuarioAct;
    }

    public void setDocUsuarioAct(String docUsuarioAct) {
        this.docUsuarioAct = docUsuarioAct;
    }

    public PrinClaseDocumento getPrinClaseDocumento() {
        return prinClaseDocumento;
    }

    public void setPrinClaseDocumento(PrinClaseDocumento prinClaseDocumento) {
        this.prinClaseDocumento = prinClaseDocumento;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public UploadedFile getArchivoOriginal() {
        return archivoOriginal;
    }

    public void setArchivoOriginal(UploadedFile archivoOriginal) {
        this.archivoOriginal = archivoOriginal;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docCodigo != null ? docCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrinDocumento)) {
            return false;
        }
        PrinDocumento other = (PrinDocumento) object;
        return !((this.docCodigo == null && other.docCodigo != null) || (this.docCodigo != null && !this.docCodigo.equals(other.docCodigo)));
    }

    @Override
    public String toString() {
        return "PrinDocumento{" + "docCodigo=" + docCodigo
                + ", docModOrigen=" + docModOrigen
                + ", docDirGeneral=" + docDirGeneral
                + ", docDirDestino=" + docDirDestino
                + ", docEntidad=" + docEntidad 
                + ", docEntidadCodigo=" + docEntidadCodigo
                + ", docExtension=" + docExtension
                + ", docNombre=" + docNombre 
                + ", docArchivo=" + docArchivo
                + ", docFechaCrea=" + docFechaCrea 
                + ", docUsuario=" + docUsuario 
                + ", docFechaAct=" + docFechaAct
                + ", docUsuarioAct=" + docUsuarioAct 
                + ", prinClaseDocumento=" + prinClaseDocumento
                + ", archivo=" + archivo 
                + ", archivoOriginal=" + archivoOriginal 
                + ", modificado=" + modificado 
                + '}';
    }




}
