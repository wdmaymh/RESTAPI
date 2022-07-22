package ac.korea.isdevelop.restapi.billMaster;


import ac.korea.isdevelop.restapi.common.TestConfig;
import ac.korea.isdevelop.restapi.getDto.BillMasterCardProofDto;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
class BillmasterCustomRepositoryImplTest {

    @Autowired
    BillMasterRepository billMasterRepository;

    @Test
    @DisplayName("결의서 / 법인카드 증빙 내역 정상 조회 테스트")
    public void qdslProjectionTest(){

        //Given
        QBillMaster qBillMaster = QBillMaster.billMaster;

        Predicate predicate = qBillMaster.accYear.eq("2021")
                .and(qBillMaster.campusCd.eq("2"))
                .and(qBillMaster.accUnitCd.eq("18"))
                ;
        //When
        List<BillMasterCardProofDto> billMasterCardProofDtoList = billMasterRepository.findAllInnerFetchJoin(predicate);

        //then
        assertThat(billMasterCardProofDtoList).isNotNull();


    }


}