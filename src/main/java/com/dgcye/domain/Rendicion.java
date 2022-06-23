package com.dgcye.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Rendicion.
 */
@Entity
@Table(name = "rendicion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Rendicion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "dni_usuario")
    private String dniUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "dni_usuario_anulador")
    private String dniUsuarioAnulador;

    @Column(name = "nombre_usuario_anulador")
    private String nombreUsuarioAnulador;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "causa_rechazo")
    private String causaRechazo;

    @Column(name = "fecha_anulacion")
    private LocalDate fechaAnulacion;

    @Column(name = "dni_alumno")
    private String dniAlumno;

    @Column(name = "nombre_alumno")
    private String nombreAlumno;

    @Column(name = "alumno_titulo_id")
    private Long alumnoTituloId;

    @Column(name = "establecimiento_id")
    private Long establecimientoId;

    @Column(name = "clave_estab")
    private String claveEstab;

    @Column(name = "rama")
    private String rama;

    @Column(name = "nro_formulario")
    private Integer nroFormulario;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Rendicion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDniUsuario() {
        return this.dniUsuario;
    }

    public Rendicion dniUsuario(String dniUsuario) {
        this.setDniUsuario(dniUsuario);
        return this;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public Rendicion nombreUsuario(String nombreUsuario) {
        this.setNombreUsuario(nombreUsuario);
        return this;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDniUsuarioAnulador() {
        return this.dniUsuarioAnulador;
    }

    public Rendicion dniUsuarioAnulador(String dniUsuarioAnulador) {
        this.setDniUsuarioAnulador(dniUsuarioAnulador);
        return this;
    }

    public void setDniUsuarioAnulador(String dniUsuarioAnulador) {
        this.dniUsuarioAnulador = dniUsuarioAnulador;
    }

    public String getNombreUsuarioAnulador() {
        return this.nombreUsuarioAnulador;
    }

    public Rendicion nombreUsuarioAnulador(String nombreUsuarioAnulador) {
        this.setNombreUsuarioAnulador(nombreUsuarioAnulador);
        return this;
    }

    public void setNombreUsuarioAnulador(String nombreUsuarioAnulador) {
        this.nombreUsuarioAnulador = nombreUsuarioAnulador;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public Rendicion motivo(String motivo) {
        this.setMotivo(motivo);
        return this;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCausaRechazo() {
        return this.causaRechazo;
    }

    public Rendicion causaRechazo(String causaRechazo) {
        this.setCausaRechazo(causaRechazo);
        return this;
    }

    public void setCausaRechazo(String causaRechazo) {
        this.causaRechazo = causaRechazo;
    }

    public LocalDate getFechaAnulacion() {
        return this.fechaAnulacion;
    }

    public Rendicion fechaAnulacion(LocalDate fechaAnulacion) {
        this.setFechaAnulacion(fechaAnulacion);
        return this;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public String getDniAlumno() {
        return this.dniAlumno;
    }

    public Rendicion dniAlumno(String dniAlumno) {
        this.setDniAlumno(dniAlumno);
        return this;
    }

    public void setDniAlumno(String dniAlumno) {
        this.dniAlumno = dniAlumno;
    }

    public String getNombreAlumno() {
        return this.nombreAlumno;
    }

    public Rendicion nombreAlumno(String nombreAlumno) {
        this.setNombreAlumno(nombreAlumno);
        return this;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public Long getAlumnoTituloId() {
        return this.alumnoTituloId;
    }

    public Rendicion alumnoTituloId(Long alumnoTituloId) {
        this.setAlumnoTituloId(alumnoTituloId);
        return this;
    }

    public void setAlumnoTituloId(Long alumnoTituloId) {
        this.alumnoTituloId = alumnoTituloId;
    }

    public Long getEstablecimientoId() {
        return this.establecimientoId;
    }

    public Rendicion establecimientoId(Long establecimientoId) {
        this.setEstablecimientoId(establecimientoId);
        return this;
    }

    public void setEstablecimientoId(Long establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public String getClaveEstab() {
        return this.claveEstab;
    }

    public Rendicion claveEstab(String claveEstab) {
        this.setClaveEstab(claveEstab);
        return this;
    }

    public void setClaveEstab(String claveEstab) {
        this.claveEstab = claveEstab;
    }

    public String getRama() {
        return this.rama;
    }

    public Rendicion rama(String rama) {
        this.setRama(rama);
        return this;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public Integer getNroFormulario() {
        return this.nroFormulario;
    }

    public Rendicion nroFormulario(Integer nroFormulario) {
        this.setNroFormulario(nroFormulario);
        return this;
    }

    public void setNroFormulario(Integer nroFormulario) {
        this.nroFormulario = nroFormulario;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rendicion)) {
            return false;
        }
        return id != null && id.equals(((Rendicion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Rendicion{" +
            "id=" + getId() +
            ", dniUsuario='" + getDniUsuario() + "'" +
            ", nombreUsuario='" + getNombreUsuario() + "'" +
            ", dniUsuarioAnulador='" + getDniUsuarioAnulador() + "'" +
            ", nombreUsuarioAnulador='" + getNombreUsuarioAnulador() + "'" +
            ", motivo='" + getMotivo() + "'" +
            ", causaRechazo='" + getCausaRechazo() + "'" +
            ", fechaAnulacion='" + getFechaAnulacion() + "'" +
            ", dniAlumno='" + getDniAlumno() + "'" +
            ", nombreAlumno='" + getNombreAlumno() + "'" +
            ", alumnoTituloId=" + getAlumnoTituloId() +
            ", establecimientoId=" + getEstablecimientoId() +
            ", claveEstab='" + getClaveEstab() + "'" +
            ", rama='" + getRama() + "'" +
            ", nroFormulario=" + getNroFormulario() +
            "}";
    }
}
