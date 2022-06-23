package com.dgcye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AlumnoTitulo.
 */
@Entity
@Table(name = "alumno_titulo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlumnoTitulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_alumno_estab_oferta")
    private Long idAlumnoEstabOferta;

    @Column(name = "fecha_emision")
    private Instant fechaEmision;

    @Column(name = "nro_serie")
    private String nroSerie;

    @Column(name = "numero_rfifd")
    private String numeroRfifd;

    @Column(name = "validez_nacional")
    private String validezNacional;

    @Column(name = "fecha_envio")
    private Instant fechaEnvio;

    @Column(name = "fecha_egreso")
    private Instant fechaEgreso;

    @Column(name = "nro_libro_matriz")
    private Integer nroLibroMatriz;

    @Column(name = "nro_acta")
    private Integer nroActa;

    @Column(name = "nro_folio")
    private Integer nroFolio;

    @Column(name = "estab_nombre")
    private String estabNombre;

    @Column(name = "estab_cue")
    private String estabCue;

    @Column(name = "estab_ubicado_en")
    private String estabUbicadoEn;

    @Column(name = "estab_ciudad")
    private String estabCiudad;

    @Column(name = "estab_provincia")
    private String estabProvincia;

    @Column(name = "id_estado_alumno_titulo")
    private Long idEstadoAlumnoTitulo;

    @Column(name = "titulo_intermedio")
    private Integer tituloIntermedio;

    @Column(name = "promedio")
    private String promedio;

    @Column(name = "fecha_ruta")
    private Instant fechaRuta;

    @Column(name = "id_rama_ruta")
    private Long idRamaRuta;

    @Column(name = "ap_ynom_listo_para_enviar")
    private String apYnomListoParaEnviar;

    @Column(name = "documento_listo_para_enviar")
    private String documentoListoParaEnviar;

    @Column(name = "ap_ynom_enviado")
    private String apYnomEnviado;

    @Column(name = "documento_enviado")
    private String documentoEnviado;

    @Column(name = "fecha_ultimo_estado")
    private Instant fechaUltimoEstado;

    @ManyToOne
    @JsonIgnoreProperties(value = { "alumnoAnaliticos", "alumnoTitulos", "alumno", "ofertaEducativa" }, allowSetters = true)
    private AlumnoEstabOferta alumnoEstabOferta;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AlumnoTitulo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAlumnoEstabOferta() {
        return this.idAlumnoEstabOferta;
    }

    public AlumnoTitulo idAlumnoEstabOferta(Long idAlumnoEstabOferta) {
        this.setIdAlumnoEstabOferta(idAlumnoEstabOferta);
        return this;
    }

    public void setIdAlumnoEstabOferta(Long idAlumnoEstabOferta) {
        this.idAlumnoEstabOferta = idAlumnoEstabOferta;
    }

    public Instant getFechaEmision() {
        return this.fechaEmision;
    }

    public AlumnoTitulo fechaEmision(Instant fechaEmision) {
        this.setFechaEmision(fechaEmision);
        return this;
    }

    public void setFechaEmision(Instant fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getNroSerie() {
        return this.nroSerie;
    }

    public AlumnoTitulo nroSerie(String nroSerie) {
        this.setNroSerie(nroSerie);
        return this;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public String getNumeroRfifd() {
        return this.numeroRfifd;
    }

    public AlumnoTitulo numeroRfifd(String numeroRfifd) {
        this.setNumeroRfifd(numeroRfifd);
        return this;
    }

    public void setNumeroRfifd(String numeroRfifd) {
        this.numeroRfifd = numeroRfifd;
    }

    public String getValidezNacional() {
        return this.validezNacional;
    }

    public AlumnoTitulo validezNacional(String validezNacional) {
        this.setValidezNacional(validezNacional);
        return this;
    }

    public void setValidezNacional(String validezNacional) {
        this.validezNacional = validezNacional;
    }

    public Instant getFechaEnvio() {
        return this.fechaEnvio;
    }

    public AlumnoTitulo fechaEnvio(Instant fechaEnvio) {
        this.setFechaEnvio(fechaEnvio);
        return this;
    }

    public void setFechaEnvio(Instant fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Instant getFechaEgreso() {
        return this.fechaEgreso;
    }

    public AlumnoTitulo fechaEgreso(Instant fechaEgreso) {
        this.setFechaEgreso(fechaEgreso);
        return this;
    }

    public void setFechaEgreso(Instant fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Integer getNroLibroMatriz() {
        return this.nroLibroMatriz;
    }

    public AlumnoTitulo nroLibroMatriz(Integer nroLibroMatriz) {
        this.setNroLibroMatriz(nroLibroMatriz);
        return this;
    }

    public void setNroLibroMatriz(Integer nroLibroMatriz) {
        this.nroLibroMatriz = nroLibroMatriz;
    }

    public Integer getNroActa() {
        return this.nroActa;
    }

    public AlumnoTitulo nroActa(Integer nroActa) {
        this.setNroActa(nroActa);
        return this;
    }

    public void setNroActa(Integer nroActa) {
        this.nroActa = nroActa;
    }

    public Integer getNroFolio() {
        return this.nroFolio;
    }

    public AlumnoTitulo nroFolio(Integer nroFolio) {
        this.setNroFolio(nroFolio);
        return this;
    }

    public void setNroFolio(Integer nroFolio) {
        this.nroFolio = nroFolio;
    }

    public String getEstabNombre() {
        return this.estabNombre;
    }

    public AlumnoTitulo estabNombre(String estabNombre) {
        this.setEstabNombre(estabNombre);
        return this;
    }

    public void setEstabNombre(String estabNombre) {
        this.estabNombre = estabNombre;
    }

    public String getEstabCue() {
        return this.estabCue;
    }

    public AlumnoTitulo estabCue(String estabCue) {
        this.setEstabCue(estabCue);
        return this;
    }

    public void setEstabCue(String estabCue) {
        this.estabCue = estabCue;
    }

    public String getEstabUbicadoEn() {
        return this.estabUbicadoEn;
    }

    public AlumnoTitulo estabUbicadoEn(String estabUbicadoEn) {
        this.setEstabUbicadoEn(estabUbicadoEn);
        return this;
    }

    public void setEstabUbicadoEn(String estabUbicadoEn) {
        this.estabUbicadoEn = estabUbicadoEn;
    }

    public String getEstabCiudad() {
        return this.estabCiudad;
    }

    public AlumnoTitulo estabCiudad(String estabCiudad) {
        this.setEstabCiudad(estabCiudad);
        return this;
    }

    public void setEstabCiudad(String estabCiudad) {
        this.estabCiudad = estabCiudad;
    }

    public String getEstabProvincia() {
        return this.estabProvincia;
    }

    public AlumnoTitulo estabProvincia(String estabProvincia) {
        this.setEstabProvincia(estabProvincia);
        return this;
    }

    public void setEstabProvincia(String estabProvincia) {
        this.estabProvincia = estabProvincia;
    }

    public Long getIdEstadoAlumnoTitulo() {
        return this.idEstadoAlumnoTitulo;
    }

    public AlumnoTitulo idEstadoAlumnoTitulo(Long idEstadoAlumnoTitulo) {
        this.setIdEstadoAlumnoTitulo(idEstadoAlumnoTitulo);
        return this;
    }

    public void setIdEstadoAlumnoTitulo(Long idEstadoAlumnoTitulo) {
        this.idEstadoAlumnoTitulo = idEstadoAlumnoTitulo;
    }

    public Integer getTituloIntermedio() {
        return this.tituloIntermedio;
    }

    public AlumnoTitulo tituloIntermedio(Integer tituloIntermedio) {
        this.setTituloIntermedio(tituloIntermedio);
        return this;
    }

    public void setTituloIntermedio(Integer tituloIntermedio) {
        this.tituloIntermedio = tituloIntermedio;
    }

    public String getPromedio() {
        return this.promedio;
    }

    public AlumnoTitulo promedio(String promedio) {
        this.setPromedio(promedio);
        return this;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public Instant getFechaRuta() {
        return this.fechaRuta;
    }

    public AlumnoTitulo fechaRuta(Instant fechaRuta) {
        this.setFechaRuta(fechaRuta);
        return this;
    }

    public void setFechaRuta(Instant fechaRuta) {
        this.fechaRuta = fechaRuta;
    }

    public Long getIdRamaRuta() {
        return this.idRamaRuta;
    }

    public AlumnoTitulo idRamaRuta(Long idRamaRuta) {
        this.setIdRamaRuta(idRamaRuta);
        return this;
    }

    public void setIdRamaRuta(Long idRamaRuta) {
        this.idRamaRuta = idRamaRuta;
    }

    public String getApYnomListoParaEnviar() {
        return this.apYnomListoParaEnviar;
    }

    public AlumnoTitulo apYnomListoParaEnviar(String apYnomListoParaEnviar) {
        this.setApYnomListoParaEnviar(apYnomListoParaEnviar);
        return this;
    }

    public void setApYnomListoParaEnviar(String apYnomListoParaEnviar) {
        this.apYnomListoParaEnviar = apYnomListoParaEnviar;
    }

    public String getDocumentoListoParaEnviar() {
        return this.documentoListoParaEnviar;
    }

    public AlumnoTitulo documentoListoParaEnviar(String documentoListoParaEnviar) {
        this.setDocumentoListoParaEnviar(documentoListoParaEnviar);
        return this;
    }

    public void setDocumentoListoParaEnviar(String documentoListoParaEnviar) {
        this.documentoListoParaEnviar = documentoListoParaEnviar;
    }

    public String getApYnomEnviado() {
        return this.apYnomEnviado;
    }

    public AlumnoTitulo apYnomEnviado(String apYnomEnviado) {
        this.setApYnomEnviado(apYnomEnviado);
        return this;
    }

    public void setApYnomEnviado(String apYnomEnviado) {
        this.apYnomEnviado = apYnomEnviado;
    }

    public String getDocumentoEnviado() {
        return this.documentoEnviado;
    }

    public AlumnoTitulo documentoEnviado(String documentoEnviado) {
        this.setDocumentoEnviado(documentoEnviado);
        return this;
    }

    public void setDocumentoEnviado(String documentoEnviado) {
        this.documentoEnviado = documentoEnviado;
    }

    public Instant getFechaUltimoEstado() {
        return this.fechaUltimoEstado;
    }

    public AlumnoTitulo fechaUltimoEstado(Instant fechaUltimoEstado) {
        this.setFechaUltimoEstado(fechaUltimoEstado);
        return this;
    }

    public void setFechaUltimoEstado(Instant fechaUltimoEstado) {
        this.fechaUltimoEstado = fechaUltimoEstado;
    }

    public AlumnoEstabOferta getAlumnoEstabOferta() {
        return this.alumnoEstabOferta;
    }

    public void setAlumnoEstabOferta(AlumnoEstabOferta alumnoEstabOferta) {
        this.alumnoEstabOferta = alumnoEstabOferta;
    }

    public AlumnoTitulo alumnoEstabOferta(AlumnoEstabOferta alumnoEstabOferta) {
        this.setAlumnoEstabOferta(alumnoEstabOferta);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlumnoTitulo)) {
            return false;
        }
        return id != null && id.equals(((AlumnoTitulo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AlumnoTitulo{" +
            "id=" + getId() +
            ", idAlumnoEstabOferta=" + getIdAlumnoEstabOferta() +
            ", fechaEmision='" + getFechaEmision() + "'" +
            ", nroSerie='" + getNroSerie() + "'" +
            ", numeroRfifd='" + getNumeroRfifd() + "'" +
            ", validezNacional='" + getValidezNacional() + "'" +
            ", fechaEnvio='" + getFechaEnvio() + "'" +
            ", fechaEgreso='" + getFechaEgreso() + "'" +
            ", nroLibroMatriz=" + getNroLibroMatriz() +
            ", nroActa=" + getNroActa() +
            ", nroFolio=" + getNroFolio() +
            ", estabNombre='" + getEstabNombre() + "'" +
            ", estabCue='" + getEstabCue() + "'" +
            ", estabUbicadoEn='" + getEstabUbicadoEn() + "'" +
            ", estabCiudad='" + getEstabCiudad() + "'" +
            ", estabProvincia='" + getEstabProvincia() + "'" +
            ", idEstadoAlumnoTitulo=" + getIdEstadoAlumnoTitulo() +
            ", tituloIntermedio=" + getTituloIntermedio() +
            ", promedio='" + getPromedio() + "'" +
            ", fechaRuta='" + getFechaRuta() + "'" +
            ", idRamaRuta=" + getIdRamaRuta() +
            ", apYnomListoParaEnviar='" + getApYnomListoParaEnviar() + "'" +
            ", documentoListoParaEnviar='" + getDocumentoListoParaEnviar() + "'" +
            ", apYnomEnviado='" + getApYnomEnviado() + "'" +
            ", documentoEnviado='" + getDocumentoEnviado() + "'" +
            ", fechaUltimoEstado='" + getFechaUltimoEstado() + "'" +
            "}";
    }
}
