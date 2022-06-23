package com.dgcye.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NroSerieTitulo.
 */
@Entity
@Table(name = "nro_serie_titulo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NroSerieTitulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "nro_serie_inicio")
    private Integer nroSerieInicio;

    @Column(name = "nro_serie_fin")
    private Integer nroSerieFin;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "usuario_alta")
    private String usuarioAlta;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    @Column(name = "serie")
    private Integer serie;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NroSerieTitulo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNroSerieInicio() {
        return this.nroSerieInicio;
    }

    public NroSerieTitulo nroSerieInicio(Integer nroSerieInicio) {
        this.setNroSerieInicio(nroSerieInicio);
        return this;
    }

    public void setNroSerieInicio(Integer nroSerieInicio) {
        this.nroSerieInicio = nroSerieInicio;
    }

    public Integer getNroSerieFin() {
        return this.nroSerieFin;
    }

    public NroSerieTitulo nroSerieFin(Integer nroSerieFin) {
        this.setNroSerieFin(nroSerieFin);
        return this;
    }

    public void setNroSerieFin(Integer nroSerieFin) {
        this.nroSerieFin = nroSerieFin;
    }

    public LocalDate getFechaInicio() {
        return this.fechaInicio;
    }

    public NroSerieTitulo fechaInicio(LocalDate fechaInicio) {
        this.setFechaInicio(fechaInicio);
        return this;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return this.fechaFin;
    }

    public NroSerieTitulo fechaFin(LocalDate fechaFin) {
        this.setFechaFin(fechaFin);
        return this;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUsuarioAlta() {
        return this.usuarioAlta;
    }

    public NroSerieTitulo usuarioAlta(String usuarioAlta) {
        this.setUsuarioAlta(usuarioAlta);
        return this;
    }

    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public String getUsuarioModificacion() {
        return this.usuarioModificacion;
    }

    public NroSerieTitulo usuarioModificacion(String usuarioModificacion) {
        this.setUsuarioModificacion(usuarioModificacion);
        return this;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Integer getSerie() {
        return this.serie;
    }

    public NroSerieTitulo serie(Integer serie) {
        this.setSerie(serie);
        return this;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NroSerieTitulo)) {
            return false;
        }
        return id != null && id.equals(((NroSerieTitulo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NroSerieTitulo{" +
            "id=" + getId() +
            ", nroSerieInicio=" + getNroSerieInicio() +
            ", nroSerieFin=" + getNroSerieFin() +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", usuarioAlta='" + getUsuarioAlta() + "'" +
            ", usuarioModificacion='" + getUsuarioModificacion() + "'" +
            ", serie=" + getSerie() +
            "}";
    }
}
