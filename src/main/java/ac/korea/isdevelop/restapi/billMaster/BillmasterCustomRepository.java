package ac.korea.isdevelop.restapi.billMaster;


import ac.korea.isdevelop.restapi.getDto.BillMasterCardProofDto;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BillmasterCustomRepository {
    Page<BillMaster> findAllInnerFetchJoin(Predicate predicate, Pageable pageable);

    List<BillMasterCardProofDto> findAllInnerFetchJoin(Predicate predicate);
}
