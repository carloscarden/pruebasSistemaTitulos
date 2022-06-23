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
 * A Alumno.
 */
@Entity
@Table(name = "alumno")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "documento")
    private String documento;

    @Column(name = "id_tipo_documento")
    private Long idTipoDocumento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fecha_nacimento")
    private Instant fechaNacimento;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "ciudad_nacimiento")
    private String ciudadNacimiento;

    @Column(name = "provincia_nacimiento")
    private String provinciaNacimiento;

    @Column(name = "id_nacionalidad")
    private Long idNacionalidad;

    @Column(name = "id_ser_creador")
    private Long idSerCreador;

    @Column(name = "id_provincia")
    private Long idProvincia;

    @OneToMany(mappedBy = "alumno")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "alumnoAnaliticos", "alumnoTitulos", "alumno", "ofertaEducativa" }, allowSetters = true)
    private Set<AlumnoEstabOferta> alumnoEstabOfertas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Alumno id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return this.documento;
    }

    public Alumno documento(String documento) {
        this.setDocumento(documento);
        return this;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Long getIdTipoDocumento() {
        return this.idTipoDocumento;
    }

    public Alumno idTipoDocumento(Long idTipoDocumento) {
        this.setIdTipoDocumento(idTipoDocumento);
        return this;
    }

    public void setIdTipoDocumento(Long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Alumno nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public Alumno apellido(String apellido) {
        this.setApellido(apellido);
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Instant getFechaNacimento() {
        return this.fechaNacimento;
    }

    public Alumno fechaNacimento(Instant fechaNacimento) {
        this.setFechaNacimento(fechaNacimento);
        return this;
    }

    public void setFechaNacimento(Instant fechaNacimento) {
        this.fechaNacimento = fechaNacimento;
    }

    public String getSexo() {
        return this.sexo;
    }

    public Alumno sexo(String sexo) {
        this.setSexo(sexo);
        return this;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCiudadNacimiento() {
        return this.ciudadNacimiento;
    }

    public Alumno ciudadNacimiento(String ciudadNacimiento) {
        this.setCiudadNacimiento(ciudadNacimiento);
        return this;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getProvinciaNacimiento() {
        return this.provinciaNacimiento;
    }

    public Alumno provinciaNacimiento(String provinciaNacimiento) {
        this.setProvinciaNacimiento(provinciaNacimiento);
        return this;
    }

    public void setProvinciaNacimiento(String provinciaNacimiento) {
        this.provinciaNacimiento = provinciaNacimiento;
    }

    public Long getIdNacionalidad() {
        return this.idNacionalidad;
    }

    public Alumno idNacionalidad(Long idNacionalidad) {
        this.setIdNacionalidad(idNacionalidad);
        return this;
    }

    public void setIdNacionalidad(Long idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public Long getIdSerCreador() {
        return this.idSerCreador;
    }

    public Alumno idSerCreador(Long idSerCreador) {
        this.setIdSerCreador(idSerCreador);
        return this;
    }

    public void setIdSerCreador(Long idSerCreador) {
        this.idSerCreador = idSerCreador;
    }

    public Long getIdProvincia() {
        return this.idProvincia;
    }

    public Alumno idProvincia(Long idProvincia) {
        this.setIdProvincia(idProvincia);
        return this;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Set<AlumnoEstabOferta> getAlumnoEstabOfertas() {
        return this.alumnoEstabOfertas;
    }

    public void setAlumnoEstabOfertas(Set<AlumnoEstabOferta> alumnoEstabOfertas) {
        if (this.alumnoEstabOfertas != null) {
            this.alumnoEstabOfertas.forEach(i -> i.setAlumno(null));
        }
        if (alumnoEstabOfertas != null) {
            alumnoEstabOfertas.forEach(i -> i.setAlumno(this));
        }
        this.alumnoEstabOfertas = alumnoEstabOfertas;
    }

    public Alumno alumnoEstabOfertas(Set<AlumnoEstabOferta> alumnoEstabOfertas) {
        this.setAlumnoEstabOfertas(alumnoEstabOfertas);
        return this;
    }

    public Alumno addAlumnoEstabOferta(AlumnoEstabOferta alumnoEstabOferta) {
        this.alumnoEstabOfertas.add(alumnoEstabOferta);
        alumnoEstabOferta.setAlumno(this);
        return this;
    }

    public Alumno removeAlumnoEstabOferta(AlumnoEstabOferta alumnoEstabOferta) {
        this.alumnoEstabOfertas.remove(alumnoEstabOferta);
        alumnoEstabOferta.setAlumno(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Alumno)) {
            return false;
        }
        return id != null && id.equals(((Alumno) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Alumno{" +
            "id=" + getId() +
            ", documento='" + getDocumento() + "'" +
            ", idTipoDocumento=" + getIdTipoDocumento() +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", fechaNacimento='" + getFechaNacimento() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", ciudadNacimiento='" + getCiudadNacimiento() + "'" +
            ", provinciaNacimiento='" + getProvinciaNacimiento() + "'" +
            ", idNacionalidad=" + getIdNacionalidad() +
            ", idSerCreador=" + getIdSerCreador() +
            ", idProvincia=" + getIdProvincia() +
            "}";
    }
}
