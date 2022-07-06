package ac.korea.isdevelop.restapi.billMaster;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillMasterRepository extends JpaRepository<BillMaster, Long> {

    Page<BillMaster> findByAccYearEqualsAndCampusCdEqualsAndAccUnitCdEquals
            (String accYear, String campusCd, String accUnitCd, Pageable pageable);

}
