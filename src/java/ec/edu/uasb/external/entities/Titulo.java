/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.external.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "V_TITULOS_DOCENTE", schema = "interfaseOcu.")
public class Titulo implements Serializable {

    private static final long serialVersionUID = 102L;
    @Id
    @Column(name = "PER_CODIGO")
    private Long perCodigo;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "UNIVERSIDAD_OBTENCION")
    private String universidadObtencion;
    @Column(name = "PAIS_OBTENCION")
    private String paisObtencion;
    @Column(name = "REGISTRO_SENECYT")
    private String regisrtoSenecyt;

    public Titulo() {
    }

    public Titulo(Long perCodigo, String titulo, String universidadObtencion, String paisObtencion) {
        this.perCodigo = perCodigo;
        this.titulo = titulo;
        this.universidadObtencion = universidadObtencion;
        this.paisObtencion = paisObtencion;
    }

    public Long getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Long perCodigo) {
        this.perCodigo = perCodigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUniversidadObtencion() {
        return universidadObtencion;
    }

    public void setUniversidadObtencion(String universidadObtencion) {
        this.universidadObtencion = universidadObtencion;
    }

    public String getPaisObtencion() {
        return paisObtencion;
    }

    public void setPaisObtencion(String paisObtencion) {
        this.paisObtencion = paisObtencion;
    }

    public String getRegisrtoSenecyt() {
        return regisrtoSenecyt;
    }

    public void setRegisrtoSenecyt(String regisrtoSenecyt) {
        this.regisrtoSenecyt = regisrtoSenecyt;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.perCodigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Titulo other = (Titulo) obj;
        return Objects.equals(this.perCodigo, other.perCodigo);
    }

    @Override
    public String toString() {
        return "Titulo{" + "perCodigo=" + perCodigo
                + ", titulo=" + titulo
                + ", universidadObtencion=" + universidadObtencion
                + ", paisObtencion=" + paisObtencion
                + ", regisrtoSenecyt=" + regisrtoSenecyt
                + '}';
    }

}
