package ac.korea.isdevelop.restapi.billMaster;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillmasterCustomRepositoryImpl implements BillmasterCustomRepository {


    private final JPAQueryFactory jpaQueryFactory;

    public BillmasterCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }



    @Override
    public List<BillMaster> findAllInnerFetchJoin(String accYear, String campusCd, String accUnitCd, Pageable pageable) {

        QBillMaster qBillMaster = QBillMaster.billMaster;
        return jpaQueryFactory.selectFrom(qBillMaster)
                .where(qBillMaster.accYear.eq(accYear))
                .where(qBillMaster.campusCd.eq(campusCd))
                .where(qBillMaster.accUnitCd.eq(accUnitCd))
                .innerJoin(qBillMaster.cardProofset)
                .fetchJoin()
                .fetch()
                ;
    }
}
