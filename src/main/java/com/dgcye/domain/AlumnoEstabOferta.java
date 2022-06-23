package com.dgcye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AlumnoEstabOferta.
 */
@Entity
@Table(name = "alumno_estab_oferta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlumnoEstabOferta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_ser")
    private Long idSer;

    @Column(name = "id_oferta_educativa")
    private Long idOfertaEducativa;

    @Column(name = "id_alumno")
    private Long idAlumno;

    @Column(name = "id_estado_alumno_estab_oferta")
    private Long idEstadoAlumnoEstabOferta;

    @Column(name = "fecha_inicio")
    private Instant fechaInicio;

    @Column(name = "fecha_fin")
    private Instant fechaFin;

    @OneToMany(mappedBy = "alumnoEstabOferta")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "alumnoEstabOferta", "materia" }, allowSetters = true)
    private Set<AlumnoAnalitico> alumnoAnaliticos = new HashSet<>();

    @OneToMany(mappedBy = "alumnoEstabOferta")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "alumnoEstabOferta" }, allowSetters = true)
    private Set<AlumnoTitulo> alumnoTitulos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "alumnoEstabOfertas" }, allowSetters = true)
    private Alumno alumno;

    @ManyToOne
    @JsonIgnoreProperties(value = { "alumnoEstabOfertas" }, allowSetters = true)
    private OfertaEducativa ofertaEducativa;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AlumnoEstabOferta id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSer() {
        return this.idSer;
    }

    public AlumnoEstabOferta idSer(Long idSer) {
        this.setIdSer(idSer);
        return this;
    }

    public void setIdSer(Long idSer) {
        this.idSer = idSer;
    }

    public Long getIdOfertaEducativa() {
        return this.idOfertaEducativa;
    }

    public AlumnoEstabOferta idOfertaEducativa(Long idOfertaEducativa) {
        this.setIdOfertaEducativa(idOfertaEducativa);
        return this;
    }

    public void setIdOfertaEducativa(Long idOfertaEducativa) {
        this.idOfertaEducativa = idOfertaEducativa;
    }

    public Long getIdAlumno() {
        return this.idAlumno;
    }

    public AlumnoEstabOferta idAlumno(Long idAlumno) {
        this.setIdAlumno(idAlumno);
        return this;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Long getIdEstadoAlumnoEstabOferta() {
        return this.idEstadoAlumnoEstabOferta;
    }

    public AlumnoEstabOferta idEstadoAlumnoEstabOferta(Long idEstadoAlumnoEstabOferta) {
        this.setIdEstadoAlumnoEstabOferta(idEstadoAlumnoEstabOferta);
        return this;
    }

    public void setIdEstadoAlumnoEstabOferta(Long idEstadoAlumnoEstabOferta) {
        this.idEstadoAlumnoEstabOferta = idEstadoAlumnoEstabOferta;
    }

    public Instant getFechaInicio() {
        return this.fechaInicio;
    }

    public AlumnoEstabOferta fechaInicio(Instant fechaInicio) {
        this.setFechaInicio(fechaInicio);
        return this;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Instant getFechaFin() {
        return this.fechaFin;
    }

    public AlumnoEstabOferta fechaFin(Instant fechaFin) {
        this.setFechaFin(fechaFin);
        return this;
    }

    public void setFechaFin(Instant fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Set<AlumnoAnalitico> getAlumnoAnaliticos() {
        return this.alumnoAnaliticos;
    }

    public void setAlumnoAnaliticos(Set<AlumnoAnalitico> alumnoAnaliticos) {
        if (this.alumnoAnaliticos != null) {
            this.alumnoAnaliticos.forEach(i -> i.setAlumnoEstabOferta(null));
        }
        if (alumnoAnaliticos != null) {
            alumnoAnaliticos.forEach(i -> i.setAlumnoEstabOferta(this));
        }
        this.alumnoAnaliticos = alumnoAnaliticos;
    }

    public AlumnoEstabOferta alumnoAnaliticos(Set<AlumnoAnalitico> alumnoAnaliticos) {
        this.setAlumnoAnaliticos(alumnoAnaliticos);
        return this;
    }

    public AlumnoEstabOferta addAlumnoAnalitico(AlumnoAnalitico alumnoAnalitico) {
        this.alumnoAnaliticos.add(alumnoAnalitico);
        alumnoAnalitico.setAlumnoEstabOferta(this);
        return this;
    }

    public AlumnoEstabOferta removeAlumnoAnalitico(AlumnoAnalitico alumnoAnalitico) {
        this.alumnoAnaliticos.remove(alumnoAnalitico);
        alumnoAnalitico.setAlumnoEstabOferta(null);
        return this;
    }

    public Set<AlumnoTitulo> getAlumnoTitulos() {
        return this.alumnoTitulos;
    }

    public void setAlumnoTitulos(Set<AlumnoTitulo> alumnoTitulos) {
        if (this.alumnoTitulos != null) {
            this.alumnoTitulos.forEach(i -> i.setAlumnoEstabOferta(null));
        }
        if (alumnoTitulos != null) {
            alumnoTitulos.forEach(i -> i.setAlumnoEstabOferta(this));
        }
        this.alumnoTitulos = alumnoTitulos;
    }

    public AlumnoEstabOferta alumnoTitulos(Set<AlumnoTitulo> alumnoTitulos) {
        this.setAlumnoTitulos(alumnoTitulos);
        return this;
    }

    public AlumnoEstabOferta addAlumnoTitulo(AlumnoTitulo alumnoTitulo) {
        this.alumnoTitulos.add(alumnoTitulo);
        alumnoTitulo.setAlumnoEstabOferta(this);
        return this;
    }

    public AlumnoEstabOferta removeAlumnoTitulo(AlumnoTitulo alumnoTitulo) {
        this.alumnoTitulos.remove(alumnoTitulo);
        alumnoTitulo.setAlumnoEstabOferta(null);
        return this;
    }

    public Alumno getAlumno() {
        return this.alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public AlumnoEstabOferta alumno(Alumno alumno) {
        this.setAlumno(alumno);
        return this;
    }

    public OfertaEducativa getOfertaEducativa() {
        return this.ofertaEducativa;
    }

    public void setOfertaEducativa(OfertaEducativa ofertaEducativa) {
        this.ofertaEducativa = ofertaEducativa;
    }

    public AlumnoEstabOferta ofertaEducativa(OfertaEducativa ofertaEducativa) {
        this.setOfertaEducativa(ofertaEducativa);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlumnoEstabOferta)) {
            return false;
        }
        return id != null && id.equals(((AlumnoEstabOferta) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AlumnoEstabOferta{" +
            "id=" + getId() +
            ", idSer=" + getIdSer() +
            ", idOfertaEducativa=" + getIdOfertaEducativa() +
            ", idAlumno=" + getIdAlumno() +
            ", idEstadoAlumnoEstabOferta=" + getIdEstadoAlumnoEstabOferta() +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            "}";
    }
}
