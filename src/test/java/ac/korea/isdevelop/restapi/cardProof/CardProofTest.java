package ac.korea.isdevelop.restapi.cardProof;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardProofTest {

    @Test
    public void builder(){
        CardProof cardProof = CardProof.builder()
                .build();

        assertThat(cardProof).isNotNull();


    }

}