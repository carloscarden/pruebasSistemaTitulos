package com.dgcye.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dgcye.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NroSerieTituloTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NroSerieTitulo.class);
        NroSerieTitulo nroSerieTitulo1 = new NroSerieTitulo();
        nroSerieTitulo1.setId(1L);
        NroSerieTitulo nroSerieTitulo2 = new NroSerieTitulo();
        nroSerieTitulo2.setId(nroSerieTitulo1.getId());
        assertThat(nroSerieTitulo1).isEqualTo(nroSerieTitulo2);
        nroSerieTitulo2.setId(2L);
        assertThat(nroSerieTitulo1).isNotEqualTo(nroSerieTitulo2);
        nroSerieTitulo1.setId(null);
        assertThat(nroSerieTitulo1).isNotEqualTo(nroSerieTitulo2);
    }
}
