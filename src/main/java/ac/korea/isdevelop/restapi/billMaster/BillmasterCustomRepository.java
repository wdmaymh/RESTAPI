package ac.korea.isdevelop.restapi.billMaster;


import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BillmasterCustomRepository {
    Page<BillMaster> findAllInnerFetchJoin(Predicate predicate, Pageable pageable);

    List<BillMaster> findAllInnerFetchJoin(Predicate predicate);

    List<BillMasterDto> findAllInnerFetchJoin(Predicate predicate, int test);
}
