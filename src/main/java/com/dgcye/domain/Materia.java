package com.dgcye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Materia.
 */
@Entity
@Table(name = "materia")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_materia_denominacion")
    private Long idMateriaDenominacion;

    @Column(name = "marca_modulo")
    private String marcaModulo;

    @Column(name = "carga_horaria")
    private Long cargaHoraria;

    @Column(name = "id_oferta_educativa")
    private Long idOfertaEducativa;

    @Column(name = "id_seccion")
    private Long idSeccion;

    @Column(name = "id_tipo_materia_opcional")
    private Long idTipoMateriaOpcional;

    @Column(name = "orden")
    private Long orden;

    @Column(name = "id_area")
    private Long idArea;

    @Column(name = "id_asignatira")
    private Long idAsignatira;

    @Column(name = "codigos_chequeados")
    private Long codigosChequeados;

    @Column(name = "obligatoriedad")
    private Long obligatoriedad;

    @Column(name = "unidad_carga_pedagogica")
    private Long unidadCargaPedagogica;

    @OneToMany(mappedBy = "materia")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "alumnoEstabOferta", "materia" }, allowSetters = true)
    private Set<AlumnoAnalitico> alumnoAnaliticos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Materia id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMateriaDenominacion() {
        return this.idMateriaDenominacion;
    }

    public Materia idMateriaDenominacion(Long idMateriaDenominacion) {
        this.setIdMateriaDenominacion(idMateriaDenominacion);
        return this;
    }

    public void setIdMateriaDenominacion(Long idMateriaDenominacion) {
        this.idMateriaDenominacion = idMateriaDenominacion;
    }

    public String getMarcaModulo() {
        return this.marcaModulo;
    }

    public Materia marcaModulo(String marcaModulo) {
        this.setMarcaModulo(marcaModulo);
        return this;
    }

    public void setMarcaModulo(String marcaModulo) {
        this.marcaModulo = marcaModulo;
    }

    public Long getCargaHoraria() {
        return this.cargaHoraria;
    }

    public Materia cargaHoraria(Long cargaHoraria) {
        this.setCargaHoraria(cargaHoraria);
        return this;
    }

    public void setCargaHoraria(Long cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Long getIdOfertaEducativa() {
        return this.idOfertaEducativa;
    }

    public Materia idOfertaEducativa(Long idOfertaEducativa) {
        this.setIdOfertaEducativa(idOfertaEducativa);
        return this;
    }

    public void setIdOfertaEducativa(Long idOfertaEducativa) {
        this.idOfertaEducativa = idOfertaEducativa;
    }

    public Long getIdSeccion() {
        return this.idSeccion;
    }

    public Materia idSeccion(Long idSeccion) {
        this.setIdSeccion(idSeccion);
        return this;
    }

    public void setIdSeccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Long getIdTipoMateriaOpcional() {
        return this.idTipoMateriaOpcional;
    }

    public Materia idTipoMateriaOpcional(Long idTipoMateriaOpcional) {
        this.setIdTipoMateriaOpcional(idTipoMateriaOpcional);
        return this;
    }

    public void setIdTipoMateriaOpcional(Long idTipoMateriaOpcional) {
        this.idTipoMateriaOpcional = idTipoMateriaOpcional;
    }

    public Long getOrden() {
        return this.orden;
    }

    public Materia orden(Long orden) {
        this.setOrden(orden);
        return this;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    public Long getIdArea() {
        return this.idArea;
    }

    public Materia idArea(Long idArea) {
        this.setIdArea(idArea);
        return this;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public Long getIdAsignatira() {
        return this.idAsignatira;
    }

    public Materia idAsignatira(Long idAsignatira) {
        this.setIdAsignatira(idAsignatira);
        return this;
    }

    public void setIdAsignatira(Long idAsignatira) {
        this.idAsignatira = idAsignatira;
    }

    public Long getCodigosChequeados() {
        return this.codigosChequeados;
    }

    public Materia codigosChequeados(Long codigosChequeados) {
        this.setCodigosChequeados(codigosChequeados);
        return this;
    }

    public void setCodigosChequeados(Long codigosChequeados) {
        this.codigosChequeados = codigosChequeados;
    }

    public Long getObligatoriedad() {
        return this.obligatoriedad;
    }

    public Materia obligatoriedad(Long obligatoriedad) {
        this.setObligatoriedad(obligatoriedad);
        return this;
    }

    public void setObligatoriedad(Long obligatoriedad) {
        this.obligatoriedad = obligatoriedad;
    }

    public Long getUnidadCargaPedagogica() {
        return this.unidadCargaPedagogica;
    }

    public Materia unidadCargaPedagogica(Long unidadCargaPedagogica) {
        this.setUnidadCargaPedagogica(unidadCargaPedagogica);
        return this;
    }

    public void setUnidadCargaPedagogica(Long unidadCargaPedagogica) {
        this.unidadCargaPedagogica = unidadCargaPedagogica;
    }

    public Set<AlumnoAnalitico> getAlumnoAnaliticos() {
        return this.alumnoAnaliticos;
    }

    public void setAlumnoAnaliticos(Set<AlumnoAnalitico> alumnoAnaliticos) {
        if (this.alumnoAnaliticos != null) {
            this.alumnoAnaliticos.forEach(i -> i.setMateria(null));
        }
        if (alumnoAnaliticos != null) {
            alumnoAnaliticos.forEach(i -> i.setMateria(this));
        }
        this.alumnoAnaliticos = alumnoAnaliticos;
    }

    public Materia alumnoAnaliticos(Set<AlumnoAnalitico> alumnoAnaliticos) {
        this.setAlumnoAnaliticos(alumnoAnaliticos);
        return this;
    }

    public Materia addAlumnoAnalitico(AlumnoAnalitico alumnoAnalitico) {
        this.alumnoAnaliticos.add(alumnoAnalitico);
        alumnoAnalitico.setMateria(this);
        return this;
    }

    public Materia removeAlumnoAnalitico(AlumnoAnalitico alumnoAnalitico) {
        this.alumnoAnaliticos.remove(alumnoAnalitico);
        alumnoAnalitico.setMateria(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Materia)) {
            return false;
        }
        return id != null && id.equals(((Materia) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Materia{" +
            "id=" + getId() +
            ", idMateriaDenominacion=" + getIdMateriaDenominacion() +
            ", marcaModulo='" + getMarcaModulo() + "'" +
            ", cargaHoraria=" + getCargaHoraria() +
            ", idOfertaEducativa=" + getIdOfertaEducativa() +
            ", idSeccion=" + getIdSeccion() +
            ", idTipoMateriaOpcional=" + getIdTipoMateriaOpcional() +
            ", orden=" + getOrden() +
            ", idArea=" + getIdArea() +
            ", idAsignatira=" + getIdAsignatira() +
            ", codigosChequeados=" + getCodigosChequeados() +
            ", obligatoriedad=" + getObligatoriedad() +
            ", unidadCargaPedagogica=" + getUnidadCargaPedagogica() +
            "}";
    }
}
