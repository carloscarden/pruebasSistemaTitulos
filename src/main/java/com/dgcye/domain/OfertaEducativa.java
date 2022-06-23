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
 * A OfertaEducativa.
 */
@Entity
@Table(name = "oferta_educativa")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OfertaEducativa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_modalidad")
    private Long idModalidad;

    @Column(name = "id_nivel")
    private Long idNivel;

    @Column(name = "id_orientacion")
    private Long idOrientacion;

    @Column(name = "id_titulo_denominacion")
    private Long idTituloDenominacion;

    @Column(name = "observiaciones")
    private String observiaciones;

    @Column(name = "id_tipo_titulo")
    private Long idTipoTitulo;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "fecha_inicio")
    private Instant fechaInicio;

    @Column(name = "fecha_fin")
    private Instant fechaFin;

    @Column(name = "fecha_estado")
    private Instant fechaEstado;

    @Column(name = "id_estado_oferta")
    private Long idEstadoOferta;

    @Column(name = "id_ley_educacion")
    private Long idLeyEducacion;

    @Column(name = "id_norma_aprobacion_den")
    private Long idNormaAprobacionDen;

    @Column(name = "id_norma_dictamen_den")
    private Long idNormaDictamenDen;

    @Column(name = "id_se_corresponde_con")
    private Long idSeCorrespondeCon;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "titulo_impresion")
    private String tituloImpresion;

    @Column(name = "titulo_intermedio")
    private String tituloIntermedio;

    @Column(name = "titulo_intermedio_impresion")
    private String tituloIntermedioImpresion;

    @Column(name = "orientacion")
    private String orientacion;

    @Column(name = "id_rama")
    private Long idRama;

    @Column(name = "id_seccion_titulo_intermedio")
    private Long idSeccionTituloIntermedio;

    @Column(name = "id_curso_grupo_nombre")
    private Long idCursoGrupoNombre;

    @Column(name = "correlatividad")
    private Integer correlatividad;

    @Column(name = "id_modalidad_dictado")
    private Long idModalidadDictado;

    @Column(name = "titula")
    private Integer titula;

    @Column(name = "ciclo_basico")
    private Integer cicloBasico;

    @OneToMany(mappedBy = "ofertaEducativa")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "alumnoAnaliticos", "alumnoTitulos", "alumno", "ofertaEducativa" }, allowSetters = true)
    private Set<AlumnoEstabOferta> alumnoEstabOfertas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OfertaEducativa id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdModalidad() {
        return this.idModalidad;
    }

    public OfertaEducativa idModalidad(Long idModalidad) {
        this.setIdModalidad(idModalidad);
        return this;
    }

    public void setIdModalidad(Long idModalidad) {
        this.idModalidad = idModalidad;
    }

    public Long getIdNivel() {
        return this.idNivel;
    }

    public OfertaEducativa idNivel(Long idNivel) {
        this.setIdNivel(idNivel);
        return this;
    }

    public void setIdNivel(Long idNivel) {
        this.idNivel = idNivel;
    }

    public Long getIdOrientacion() {
        return this.idOrientacion;
    }

    public OfertaEducativa idOrientacion(Long idOrientacion) {
        this.setIdOrientacion(idOrientacion);
        return this;
    }

    public void setIdOrientacion(Long idOrientacion) {
        this.idOrientacion = idOrientacion;
    }

    public Long getIdTituloDenominacion() {
        return this.idTituloDenominacion;
    }

    public OfertaEducativa idTituloDenominacion(Long idTituloDenominacion) {
        this.setIdTituloDenominacion(idTituloDenominacion);
        return this;
    }

    public void setIdTituloDenominacion(Long idTituloDenominacion) {
        this.idTituloDenominacion = idTituloDenominacion;
    }

    public String getObserviaciones() {
        return this.observiaciones;
    }

    public OfertaEducativa observiaciones(String observiaciones) {
        this.setObserviaciones(observiaciones);
        return this;
    }

    public void setObserviaciones(String observiaciones) {
        this.observiaciones = observiaciones;
    }

    public Long getIdTipoTitulo() {
        return this.idTipoTitulo;
    }

    public OfertaEducativa idTipoTitulo(Long idTipoTitulo) {
        this.setIdTipoTitulo(idTipoTitulo);
        return this;
    }

    public void setIdTipoTitulo(Long idTipoTitulo) {
        this.idTipoTitulo = idTipoTitulo;
    }

    public Long getIdUser() {
        return this.idUser;
    }

    public OfertaEducativa idUser(Long idUser) {
        this.setIdUser(idUser);
        return this;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Instant getFechaInicio() {
        return this.fechaInicio;
    }

    public OfertaEducativa fechaInicio(Instant fechaInicio) {
        this.setFechaInicio(fechaInicio);
        return this;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Instant getFechaFin() {
        return this.fechaFin;
    }

    public OfertaEducativa fechaFin(Instant fechaFin) {
        this.setFechaFin(fechaFin);
        return this;
    }

    public void setFechaFin(Instant fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Instant getFechaEstado() {
        return this.fechaEstado;
    }

    public OfertaEducativa fechaEstado(Instant fechaEstado) {
        this.setFechaEstado(fechaEstado);
        return this;
    }

    public void setFechaEstado(Instant fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public Long getIdEstadoOferta() {
        return this.idEstadoOferta;
    }

    public OfertaEducativa idEstadoOferta(Long idEstadoOferta) {
        this.setIdEstadoOferta(idEstadoOferta);
        return this;
    }

    public void setIdEstadoOferta(Long idEstadoOferta) {
        this.idEstadoOferta = idEstadoOferta;
    }

    public Long getIdLeyEducacion() {
        return this.idLeyEducacion;
    }

    public OfertaEducativa idLeyEducacion(Long idLeyEducacion) {
        this.setIdLeyEducacion(idLeyEducacion);
        return this;
    }

    public void setIdLeyEducacion(Long idLeyEducacion) {
        this.idLeyEducacion = idLeyEducacion;
    }

    public Long getIdNormaAprobacionDen() {
        return this.idNormaAprobacionDen;
    }

    public OfertaEducativa idNormaAprobacionDen(Long idNormaAprobacionDen) {
        this.setIdNormaAprobacionDen(idNormaAprobacionDen);
        return this;
    }

    public void setIdNormaAprobacionDen(Long idNormaAprobacionDen) {
        this.idNormaAprobacionDen = idNormaAprobacionDen;
    }

    public Long getIdNormaDictamenDen() {
        return this.idNormaDictamenDen;
    }

    public OfertaEducativa idNormaDictamenDen(Long idNormaDictamenDen) {
        this.setIdNormaDictamenDen(idNormaDictamenDen);
        return this;
    }

    public void setIdNormaDictamenDen(Long idNormaDictamenDen) {
        this.idNormaDictamenDen = idNormaDictamenDen;
    }

    public Long getIdSeCorrespondeCon() {
        return this.idSeCorrespondeCon;
    }

    public OfertaEducativa idSeCorrespondeCon(Long idSeCorrespondeCon) {
        this.setIdSeCorrespondeCon(idSeCorrespondeCon);
        return this;
    }

    public void setIdSeCorrespondeCon(Long idSeCorrespondeCon) {
        this.idSeCorrespondeCon = idSeCorrespondeCon;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public OfertaEducativa titulo(String titulo) {
        this.setTitulo(titulo);
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloImpresion() {
        return this.tituloImpresion;
    }

    public OfertaEducativa tituloImpresion(String tituloImpresion) {
        this.setTituloImpresion(tituloImpresion);
        return this;
    }

    public void setTituloImpresion(String tituloImpresion) {
        this.tituloImpresion = tituloImpresion;
    }

    public String getTituloIntermedio() {
        return this.tituloIntermedio;
    }

    public OfertaEducativa tituloIntermedio(String tituloIntermedio) {
        this.setTituloIntermedio(tituloIntermedio);
        return this;
    }

    public void setTituloIntermedio(String tituloIntermedio) {
        this.tituloIntermedio = tituloIntermedio;
    }

    public String getTituloIntermedioImpresion() {
        return this.tituloIntermedioImpresion;
    }

    public OfertaEducativa tituloIntermedioImpresion(String tituloIntermedioImpresion) {
        this.setTituloIntermedioImpresion(tituloIntermedioImpresion);
        return this;
    }

    public void setTituloIntermedioImpresion(String tituloIntermedioImpresion) {
        this.tituloIntermedioImpresion = tituloIntermedioImpresion;
    }

    public String getOrientacion() {
        return this.orientacion;
    }

    public OfertaEducativa orientacion(String orientacion) {
        this.setOrientacion(orientacion);
        return this;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public Long getIdRama() {
        return this.idRama;
    }

    public OfertaEducativa idRama(Long idRama) {
        this.setIdRama(idRama);
        return this;
    }

    public void setIdRama(Long idRama) {
        this.idRama = idRama;
    }

    public Long getIdSeccionTituloIntermedio() {
        return this.idSeccionTituloIntermedio;
    }

    public OfertaEducativa idSeccionTituloIntermedio(Long idSeccionTituloIntermedio) {
        this.setIdSeccionTituloIntermedio(idSeccionTituloIntermedio);
        return this;
    }

    public void setIdSeccionTituloIntermedio(Long idSeccionTituloIntermedio) {
        this.idSeccionTituloIntermedio = idSeccionTituloIntermedio;
    }

    public Long getIdCursoGrupoNombre() {
        return this.idCursoGrupoNombre;
    }

    public OfertaEducativa idCursoGrupoNombre(Long idCursoGrupoNombre) {
        this.setIdCursoGrupoNombre(idCursoGrupoNombre);
        return this;
    }

    public void setIdCursoGrupoNombre(Long idCursoGrupoNombre) {
        this.idCursoGrupoNombre = idCursoGrupoNombre;
    }

    public Integer getCorrelatividad() {
        return this.correlatividad;
    }

    public OfertaEducativa correlatividad(Integer correlatividad) {
        this.setCorrelatividad(correlatividad);
        return this;
    }

    public void setCorrelatividad(Integer correlatividad) {
        this.correlatividad = correlatividad;
    }

    public Long getIdModalidadDictado() {
        return this.idModalidadDictado;
    }

    public OfertaEducativa idModalidadDictado(Long idModalidadDictado) {
        this.setIdModalidadDictado(idModalidadDictado);
        return this;
    }

    public void setIdModalidadDictado(Long idModalidadDictado) {
        this.idModalidadDictado = idModalidadDictado;
    }

    public Integer getTitula() {
        return this.titula;
    }

    public OfertaEducativa titula(Integer titula) {
        this.setTitula(titula);
        return this;
    }

    public void setTitula(Integer titula) {
        this.titula = titula;
    }

    public Integer getCicloBasico() {
        return this.cicloBasico;
    }

    public OfertaEducativa cicloBasico(Integer cicloBasico) {
        this.setCicloBasico(cicloBasico);
        return this;
    }

    public void setCicloBasico(Integer cicloBasico) {
        this.cicloBasico = cicloBasico;
    }

    public Set<AlumnoEstabOferta> getAlumnoEstabOfertas() {
        return this.alumnoEstabOfertas;
    }

    public void setAlumnoEstabOfertas(Set<AlumnoEstabOferta> alumnoEstabOfertas) {
        if (this.alumnoEstabOfertas != null) {
            this.alumnoEstabOfertas.forEach(i -> i.setOfertaEducativa(null));
        }
        if (alumnoEstabOfertas != null) {
            alumnoEstabOfertas.forEach(i -> i.setOfertaEducativa(this));
        }
        this.alumnoEstabOfertas = alumnoEstabOfertas;
    }

    public OfertaEducativa alumnoEstabOfertas(Set<AlumnoEstabOferta> alumnoEstabOfertas) {
        this.setAlumnoEstabOfertas(alumnoEstabOfertas);
        return this;
    }

    public OfertaEducativa addAlumnoEstabOferta(AlumnoEstabOferta alumnoEstabOferta) {
        this.alumnoEstabOfertas.add(alumnoEstabOferta);
        alumnoEstabOferta.setOfertaEducativa(this);
        return this;
    }

    public OfertaEducativa removeAlumnoEstabOferta(AlumnoEstabOferta alumnoEstabOferta) {
        this.alumnoEstabOfertas.remove(alumnoEstabOferta);
        alumnoEstabOferta.setOfertaEducativa(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OfertaEducativa)) {
            return false;
        }
        return id != null && id.equals(((OfertaEducativa) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OfertaEducativa{" +
            "id=" + getId() +
            ", idModalidad=" + getIdModalidad() +
            ", idNivel=" + getIdNivel() +
            ", idOrientacion=" + getIdOrientacion() +
            ", idTituloDenominacion=" + getIdTituloDenominacion() +
            ", observiaciones='" + getObserviaciones() + "'" +
            ", idTipoTitulo=" + getIdTipoTitulo() +
            ", idUser=" + getIdUser() +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", fechaEstado='" + getFechaEstado() + "'" +
            ", idEstadoOferta=" + getIdEstadoOferta() +
            ", idLeyEducacion=" + getIdLeyEducacion() +
            ", idNormaAprobacionDen=" + getIdNormaAprobacionDen() +
            ", idNormaDictamenDen=" + getIdNormaDictamenDen() +
            ", idSeCorrespondeCon=" + getIdSeCorrespondeCon() +
            ", titulo='" + getTitulo() + "'" +
            ", tituloImpresion='" + getTituloImpresion() + "'" +
            ", tituloIntermedio='" + getTituloIntermedio() + "'" +
            ", tituloIntermedioImpresion='" + getTituloIntermedioImpresion() + "'" +
            ", orientacion='" + getOrientacion() + "'" +
            ", idRama=" + getIdRama() +
            ", idSeccionTituloIntermedio=" + getIdSeccionTituloIntermedio() +
            ", idCursoGrupoNombre=" + getIdCursoGrupoNombre() +
            ", correlatividad=" + getCorrelatividad() +
            ", idModalidadDictado=" + getIdModalidadDictado() +
            ", titula=" + getTitula() +
            ", cicloBasico=" + getCicloBasico() +
            "}";
    }
}
