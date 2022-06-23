package com.dgcye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AlumnoAnalitico.
 */
@Entity
@Table(name = "alumno_analitico")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlumnoAnalitico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "nota")
    private Long nota;

    @Column(name = "id_estado_alumno_analitico")
    private Long idEstadoAlumnoAnalitico;

    @Column(name = "id_alumno_estab_oferta")
    private Long idAlumnoEstabOferta;

    @Column(name = "id_materia")
    private Long idMateria;

    @Column(name = "id_mes_imp")
    private Long idMesImp;

    @Column(name = "id_ano_imp")
    private Long idAnoImp;

    @Column(name = "establecimiento_imp")
    private String establecimientoImp;

    @ManyToOne
    @JsonIgnoreProperties(value = { "alumnoAnaliticos", "alumnoTitulos", "alumno", "ofertaEducativa" }, allowSetters = true)
    private AlumnoEstabOferta alumnoEstabOferta;

    @ManyToOne
    @JsonIgnoreProperties(value = { "alumnoAnaliticos" }, allowSetters = true)
    private Materia materia;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AlumnoAnalitico id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNota() {
        return this.nota;
    }

    public AlumnoAnalitico nota(Long nota) {
        this.setNota(nota);
        return this;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }

    public Long getIdEstadoAlumnoAnalitico() {
        return this.idEstadoAlumnoAnalitico;
    }

    public AlumnoAnalitico idEstadoAlumnoAnalitico(Long idEstadoAlumnoAnalitico) {
        this.setIdEstadoAlumnoAnalitico(idEstadoAlumnoAnalitico);
        return this;
    }

    public void setIdEstadoAlumnoAnalitico(Long idEstadoAlumnoAnalitico) {
        this.idEstadoAlumnoAnalitico = idEstadoAlumnoAnalitico;
    }

    public Long getIdAlumnoEstabOferta() {
        return this.idAlumnoEstabOferta;
    }

    public AlumnoAnalitico idAlumnoEstabOferta(Long idAlumnoEstabOferta) {
        this.setIdAlumnoEstabOferta(idAlumnoEstabOferta);
        return this;
    }

    public void setIdAlumnoEstabOferta(Long idAlumnoEstabOferta) {
        this.idAlumnoEstabOferta = idAlumnoEstabOferta;
    }

    public Long getIdMateria() {
        return this.idMateria;
    }

    public AlumnoAnalitico idMateria(Long idMateria) {
        this.setIdMateria(idMateria);
        return this;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public Long getIdMesImp() {
        return this.idMesImp;
    }

    public AlumnoAnalitico idMesImp(Long idMesImp) {
        this.setIdMesImp(idMesImp);
        return this;
    }

    public void setIdMesImp(Long idMesImp) {
        this.idMesImp = idMesImp;
    }

    public Long getIdAnoImp() {
        return this.idAnoImp;
    }

    public AlumnoAnalitico idAnoImp(Long idAnoImp) {
        this.setIdAnoImp(idAnoImp);
        return this;
    }

    public void setIdAnoImp(Long idAnoImp) {
        this.idAnoImp = idAnoImp;
    }

    public String getEstablecimientoImp() {
        return this.establecimientoImp;
    }

    public AlumnoAnalitico establecimientoImp(String establecimientoImp) {
        this.setEstablecimientoImp(establecimientoImp);
        return this;
    }

    public void setEstablecimientoImp(String establecimientoImp) {
        this.establecimientoImp = establecimientoImp;
    }

    public AlumnoEstabOferta getAlumnoEstabOferta() {
        return this.alumnoEstabOferta;
    }

    public void setAlumnoEstabOferta(AlumnoEstabOferta alumnoEstabOferta) {
        this.alumnoEstabOferta = alumnoEstabOferta;
    }

    public AlumnoAnalitico alumnoEstabOferta(AlumnoEstabOferta alumnoEstabOferta) {
        this.setAlumnoEstabOferta(alumnoEstabOferta);
        return this;
    }

    public Materia getMateria() {
        return this.materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public AlumnoAnalitico materia(Materia materia) {
        this.setMateria(materia);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlumnoAnalitico)) {
            return false;
        }
        return id != null && id.equals(((AlumnoAnalitico) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AlumnoAnalitico{" +
            "id=" + getId() +
            ", nota=" + getNota() +
            ", idEstadoAlumnoAnalitico=" + getIdEstadoAlumnoAnalitico() +
            ", idAlumnoEstabOferta=" + getIdAlumnoEstabOferta() +
            ", idMateria=" + getIdMateria() +
            ", idMesImp=" + getIdMesImp() +
            ", idAnoImp=" + getIdAnoImp() +
            ", establecimientoImp='" + getEstablecimientoImp() + "'" +
            "}";
    }
}
