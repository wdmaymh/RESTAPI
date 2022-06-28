package ac.korea.isdevelop.restapi.billMaster;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BillMasterTest {

    @Test
    public void builder() {
        BillMaster billMaster = BillMaster.builder()
                .billTitle("BILL TEST")
                .build();
        assertThat(billMaster).isNotNull();
    }

    @Test
    public void javaBean() {
        //Given
        String billTitle = "Bill Test";

        //When
        BillMaster billMaster = new BillMaster();
        billMaster.setBillTitle(billTitle);

        //Then
        assertThat(billMaster.getBillTitle()).isEqualTo(billTitle);
    }
}