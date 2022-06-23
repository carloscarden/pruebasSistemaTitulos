package com.dgcye.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Turno.
 */
@Entity
@Table(name = "turno")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Turno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "turno")
    private String turno;

    @Column(name = "descripcion")
    private LocalDate descripcion;

    @Column(name = "vigh")
    private LocalDate vigh;

    @Column(name = "orden")
    private Integer orden;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Turno id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTurno() {
        return this.turno;
    }

    public Turno turno(String turno) {
        this.setTurno(turno);
        return this;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public LocalDate getDescripcion() {
        return this.descripcion;
    }

    public Turno descripcion(LocalDate descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(LocalDate descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getVigh() {
        return this.vigh;
    }

    public Turno vigh(LocalDate vigh) {
        this.setVigh(vigh);
        return this;
    }

    public void setVigh(LocalDate vigh) {
        this.vigh = vigh;
    }

    public Integer getOrden() {
        return this.orden;
    }

    public Turno orden(Integer orden) {
        this.setOrden(orden);
        return this;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Turno)) {
            return false;
        }
        return id != null && id.equals(((Turno) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Turno{" +
            "id=" + getId() +
            ", turno='" + getTurno() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", vigh='" + getVigh() + "'" +
            ", orden=" + getOrden() +
            "}";
    }
}
