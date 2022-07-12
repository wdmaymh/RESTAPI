package ac.korea.isdevelop.restapi.billMaster;


import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BillmasterCustomRepository {
    List<BillMaster> findAllInnerFetchJoin(String accYear, String campusCd, String accUnitCd, Pageable pageable);
}
