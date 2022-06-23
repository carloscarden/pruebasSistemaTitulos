package com.dgcye.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dgcye.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JornadaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Jornada.class);
        Jornada jornada1 = new Jornada();
        jornada1.setId(1L);
        Jornada jornada2 = new Jornada();
        jornada2.setId(jornada1.getId());
        assertThat(jornada1).isEqualTo(jornada2);
        jornada2.setId(2L);
        assertThat(jornada1).isNotEqualTo(jornada2);
        jornada1.setId(null);
        assertThat(jornada1).isNotEqualTo(jornada2);
    }
}
