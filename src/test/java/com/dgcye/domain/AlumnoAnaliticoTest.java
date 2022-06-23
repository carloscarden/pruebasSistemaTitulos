package com.dgcye.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dgcye.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AlumnoAnaliticoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlumnoAnalitico.class);
        AlumnoAnalitico alumnoAnalitico1 = new AlumnoAnalitico();
        alumnoAnalitico1.setId(1L);
        AlumnoAnalitico alumnoAnalitico2 = new AlumnoAnalitico();
        alumnoAnalitico2.setId(alumnoAnalitico1.getId());
        assertThat(alumnoAnalitico1).isEqualTo(alumnoAnalitico2);
        alumnoAnalitico2.setId(2L);
        assertThat(alumnoAnalitico1).isNotEqualTo(alumnoAnalitico2);
        alumnoAnalitico1.setId(null);
        assertThat(alumnoAnalitico1).isNotEqualTo(alumnoAnalitico2);
    }
}
