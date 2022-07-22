package ac.korea.isdevelop.restapi.billMaster;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;




@Repository
public class BillmasterCustomRepositoryImpl extends QuerydslRepositorySupport implements BillmasterCustomRepository {

    private final JPAQueryFactory queryFactory;

    public BillmasterCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(BillMaster.class);
        this.queryFactory = jpaQueryFactory;
    }

    @Override
    public Page<BillMaster> findAllInnerFetchJoin(Predicate predicate, Pageable pageable) {

        QBillMaster qBillMaster = QBillMaster.billMaster;

        JPQLQuery<BillMaster> query =
                from(qBillMaster).where(predicate)
                .innerJoin(qBillMaster.cardProofset).fetchJoin();

        JPQLQuery<BillMaster> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<BillMaster> fetchResults = pageableQuery.fetchResults();

        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());

    }

    @Override
    public List<BillMaster> findAllInnerFetchJoin(Predicate predicate) {
        QBillMaster qBillMaster = QBillMaster.billMaster;

        JPQLQuery<BillMaster> query =

                        from(qBillMaster).where(predicate)
                .innerJoin(qBillMaster.cardProofset).fetchJoin();

        return query.fetch();
    }

    @Override
    public List<BillMasterDto> findAllInnerFetchJoin(Predicate predicate, int test) {
        QBillMaster qBillMaster = QBillMaster.billMaster;
        JPAQuery<BillMasterDto> query = queryFactory.select(
                new QBillMasterDto(
                        qBillMaster.accYear,
                        qBillMaster.campusCd,
                        qBillMaster.accUnitCd))
                .from(qBillMaster).where(predicate)
                .innerJoin(qBillMaster.cardProofset);
        return query.fetch();
    }
}
