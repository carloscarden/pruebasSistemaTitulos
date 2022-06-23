package com.dgcye.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dgcye.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AlumnoTituloTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlumnoTitulo.class);
        AlumnoTitulo alumnoTitulo1 = new AlumnoTitulo();
        alumnoTitulo1.setId(1L);
        AlumnoTitulo alumnoTitulo2 = new AlumnoTitulo();
        alumnoTitulo2.setId(alumnoTitulo1.getId());
        assertThat(alumnoTitulo1).isEqualTo(alumnoTitulo2);
        alumnoTitulo2.setId(2L);
        assertThat(alumnoTitulo1).isNotEqualTo(alumnoTitulo2);
        alumnoTitulo1.setId(null);
        assertThat(alumnoTitulo1).isNotEqualTo(alumnoTitulo2);
    }
}
