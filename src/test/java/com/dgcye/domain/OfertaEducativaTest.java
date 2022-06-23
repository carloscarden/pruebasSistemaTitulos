package com.dgcye.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.dgcye.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OfertaEducativaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfertaEducativa.class);
        OfertaEducativa ofertaEducativa1 = new OfertaEducativa();
        ofertaEducativa1.setId(1L);
        OfertaEducativa ofertaEducativa2 = new OfertaEducativa();
        ofertaEducativa2.setId(ofertaEducativa1.getId());
        assertThat(ofertaEducativa1).isEqualTo(ofertaEducativa2);
        ofertaEducativa2.setId(2L);
        assertThat(ofertaEducativa1).isNotEqualTo(ofertaEducativa2);
        ofertaEducativa1.setId(null);
        assertThat(ofertaEducativa1).isNotEqualTo(ofertaEducativa2);
    }
}
