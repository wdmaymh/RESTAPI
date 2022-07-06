package ac.korea.isdevelop.restapi.billMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BillMasterRepository extends JpaRepository<BillMaster, Long>, QuerydslPredicateExecutor<BillMaster> {

}
