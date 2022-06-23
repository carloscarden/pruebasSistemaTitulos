package com.dgcye.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dgcye.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AlumnoEstabOfertaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlumnoEstabOferta.class);
        AlumnoEstabOferta alumnoEstabOferta1 = new AlumnoEstabOferta();
        alumnoEstabOferta1.setId(1L);
        AlumnoEstabOferta alumnoEstabOferta2 = new AlumnoEstabOferta();
        alumnoEstabOferta2.setId(alumnoEstabOferta1.getId());
        assertThat(alumnoEstabOferta1).isEqualTo(alumnoEstabOferta2);
        alumnoEstabOferta2.setId(2L);
        assertThat(alumnoEstabOferta1).isNotEqualTo(alumnoEstabOferta2);
        alumnoEstabOferta1.setId(null);
        assertThat(alumnoEstabOferta1).isNotEqualTo(alumnoEstabOferta2);
    }
}
