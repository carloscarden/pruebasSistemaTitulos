package com.dgcye.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dgcye.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RendicionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rendicion.class);
        Rendicion rendicion1 = new Rendicion();
        rendicion1.setId(1L);
        Rendicion rendicion2 = new Rendicion();
        rendicion2.setId(rendicion1.getId());
        assertThat(rendicion1).isEqualTo(rendicion2);
        rendicion2.setId(2L);
        assertThat(rendicion1).isNotEqualTo(rendicion2);
        rendicion1.setId(null);
        assertThat(rendicion1).isNotEqualTo(rendicion2);
    }
}
